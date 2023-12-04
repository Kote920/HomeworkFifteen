package com.example.examfourr.chat

import android.content.Context
import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException
import java.io.InputStream

class ChatsViewModel: ViewModel(){




    fun parseJson(jsonString: String): List<Chat>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val listType = Types.newParameterizedType(List::class.java, Chat::class.java)
        val adapter: JsonAdapter<List<Chat>> = moshi.adapter(listType)

        return adapter.fromJson(jsonString)

    }





}