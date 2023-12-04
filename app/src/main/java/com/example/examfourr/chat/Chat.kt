package com.example.examfourr.chat

data class Chat(
    var id: Int,
    var image: String?,
    var owner: String,
    var last_message: String,
    var last_active: String,
    var unread_messages: Int,
    var is_typing: Boolean,
    var laste_message_type: String
) {
}
enum class MessageType{
    TEXT,
    VOICE,
    FILE
}