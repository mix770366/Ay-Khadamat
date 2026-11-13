package com.example.data

import com.example.model.ProductItem
import com.example.model.ServiceType
import com.example.model.VendorStore

object AyKhadamatData {

    val sampleStores: List<VendorStore> = listOf(
        // 1. Restaurants
        VendorStore(
            id = "store_sheibani",
            nameAr = "مطاعم الشيباني السياحية",
            serviceType = ServiceType.RESTAURANTS,
            areaAr = "شارع العدين - قلب مدينة إب",
            rating = 4.9,
            reviewCount = 520,
            deliveryTimeMinutes = "25 - 35 دقيقة",
            deliveryFeeYer = 1000,
            minOrderYer = 2000,
            tagAr = "وجبات ساخنة بحقيبة حرارية",
            products = listOf(
                ProductItem(
                    id = "sh_1",
                    vendorId = "store_sheibani",
                    nameAr = "فحسة لحم بلدي حارة بمقلى مدر",
                    descriptionAr = "فحسة لحم بلدي طازجة مع حلبة إب ومشروب المرق الساخن، تصلك تغلي بحقيبة حرارية",
                    priceYer = 3500,
                    categoryNameAr = "شعبيات وطواجن",
                    isPopular = true
                ),
                ProductItem(
                    id = "sh_2",
                    vendorId = "store_sheibani",
                    nameAr = "نصف تيس بلدي مندي مع أرز بشاور",
                    descriptionAr = "مندي حنيذ بلدي على الحطب متبل ببهارات خاصة مع أرز مزة فاخر وسلطات حارة",
                    priceYer = 9500,
                    categoryNameAr = "مندي ومشويات",
                    isPopular = true
                ),
                ProductItem(
                    id = "sh_3",
                    vendorId = "store_sheibani",
                    nameAr = "سلتة صنعانية وخضار مع رغيف ملوج",
                    descriptionAr = "سلتة تقليدية بمرق اللحم والخضار ورغيف خبز ملوج بلدي ساخن من التنور",
                    priceYer = 2200,
                    categoryNameAr = "شعبيات وطواجن"
                ),
                ProductItem(
                    id = "sh_4",
                    vendorId = "store_sheibani",
                    nameAr = "مشكل مشاوي مشوية على الفحم (كيلو)",
                    descriptionAr = "أوصال لحم، كباب بلدي، شيش طاووق مع الخبز الطازج والمقبلات",
                    priceYer = 8000,
                    categoryNameAr = "مندي ومشويات",
                    originalPriceYer = 9000
                )
            )
        ),
        VendorStore(
            id = "store_remas",
            nameAr = "مطاعم ريماس السياحية",
            serviceType = ServiceType.RESTAURANTS,
            areaAr = "الدائري الغربي - إب",
            rating = 4.8,
            reviewCount = 380,
            deliveryTimeMinutes = "30 - 40 دقيقة",
            deliveryFeeYer = 1200,
            minOrderYer = 2500,
            tagAr = "مشاوي وشواية وشاورما عائلية",
            products = listOf(
                ProductItem(
                    id = "rm_1",
                    vendorId = "store_remas",
                    nameAr = "دجاج شواية مع أرز بخاري ومقبلات",
                    descriptionAr = "حبة دجاج متبلة ومحمرة بتتبيلة ريماس الذهبية مع أرز وسحاوق جبن",
                    priceYer = 4200,
                    categoryNameAr = "دجاج وشواية",
                    isPopular = true
                ),
                ProductItem(
                    id = "rm_2",
                    vendorId = "store_remas",
                    nameAr = "صحن شاورما عربي دبل مع صوص الثومية",
                    descriptionAr = "شاورما دجاج بخبز الصاج مع بطاطس مقرمشة ومخللات وثومية شهية",
                    priceYer = 2800,
                    categoryNameAr = "سندوتشات وسريع"
                )
            )
        ),

        // 2. Retail Stores
        VendorStore(
            id = "store_retail_alamal",
            nameAr = "مركز الأمل للأدوات المنزلية والقرطاسية",
            serviceType = ServiceType.RETAIL_STORES,
            areaAr = "شارع بغداد - إب",
            rating = 4.7,
            reviewCount = 140,
            deliveryTimeMinutes = "30 - 45 دقيقة",
            deliveryFeeYer = 1000,
            minOrderYer = 1500,
            tagAr = "أدوات منزلية ومستلزمات مكتبية",
            products = listOf(
                ProductItem(
                    id = "rt_1",
                    vendorId = "store_retail_alamal",
                    nameAr = "طقم ترامس شاي وقهوة حرارية (2 حبة)",
                    descriptionAr = "ترامس حفظ الحرارة لمدة 24 ساعة بتصميم أنيق ومقاوم للتسرب",
                    priceYer = 8500,
                    categoryNameAr = "أدوات منزلية",
                    originalPriceYer = 10000
                ),
                ProductItem(
                    id = "rt_2",
                    vendorId = "store_retail_alamal",
                    nameAr = "باكت ورق طباعة A4 فاخر (500 ورقة)",
                    descriptionAr = "ورق تصوير وطباعة عالي البياض 80 جرام للشركات والطلاب",
                    priceYer = 3200,
                    categoryNameAr = "قرطاسية وأدوات مكتبية"
                ),
                ProductItem(
                    id = "rt_3",
                    vendorId = "store_retail_alamal",
                    nameAr = "طقم سكاكين ستانلس ستيل مع حامل خشبي",
                    descriptionAr = "شفرات حادة ومقاومة للصدأ مريحة لليد للمطبخ العصري",
                    priceYer = 4500,
                    categoryNameAr = "أدوات منزلية"
                )
            )
        ),

        // 3. Supermarket
        VendorStore(
            id = "store_dhamran",
            nameAr = "سوبرماركت ظمران إكسبرس",
            serviceType = ServiceType.SUPERMARKET,
            areaAr = "مفرق جبلة - إب",
            rating = 4.9,
            reviewCount = 610,
            deliveryTimeMinutes = "30 - 45 دقيقة",
            deliveryFeeYer = 1200,
            minOrderYer = 3000,
            tagAr = "تغليف محكم وعناية بالمنتجات الحساسة",
            products = listOf(
                ProductItem(
                    id = "sp_1",
                    vendorId = "store_dhamran",
                    nameAr = "كرتون حليب المراعي طويل الأجل (12 لتر)",
                    descriptionAr = "حليب كامل الدسم بجودة عالية محفوظ بعناية",
                    priceYer = 9800,
                    categoryNameAr = "ألبان وأجبان",
                    isPopular = true
                ),
                ProductItem(
                    id = "sp_2",
                    vendorId = "store_dhamran",
                    nameAr = "كيس أرز بسمتي هندي ملكي (10 كجم)",
                    descriptionAr = "حبة طويلة نقية معطرة ممتازة للكبسات والعزائم",
                    priceYer = 14500,
                    categoryNameAr = "حبوب وتموين"
                ),
                ProductItem(
                    id = "sp_3",
                    vendorId = "store_dhamran",
                    nameAr = "زيت طبخ عافية ذرة نقي (4 لتر)",
                    descriptionAr = "زيت ذرة خفيف وصحي للقلي والطهي بتغليف آمن مانع للتسريب",
                    priceYer = 7800,
                    categoryNameAr = "زيوت ومعلبات"
                ),
                ProductItem(
                    id = "sp_4",
                    vendorId = "store_dhamran",
                    nameAr = "طقم منظفات المنزل ومطهر معقم (3 عبوات)",
                    descriptionAr = "كلوركس، صابون أطباق، ومعطر أرضيات برائحة اللافندر",
                    priceYer = 3800,
                    categoryNameAr = "عناية منزلية"
                )
            )
        ),

        // 4. Fresh Produce
        VendorStore(
            id = "store_green_market",
            nameAr = "أسواق اللواء الأخضر للخضار والفواكه",
            serviceType = ServiceType.FRESH_PRODUCE,
            areaAr = "السوق المركزي - حراثة",
            rating = 4.9,
            reviewCount = 420,
            deliveryTimeMinutes = "25 - 40 دقيقة",
            deliveryFeeYer = 900,
            minOrderYer = 2000,
            tagAr = "قطاف صباحي من مزارع وأودية إب",
            products = listOf(
                ProductItem(
                    id = "fp_1",
                    vendorId = "store_green_market",
                    nameAr = "طماطم بلدي إبي طازج (سلة 3 كجم)",
                    descriptionAr = "طماطم بلدي حمراء ممتازة مفرزة حبة بحبة خالية من العيوب",
                    priceYer = 2400,
                    categoryNameAr = "خضار طازجة",
                    isPopular = true,
                    unitAr = "سلة"
                ),
                ProductItem(
                    id = "fp_2",
                    vendorId = "store_green_market",
                    nameAr = "بطاطس بلدي عالي الجودة (كيس 5 كجم)",
                    descriptionAr = "بطاطس إب الشهيرة ممتازة للقلي والطبخ",
                    priceYer = 3200,
                    categoryNameAr = "خضار طازجة",
                    unitAr = "كيس"
                ),
                ProductItem(
                    id = "fp_3",
                    vendorId = "store_green_market",
                    nameAr = "سلة فواكه مشكلة مشكلة ومختارة بعناية (4 كجم)",
                    descriptionAr = "تفاح بلدي، موز، رمان، وبرتقال طازج مقطوف بعناية",
                    priceYer = 5600,
                    categoryNameAr = "فواكه موسمية",
                    isPopular = true
                ),
                ProductItem(
                    id = "fp_4",
                    vendorId = "store_green_market",
                    nameAr = "حزمة ورقيات بلدي خضراء (كزبرة، كراث، بقدونس، نعناع)",
                    descriptionAr = "حزم طازجة معطرة ومغسولة بعناية لطهي أشهى الأطباق",
                    priceYer = 700,
                    categoryNameAr = "ورقيات"
                )
            )
        ),

        // 5. Shopping Malls & Personal Shopper
        VendorStore(
            id = "store_city_center",
            nameAr = "سيتي سنتر إب ومول المروة",
            serviceType = ServiceType.SHOPPING_MALLS,
            areaAr = "الدائري - شارع تعز إب",
            rating = 4.8,
            reviewCount = 210,
            deliveryTimeMinutes = "40 - 60 دقيقة",
            deliveryFeeYer = 1500,
            minOrderYer = 5000,
            tagAr = "خدمة تسوق شخصي بالنيابة عنك",
            products = listOf(
                ProductItem(
                    id = "ml_1",
                    vendorId = "store_city_center",
                    nameAr = "خدمة المشتري الشخصي (تسوق من أي متجر بالمول)",
                    descriptionAr = "يقوم مندوبنا بالتجول داخل محلات المول واختيار القطع وتصويرها لك والتأكد من المقاسات",
                    priceYer = 2500,
                    categoryNameAr = "خدمات تسوق"
                ),
                ProductItem(
                    id = "ml_2",
                    vendorId = "store_city_center",
                    nameAr = "حقيبة ظهر أنيقة للجامعة والعمل ماركة أصلية",
                    descriptionAr = "خامة مقاومة للماء مع فتحة شحن USB وجيوب متعددة",
                    priceYer = 12000,
                    categoryNameAr = "حقائب وإكسسوارات"
                )
            )
        ),

        // 6. Pharmacies & Medical Supplies
        VendorStore(
            id = "store_pharmacy_sina",
            nameAr = "صيدليات ابن سينا والنور (24/7)",
            serviceType = ServiceType.PHARMACY,
            areaAr = "مستشفى الثورة - حي المعاين",
            rating = 5.0,
            reviewCount = 890,
            deliveryTimeMinutes = "15 - 25 دقيقة",
            deliveryFeeYer = 1000,
            minOrderYer = 1000,
            tagAr = "خدمة طارئة سريعة وحفظ مبرد للأدوية",
            products = listOf(
                ProductItem(
                    id = "ph_1",
                    vendorId = "store_pharmacy_sina",
                    nameAr = "صرف وصفة طبية مستعجلة (اكتب أدويتك في الملاحظات)",
                    descriptionAr = "يرجى كتابة أسماء الأدوية أو تصوير الوصفة وسيقوم الصيدلي بتجهيزها فوراً مع الشرح",
                    priceYer = 2000,
                    categoryNameAr = "وصفات طبية",
                    isPopular = true
                ),
                ProductItem(
                    id = "ph_2",
                    vendorId = "store_pharmacy_sina",
                    nameAr = "جهاز قياس ضغط الدم الإلكتروني الرقمي",
                    descriptionAr = "شاشة LCD واضحة، ذاكرة قراءات متعددة وكف مريح للذراع",
                    priceYer = 13500,
                    categoryNameAr = "أجهزة ومستلزمات",
                    originalPriceYer = 15000
                ),
                ProductItem(
                    id = "ph_3",
                    vendorId = "store_pharmacy_sina",
                    nameAr = "حقيبة إسعافات أولية منزلية متكاملة",
                    descriptionAr = "شاش طبي، مطهر بيتادين، لصقات جروح، قطن، ومسكنات حرارة ومغص",
                    priceYer = 4500,
                    categoryNameAr = "إسعافات أولية"
                ),
                ProductItem(
                    id = "ph_4",
                    vendorId = "store_pharmacy_sina",
                    nameAr = "فيتامين سي زنك فوار مع فيتامين د3 (علبة 20 قرص)",
                    descriptionAr = "لدعم المناعة ومقاومة الإنفلونزا والإرهاق",
                    priceYer = 2200,
                    categoryNameAr = "مكملات وفيتامينات"
                )
            )
        ),

        // 7. Electronics & Mobile Accessories
        VendorStore(
            id = "store_electronics_apex",
            nameAr = "قمة التكنولوجيا للهواتف والإلكترونيات",
            serviceType = ServiceType.ELECTRONICS,
            areaAr = "شارع العدين أمام مجمع بنك اليمن الدولي",
            rating = 4.8,
            reviewCount = 310,
            deliveryTimeMinutes = "20 - 35 دقيقة",
            deliveryFeeYer = 1000,
            minOrderYer = 2000,
            tagAr = "تغليف مدبل ضد الصدمات والاهتزازات",
            products = listOf(
                ProductItem(
                    id = "el_1",
                    vendorId = "store_electronics_apex",
                    nameAr = "باور بانك سريع 20,000 مللي أمبير بقوة 65 واط",
                    descriptionAr = "شحن فائق السرعة للهواتف واللابتوبات مع شاشة رقمية للنسبة المئوية",
                    priceYer = 16500,
                    categoryNameAr = "شواحن وبطاريات",
                    isPopular = true
                ),
                ProductItem(
                    id = "el_2",
                    vendorId = "store_electronics_apex",
                    nameAr = "سماعة بلوتوث لاسلكية عازلة للضوضاء",
                    descriptionAr = "صوت نقي، بطارية تدوم 30 ساعة مع علبة شحن أنيقة وميكروفون واضح",
                    priceYer = 9500,
                    categoryNameAr = "سماعات وصوتيات"
                ),
                ProductItem(
                    id = "el_3",
                    vendorId = "store_electronics_apex",
                    nameAr = "كيبل شحن سريع مضفر غير قابل للقطع (Type-C / Lightning)",
                    descriptionAr = "طول 1.5 متر مع تدعيم كامل عند الأطراف وشحن 100W",
                    priceYer = 2200,
                    categoryNameAr = "كيابل وتوصيلات"
                )
            )
        ),

        // 8. Gifts, Flowers & Chocolates
        VendorStore(
            id = "store_gifts_orchid",
            nameAr = "أوركيد للورود والهدايا الفاخرة",
            serviceType = ServiceType.GIFTS_AND_FLOWERS,
            areaAr = "الدائري أمام منتجع إب السياحي",
            rating = 4.9,
            reviewCount = 280,
            deliveryTimeMinutes = "30 - 45 دقيقة",
            deliveryFeeYer = 1500,
            minOrderYer = 4000,
            tagAr = "تسليم أنيق يرسم الابتسامة مع كرت إهداء",
            products = listOf(
                ProductItem(
                    id = "gf_1",
                    vendorId = "store_gifts_orchid",
                    nameAr = "باقة ورد جوري طبيعي فاخرة منسقة بعناية",
                    descriptionAr = "ورود حمراء وبيضاء طبيعية منتقاة مع تغليف راقٍ وشريطة ساتان وكتابة رسالتك",
                    priceYer = 8500,
                    categoryNameAr = "باقات ورود",
                    isPopular = true
                ),
                ProductItem(
                    id = "gf_2",
                    vendorId = "store_gifts_orchid",
                    nameAr = "صندوق شوكولاتة بلجيكية فاخرة مشكلة (نصف كيلو)",
                    descriptionAr = "حبات شوكولاتة محشوة بالمكسرات والكراميل بتقديم هدايا مميز",
                    priceYer = 7500,
                    categoryNameAr = "شوكولاتة وهدايا"
                ),
                ProductItem(
                    id = "gf_3",
                    vendorId = "store_gifts_orchid",
                    nameAr = "بوكس مفاجأة متكامل (باقة ورد + شوكولاتة + دمية دب)",
                    descriptionAr = "هدية متكاملة تسلم لأحبائك في إب مباشرة مع إشعار بالاستلام",
                    priceYer = 15000,
                    categoryNameAr = "باقات المناسبات",
                    originalPriceYer = 17500,
                    isPopular = true
                )
            )
        ),

        // 9. Fashion & Tailoring pickup
        VendorStore(
            id = "store_fashion_elite",
            nameAr = "خياطة ومعارض النخبة والأزياء",
            serviceType = ServiceType.FASHION_TAILORING,
            areaAr = "شارع تعز - المعاين",
            rating = 4.8,
            reviewCount = 190,
            deliveryTimeMinutes = "30 - 45 دقيقة",
            deliveryFeeYer = 1000,
            minOrderYer = 2000,
            tagAr = "توصيل مع الحفاظ على المكواة والترتيب داخل أكياس معلقة",
            products = listOf(
                ProductItem(
                    id = "fs_1",
                    vendorId = "store_fashion_elite",
                    nameAr = "خدمة استلام وتوصيل ملابس من المغسلة/الخياط",
                    descriptionAr = "مندوبنا يستلم ملابسك ويعلقها بحامل خاص مع الحفاظ التام على الكي والنظافة",
                    priceYer = 2000,
                    categoryNameAr = "خدمات توصيل ملابس",
                    isPopular = true
                ),
                ProductItem(
                    id = "fs_2",
                    vendorId = "store_fashion_elite",
                    nameAr = "شال صوف كشميري يمني فاخر نخب أول",
                    descriptionAr = "نقش يدوي راقٍ بلون مميز ودفء مثالي لأجواء إب الباردة",
                    priceYer = 18000,
                    categoryNameAr = "أزياء وإكسسوارات"
                )
            )
        )
    )
}
