package id.synertia.belajarbahasajepang.base

import android.app.Application
import android.os.StrictMode
import android.view.View
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.HiltAndroidApp
import id.synertia.belajarbahasajepang.helper.Helper
import id.synertia.belajarbahasajepang.helper.SharedPref
import id.synertia.belajarbahasajepang.ui.sharedView.LoadingDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(this@App) {}
        }


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