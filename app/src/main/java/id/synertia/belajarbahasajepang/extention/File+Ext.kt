package id.synertia.belajarbahasajepang.extention

import android.webkit.MimeTypeMap
import java.io.File

fun File.getMimeType(): String? {
    val extension = MimeTypeMap.getFileExtensionFromUrl(this.absolutePath)
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
}