package com.example.newsdemoarimac.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdemoarimac.databinding.CommonNewsAdapterLayoutBinding
import com.example.newsdemoarimac.models.Article

class TopNewsAdapter(
    var newsList: List<Article>
): RecyclerView.Adapter<TopNewsAdapter.ViewHolder>() {
    private lateinit var binding: CommonNewsAdapterLayoutBinding
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position:Int){
            val article = newsList[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CommonNewsAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = newsList.size
}