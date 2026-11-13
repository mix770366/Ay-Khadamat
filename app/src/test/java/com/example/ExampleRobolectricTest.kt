package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.ServiceType
import com.example.repository.AyServicesRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("أي خدمات", appName)
    }

    @Test
    fun `verify all 12 services exist with guarantees`() {
        assertEquals(12, ServiceType.entries.size)
        assertTrue(ServiceType.entries.all { it.guaranteeAr.isNotBlank() })
    }

    @Test
    fun `place custom order creates order with valid guarantee`() {
        val repository = AyServicesRepository()
        val order = repository.placeCustomOrder(
            serviceType = ServiceType.RESTAURANTS,
            customDescription = "وجبة ساخنة من الشيباني",
            pickupDistrict = "شارع العدين",
            dropoffDistrict = "الدائري"
        )
        assertTrue(order.id.startsWith("AY-"))
        assertEquals("وجبات المطاعم - حقيبة حرارية", order.titleAr)
        assertEquals("شارع العدين", order.pickupDistrictAr)
        assertEquals("الدائري", order.dropoffDistrictAr)
    }

    @Test
    fun `submitOrderReview updates rating tags comment and tip`() {
        val repository = AyServicesRepository()
        val initialOrder = repository.orders.value.first()
        repository.submitOrderReview(
            orderId = initialOrder.id,
            rating = 5,
            tags = listOf("الوجبة وصلت ساخنة", "سرعة استثنائية"),
            comment = "الكابتن وصل بسرعة خيالية وحفظ السخونة تماماً",
            courierTipYer = 500
        )
        val updated = repository.orders.value.find { it.id == initialOrder.id }
        assertEquals(5, updated?.rating)
        assertEquals(2, updated?.reviewTags?.size)
        assertEquals("الكابتن وصل بسرعة خيالية وحفظ السخونة تماماً", updated?.reviewComment)
        assertEquals(500, updated?.courierTipYer)
        assertTrue(updated?.ratedAt?.isNotBlank() == true)
    }
}
