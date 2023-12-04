package com.example.examfourr.chat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.examfourr.BaseFragment
import com.example.examfourr.R
import com.example.examfourr.databinding.FragmentChatsBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException
import java.io.InputStream


class ChatsFragment : BaseFragment<FragmentChatsBinding>(FragmentChatsBinding::inflate) {

    private lateinit var adapter: ChatsRecyclerAdapter
    private var  chats: List<Chat>? = null


    private val viewModel: ChatsViewModel by viewModels()



    override fun setUp() {

        val jsonString = readJSONFromAsset(requireContext(), "data.json")
        chats = parseJson(jsonString!!)
        initItemRecycler()
    }



    private fun initItemRecycler() {
        adapter = ChatsRecyclerAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerView.adapter = adapter
        adapter.apply {  submitList(chats)
            }
    }

    override fun listeners() {
        print("@")
    }

    fun parseJson(jsonString: String): List<Chat>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val listType = Types.newParameterizedType(List::class.java, Chat::class.java)
        val adapter: JsonAdapter<List<Chat>> = moshi.adapter(listType)

        return adapter.fromJson(jsonString)

    }



    fun readJSONFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

}