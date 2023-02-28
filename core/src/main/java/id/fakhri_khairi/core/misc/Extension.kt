package id.fakhri_khairi.core.misc

import java.util.Locale

fun Float.convertToPrice() : String {
    return if (Locale.getDefault() == Locale("in","ID")) {
        "Rp.${this * 15.264}"
    } else {
        "$this USD"
    }
}