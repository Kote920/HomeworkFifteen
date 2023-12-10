package com.example.examfourr.chat

import com.squareup.moshi.Json

data class Chat(
    var id: Int,
    var image: String?,
    var owner: String,
    @Json(name = "last_message")
    var lastMessage: String,
    @Json(name = "last_active")
    var lastActive: String,
    @Json(name = "unread_messages")
    var unreadMessages: Int,
    @Json(name = "is_typing")
    var isTyping: Boolean,
    @Json(name = "laste_message_type")
    var lastMessageType: String
) {
}
enum class MessageType{
    TEXT,
    VOICE,
    FILE
}