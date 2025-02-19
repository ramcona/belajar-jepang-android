package id.synertia.belajarbahasajepang.extention

import java.text.NumberFormat
import java.util.*

fun Int.formatRupiah(): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(this).replace(",00", "")
}

fun Int.formatViews(): String {
    val value = this
    return when {
        value >= 10000000 -> "${value / 1000000}.0M"
        value >= 1000000 -> {
            val decimalPart = (value % 1000000) / 100000
            "${value / 1000000}.${decimalPart}M"
        }
        value >= 10000 -> "${value / 1000}.0k"
        value >= 1000 -> {
            val decimalPart = (value % 1000) / 100
            "${value / 1000}.${decimalPart}k"
        }
        else -> value.toString()
    }
}