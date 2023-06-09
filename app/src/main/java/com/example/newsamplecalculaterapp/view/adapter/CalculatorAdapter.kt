package com.example.newsamplecalculaterapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsamplecalculaterapp.databinding.CalculateAdapterLayoutBinding
import com.example.newsamplecalculaterapp.model.dataclass.CalculatorData

class CalculatorAdapter:RecyclerView.Adapter<CalculatorAdapter.ViewHolderItemOne>() {

    inner class ViewHolderItemOne(private val binding: CalculateAdapterLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(items: CalculatorData){
            binding.apply {
                textView.text=items.result.toString()
            }
        }
    }

    private val differCallBack=object : DiffUtil.ItemCallback<CalculatorData>(){
        override fun areItemsTheSame(oldItem: CalculatorData, newItem: CalculatorData): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: CalculatorData, newItem: CalculatorData): Boolean {
            return oldItem==newItem
        }
    }

    val differ= AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemOne {
        return ViewHolderItemOne(binding = CalculateAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderItemOne, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}