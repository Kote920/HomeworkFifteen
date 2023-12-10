package com.example.examfourr.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examfourr.network.Network
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatsViewModel() : ViewModel() {


    private val _chatFlow = MutableStateFlow<List<Chat>>(emptyList())
    val chatsFlow: SharedFlow<List<Chat>> = _chatFlow.asSharedFlow()


    fun getChats() {
        viewModelScope.launch {
            val response = Network.chatsService().getChats()
            if (response.isSuccessful) {
                _chatFlow.value = response.body()!!
            }else{
                println("bla")
            }
        }
    }


}