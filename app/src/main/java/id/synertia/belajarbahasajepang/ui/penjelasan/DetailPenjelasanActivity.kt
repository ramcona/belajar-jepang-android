package id.synertia.belajarbahasajepang.ui.penjelasan

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import id.synertia.belajarbahasajepang.R
import id.synertia.belajarbahasajepang.base.BaseActivity
import id.synertia.belajarbahasajepang.data.model.Level
import id.synertia.belajarbahasajepang.data.model.Penjelasan
import id.synertia.belajarbahasajepang.databinding.ActivityDetailPenjelasanBinding
import id.synertia.belajarbahasajepang.databinding.ActivitySubMenuBinding
import id.synertia.belajarbahasajepang.extention.getParcelableExtras
import id.synertia.belajarbahasajepang.extention.loadResponsiveHtml
import id.synertia.belajarbahasajepang.extention.parseHtml
import id.synertia.belajarbahasajepang.helper.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Arrays

class DetailPenjelasanActivity : BaseActivity() {
    private val binding by viewBinding(ActivityDetailPenjelasanBinding::inflate)

    var data: Penjelasan = Penjelasan()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(this@DetailPenjelasanActivity) {}
        }
        loadBanner(binding.adView)


        data = intent.getParcelableExtras(Penjelasan())

        setToolbar("Penjelasan", binding.toolbar, isWhite = true)

        binding.tvTitle.text = data.judul
        binding.tvDesc.isVisible = false
        binding.webview.loadResponsiveHtml(data.penjelasan)

        binding.webview.setBackgroundColor(Color.WHITE)
        RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("F1F574863C5D2487837E2B2901A88E41"))

        loadNativeAds()
    }

    private fun loadNativeAds() {
        val adLoader = AdLoader.Builder(this, "ca-app-pub-6929098745446472/6543593161")
            .forNativeAd { ad : NativeAd ->
                // Show the ad.
                Log.e("S", "Show the ad")
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Handle the failure.
                    Log.e("S", "Show the ad failed ${adError.message}")
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                // Methods in the NativeAdOptions.Builder class can be
                // used here to specify individual options settings.
                .build())
            .build()

        adLoader.loadAds(AdRequest.Builder().build(), 3)

    }
}