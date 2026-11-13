package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.OrderStatus
import com.example.ui.AyKhadamatViewModel
import com.example.ui.Screen
import com.example.ui.components.AyBottomBar
import com.example.ui.components.AyTopAppBar
import com.example.ui.dialogs.CartBottomSheet
import com.example.ui.dialogs.LocationSelectorDialog
import com.example.ui.dialogs.RatingBottomSheet
import com.example.ui.screens.CustomOrderScreen
import com.example.ui.screens.GuaranteeScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.OrderTrackingScreen
import com.example.ui.screens.OrdersScreen
import com.example.ui.screens.ServicesScreen
import com.example.ui.screens.StoreDetailScreen
import com.example.ui.theme.AyKhadamatTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AyKhadamatTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    AyKhadamatApp()
                }
            }
        }
    }
}

@Composable
fun AyKhadamatApp(
    viewModel: AyKhadamatViewModel = viewModel()
) {
    val context = LocalContext.current
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val cartItems by viewModel.cart.collectAsStateWithLifecycle()
    val selectedDistrict by viewModel.selectedDistrict.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val orders by viewModel.orders.collectAsStateWithLifecycle()
    val filteredStores by viewModel.filteredStores.collectAsStateWithLifecycle()
    val selectedStore by viewModel.selectedStore.collectAsStateWithLifecycle()
    val trackedOrder by viewModel.trackedOrder.collectAsStateWithLifecycle()
    val selectedCategoryFilter by viewModel.selectedCategoryFilter.collectAsStateWithLifecycle()

    val showCartSheet by viewModel.showCartSheet.collectAsStateWithLifecycle()
    val showLocationDialog by viewModel.showLocationDialog.collectAsStateWithLifecycle()
    val orderToRate by viewModel.orderToRate.collectAsStateWithLifecycle()

    // Custom Order Draft State
    val customOrderServiceType by viewModel.customOrderServiceType.collectAsStateWithLifecycle()
    val customOrderNotes by viewModel.customOrderNotes.collectAsStateWithLifecycle()
    val customPickupDistrict by viewModel.customPickupDistrict.collectAsStateWithLifecycle()
    val customDropoffDistrict by viewModel.customDropoffDistrict.collectAsStateWithLifecycle()
    val customIsUrgent by viewModel.customIsUrgent.collectAsStateWithLifecycle()
    val customRequiresSignature by viewModel.customRequiresSignature.collectAsStateWithLifecycle()
    val customIsSurpriseGift by viewModel.customIsSurpriseGift.collectAsStateWithLifecycle()
    val customGiftRecipientName by viewModel.customGiftRecipientName.collectAsStateWithLifecycle()
    val customGiftRecipientPhone by viewModel.customGiftRecipientPhone.collectAsStateWithLifecycle()
    val customEstimatedGoodsPriceYer by viewModel.customEstimatedGoodsPriceYer.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.notifications.collectLatest { notif ->
            snackbarHostState.showSnackbar(notif.message)
        }
    }

    // Handle Hardware & System Back Button
    BackHandler(enabled = currentScreen != Screen.HOME) {
        viewModel.navigateBack()
    }

    val activeOrdersCount = orders.count {
        it.status != OrderStatus.DELIVERED && it.status != OrderStatus.CANCELLED
    }
    val activeFirstOrder = orders.firstOrNull {
        it.status != OrderStatus.DELIVERED && it.status != OrderStatus.CANCELLED
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (currentScreen != Screen.ORDER_TRACKING && currentScreen != Screen.STORE_DETAIL) {
                AyTopAppBar(
                    currentDistrict = selectedDistrict,
                    cartItemCount = cartItems.sumOf { it.quantity },
                    onDistrictClick = { viewModel.toggleLocationDialog(true) },
                    onCartClick = { viewModel.toggleCartSheet(true) },
                    onGuaranteeClick = { viewModel.navigateTo(Screen.GUARANTEE_INFO) }
                )
            }
        },
        bottomBar = {
            AyBottomBar(
                currentScreen = currentScreen,
                activeOrdersCount = activeOrdersCount,
                onNavigate = { viewModel.navigateTo(it) }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "ScreenTransition"
            ) { screen ->
                when (screen) {
                    Screen.HOME -> {
                        HomeScreen(
                            searchQuery = searchQuery,
                            onSearchChange = { viewModel.setSearchQuery(it) },
                            activeOrder = activeFirstOrder,
                            stores = filteredStores,
                            onServiceClick = { service -> viewModel.openService(service) },
                            onStoreClick = { store -> viewModel.openStoreDetail(store) },
                            onCustomOrderClick = { viewModel.navigateTo(Screen.CUSTOM_ORDER) },
                            onTrackOrderClick = { order -> viewModel.openTrackOrder(order) },
                            onGuaranteeClick = { viewModel.navigateTo(Screen.GUARANTEE_INFO) }
                        )
                    }

                    Screen.SERVICES_LIST -> {
                        ServicesScreen(
                            selectedCategory = selectedCategoryFilter,
                            onCategorySelected = { viewModel.filterByCategory(it) },
                            onOrderDirectly = { service -> viewModel.startCustomOrderWithService(service) },
                            onBrowseStores = { service ->
                                viewModel.filterByCategory(service)
                                viewModel.navigateTo(Screen.HOME)
                            },
                            stores = filteredStores,
                            onBack = { viewModel.navigateBack() }
                        )
                    }

                    Screen.CUSTOM_ORDER -> {
                        CustomOrderScreen(
                            serviceType = customOrderServiceType,
                            onServiceTypeChange = { viewModel.customOrderServiceType.value = it },
                            orderNotes = customOrderNotes,
                            onOrderNotesChange = { viewModel.customOrderNotes.value = it },
                            pickupDistrict = customPickupDistrict,
                            onPickupDistrictChange = { viewModel.customPickupDistrict.value = it },
                            dropoffDistrict = customDropoffDistrict,
                            onDropoffDistrictChange = { viewModel.customDropoffDistrict.value = it },
                            isUrgent = customIsUrgent,
                            onUrgentChange = { viewModel.customIsUrgent.value = it },
                            requiresSignature = customRequiresSignature,
                            onRequiresSignatureChange = { viewModel.customRequiresSignature.value = it },
                            isSurpriseGift = customIsSurpriseGift,
                            onIsSurpriseGiftChange = { viewModel.customIsSurpriseGift.value = it },
                            giftRecipientName = customGiftRecipientName,
                            onGiftRecipientNameChange = { viewModel.customGiftRecipientName.value = it },
                            giftRecipientPhone = customGiftRecipientPhone,
                            onGiftRecipientPhoneChange = { viewModel.customGiftRecipientPhone.value = it },
                            estimatedGoodsPrice = customEstimatedGoodsPriceYer,
                            onEstimatedGoodsPriceChange = { viewModel.customEstimatedGoodsPriceYer.value = it },
                            onSubmit = { viewModel.submitCustomOrder() },
                            onBack = { viewModel.navigateBack() }
                        )
                    }

                    Screen.STORE_DETAIL -> {
                        selectedStore?.let { store ->
                            StoreDetailScreen(
                                store = store,
                                cartItems = cartItems,
                                onAddToCart = { product ->
                                    viewModel.addToCart(product, store.nameAr)
                                },
                                onViewCart = { viewModel.toggleCartSheet(true) },
                                onBack = { viewModel.navigateBack() }
                            )
                        } ?: run {
                            viewModel.navigateBack()
                        }
                    }

                    Screen.ORDERS -> {
                        OrdersScreen(
                            orders = orders,
                            onOrderClick = { order -> viewModel.openTrackOrder(order) },
                            onRateOrder = { order -> viewModel.openRatingDialog(order) },
                            onStartOrder = { viewModel.navigateTo(Screen.CUSTOM_ORDER) }
                        )
                    }

                    Screen.ORDER_TRACKING -> {
                        val currentOrder = trackedOrder ?: activeFirstOrder
                        if (currentOrder != null) {
                            OrderTrackingScreen(
                                order = currentOrder,
                                onAdvanceStatus = { viewModel.advanceCurrentOrderStatus() },
                                onCancelOrder = { viewModel.cancelTrackedOrder() },
                                onRateOrder = { order -> viewModel.openRatingDialog(order) },
                                onBack = { viewModel.navigateBack() }
                            )
                        } else {
                            viewModel.navigateTo(Screen.ORDERS)
                        }
                    }

                    Screen.GUARANTEE_INFO -> {
                        GuaranteeScreen(
                            onStartOrder = { viewModel.navigateTo(Screen.CUSTOM_ORDER) }
                        )
                    }
                }
            }
        }
    }

    // Modal Cart Sheet
    if (showCartSheet) {
        CartBottomSheet(
            cartItems = cartItems,
            currentDistrict = selectedDistrict,
            onUpdateQuantity = { id, delta -> viewModel.updateCartQuantity(id, delta) },
            onRemoveItem = { id -> viewModel.removeFromCart(id) },
            onCheckout = { notes -> viewModel.checkoutCart(notes) },
            onDismiss = { viewModel.toggleCartSheet(false) }
        )
    }

    // Location Selector Dialog
    if (showLocationDialog) {
        LocationSelectorDialog(
            currentDistrict = selectedDistrict,
            onSelectDistrict = { viewModel.setSelectedDistrict(it) },
            onDismiss = { viewModel.toggleLocationDialog(false) }
        )
    }

    // Rating & Feedback Bottom Sheet
    orderToRate?.let { order ->
        RatingBottomSheet(
            order = order,
            onSubmit = { rating, tags, comment, tipYer ->
                viewModel.submitRating(order.id, rating, tags, comment, tipYer)
            },
            onDismiss = { viewModel.closeRatingDialog() }
        )
    }
}
