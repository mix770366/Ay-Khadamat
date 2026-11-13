package com.example.repository

import com.example.data.AyKhadamatData
import com.example.model.AyOrder
import com.example.model.CartItem
import com.example.model.IbbDistricts
import com.example.model.OrderStatus
import com.example.model.ProductItem
import com.example.model.ServiceType
import com.example.model.VendorStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class AyServicesRepository(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {

    private val _stores = MutableStateFlow<List<VendorStore>>(AyKhadamatData.sampleStores)
    val stores: StateFlow<List<VendorStore>> = _stores.asStateFlow()

    private val _cart = MutableStateFlow<List<CartItem>>(emptyList())
    val cart: StateFlow<List<CartItem>> = _cart.asStateFlow()

    private val _selectedDistrict = MutableStateFlow(IbbDistricts.list.first())
    val selectedDistrict: StateFlow<String> = _selectedDistrict.asStateFlow()

    private val _orders = MutableStateFlow<List<AyOrder>>(createInitialOrders())
    val orders: StateFlow<List<AyOrder>> = _orders.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSelectedDistrict(district: String) {
        _selectedDistrict.value = district
    }

    // Cart Management
    fun addToCart(product: ProductItem, vendorName: String, quantity: Int = 1, notes: String = "") {
        _cart.update { current ->
            val existing = current.find { it.product.id == product.id }
            if (existing != null) {
                current.map {
                    if (it.product.id == product.id) {
                        it.copy(quantity = it.quantity + quantity, specialNotes = notes.ifEmpty { it.specialNotes })
                    } else it
                }
            } else {
                current + CartItem(product, vendorName, quantity, notes)
            }
        }
    }

    fun updateQuantity(productId: String, delta: Int) {
        _cart.update { current ->
            current.mapNotNull { item ->
                if (item.product.id == productId) {
                    val newQty = item.quantity + delta
                    if (newQty > 0) item.copy(quantity = newQty) else null
                } else item
            }
        }
    }

    fun removeFromCart(productId: String) {
        _cart.update { current ->
            current.filterNot { it.product.id == productId }
        }
    }

    fun clearCart() {
        _cart.value = emptyList()
    }

    // Checkout Store Order
    fun placeStoreOrder(
        pickupDistrict: String,
        dropoffDistrict: String,
        notes: String
    ): AyOrder {
        val currentCart = _cart.value
        val subtotal = currentCart.sumOf { it.product.priceYer * it.quantity }
        val deliveryFee = 1000
        val total = subtotal + deliveryFee

        val primaryVendorStore = _stores.value.find { store ->
            store.products.any { it.id == currentCart.firstOrNull()?.product?.id }
        }
        val serviceType = primaryVendorStore?.serviceType ?: ServiceType.RESTAURANTS

        val newOrder = AyOrder(
            id = "AY-${System.currentTimeMillis().toString().takeLast(6)}",
            serviceType = serviceType,
            titleAr = "طلب من ${primaryVendorStore?.nameAr ?: "متجر معتمد"}",
            status = OrderStatus.COURIER_ASSIGNED,
            createdAt = getCurrentTimeString(),
            pickupDistrictAr = pickupDistrict,
            dropoffDistrictAr = dropoffDistrict,
            items = currentCart,
            customNotesAr = notes,
            deliveryFeeYer = deliveryFee,
            itemsSubtotalYer = subtotal,
            totalAmountYer = total,
            guaranteeAppliedAr = serviceType.guaranteeAr,
            courierNameAr = "كابتن أسامة العواضي",
            courierPhone = "773456789",
            courierRating = 4.95,
            courierVehicleAr = "دراجة نارية مزودة بحقيبة حرارية مصفحة"
        )

        _orders.update { listOf(newOrder) + it }
        clearCart()
        simulateAutoProgression(newOrder.id)
        return newOrder
    }

    // Place Custom Errand / Shipping / Parcel / Document / Gift order
    fun placeCustomOrder(
        serviceType: ServiceType,
        customDescription: String,
        pickupDistrict: String,
        dropoffDistrict: String,
        isUrgent: Boolean = false,
        requiresSignature: Boolean = false,
        isSurpriseGift: Boolean = false,
        giftRecipientName: String = "",
        giftRecipientPhone: String = "",
        customEstimatedGoodsPriceYer: Int = 0
    ): AyOrder {
        val baseFee = when (serviceType) {
            ServiceType.PARCEL_SHIPPING -> 1500
            ServiceType.CONFIDENTIAL_DOCUMENTS -> 1800
            ServiceType.CUSTOM_ERRANDS -> 1400
            ServiceType.GIFTS_AND_FLOWERS -> 1600
            ServiceType.SHOPPING_MALLS -> 1800
            else -> 1200
        }
        val urgencyFee = if (isUrgent) 500 else 0
        val deliveryFee = baseFee + urgencyFee
        val total = deliveryFee + customEstimatedGoodsPriceYer

        val courierName = when (serviceType) {
            ServiceType.CONFIDENTIAL_DOCUMENTS -> "كابتن سيف الحميدي (موثق أمانات)"
            ServiceType.GIFTS_AND_FLOWERS -> "كابتن وسيم الورافي (تسليم هدايا VIP)"
            else -> "كابتن أحمد الرعوي (مندوب خاص)"
        }

        val order = AyOrder(
            id = "AY-${System.currentTimeMillis().toString().takeLast(6)}",
            serviceType = serviceType,
            titleAr = "${serviceType.titleAr} - ${serviceType.badgeAr}",
            status = OrderStatus.PENDING,
            createdAt = getCurrentTimeString(),
            pickupDistrictAr = pickupDistrict,
            dropoffDistrictAr = dropoffDistrict,
            items = emptyList(),
            customNotesAr = customDescription,
            deliveryFeeYer = deliveryFee,
            itemsSubtotalYer = customEstimatedGoodsPriceYer,
            totalAmountYer = total,
            guaranteeAppliedAr = serviceType.guaranteeAr,
            courierNameAr = courierName,
            courierPhone = "775987654",
            courierRating = 4.98,
            courierVehicleAr = if (serviceType == ServiceType.CONFIDENTIAL_DOCUMENTS) "حقيبة أمانات مغلقة ومختومة" else "دراجة سريعة مع معدات حماية",
            isUrgent = isUrgent,
            requiresSignature = requiresSignature,
            isSurpriseGift = isSurpriseGift,
            giftRecipientName = giftRecipientName,
            giftRecipientPhone = giftRecipientPhone
        )

        _orders.update { listOf(order) + it }
        simulateAutoProgression(order.id)
        return order
    }

    fun advanceOrderStatus(orderId: String) {
        _orders.update { list ->
            list.map { order ->
                if (order.id == orderId) {
                    val nextStatus = when (order.status) {
                        OrderStatus.PENDING -> OrderStatus.COURIER_ASSIGNED
                        OrderStatus.COURIER_ASSIGNED -> OrderStatus.PREPARING_PICKING
                        OrderStatus.PREPARING_PICKING -> OrderStatus.ON_THE_WAY
                        OrderStatus.ON_THE_WAY -> OrderStatus.DELIVERED
                        OrderStatus.DELIVERED -> OrderStatus.DELIVERED
                        OrderStatus.CANCELLED -> OrderStatus.CANCELLED
                    }
                    order.copy(status = nextStatus)
                } else order
            }
        }
    }

    fun submitOrderReview(
        orderId: String,
        rating: Int,
        tags: List<String>,
        comment: String,
        courierTipYer: Int
    ) {
        val nowTime = getCurrentTimeString()
        _orders.update { list ->
            list.map { order ->
                if (order.id == orderId) {
                    order.copy(
                        rating = rating,
                        reviewTags = tags,
                        reviewComment = comment.ifBlank { null },
                        courierTipYer = courierTipYer,
                        ratedAt = nowTime
                    )
                } else order
            }
        }
    }

    fun cancelOrder(orderId: String) {
        _orders.update { list ->
            list.map {
                if (it.id == orderId) it.copy(status = OrderStatus.CANCELLED) else it
            }
        }
    }

    private fun simulateAutoProgression(orderId: String) {
        scope.launch {
            delay(12000)
            advanceOrderStatus(orderId)
            delay(15000)
            advanceOrderStatus(orderId)
        }
    }

    private fun getCurrentTimeString(): String {
        val sdf = SimpleDateFormat("HH:mm - yyyy/MM/dd", Locale("ar"))
        return sdf.format(Date())
    }

    private fun createInitialOrders(): List<AyOrder> {
        return listOf(
            AyOrder(
                id = "AY-928103",
                serviceType = ServiceType.RESTAURANTS,
                titleAr = "طلب من مطاعم الشيباني السياحية",
                status = OrderStatus.ON_THE_WAY,
                createdAt = "منذ 15 دقيقة",
                pickupDistrictAr = "شارع العدين",
                dropoffDistrictAr = "الدائري الغربي - عمارة الشروق",
                items = listOf(
                    CartItem(
                        product = AyKhadamatData.sampleStores[0].products[0],
                        vendorName = "مطاعم الشيباني",
                        quantity = 2,
                        specialNotes = "كثر الحلبة والمرق ساخن جداً"
                    ),
                    CartItem(
                        product = AyKhadamatData.sampleStores[0].products[1],
                        vendorName = "مطاعم الشيباني",
                        quantity = 1,
                        specialNotes = "أرز بشاور إضافي"
                    )
                ),
                customNotesAr = "يرجى الحفاظ على سخونة الوجبة بحقيبة حرارية كما هو مضمون",
                deliveryFeeYer = 1000,
                itemsSubtotalYer = 16500,
                totalAmountYer = 17500,
                guaranteeAppliedAr = "ضمان الحقيبة الحرارية لحفظ السخونة والنكهة الأصلية",
                courierNameAr = "كابتن وليد الشامي",
                courierPhone = "771239847",
                courierRating = 4.94,
                courierVehicleAr = "دراجة سريعة مع حقيبة حرارية إلكترونية عازلة"
            ),
            AyOrder(
                id = "AY-841920",
                serviceType = ServiceType.CONFIDENTIAL_DOCUMENTS,
                titleAr = "معاملة ورقية وعقد استئجار موثق",
                status = OrderStatus.DELIVERED,
                createdAt = "أمس - 04:30 مساءً",
                pickupDistrictAr = "شارع بغداد",
                dropoffDistrictAr = "مفرق جبلة",
                items = emptyList(),
                customNotesAr = "استلام عقد الاستئجار وتوقيعه من الطرف الثاني وتسليم النسخة الموقعة",
                deliveryFeeYer = 1800,
                itemsSubtotalYer = 0,
                totalAmountYer = 1800,
                guaranteeAppliedAr = "ضمان السرية المطلقة والتسليم بتوقيع موثق",
                courierNameAr = "كابتن سيف الحميدي",
                courierPhone = "777654321",
                courierRating = 5.0,
                requiresSignature = true
            )
        )
    }
}
