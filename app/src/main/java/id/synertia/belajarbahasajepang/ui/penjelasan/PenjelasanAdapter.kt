package id.synertia.belajarbahasajepang.ui.penjelasan

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.synertia.belajarbahasajepang.data.model.Penjelasan
import id.synertia.belajarbahasajepang.databinding.ItemPenjelasanBinding
import id.synertia.belajarbahasajepang.extention.parseHtml
import id.synertia.belajarbahasajepang.helper.Go
import java.util.Locale

class PenjelasanAdapter(var datas:ArrayList<Penjelasan>) : RecyclerView.Adapter<PenjelasanAdapter.Holder>(),
    Filterable  {


    var allDatas: ArrayList<Penjelasan> =  datas
    var listDataFiltered: ArrayList<Penjelasan> = datas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemPenjelasanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listDataFiltered[position])
    }

    fun reset() {
        allDatas.clear()
        listDataFiltered.clear()
    }

    fun add(newData:List<Penjelasan>) {
        reset()
        allDatas.addAll(newData)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return listDataFiltered.size
    }


    class Holder(var item: ItemPenjelasanBinding): RecyclerView.ViewHolder(item.root) {


        val context: Context = itemView.context

        fun bind(data: Penjelasan) {
            item.tvNama.text = data.judul
            item.tvDesc.text = data.penjelasan.parseHtml()

            item.tvDesc.rootView.setOnClickListener {
                Go(context).move(DetailPenjelasanActivity::class.java, data = data)
            }
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
                        listOf(map.judul, map.penjelasan).any {
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
                listDataFiltered = results.values as java.util.ArrayList<Penjelasan>
                notifyDataSetChanged()
            }
        }
    }

}