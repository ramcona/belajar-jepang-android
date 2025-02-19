package id.synertia.belajarbahasajepang.ui.kosa.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.synertia.belajarbahasajepang.data.model.Kosa
import id.synertia.belajarbahasajepang.databinding.ItemKosaBinding
import java.util.Locale

class KosaAdapter(var datas:ArrayList<Kosa>, var callback:Holder.Callback? = null) : RecyclerView.Adapter<KosaAdapter.Holder>(),
    Filterable  {


    var allDatas: ArrayList<Kosa> =  datas
    var listDataFiltered: ArrayList<Kosa> = datas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemKosaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listDataFiltered[position], callback)
    }

    fun reset() {
        allDatas.clear()
        listDataFiltered.clear()
    }

    fun add(newData:List<Kosa>) {
        reset()
        allDatas.addAll(newData)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return listDataFiltered.size
    }


    class Holder(var item: ItemKosaBinding): RecyclerView.ViewHolder(item.root) {


        val context: Context = itemView.context

        fun bind(data: Kosa, callback: Callback?) {
            item.tvArti.text = data.arti
            item.tvKanji.text = data.kanji
            item.tvHiragana.text = data.hiragana
            item.tvRomanji.text = data.romanji

            item.tvArti.rootView.setOnClickListener {
                callback?.onSelected(data)
            }
        }

        interface Callback {
            fun onSelected(data: Kosa)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            @SuppressLint("DefaultLocale")
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                val constraintStr = constraint?.toString()?.lowercase(Locale.getDefault()) ?: ""

                if (constraintStr.isNotEmpty()) {
                    val result2 = allDatas.filter { map ->
                        listOf(map.arti, map.kanji, map.romanji, map.hiragana).any {
                            it.lowercase(Locale.getDefault()).contains(constraintStr)
                        }
                    }
                    results.values = ArrayList(result2)
                } else {
                    results.values = allDatas
                }

                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                listDataFiltered = results.values as java.util.ArrayList<Kosa>
                notifyDataSetChanged()
            }
        }
    }

}