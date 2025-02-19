package id.synertia.belajarbahasajepang.extention

import java.text.NumberFormat
import java.util.Locale

fun Long.formatRupiah(): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(this).replace(",00", "")
}