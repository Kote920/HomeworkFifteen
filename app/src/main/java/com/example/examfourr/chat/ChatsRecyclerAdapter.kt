package com.example.examfourr.chat

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examfourr.databinding.ItemRecyclerviewFileBinding
import com.example.examfourr.databinding.ItemRecyclerviewTextBinding
import com.example.examfourr.databinding.ItemRecyclerviewVoiceBinding

class ChatsRecyclerAdapter(private val context: Context) :
    ListAdapter<Chat, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem == newItem
        }

    }) {



    companion object {
        const val TEXT_TYPE = 1
        const val VOICE_TYPE = 2
        const val FILE_TYPE = 3

    }

    override fun getItemViewType(position: Int): Int {
        if (currentList[position].lastMessageType == "text") {
            return TEXT_TYPE
        } else if (currentList[position].lastMessageType == "voice") {
            return VOICE_TYPE

        } else {
            return FILE_TYPE
        }
    }


    inner class TextViewHolder(private val binding: ItemRecyclerviewTextBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {
                binding.tvName.text = item.owner
                binding.tvMessage.text = item.lastMessage
                binding.tvTime.text = item.lastActive
                typingManager(item, binding.ivDots)
                unreadsManager(
                    item,
                    binding.tvUnreads,
                    binding.tvName,
                    binding.tvMessage,
                    binding.tvTime
                )
                imageManagement(item.image, binding.ivPic)


            }


        }


    }

    inner class FileViewHolder(private val binding: ItemRecyclerviewFileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {
                binding.tvName.text = item.owner
                binding.tvMessage.text = item.lastMessage
                binding.tvTime.text = item.lastActive
                typingManager(item, binding.ivDots)
                unreadsManager(
                    item,
                    binding.tvUnreads,
                    binding.tvName,
                    binding.tvMessage,
                    binding.tvTime
                )
                imageManagement(item.image, binding.ivPic)
            }


        }


    }

    inner class VoiceViewHolder(private val binding: ItemRecyclerviewVoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {

                binding.tvName.text = item.owner
                binding.tvMessage.text = item.lastMessage
                binding.tvTime.text = item.lastActive
                typingManager(item, binding.ivDots)
                unreadsManager(
                    item,
                    binding.tvUnreads,
                    binding.tvName,
                    binding.tvMessage,
                    binding.tvTime
                )
                imageManagement(item.image, binding.ivPic)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TEXT_TYPE) {
            return TextViewHolder(
                ItemRecyclerviewTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else if (viewType == VOICE_TYPE) {
            return VoiceViewHolder(
                ItemRecyclerviewVoiceBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        } else {
            return FileViewHolder(
                ItemRecyclerviewFileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextViewHolder) {
            holder.bind()
        } else if (holder is VoiceViewHolder) {
            holder.bind()

        } else if (holder is FileViewHolder) {
            holder.bind()
        }
    }

    private fun typingManager(item: Chat, dots: AppCompatImageView) {
        if (item.isTyping == true) {
            dots.visibility = View.VISIBLE
        }
    }

    private fun unreadsManager(
        item: Chat,
        tv: AppCompatTextView,
        tvOwner: AppCompatTextView,
        tvLastMEssage: AppCompatTextView,
        tvTime: AppCompatTextView
    ) {
        if (item.unreadMessages > 0) {
            tv.text = item.unreadMessages.toString()
            tvOwner.setTextColor(Color.parseColor("#FFFFFF"))
            tvLastMEssage.setTextColor(Color.parseColor("#FFFFFF"))
            tvTime.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            tv.visibility = View.GONE
            tvOwner.setTextColor(Color.parseColor("#96A7AF"))
            tvLastMEssage.setTextColor(Color.parseColor("#96A7AF"))
            tvTime.setTextColor(Color.parseColor("#96A7AF"))
        }

        if(item.isTyping){
            tv.visibility = View.GONE
        }
    }

    fun imageManagement(imageUrl: String? ,imageView: AppCompatImageView){
        imageUrl?.let {
            Glide.with(context)
                .load(imageUrl)
                .fitCenter()
                .into(imageView)
        }
        if(imageUrl.isNullOrEmpty()){
            Glide.with(context)
                .load("https://upload.wikimedia.org/wikipedia/commons/a/ac/Default_pfp.jpg")
                .fitCenter()
                .into(imageView)
        }
    }


}