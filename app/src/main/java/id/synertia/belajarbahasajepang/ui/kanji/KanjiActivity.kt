package id.synertia.belajarbahasajepang.ui.kanji

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.reflect.TypeToken
import id.synertia.belajarbahasajepang.base.App.Companion.pref
import id.synertia.belajarbahasajepang.base.BaseActivity
import id.synertia.belajarbahasajepang.data.model.Kanji
import id.synertia.belajarbahasajepang.data.model.Level
import id.synertia.belajarbahasajepang.databinding.ActivityKanjiBinding
import id.synertia.belajarbahasajepang.extention.getParcelableExtras
import id.synertia.belajarbahasajepang.extention.onTextChanged
import id.synertia.belajarbahasajepang.helper.viewBinding
import id.synertia.belajarbahasajepang.networkUtils.Status

class KanjiActivity : BaseActivity() {
    private val binding by viewBinding(ActivityKanjiBinding::inflate)
    private val viewModel: KanjiViewModel by viewModels()

    private val datasAdapter: KanjiAdapter by lazy {
        KanjiAdapter(ArrayList())
    }

    var level: Level = Level()
    var key = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadBanner(binding.adView)

        level = intent.getParcelableExtras(Level())

        setToolbar("Kanji", binding.toolbar, isWhite = true)
        binding.toolbar.toolbarSubtitle.isVisible = true
        binding.toolbar.toolbarSubtitle.text = level.name

        key = "kanji_${level.id}"


        setupUI()
        setupObserver()
        actionView()

        loadData()

    }

    private fun loadData() {
        val datas  = pref.loadListData(key, object : TypeToken<List<Kanji>>() {})
        if (datas.isNullOrEmpty() ) {
            viewModel.get(level.id)
        }else {
            datasAdapter.add(datas)
        }

    }


    private fun setupUI() {

        binding.rvData.apply {
            layoutManager = GridLayoutManager(this@KanjiActivity, 2)
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