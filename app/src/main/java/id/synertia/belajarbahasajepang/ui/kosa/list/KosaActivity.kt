package id.synertia.belajarbahasajepang.ui.kosa.list

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.reflect.TypeToken
import id.synertia.belajarbahasajepang.R
import id.synertia.belajarbahasajepang.base.App.Companion.pref
import id.synertia.belajarbahasajepang.base.BaseActivity
import id.synertia.belajarbahasajepang.data.model.BabKosa
import id.synertia.belajarbahasajepang.data.model.Kosa
import id.synertia.belajarbahasajepang.databinding.ActivityKosaBinding
import id.synertia.belajarbahasajepang.databinding.SheetBunpoBinding
import id.synertia.belajarbahasajepang.databinding.SheetKanjiBinding
import id.synertia.belajarbahasajepang.extention.getParcelableExtras
import id.synertia.belajarbahasajepang.extention.onTextChanged
import id.synertia.belajarbahasajepang.helper.viewBinding
import id.synertia.belajarbahasajepang.networkUtils.Status

class KosaActivity : BaseActivity() {
    private val binding by viewBinding(ActivityKosaBinding::inflate)
    private val viewModel: KosaViewModel by viewModels()
    var key = ""


    private val datasAdapter: KosaAdapter by lazy {
        KosaAdapter(ArrayList(), callback = object : KosaAdapter.Holder.Callback {
            override fun onSelected(data: Kosa) {
                showSheet(data)
            }

        })
    }

    private fun showSheet(data: Kosa) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bindingSheet: SheetKanjiBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.sheet_kanji, null, false)
        bottomSheetDialog.setContentView(bindingSheet.root)
        bottomSheetDialog.dismissWithAnimation = true

        bindingSheet.tvArti.text = data.arti
        bindingSheet.tvRomanji.text = data.romanji
        bindingSheet.tvHiragana.text = data.hiragana
        bindingSheet.tvKanji.text = data.kanji
        bottomSheetDialog.show()
    }


    var bab: BabKosa = BabKosa()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bab = intent.getParcelableExtras(BabKosa())
        key = "kosa_${bab.id}"

        setToolbar("Kosa", binding.toolbar, isWhite = true)
        binding.toolbar.toolbarSubtitle.isVisible = true
        binding.toolbar.toolbarSubtitle.text = bab.nama


        setupUI()
        setupObserver()
        actionView()
        loadData()
    }


    private fun setupUI() {

        binding.rvData.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = datasAdapter
        }
    }

    private fun loadData() {
        val datas  = pref.loadListData(key, object : TypeToken<List<Kosa>>() {})
        if (datas.isNullOrEmpty() ) {
            viewModel.get(bab.id)
        }else {
            datasAdapter.add(datas)
        }

    }

    private fun actionView() {

        binding.swipe.setOnRefreshListener {
            datasAdapter.reset()
            viewModel.get(bab.id)

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