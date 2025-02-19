package id.synertia.belajarbahasajepang.ui.main

import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import id.synertia.belajarbahasajepang.base.BaseActivity
import id.synertia.belajarbahasajepang.data.model.Level
import id.synertia.belajarbahasajepang.databinding.ActivityMainBinding
import id.synertia.belajarbahasajepang.helper.Go
import id.synertia.belajarbahasajepang.helper.viewBinding
import id.synertia.belajarbahasajepang.ui.subMenu.SubMenuActivity

class MainActivity : BaseActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    var level: Level = Level()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        removeActionBar()

        action()

    }

    override fun onResume() {
        super.onResume()
        animateFadeInFromBottom(binding.tvTitle1)
        animateFadeInFromBottom(binding.tvTitle2)
        animateLinearLayoutFromBottom(binding.linN4)
        animateLinearLayoutFromBottom(binding.linN5)
    }

    private fun action() {
        binding.linN5.setOnClickListener {
            val level = Level()
            level.id = "5f0b72ebb3ed3"
            level.desc = "Pemula (初心者 / Shoshinsha)"
            level.name = "N5"
            Go(this).move(SubMenuActivity::class.java, data = level)
        }

        binding.linN4.setOnClickListener {
            val level = Level()
            level.id = "5f31f64f5a55e"
            level.desc = "Menengah Dasar (初級 / Shokyuu)"
            level.name = "N4"


            Go(this).move(SubMenuActivity::class.java, data = level)
        }
    }

    private fun animateFadeInFromBottom(textView: TextView, duration: Long = 1000) {
        textView.alpha = 0f
        textView.translationY = 100f  // Start position (100px below)

        textView.animate()
            .alpha(1f)  // Fade in
            .translationY(0f)  // Move to original position
            .setDuration(duration)
            .setInterpolator(AccelerateDecelerateInterpolator())  // Smooth animation
            .start()
    }

    private fun animateLinearLayoutFromBottom(linearLayout: LinearLayout, duration: Long = 1000) {
        linearLayout.alpha = 0f
        linearLayout.translationY = 100f  // Start position (100px below)

        linearLayout.animate()
            .alpha(1f)  // Fade in
            .translationY(0f)  // Move to original position
            .setDuration(duration)
            .setInterpolator(AccelerateDecelerateInterpolator())  // Smooth animation
            .start()
    }

}