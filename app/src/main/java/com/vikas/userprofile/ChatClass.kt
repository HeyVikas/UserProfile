package com.vikas.userprofile

data class Message(
    var message: String = ""
)
    data class ArrayOfMessage(
        val messagelist : List<Message> = listOf()
    )

