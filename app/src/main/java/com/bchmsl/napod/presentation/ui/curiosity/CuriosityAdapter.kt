package com.bchmsl.napod.presentation.ui.curiosity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bchmsl.napod.common.extensions.loadImage
import com.bchmsl.napod.databinding.LayoutCuriosityBinding
import com.bchmsl.napod.presentation.ui.curiosity.model.CuriosityUiModel

class CuriosityAdapter :
    PagingDataAdapter<CuriosityUiModel, CuriosityAdapter.CuriosityViewHolder>(curiosityCallback) {
    inner class CuriosityViewHolder(private val binding: LayoutCuriosityBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(absoluteAdapterPosition)
            binding.apply {
                ivImage.loadImage(currentItem?.imgSrc ?: "")
                tvCamera.text = currentItem?.camera?.fullName
                tvCameraShort.text = currentItem?.camera?.name
                tvId.text = currentItem?.id.toString()
                tvEarthDate.text = currentItem?.earthDate
                tvSolarDay.text = currentItem?.sol.toString()
            }
        }
    }

    override fun onBindViewHolder(holder: CuriosityViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuriosityViewHolder {
        return CuriosityViewHolder(
            LayoutCuriosityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        val curiosityCallback = object : DiffUtil.ItemCallback<CuriosityUiModel>() {
            override fun areItemsTheSame(
                oldItem: CuriosityUiModel,
                newItem: CuriosityUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CuriosityUiModel,
                newItem: CuriosityUiModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}