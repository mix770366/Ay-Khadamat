package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.IbbDistricts
import com.example.model.ServiceType
import com.example.ui.components.getServiceIcon
import com.example.ui.theme.AmberAccentSecondary
import com.example.ui.theme.EmeraldGreenDark
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.GoldGuarantee

@Composable
fun CustomOrderScreen(
    serviceType: ServiceType,
    onServiceTypeChange: (ServiceType) -> Unit,
    orderNotes: String,
    onOrderNotesChange: (String) -> Unit,
    pickupDistrict: String,
    onPickupDistrictChange: (String) -> Unit,
    dropoffDistrict: String,
    onDropoffDistrictChange: (String) -> Unit,
    isUrgent: Boolean,
    onUrgentChange: (Boolean) -> Unit,
    requiresSignature: Boolean,
    onRequiresSignatureChange: (Boolean) -> Unit,
    isSurpriseGift: Boolean,
    onIsSurpriseGiftChange: (Boolean) -> Unit,
    giftRecipientName: String,
    onGiftRecipientNameChange: (String) -> Unit,
    giftRecipientPhone: String,
    onGiftRecipientPhoneChange: (String) -> Unit,
    estimatedGoodsPrice: String,
    onEstimatedGoodsPriceChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showPickupDropdown by remember { mutableStateOf(false) }
    var showDropoffDropdown by remember { mutableStateOf(false) }

    val baseDeliveryFee = when (serviceType) {
        ServiceType.PARCEL_SHIPPING -> 1500
        ServiceType.CONFIDENTIAL_DOCUMENTS -> 1800
        ServiceType.CUSTOM_ERRANDS -> 1400
        ServiceType.GIFTS_AND_FLOWERS -> 1600
        ServiceType.SHOPPING_MALLS -> 1800
        else -> 1200
    }
    val urgencyFee = if (isUrgent) 500 else 0
    val totalFee = baseDeliveryFee + urgencyFee

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("custom_order_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // 1. Header Bar
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "رجوع",
                            tint = EmeraldGreenPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text(
                            text = "اطلب أي خدمة أو مشوار خاص",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )
                        Text(
                            text = "مندوبك الشخصي رهن إشارتك في كافة أحياء إب",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }

        // 2. Select Service Type Pills
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Text(
                    text = "حدد نوع الخدمة أو المشوار:",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(ServiceType.entries.toList()) { s ->
                        FilterChip(
                            selected = serviceType == s,
                            onClick = { onServiceTypeChange(s) },
                            label = { Text(s.titleAr, fontSize = 12.sp) },
                            leadingIcon = {
                                Icon(
                                    imageVector = getServiceIcon(s),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = EmeraldGreenPrimary,
                                selectedLabelColor = Color.White,
                                selectedLeadingIconColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        // 3. Guarantee badge for this service
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.VerifiedUser,
                            contentDescription = null,
                            tint = EmeraldGreenPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "ضمان أي خدمات المطبق على هذا الطلب:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldGreenDark
                            )
                            Text(
                                text = serviceType.guaranteeAr,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // 4. Description Field
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Text(
                    text = "اكتب تفاصيل طلبك أو مشوارك بدقة:",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = orderNotes,
                    onValueChange = onOrderNotesChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .testTag("custom_order_notes_input"),
                    placeholder = {
                        Text(
                            text = when (serviceType) {
                                ServiceType.RESTAURANTS -> "مثال: وجبة مندي مع سلتة ساخنة من الشيباني وتوصيلها بحقيبة حرارية"
                                ServiceType.PARCEL_SHIPPING -> "مثال: استلام كرتون بضاعة من مكتب بريد إب وتسليمه في مفرق جبلة"
                                ServiceType.CONFIDENTIAL_DOCUMENTS -> "مثال: استلام أوراق عقد رسمي وتوقيعه من الشخص الآخر وإعادة النسخة بظرف مختوم"
                                ServiceType.GIFTS_AND_FLOWERS -> "مثال: باقة ورد أحمر مع شوكولاتة وكرت تهنئة بمناسبة النجاح وتسليمها مفاجأة"
                                ServiceType.ELECTRONICS -> "مثال: شراء شاحن أنكر وباوربانك وتوصيله بحماية ضد الصدمات"
                                ServiceType.FASHION_TAILORING -> "مثال: استلام ثوبين من خياط النخبة بالمعاين مع الحفاظ على الكي بأكياس معلقة"
                                ServiceType.PHARMACY -> "مثال: أدوية الضغط الموصوفة من صيدلية ابن سينا بصورة عاجلة"
                                else -> "اكتب قائمة مشترياتك، أو الأغراض المراد استلامها ونقلها، وأي تعليمات خاصة للمندوب..."
                            },
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldGreenPrimary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    )
                )
            }
        }

        // 5. Pickup & Dropoff Districts in Ibb
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "مسار التوصيل في محافظة إب 📍",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Pickup
                    Text(text = "نقطة الاستلام (المتجر أو الموقع):", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                    Spacer(modifier = Modifier.height(4.dp))
                    Box {
                        OutlinedTextField(
                            value = pickupDistrict,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                IconButton(onClick = { showPickupDropdown = true }) {
                                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "اختر")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showPickupDropdown = true },
                            shape = RoundedCornerShape(10.dp)
                        )
                        DropdownMenu(
                            expanded = showPickupDropdown,
                            onDismissRequest = { showPickupDropdown = false }
                        ) {
                            IbbDistricts.list.forEach { district ->
                                DropdownMenuItem(
                                    text = { Text(district) },
                                    onClick = {
                                        onPickupDistrictChange(district)
                                        showPickupDropdown = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Dropoff
                    Text(text = "نقطة التسليم (عنوانك أو المستلم):", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                    Spacer(modifier = Modifier.height(4.dp))
                    Box {
                        OutlinedTextField(
                            value = dropoffDistrict,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                IconButton(onClick = { showDropoffDropdown = true }) {
                                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "اختر")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showDropoffDropdown = true },
                            shape = RoundedCornerShape(10.dp)
                        )
                        DropdownMenu(
                            expanded = showDropoffDropdown,
                            onDismissRequest = { showDropoffDropdown = false }
                        ) {
                            IbbDistricts.list.forEach { district ->
                                DropdownMenuItem(
                                    text = { Text(district) },
                                    onClick = {
                                        onDropoffDistrictChange(district)
                                        showDropoffDropdown = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        // 6. Guarantee Options and Special Flags
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "خيارات الأمان والضمان الإضافية 🛡️",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Urgent Switch
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            Icon(Icons.Default.FlashOn, contentDescription = null, tint = AmberAccentSecondary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "توصيل طارئ وفوري (+500 ريال)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                Text(text = "توجيه أقرب كابتن فائق السرعة فوراً", fontSize = 11.sp, color = MaterialTheme.colorScheme.outline)
                            }
                        }
                        Switch(
                            checked = isUrgent,
                            onCheckedChange = onUrgentChange,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = EmeraldGreenPrimary
                            ),
                            modifier = Modifier.testTag("urgent_switch")
                        )
                    }

                    // Signature option
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = EmeraldGreenPrimary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "طلب توقيع رسمي بالاستلام", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                Text(text = "توثيق استلام الأمانات والوثائق مع إشعار", fontSize = 11.sp, color = MaterialTheme.colorScheme.outline)
                            }
                        }
                        Checkbox(
                            checked = requiresSignature,
                            onCheckedChange = onRequiresSignatureChange,
                            colors = CheckboxDefaults.colors(checkedColor = EmeraldGreenPrimary)
                        )
                    }

                    // Surprise Gift option
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            Icon(Icons.Default.CardGiftcard, contentDescription = null, tint = Color(0xFFC2185B))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "هدية مفاجأة لمستلم آخر", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                Text(text = "تسليم أنيق دون إبلاغ المستلم المسبق مع كرت إهداء", fontSize = 11.sp, color = MaterialTheme.colorScheme.outline)
                            }
                        }
                        Checkbox(
                            checked = isSurpriseGift,
                            onCheckedChange = onIsSurpriseGiftChange,
                            colors = CheckboxDefaults.colors(checkedColor = EmeraldGreenPrimary)
                        )
                    }

                    AnimatedVisibility(visible = isSurpriseGift) {
                        Column(modifier = Modifier.padding(top = 8.dp)) {
                            OutlinedTextField(
                                value = giftRecipientName,
                                onValueChange = onGiftRecipientNameChange,
                                label = { Text("اسم المستلم للهدية") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = giftRecipientPhone,
                                onValueChange = onGiftRecipientPhoneChange,
                                label = { Text("رقم هاتف المستلم (لتنسيق التوصيل)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            )
                        }
                    }
                }
            }
        }

        // 7. Estimated goods price
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                OutlinedTextField(
                    value = estimatedGoodsPrice,
                    onValueChange = onEstimatedGoodsPriceChange,
                    label = { Text("القيمة التقريبية للمشتريات/الأغراض (ريال يمني - اختياري)") },
                    placeholder = { Text("مثال: 5000") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }

        // 8. Cost Breakdown & Submit
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "رسوم التوصيل والخدمة الأساسية:", fontSize = 13.sp)
                        Text(text = "$baseDeliveryFee ريال يمني", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }

                    if (isUrgent) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "رسوم التوصيل الطارئ (VIP):", fontSize = 13.sp, color = AmberAccentSecondary)
                            Text(text = "+500 ريال", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = AmberAccentSecondary)
                        }
                    }

                    val goodsVal = estimatedGoodsPrice.toIntOrNull() ?: 0
                    if (goodsVal > 0) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "قيمة الأغراض التقديرية:", fontSize = 13.sp)
                            Text(text = "$goodsVal ريال", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "الإجمالي التقديري:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = EmeraldGreenPrimary
                        )
                        Text(
                            text = "${totalFee + goodsVal} ريال يمني",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = EmeraldGreenPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = onSubmit,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EmeraldGreenPrimary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("submit_custom_order_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "تأكيد وإرسال الطلب للمندوب فوراً 🚀",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
