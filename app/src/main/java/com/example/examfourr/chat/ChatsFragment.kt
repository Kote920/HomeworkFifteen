package com.example.examfourr.chat

import android.content.Context
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examfourr.BaseFragment
import com.example.examfourr.R
import com.example.examfourr.databinding.FragmentChatsBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream


class ChatsFragment : BaseFragment<FragmentChatsBinding>(FragmentChatsBinding::inflate) {

    private lateinit var adapter: ChatsRecyclerAdapter


    private val viewModel: ChatsViewModel by viewModels()
    private var filteredList: MutableList<Chat> = mutableListOf()
    private var originalList: MutableList<Chat> = mutableListOf()


    override fun setUp() {


        initItemRecycler()
        viewModel.getChats()

//
//        viewLifecycleOwner.lifecycleScope .launch{
//
//        }
//
//        CoroutineScope(Dispatchers.IO).launch {
//
//        }
    }



    private fun initItemRecycler() {
        adapter = ChatsRecyclerAdapter(requireContext())
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerView.adapter = adapter

    }

    override fun listeners() {




    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.chatsFlow.collect {
                    adapter.submitList(it.toMutableList())
                    originalList = it.toMutableList()

                }
            }
        }
    }


//    fun readJSONFromAsset(context: Context, fileName: String): String? {
//        return try {
//            val inputStream: InputStream = context.assets.open(fileName)
//            val size: Int = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            inputStream.close()
//            String(buffer, Charsets.UTF_8)
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//            null
//        }
//    }


}