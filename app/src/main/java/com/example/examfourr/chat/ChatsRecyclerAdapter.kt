package com.example.examfourr.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examfourr.databinding.ItemRecyclerviewFileBinding
import com.example.examfourr.databinding.ItemRecyclerviewTextBinding
import com.example.examfourr.databinding.ItemRecyclerviewVoiceBinding
import kotlin.io.path.fileVisitor

class ChatsRecyclerAdapter :   ListAdapter<Chat, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }

})





{


    companion object {
        const val TEXT_TYPE = 1
        const val VOICE_TYPE = 2
        const val FILE_TYPE = 3

    }

    override fun getItemViewType(position: Int): Int {
        if(currentList[position].laste_message_type == "text"){
            return TEXT_TYPE
        }else if(currentList[position].laste_message_type == "voice"){
            return VOICE_TYPE

        }else{
            return FILE_TYPE }
        }




    inner class TextViewHolder(private val binding: ItemRecyclerviewTextBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(){
            val item = currentList[adapterPosition]
            with(binding){
                binding.tvName.text = item.owner
                binding.tvMessage.text = item.last_message
                binding.tvTime.text = item.last_active


            }


        }



    }

    inner class FileViewHolder(private val binding: ItemRecyclerviewFileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(){
            val item = currentList[adapterPosition]
            with(binding){

            }


        }




    }
    inner class VoiceViewHolder(private val binding:ItemRecyclerviewVoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(){
            val item = currentList[adapterPosition]
            with(binding){

            }


        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TEXT_TYPE){
            return TextViewHolder(
                ItemRecyclerviewTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )}
        else if(viewType == VOICE_TYPE){
            return VoiceViewHolder(
                ItemRecyclerviewVoiceBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }else{
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
        if(holder is TextViewHolder){
            holder.bind()
        }else if(holder is VoiceViewHolder){
            holder.bind()

        }else if(holder is FileViewHolder){
            holder.bind()
        }
    }
}