package id.synertia.belajarbahasajepang.ui.bunpo.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.synertia.belajarbahasajepang.data.model.Bunpo
import id.synertia.belajarbahasajepang.databinding.ItemSingleBinding
import id.synertia.belajarbahasajepang.helper.Go
import java.util.Locale

class BunpoAdapter(var datas:ArrayList<Bunpo>, var callback:Holder.Callback? = null) : RecyclerView.Adapter<BunpoAdapter.Holder>(),
    Filterable  {


    var allDatas: ArrayList<Bunpo> =  datas
    var listDataFiltered: ArrayList<Bunpo> = datas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemSingleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listDataFiltered[position], callback)
    }

    fun reset() {
        allDatas.clear()
        listDataFiltered.clear()
    }

    fun add(newData:List<Bunpo>) {
        reset()
        allDatas.addAll(newData)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return listDataFiltered.size
    }


    class Holder(var item: ItemSingleBinding): RecyclerView.ViewHolder(item.root) {


        val context: Context = itemView.context

        fun bind(data: Bunpo, callback: Callback?) {
            item.tvNama.text = data.judul

            item.tvDesc.rootView.setOnClickListener {
//                callback?.let {
//                    callback.onSelected(data)
//                }

                Go(context).move(DetailBunpoActivity::class.java, data = data)
            }
        }

        interface Callback {
            fun onSelected(data: Bunpo)
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
                listDataFiltered = results.values as java.util.ArrayList<Bunpo>
                notifyDataSetChanged()
            }
        }
    }

}