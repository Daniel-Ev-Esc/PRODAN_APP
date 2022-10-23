package com.example.prodan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prodan.data.Data
import com.example.prodan.databinding.PetItemBinding

class adapter (val context: Context, var data: List<Data>,
               private val funcionX : (Data) ->Unit ) :
    RecyclerView.Adapter<adapter.ViewHolder>()
{
    // Cambio de filtros
    // Recibe una nueva lista y notifica que cambió
    fun filterList(filterList: List<Data>){
        data = filterList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: PetItemBinding, funcionZ: (Int) -> Unit  ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                funcionZ(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PetItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view) {
            funcionX(data[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textViewId.text = data[position].id.toString()
            textViewName.text = data[position].attributes.name
            if (data[position].attributes.male)
                imageViewGender.setImageResource(R.drawable.boy)
            else
                imageViewGender.setImageResource(R.drawable.girl)
        }
           Glide.with(context)
               .load(data[position].attributes.image?.data?.attributes?.formats?.small?.url)
               .into(holder.binding.imageViewPhoto)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}