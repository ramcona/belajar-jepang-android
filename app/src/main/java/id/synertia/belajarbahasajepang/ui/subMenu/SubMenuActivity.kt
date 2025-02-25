package id.synertia.belajarbahasajepang.ui.subMenu

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import id.synertia.belajarbahasajepang.R
import id.synertia.belajarbahasajepang.base.BaseActivity
import id.synertia.belajarbahasajepang.data.model.Level
import id.synertia.belajarbahasajepang.databinding.ActivityPenjelasanBinding
import id.synertia.belajarbahasajepang.databinding.ActivitySubMenuBinding
import id.synertia.belajarbahasajepang.extention.getParcelableExtras
import id.synertia.belajarbahasajepang.helper.Go
import id.synertia.belajarbahasajepang.helper.viewBinding
import id.synertia.belajarbahasajepang.ui.bunpo.bab.BabBunpoActivity
import id.synertia.belajarbahasajepang.ui.kanji.KanjiActivity
import id.synertia.belajarbahasajepang.ui.kosa.bab.BabKosaActivity
import id.synertia.belajarbahasajepang.ui.penjelasan.PenjelasanActivity
import id.synertia.belajarbahasajepang.ui.penjelasan.PenjelasanAdapter
import id.synertia.belajarbahasajepang.ui.penjelasan.PenjelasanViewModel

class SubMenuActivity : BaseActivity() {
    private val binding by viewBinding(ActivitySubMenuBinding::inflate)

    var level: Level = Level()

    private var mInterstitialAd: InterstitialAd? = null
    private final val TAG = "SubMenuActivity"
    private var openTime = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        level = intent.getParcelableExtras(Level())

        setToolbar(level.name, binding.toolbar, isWhite = true)
        binding.toolbar.toolbarSubtitle.isVisible = true
        binding.toolbar.toolbarSubtitle.text = level.desc

        actionView()

        loadAds()
    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-6929098745446472/4495722742", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError?.toString().toString())
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })

        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.")
            }
        }
    }

    override fun onResume() {
        super.onResume()

        openTime++
        if((openTime / 2) % 2 == 0) {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
        }

    }

    private fun actionView() {
        binding.linPenjelasan.setOnClickListener {
            Go(this).move(PenjelasanActivity::class.java, data = level)
        }

        binding.linBunpo.setOnClickListener {
            Go(this).move(BabBunpoActivity::class.java, data = level)
        }

        binding.linKanji.setOnClickListener {
            Go(this).move(KanjiActivity::class.java, data = level)
        }

        binding.linKosa.setOnClickListener {
            Go(this).move(BabKosaActivity::class.java, data = level)
        }
    }
}