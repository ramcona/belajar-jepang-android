package id.synertia.belajarbahasajepang.ui.penjelasan

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
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

class DetailPenjelasanActivity : BaseActivity() {
    private val binding by viewBinding(ActivityDetailPenjelasanBinding::inflate)

    var data: Penjelasan = Penjelasan()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        data = intent.getParcelableExtras(Penjelasan())

        setToolbar("Penjelasan", binding.toolbar, isWhite = true)

        binding.tvTitle.text = data.judul
        binding.tvDesc.isVisible = false
        binding.webview.loadResponsiveHtml(data.penjelasan)

        binding.webview.setBackgroundColor(Color.WHITE)

    }
}