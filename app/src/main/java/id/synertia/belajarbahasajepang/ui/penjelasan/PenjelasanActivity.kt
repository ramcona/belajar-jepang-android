package id.synertia.belajarbahasajepang.ui.penjelasan

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.reflect.TypeToken
import id.synertia.belajarbahasajepang.R
import id.synertia.belajarbahasajepang.base.App.Companion.pref
import id.synertia.belajarbahasajepang.base.BaseActivity
import id.synertia.belajarbahasajepang.data.model.Level
import id.synertia.belajarbahasajepang.data.model.Penjelasan
import id.synertia.belajarbahasajepang.databinding.ActivityPenjelasanBinding
import id.synertia.belajarbahasajepang.extention.getParcelableExtras
import id.synertia.belajarbahasajepang.extention.onTextChanged
import id.synertia.belajarbahasajepang.helper.viewBinding
import id.synertia.belajarbahasajepang.networkUtils.Status

class PenjelasanActivity : BaseActivity() {
    private val binding by viewBinding(ActivityPenjelasanBinding::inflate)
    private val viewModel:PenjelasanViewModel by viewModels()

    private val datasAdapter: PenjelasanAdapter by lazy {
        PenjelasanAdapter(ArrayList())
    }

    var level:Level = Level()
    var key = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        level = intent.getParcelableExtras(Level())

        setToolbar("Penjelasan", binding.toolbar, isWhite = true)
        binding.toolbar.toolbarSubtitle.isVisible = true
        binding.toolbar.toolbarSubtitle.text = level.name

        key = "penjelasan_${level.id}"


        setupUI()
        setupObserver()
        actionView()

        loadData()

    }

    private fun loadData() {
        val datas  = pref.loadListData(key, object : TypeToken<List<Penjelasan>>() {})
        if (datas.isNullOrEmpty() ) {
            viewModel.get(level.id)
        }else {
            datasAdapter.add(datas)
        }

    }


    private fun setupUI() {

        binding.rvData.apply {
            layoutManager = LinearLayoutManager(this@PenjelasanActivity)
            adapter = datasAdapter
        }
    }

    private fun actionView() {

        binding.swipe.setOnRefreshListener {
            datasAdapter.reset()
            loadData()

            binding.swipe.isRefreshing = false
        }

        binding.edtSearch.onTextChanged {
        }

        binding.edtSearch.onTextChanged {
            datasAdapter.filter.filter(it)
        }

        binding.edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.edtSearch.text.toString()
                datasAdapter.filter.filter(query)
            }
            true
        }


    }

    private fun setupObserver() {
        viewModel.response.observe(this) { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    removeLoading(binding.shimmer)
                    it.data?.let {datas ->
                        datasAdapter.add(datas)

                        if (datas.isNotEmpty()) pref.saveListData(key, datas)

                        binding.viewNoData.isVisible = (datas.isEmpty())
                    }
                }

                Status.ERROR -> {
                    removeLoading(binding.shimmer)
                    binding.viewNoData.isVisible = true

                }

                Status.LOADING -> {
                    showLoading(binding.shimmer)
                    binding.viewNoData.isVisible = false
                }
            }
        }
    }
}