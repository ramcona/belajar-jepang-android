package id.synertia.belajarbahasajepang.base

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import dagger.hilt.android.AndroidEntryPoint
import id.synertia.belajarbahasajepang.R
import id.synertia.belajarbahasajepang.databinding.LayoutToolbarBinding
import id.synertia.belajarbahasajepang.ui.sharedView.InformationDialog
import id.synertia.belajarbahasajepang.ui.sharedView.LoadingDialog


@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    private var mAdView: AdView? = null


    fun removeActionBar() {
        supportActionBar?.hide()

        val window: Window = window
        val decorView: View = window.decorView
        val wic = WindowInsetsControllerCompat(window, decorView)


        wic.isAppearanceLightStatusBars = false  // true or false as desired.
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

    }



    fun setToolbar(title: String, toolbarTitle: LayoutToolbarBinding, shareCallback: ShareCallback? = null, isWhite:Boolean = false) {
        removeActionBar()
        if (shareCallback != null){
            toolbarTitle.toolbarShare.visibility = View.VISIBLE
            toolbarTitle.toolbarShare.setOnClickListener {
                shareCallback.onClicked()
            }
        }else{
            toolbarTitle.toolbarShare.visibility = View.GONE
        }

//        if(isWhite) {
//            toolbarTitle.toolbarBack.setImageResource(R.drawable.ic)
//            toolbarTitle.toolbarTitle.setTextColor(ContextCompat.getColor(this, R.color.white))
//        }

        toolbarTitle.toolbarTitle.text = title
        toolbarTitle.toolbarBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        //mengubah warna status bar
    }

    fun removeLoading(shimmer: ShimmerFrameLayout? = null) {
        if (shimmer != null) {
            shimmer.isVisible = false
            shimmer.stopShimmer()
        }else {
            App.loadingDialog.dismiss()
        }
    }

    fun showLoading(shimmer: ShimmerFrameLayout? = null) {

        if (shimmer != null) {
            shimmer.isVisible = true
            shimmer.showShimmer(true)
        }else {
            App.loadingDialog = LoadingDialog(this)
            App.loadingDialog.show()
        }
    }
//
    fun showMessageDialog(title:String, message:String, callback: InformationDialog.Callback? = null) {
        InformationDialog(this, title, message,callback).show()
    }

    fun openGoogleMap(latitude: Double, longitude: Double) {
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude"))
        mapIntent.setPackage("com.google.android.apps.maps")

        // Check if Google Maps is installed.
        if (packageManager.resolveActivity(mapIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            startActivity(mapIntent)
        } else {
            // Google Maps is not installed, so open the location in a web browser.
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=$latitude,$longitude"))
            startActivity(webIntent)
        }
    }

    fun loadBanner(adView:AdView) {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        mAdView = adView

    }

    override fun onPause() {
        if (mAdView != null) {
            mAdView!!.pause()
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        if (mAdView != null) {
            mAdView!!.resume()
        }
    }

    override fun onDestroy() {
        if (mAdView != null) {
            mAdView!!.destroy()
        }
        super.onDestroy()
    }

}

interface ShareCallback {
    fun onClicked()
}