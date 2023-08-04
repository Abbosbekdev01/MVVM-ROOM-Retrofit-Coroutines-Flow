package uz.abbosbek.crud_task_2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.abbosbek.crud_task_2.database.entity.CountryEntity
import uz.abbosbek.crud_task_2.databinding.ItemRvBinding

class RvAdapter(var list: ArrayList<CountryEntity> = ArrayList(), val rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(userEntity: CountryEntity, position: Int) {
            itemRvBinding.tvName.text = userEntity.countryName
            Picasso.get().load(userEntity.flagImage).into(itemRvBinding.flagView)

            itemRvBinding.root.setOnClickListener {
                rvClick.itemClick(userEntity, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }
}

interface RvClick {
    fun itemClick(userEntity: CountryEntity, position: Int)
}