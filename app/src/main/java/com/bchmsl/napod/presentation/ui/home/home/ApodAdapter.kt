package com.bchmsl.napod.presentation.ui.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bchmsl.napod.common.extensions.loadImage
import com.bchmsl.napod.databinding.LayoutApodBinding
import com.bchmsl.napod.presentation.ui.home.model.ApodUiModel

class ApodAdapter : ListAdapter<ApodUiModel, ApodAdapter.ApodViewHolder>(apodCallback) {

    var onApodClick: ((ApodUiModel) -> Unit)? = null

    inner class ApodViewHolder(private val binding: LayoutApodBinding) : ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            binding.apply {
                ivImage.loadImage(currentItem.url)
                tvTitle.text = currentItem.title
                tvDate.text = currentItem.date
                tvDescription.text = currentItem.explanation.plus("...")
                tvAuthor.text = currentItem.author
                root.setOnClickListener {
                    onApodClick?.invoke(currentItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder =
        ApodViewHolder(
            LayoutApodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        holder.bind()
    }

    companion object{
        val apodCallback = object : DiffUtil.ItemCallback<ApodUiModel>(){
            override fun areItemsTheSame(oldItem: ApodUiModel, newItem: ApodUiModel): Boolean {
                return oldItem.explanation == newItem.explanation
            }

            override fun areContentsTheSame(oldItem: ApodUiModel, newItem: ApodUiModel): Boolean {
                return oldItem == newItem
            }

        }
    }

}