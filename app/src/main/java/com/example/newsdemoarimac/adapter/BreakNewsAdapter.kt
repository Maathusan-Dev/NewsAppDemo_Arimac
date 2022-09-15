package com.example.newsdemoarimac.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdemoarimac.databinding.BreakNewsAdapterLayoutBinding

class BreakNewsAdapter: RecyclerView.Adapter<BreakNewsAdapter.ViewHolder>() {
    private lateinit var binding:BreakNewsAdapterLayoutBinding
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = BreakNewsAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.onBind(position)
    }

    override fun getItemCount(): Int = 5
}