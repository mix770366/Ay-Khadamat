package com.example.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Service Category IDs representing the 12 core services requested
 */
enum class ServiceType(
    val id: String,
    val titleAr: String,
    val shortDescAr: String,
    val fullDescAr: String,
    val guaranteeAr: String,
    val badgeAr: String,
    val estimatedMinutes: String
) {
    RESTAURANTS(
        id = "restaurants",
        titleAr = "وجبات المطاعم",
        shortDescAr = "أشهر مطاعم إب ساخنة وطازجة",
        fullDescAr = "استلام الوجبات الساخنة والشهية من أشهر مطاعم إب وتوصيلها فورياً بحقائب حرارية تحافظ على نكهتها وحرارتها كأنها خرجت للتو من المطبخ.",
        guaranteeAr = "ضمان الحقيبة الحرارية لحفظ السخونة والنكهة",
        badgeAr = "حقيبة حرارية",
        estimatedMinutes = "25 - 40 دقيقة"
    ),
    RETAIL_STORES(
        id = "retail",
        titleAr = "المحلات والتجزئة",
        shortDescAr = "أدوات منزلية، قرطاسيات واحتياجات",
        fullDescAr = "شراء وتوصيل كافة الاحتياجات من محلات التجزئة، الأدوات المنزلية، والقرطاسيات بمرونة وسرعة تامة.",
        guaranteeAr = "ضمان الدقة في اختيار المواصفات والسرعة",
        badgeAr = "توصيل مرن",
        estimatedMinutes = "30 - 45 دقيقة"
    ),
    SUPERMARKET(
        id = "supermarket",
        titleAr = "مقاضي السوبر ماركت",
        shortDescAr = "تموين منزلي، ألبان، ومعلبات",
        fullDescAr = "توصيل جميع المشتريات التموينية، المعلبات، الألبان، ومنتجات العناية المنزلية بعناية فائقة وتغليف محكم إلى باب بيتك.",
        guaranteeAr = "ضمان التغليف المحكم ومراعاة تواريخ الصلاحية",
        badgeAr = "تغليف محكم",
        estimatedMinutes = "35 - 50 دقيقة"
    ),
    FRESH_PRODUCE(
        id = "produce",
        titleAr = "خضروات وفواكه طازجة",
        shortDescAr = "من أسواق اللواء الأخضر لمطبخك",
        fullDescAr = "انتقاء يومي من أسواق اللواء الأخضر لأجود أنواع الخضار والفواكه الطازجة وتوصيلها مباشرة لمطبخك بحالة ممتازة وجودة قطاف أولى.",
        guaranteeAr = "ضمان الفرز والانتقاء اليدوي لأعلى جودة",
        badgeAr = "فرز يومي طازج",
        estimatedMinutes = "30 - 45 دقيقة"
    ),
    SHOPPING_MALLS(
        id = "malls",
        titleAr = "المولات والمراكز",
        shortDescAr = "تسوق شخصي متكامل بأكبر المراكز",
        fullDescAr = "خدمة تسوق شخصية متكاملة داخل المولات والمراكز الكبرى بإب؛ ننتقي لك طلباتك ونوصلها لك فوراً أينما كنت.",
        guaranteeAr = "ضمان تسوق شخصي وتطابق القطع والمقاسات",
        badgeAr = "تسوق شخصي",
        estimatedMinutes = "45 - 60 دقيقة"
    ),
    PHARMACY(
        id = "pharmacy",
        titleAr = "صيدليات ومستلزمات طبية",
        shortDescAr = "توصيل أدوية ووصفات 24/7",
        fullDescAr = "توصيل عاجل للأدوية والوصفات الطبية والمستلزمات العلاجية والصحية على مدار الساعة وبسرعة استثنائية وأمان كامل.",
        guaranteeAr = "ضمان السرعة القصوى والنقل الآمن للأدوية",
        badgeAr = "خدمة 24/7 طارئة",
        estimatedMinutes = "15 - 30 دقيقة"
    ),
    ELECTRONICS(
        id = "electronics",
        titleAr = "إلكترونيات وملحقات الجوال",
        shortDescAr = "أجهزة وإكسسوارات بحماية ضد الصدمات",
        fullDescAr = "نقل وتوصيل الأجهزة الإلكترونية والهواتف والشواحن والإكسسوارات بحماية مضاعفة ضد الصدمات والاهتزازات.",
        guaranteeAr = "ضمان حماية مضاعفة ضد الصدمات والرطوبة",
        badgeAr = "حماية ضد الصدمات",
        estimatedMinutes = "25 - 40 دقيقة"
    ),
    GIFTS_AND_FLOWERS(
        id = "gifts",
        titleAr = "الهدايا وباقات المناسبات",
        shortDescAr = "ورود، شوكولاتة ومفاجآت أنيقة",
        fullDescAr = "توصيل الهدايا، باقات الورود، علب الشوكولاتة، والمفاجآت لأحبائك في إب بتنسيق أنيق وتسليم راقٍ يرسم الابتسامة.",
        guaranteeAr = "ضمان التسليم الأنيق وسلامة الباقات وكتابة الكرت",
        badgeAr = "توصيل أنيق وفاخر",
        estimatedMinutes = "30 - 50 دقيقة"
    ),
    FASHION_TAILORING(
        id = "fashion",
        titleAr = "الملابس والأزياء والخياطة",
        shortDescAr = "توصيل مع الحفاظ على الكي والترتيب",
        fullDescAr = "استلام وتوصيل الملابس من معارض الأزياء والخياطين ومحلات الملابس، مع الحفاظ على المكواة والترتيب بأكياس تعليق واقية.",
        guaranteeAr = "ضمان حفظ المكواة والأناقة بدون تجاعيد",
        badgeAr = "حفظ الكي والأناقة",
        estimatedMinutes = "30 - 45 دقيقة"
    ),
    PARCEL_SHIPPING(
        id = "parcels",
        titleAr = "الطرود والشحن الداخلي",
        shortDescAr = "شحن سريع ومضمون بين مناطق إب",
        fullDescAr = "شحن وتسليم الطرود والصناديق والأغراض بين مختلف مناطق ومربعات محافظة إب والفرز السريع المضمون في نفس اليوم.",
        guaranteeAr = "ضمان سلامة الطرد وسرعة التوجيه بين المربعات",
        badgeAr = "بين مناطق إب",
        estimatedMinutes = "30 - 60 دقيقة"
    ),
    CONFIDENTIAL_DOCUMENTS(
        id = "documents",
        titleAr = "الرسائل والأمانات والوثائق",
        shortDescAr = "سرية تامة، أمان وتوقيع بالاستلام",
        fullDescAr = "استلام وتسليم المعاملات الورقية، العقود، الرسائل المهمة، والأمانات الخاصة بخصوصية وسرية تامة وتوقيع رسمي بالاستلام.",
        guaranteeAr = "ضمان السرية المطلقة والتسليم بتوقيع موثق",
        badgeAr = "سرية وتوقيع رسمي",
        estimatedMinutes = "20 - 35 دقيقة"
    ),
    CUSTOM_ERRANDS(
        id = "errands",
        titleAr = "المشاوير والمهام الخاصة",
        shortDescAr = "مندوبك الشخصي لأي غرض مستعجل",
        fullDescAr = "مندوب خاص رهن إشارتك لإنجاز أي مشوار شخصي أو غرض مستعجل حسب طلبك الخاص في أي وقت في كافة أحياء إب.",
        guaranteeAr = "ضمان تنفيذ المهمة بدقة المندوب الخاص والأمانة",
        badgeAr = "مندوبك الخاص",
        estimatedMinutes = "فوري حسب الطلب"
    );

    companion object {
        fun fromId(id: String): ServiceType =
            entries.find { it.id.equals(id, ignoreCase = true) } ?: RESTAURANTS
    }
}

/**
 * Product Item inside stores or categories
 */
data class ProductItem(
    val id: String,
    val vendorId: String,
    val nameAr: String,
    val descriptionAr: String,
    val priceYer: Int, // Yemeni Rial
    val categoryNameAr: String,
    val originalPriceYer: Int? = null,
    val isPopular: Boolean = false,
    val unitAr: String = "حبة"
)

/**
 * Store or Vendor in Ibb
 */
data class VendorStore(
    val id: String,
    val nameAr: String,
    val serviceType: ServiceType,
    val areaAr: String, // District in Ibb
    val rating: Double,
    val reviewCount: Int,
    val deliveryTimeMinutes: String,
    val deliveryFeeYer: Int,
    val minOrderYer: Int,
    val tagAr: String,
    val isOpen: Boolean = true,
    val products: List<ProductItem> = emptyList()
)

/**
 * Cart Item
 */
data class CartItem(
    val product: ProductItem,
    val vendorName: String,
    val quantity: Int,
    val specialNotes: String = ""
)

/**
 * Order Status
 */
enum class OrderStatus(val titleAr: String, val subtitleAr: String, val stepIndex: Int) {
    PENDING("تم استلام الطلب", "يتم مراجعة طلبك وإرساله لأقرب مندوب", 0),
    COURIER_ASSIGNED("تم إسناد الكابتن", "كابتن أي خدمات في طريقه لنقطة الاستلام", 1),
    PREPARING_PICKING("جاري الاستلام والتجهيز", "الكابتن يفحص الأغراض ويطبق معايير الضمان", 2),
    ON_THE_WAY("في الطريق إليك", "الكابتن قادم إلى عنوانك بعناية تامة", 3),
    DELIVERED("تم التسليم بنجاح", "تم استلام الطلب مع ضمان الجودة والأمانة", 4),
    CANCELLED("ملغي", "تم إلغاء الطلب", -1)
}

/**
 * Ay Services Order
 */
data class AyOrder(
    val id: String,
    val serviceType: ServiceType,
    val titleAr: String,
    val status: OrderStatus,
    val createdAt: String,
    val pickupDistrictAr: String,
    val dropoffDistrictAr: String,
    val items: List<CartItem> = emptyList(),
    val customNotesAr: String = "",
    val deliveryFeeYer: Int,
    val itemsSubtotalYer: Int,
    val totalAmountYer: Int,
    val guaranteeAppliedAr: String,
    val courierNameAr: String = "كابتن أحمد الحبيشي",
    val courierPhone: String = "777123456",
    val courierRating: Double = 4.9,
    val courierVehicleAr: String = "دراجة نارية مع حقيبة حرارية مصفحة",
    val isUrgent: Boolean = false,
    val requiresSignature: Boolean = false,
    val isSurpriseGift: Boolean = false,
    val giftRecipientName: String = "",
    val giftRecipientPhone: String = "",
    val rating: Int? = null,
    val reviewTags: List<String> = emptyList(),
    val reviewComment: String? = null,
    val courierTipYer: Int = 0,
    val ratedAt: String? = null
)

/**
 * Predefined districts in Ibb
 */
object IbbDistricts {
    val list = listOf(
        "شارع العدين",
        "شارع بغداد",
        "الدائري الغربي",
        "الدائري الشرقي",
        "مفرق جبلة",
        "حي المعاين",
        "دار الشرف",
        "حي حراثة",
        "منطقة قحزة",
        "حي المشنة",
        "حي الظهار",
        "سبلة ميت",
        "السحول",
        "منطقة ميتم",
        "مدينة جبلة",
        "مدينة القاعدة",
        "حبيش",
        "يريم"
    )
}
