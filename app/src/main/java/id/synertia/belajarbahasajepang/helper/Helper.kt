package id.synertia.belajarbahasajepang.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import java.io.ByteArrayOutputStream
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import android.util.Base64
import java.util.Date


object Helper {



    fun encodeImageToBase64(filePath: String): String {
        val bitmap = BitmapFactory.decodeFile(filePath)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArr = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArr, Base64.NO_WRAP)
    }


    fun toHexString(data: ByteArray): String{
        return data.asUByteArray().joinToString("") {
            it.toString(16).padStart(2, '0')
        }
    }

    fun fromHtml(source: String): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(source)
        }
    }


    //MAKE LOG RESUCE
    fun log(place: String = "", msg: String){
        //CHECK RELEASE MODE [if release log not show]

        if (!Validasi().isRelease()){
            if (place.isBlank()){
                Log.e("MGS", msg)
            }else{
                Log.e("MGS " + place, msg)
            }
        }

    }

    fun getCurrentDateTime():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    fun provideCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    fun provideTime():String{
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }


}