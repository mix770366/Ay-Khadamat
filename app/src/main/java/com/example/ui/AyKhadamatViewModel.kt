package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.AyOrder
import com.example.model.CartItem
import com.example.model.IbbDistricts
import com.example.model.ProductItem
import com.example.model.ServiceType
import com.example.model.VendorStore
import com.example.repository.AyServicesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class Screen {
    HOME,
    SERVICES_LIST,
    CUSTOM_ORDER,
    STORE_DETAIL,
    ORDERS,
    ORDER_TRACKING,
    GUARANTEE_INFO
}

data class UiNotification(
    val message: String,
    val isSuccess: Boolean = true
)

class AyKhadamatViewModel(
    private val repository: AyServicesRepository = AyServicesRepository()
) : ViewModel() {

    private val _currentScreen = MutableStateFlow(Screen.HOME)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _screenHistory = mutableListOf<Screen>()

    private val _selectedCategoryFilter = MutableStateFlow<ServiceType?>(null)
    val selectedCategoryFilter: StateFlow<ServiceType?> = _selectedCategoryFilter.asStateFlow()

    private val _selectedStore = MutableStateFlow<VendorStore?>(null)
    val selectedStore: StateFlow<VendorStore?> = _selectedStore.asStateFlow()

    private val _trackedOrder = MutableStateFlow<AyOrder?>(null)
    val trackedOrder: StateFlow<AyOrder?> = _trackedOrder.asStateFlow()

    private val _showCartSheet = MutableStateFlow(false)
    val showCartSheet: StateFlow<Boolean> = _showCartSheet.asStateFlow()

    private val _showLocationDialog = MutableStateFlow(false)
    val showLocationDialog: StateFlow<Boolean> = _showLocationDialog.asStateFlow()

    private val _orderToRate = MutableStateFlow<AyOrder?>(null)
    val orderToRate: StateFlow<AyOrder?> = _orderToRate.asStateFlow()

    private val _notifications = MutableSharedFlow<UiNotification>()
    val notifications: SharedFlow<UiNotification> = _notifications.asSharedFlow()

    // Expose repository state
    val cart: StateFlow<List<CartItem>> = repository.cart
    val selectedDistrict: StateFlow<String> = repository.selectedDistrict
    val searchQuery: StateFlow<String> = repository.searchQuery
    val orders: StateFlow<List<AyOrder>> = repository.orders

    // Filtered stores
    val filteredStores: StateFlow<List<VendorStore>> = combine(
        repository.stores,
        _selectedCategoryFilter,
        repository.searchQuery
    ) { stores, category, query ->
        stores.filter { store ->
            val matchesCategory = (category == null || store.serviceType == category)
            val matchesQuery = if (query.isBlank()) true else {
                store.nameAr.contains(query, ignoreCase = true) ||
                        store.areaAr.contains(query, ignoreCase = true) ||
                        store.tagAr.contains(query, ignoreCase = true) ||
                        store.products.any { it.nameAr.contains(query, ignoreCase = true) }
            }
            matchesCategory && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Custom Order Draft State
    val customOrderServiceType = MutableStateFlow(ServiceType.CUSTOM_ERRANDS)
    val customOrderNotes = MutableStateFlow("")
    val customPickupDistrict = MutableStateFlow(IbbDistricts.list[0])
    val customDropoffDistrict = MutableStateFlow(IbbDistricts.list[1])
    val customIsUrgent = MutableStateFlow(false)
    val customRequiresSignature = MutableStateFlow(false)
    val customIsSurpriseGift = MutableStateFlow(false)
    val customGiftRecipientName = MutableStateFlow("")
    val customGiftRecipientPhone = MutableStateFlow("")
    val customEstimatedGoodsPriceYer = MutableStateFlow("")

    fun navigateTo(screen: Screen) {
        if (_currentScreen.value != screen) {
            _screenHistory.add(_currentScreen.value)
            _currentScreen.value = screen
        }
    }

    fun navigateBack(): Boolean {
        return if (_screenHistory.isNotEmpty()) {
            val prev = _screenHistory.removeAt(_screenHistory.lastIndex)
            _currentScreen.value = prev
            true
        } else if (_currentScreen.value != Screen.HOME) {
            _currentScreen.value = Screen.HOME
            true
        } else {
            false
        }
    }

    fun openService(service: ServiceType) {
        _selectedCategoryFilter.value = service
        // If it's pure errand or confidential docs or shipping, direct to custom order preset
        if (service == ServiceType.CUSTOM_ERRANDS ||
            service == ServiceType.CONFIDENTIAL_DOCUMENTS ||
            service == ServiceType.PARCEL_SHIPPING
        ) {
            startCustomOrderWithService(service)
        } else {
            navigateTo(Screen.SERVICES_LIST)
        }
    }

    fun filterByCategory(service: ServiceType?) {
        _selectedCategoryFilter.value = service
    }

    fun setSearchQuery(query: String) {
        repository.setSearchQuery(query)
    }

    fun setSelectedDistrict(district: String) {
        repository.setSelectedDistrict(district)
        _showLocationDialog.value = false
        emitNotification("تم تغيير منطقتك إلى: $district")
    }

    fun openStoreDetail(store: VendorStore) {
        _selectedStore.value = store
        navigateTo(Screen.STORE_DETAIL)
    }

    fun openTrackOrder(order: AyOrder) {
        _trackedOrder.value = order
        navigateTo(Screen.ORDER_TRACKING)
    }

    fun toggleCartSheet(show: Boolean) {
        _showCartSheet.value = show
    }

    fun toggleLocationDialog(show: Boolean) {
        _showLocationDialog.value = show
    }

    // Cart actions
    fun addToCart(product: ProductItem, vendorName: String, quantity: Int = 1, notes: String = "") {
        repository.addToCart(product, vendorName, quantity, notes)
        emitNotification("تمت إضافة \"${product.nameAr}\" إلى سلة طلباتك")
    }

    fun updateCartQuantity(productId: String, delta: Int) {
        repository.updateQuantity(productId, delta)
    }

    fun removeFromCart(productId: String) {
        repository.removeFromCart(productId)
    }

    fun clearCart() {
        repository.clearCart()
    }

    fun checkoutCart(deliveryNotes: String) {
        val currentDistrict = repository.selectedDistrict.value
        val order = repository.placeStoreOrder(
            pickupDistrict = "مركز المتجر - إب",
            dropoffDistrict = currentDistrict,
            notes = deliveryNotes
        )
        _showCartSheet.value = false
        _trackedOrder.value = order
        navigateTo(Screen.ORDER_TRACKING)
        emitNotification("تم تأكيد طلبك بنجاح بضمان أي خدمات!")
    }

    // Custom Order actions
    fun startCustomOrderWithService(serviceType: ServiceType) {
        customOrderServiceType.value = serviceType
        customRequiresSignature.value = (serviceType == ServiceType.CONFIDENTIAL_DOCUMENTS)
        customIsSurpriseGift.value = (serviceType == ServiceType.GIFTS_AND_FLOWERS)
        navigateTo(Screen.CUSTOM_ORDER)
    }

    fun submitCustomOrder() {
        val notes = customOrderNotes.value.trim()
        if (notes.isBlank()) {
            emitNotification("يرجى كتابة تفاصيل ما تود طلبه أو توصيله", isSuccess = false)
            return
        }

        val goodsPrice = customEstimatedGoodsPriceYer.value.toIntOrNull() ?: 0

        val order = repository.placeCustomOrder(
            serviceType = customOrderServiceType.value,
            customDescription = notes,
            pickupDistrict = customPickupDistrict.value,
            dropoffDistrict = customDropoffDistrict.value,
            isUrgent = customIsUrgent.value,
            requiresSignature = customRequiresSignature.value,
            isSurpriseGift = customIsSurpriseGift.value,
            giftRecipientName = customGiftRecipientName.value,
            giftRecipientPhone = customGiftRecipientPhone.value,
            customEstimatedGoodsPriceYer = goodsPrice
        )

        // Reset draft
        customOrderNotes.value = ""
        customGiftRecipientName.value = ""
        customGiftRecipientPhone.value = ""
        customEstimatedGoodsPriceYer.value = ""

        _trackedOrder.value = order
        navigateTo(Screen.ORDER_TRACKING)
        emitNotification("تم استلام مشوارك/طلبك وتوجيه المندوب الخاص فوراً!")
    }

    fun advanceCurrentOrderStatus() {
        _trackedOrder.value?.let { current ->
            repository.advanceOrderStatus(current.id)
            val updated = repository.orders.value.find { it.id == current.id }
            _trackedOrder.value = updated
        }
    }

    fun openRatingDialog(order: AyOrder) {
        _orderToRate.value = order
    }

    fun closeRatingDialog() {
        _orderToRate.value = null
    }

    fun submitRating(orderId: String, stars: Int, tags: List<String>, comment: String, tipYer: Int) {
        repository.submitOrderReview(orderId, stars, tags, comment, tipYer)
        _orderToRate.value = null
        if (_trackedOrder.value?.id == orderId) {
            val updated = repository.orders.value.find { it.id == orderId }
            _trackedOrder.value = updated
        }
        emitNotification("شكراً لتقييمك! نسعى دائماً لتقديم أفضل تجربة توصيل بضمان أي خدمات ⭐")
    }

    fun cancelTrackedOrder() {
        _trackedOrder.value?.let { current ->
            repository.cancelOrder(current.id)
            val updated = repository.orders.value.find { it.id == current.id }
            _trackedOrder.value = updated
            emitNotification("تم إلغاء الطلب")
        }
    }

    private fun emitNotification(message: String, isSuccess: Boolean = true) {
        viewModelScope.launch {
            _notifications.emit(UiNotification(message, isSuccess))
        }
    }
}
