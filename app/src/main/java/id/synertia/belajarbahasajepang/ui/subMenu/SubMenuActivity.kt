package id.synertia.belajarbahasajepang.ui.subMenu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        level = intent.getParcelableExtras(Level())

        setToolbar(level.name, binding.toolbar, isWhite = true)
        binding.toolbar.toolbarSubtitle.isVisible = true
        binding.toolbar.toolbarSubtitle.text = level.desc

        actionView()
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