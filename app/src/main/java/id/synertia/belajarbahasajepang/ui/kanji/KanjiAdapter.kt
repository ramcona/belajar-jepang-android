package id.synertia.belajarbahasajepang.ui.kanji

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.synertia.belajarbahasajepang.data.model.Kanji
import id.synertia.belajarbahasajepang.databinding.ItemKanjiBinding
import java.util.Locale

class KanjiAdapter(var datas:ArrayList<Kanji>) : RecyclerView.Adapter<KanjiAdapter.Holder>(),
    Filterable  {


    var allDatas: ArrayList<Kanji> =  datas
    var listDataFiltered: ArrayList<Kanji> = datas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemKanjiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listDataFiltered[position])
    }

    fun reset() {
        allDatas.clear()
        listDataFiltered.clear()
    }

    fun add(newData:List<Kanji>) {
        reset()
        allDatas.addAll(newData)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return listDataFiltered.size
    }


    class Holder(var item: ItemKanjiBinding): RecyclerView.ViewHolder(item.root) {


        val context: Context = itemView.context

        fun bind(data: Kanji) {
            item.tvKanji.text = data.kanji
            item.tvDesc.text = data.arti
            item.tvKunyomi.text = data.kunyomi
            item.tvOnyomi.text = data.onyomi
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
                        listOf(map.kanji, map.kunyomi, map.onyomi, map.arti).any {
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
                listDataFiltered = results.values as java.util.ArrayList<Kanji>
                notifyDataSetChanged()
            }
        }
    }

}