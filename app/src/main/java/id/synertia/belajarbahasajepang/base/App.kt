package id.synertia.belajarbahasajepang.base

import android.app.Application
import android.os.StrictMode
import android.view.View
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.HiltAndroidApp
import id.synertia.belajarbahasajepang.helper.Helper
import id.synertia.belajarbahasajepang.helper.SharedPref
import id.synertia.belajarbahasajepang.ui.sharedView.LoadingDialog

@HiltAndroidApp
class App:Application() {
    companion object {
        lateinit var pref: SharedPref
        var helper = Helper

        lateinit var app:App
        lateinit var loadingDialog: LoadingDialog

    }

    fun clearAppData(){
        pref.clearAll()
    }


    override fun onCreate() {
        super.onCreate()

//        FirebaseMessaging.getInstance().isAutoInitEnabled = true


        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        pref = SharedPref(this)
        app = this
        firebaseToken()
    }

    fun firebaseToken() {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val fcmToken = task.result
//            if (fcmToken.isNotEmpty()) pref.fcmToken = fcmToken
//
//        })
    }


    fun showSnackbar(view: View, message:String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

}