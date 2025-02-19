package id.synertia.belajarbahasajepang.ui.kosa.bab

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.synertia.belajarbahasajepang.data.model.BabKosa
import id.synertia.belajarbahasajepang.databinding.ItemSingleBinding
import id.synertia.belajarbahasajepang.helper.Go
import id.synertia.belajarbahasajepang.ui.bunpo.list.BunpoActivity
import id.synertia.belajarbahasajepang.ui.kosa.list.KosaActivity
import java.util.Locale

class BabKosaAdapter(var datas:ArrayList<BabKosa>) : RecyclerView.Adapter<BabKosaAdapter.Holder>(),
    Filterable  {


    var allDatas: ArrayList<BabKosa> =  datas
    var listDataFiltered: ArrayList<BabKosa> = datas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemSingleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listDataFiltered[position])
    }

    fun reset() {
        allDatas.clear()
        listDataFiltered.clear()
    }

    fun add(newData:List<BabKosa>) {
        reset()
        allDatas.addAll(newData)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return listDataFiltered.size
    }


    class Holder(var item: ItemSingleBinding): RecyclerView.ViewHolder(item.root) {


        val context: Context = itemView.context

        fun bind(data: BabKosa) {
            item.tvNama.text = data.nama

            item.tvDesc.rootView.setOnClickListener {
                Go(context).move(KosaActivity::class.java, data = data)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            @SuppressLint("DefaultLocale")
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                val result2 = java.util.ArrayList<BabKosa>()
                listDataFiltered = allDatas
                if (constraint != null) {
                    if ((listDataFiltered.size > 0)) {
                        for (map in listDataFiltered) {
                            if (map.nama.lowercase(Locale.getDefault()).contains(constraint.toString().lowercase(
                                    Locale.getDefault()))) {
                                result2.add(map)
                            }
                        }

                    }
                    results.values = result2
                }

                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                listDataFiltered = results.values as java.util.ArrayList<BabKosa>
                notifyDataSetChanged()
            }
        }
    }

}