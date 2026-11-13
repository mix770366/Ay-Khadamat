package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmberAccentSecondary
import com.example.ui.theme.EmeraldGreenDark
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.GoldGuarantee

data class GuaranteePillar(
    val titleAr: String,
    val descAr: String,
    val icon: ImageVector,
    val badgeAr: String
)

@Composable
fun GuaranteeScreen(
    onStartOrder: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val pillars = listOf(
        GuaranteePillar(
            titleAr = "ضمان الحقيبة الحرارية لوجبات المطاعم",
            descAr = "نستخدم حقائب حرارية مخصصة وعازلة تحافظ على حرارة ونكهة الوجبات كأنها خرجت للتو من المطبخ. في حال وصلت الوجبة باردة، يتم تعويضك فوراً.",
            icon = Icons.Default.Thermostat,
            badgeAr = "حرارة ونكهة طازجة"
        ),
        GuaranteePillar(
            titleAr = "ضمان السرية التامة وتوقيع استلام الأمانات",
            descAr = "تسليم المعاملات الورقية والعقود والأمانات بحقائب محكمة الإغلاق وسرية مطلقة، مع توثيق التوقيع الرسمي عند الاستلام والتسليم.",
            icon = Icons.Default.Lock,
            badgeAr = "سرية وتوقيع رسمي"
        ),
        GuaranteePillar(
            titleAr = "ضمان الحماية المضاعفة للإلكترونيات والجوالات",
            descAr = "حماية فائقة مبطنة ضد الصدمات والاهتزازات والرطوبة لنقل الشاشات والهواتف والشواحن والأجهزة الحساسة بأمان 100%.",
            icon = Icons.Default.PhoneIphone,
            badgeAr = "مقاومة للصدمات"
        ),
        GuaranteePillar(
            titleAr = "ضمان التغليف المحكم وصلاحية المقاضي",
            descAr = "فحص دقيق لتواريخ الصلاحية لمنتجات السوبرماركت والألبان، وفصل المنظفات عن المواد الغذائية بتغليف محكم وعازل.",
            icon = Icons.Default.ShoppingCart,
            badgeAr = "عناية فائقة وتغليف"
        ),
        GuaranteePillar(
            titleAr = "ضمان الفرز اليومي الطازج من أسواق اللواء الأخضر",
            descAr = "انتقاء يدوي حبة بحبة لأجود أصناف الخضروات والفواكه من أسواق ومزارع إب الخصبة مباشرة لمطبخك.",
            icon = Icons.Default.Eco,
            badgeAr = "قطاف صباحي أول"
        ),
        GuaranteePillar(
            titleAr = "ضمان حفظ المكواة والأناقة للملابس والهدايا",
            descAr = "نقل الملابس بأكياس تعليق تحافظ على الكي والترتيب بدون تجاعيد، وتسليم باقات الورود والهدايا بتنسيق راقٍ يرسم الابتسامة.",
            icon = Icons.Default.Checkroom,
            badgeAr = "كي أنيق وهدايا راقية"
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("guarantee_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Header Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                listOf(EmeraldGreenDark, EmeraldGreenPrimary, Color(0xFF0F5A34))
                            )
                        )
                        .padding(20.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(GoldGuarantee.copy(alpha = 0.25f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Security,
                                contentDescription = null,
                                tint = GoldGuarantee,
                                modifier = Modifier.size(34.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "ميثاق ضمان «أي خدمات»",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "«التوصيل لكل هذا أنا له ضامن»",
                            color = GoldGuarantee,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "نلتزم بأعلى معايير السرعة، الأمانة، الدقة، والحفاظ التام على ممتلكاتك ووجباتك وأماناتك في كافة أنحاء محافظة إب",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // Section Title
        item {
            PaddingValues(horizontal = 16.dp, vertical = 6.dp)
            Text(
                text = "ركائز الضمان الستة في منصة أي خدمات:",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
            )
        }

        // Pillars List
        items(pillars) { pillar ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(EmeraldGreenPrimary.copy(alpha = 0.12f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = pillar.icon,
                                    contentDescription = null,
                                    tint = EmeraldGreenPrimary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = pillar.titleAr,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(AmberAccentSecondary.copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = pillar.badgeAr,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = AmberAccentSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = pillar.descAr,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Customer Support & Hotline in Ibb
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.SupportAgent,
                            contentDescription = null,
                            tint = EmeraldGreenPrimary,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "فريق خدمة عملاء إب المباشر 📞",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Text(
                                text = "جاهزون للرد وحل أي ملاحظة فوراً على مدار الساعة",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:770000000")
                                }
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = EmeraldGreenPrimary,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("اتصال مباشر", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = onStartOrder,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AmberAccentSecondary,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                        ) {
                            Text("اطلب الآن بضمان", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
