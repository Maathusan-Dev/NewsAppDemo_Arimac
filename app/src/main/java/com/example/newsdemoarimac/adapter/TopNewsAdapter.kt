package com.example.newsdemoarimac.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdemoarimac.R
import com.example.newsdemoarimac.callBack.AdapterCallBack
import com.example.newsdemoarimac.databinding.CommonNewsAdapterLayoutBinding
import com.example.newsdemoarimac.extentions.loadImage
import com.example.newsdemoarimac.models.Article

class TopNewsAdapter(
    var newsList: List<Article>,
    var context: Context
) : RecyclerView.Adapter<TopNewsAdapter.ViewHolder>() {
    private lateinit var binding: CommonNewsAdapterLayoutBinding
    var callBack: AdapterCallBack? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val news = newsList[position]

            with(news) {
                binding.ivImageBg
                    .loadImage(
                        urlToImage,
                        ContextCompat
                            .getDrawable(
                                context,
                                R.drawable.ic_sample
                            )!!
                    )
                if (author != null) {
                    binding.tvBy.text = author
                }

                binding.tvTitle.text = title
                binding.tvDate.text = publishedAt
            }

            itemView.setOnClickListener {
                callBack?.onItemClick(news)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CommonNewsAdapterLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
        holder.setIsRecyclable(false)
    }


    override fun getItemCount(): Int = newsList.size
}