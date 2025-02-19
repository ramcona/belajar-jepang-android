package id.synertia.belajarbahasajepang.ui.bunpo.list

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import id.synertia.belajarbahasajepang.R
import id.synertia.belajarbahasajepang.base.BaseActivity
import id.synertia.belajarbahasajepang.data.model.Bunpo
import id.synertia.belajarbahasajepang.data.model.Penjelasan
import id.synertia.belajarbahasajepang.databinding.ActivityDetailBunpoBinding
import id.synertia.belajarbahasajepang.databinding.ActivityDetailPenjelasanBinding
import id.synertia.belajarbahasajepang.extention.getParcelableExtras
import id.synertia.belajarbahasajepang.extention.loadResponsiveHtml
import id.synertia.belajarbahasajepang.helper.viewBinding

class DetailBunpoActivity : BaseActivity() {
    private val binding by viewBinding(ActivityDetailBunpoBinding::inflate)

    var data: Bunpo = Bunpo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        data = intent.getParcelableExtras(Bunpo())

        setToolbar("Bunpo", binding.toolbar, isWhite = true)

        binding.tvTitle.text = data.judul
        binding.tvDesc.isVisible = false
        binding.webview.loadResponsiveHtml(data.penjelasan)

        binding.webview.setBackgroundColor(Color.WHITE)

    }
}