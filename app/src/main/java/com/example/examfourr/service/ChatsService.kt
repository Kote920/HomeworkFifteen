package com.example.examfourr.service

import com.example.examfourr.chat.Chat
import retrofit2.Response
import retrofit2.http.GET

interface ChatsService {

        @GET("744fa574-a483-43f6-a1d7-c65c87b5d9b2")
    suspend fun getChats(): Response<List<Chat>>


}