package com.vikas.userprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun chatScreen(mainViewModel: MainViewModel) {
Column {

    Button(onClick = { mainViewModel.getMychat.value = true }) {
        Text(text = "Fetch")
    }
    if (mainViewModel.getMychat.value) {
        mainViewModel.getMessage()
        LazyColumn() {
            items(mainViewModel.listOfMessage.value){
                Card (){
                    Text(text = it.message)
                }
            }
        }
    }
}

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(value = mainViewModel.text.value,
                onValueChange = {
                    mainViewModel.text.value = it
                                mainViewModel.msg.value = mainViewModel.text.value},
                label = { Text(text = "Text" )}
            )

            Button(onClick = {mainViewModel.sendmessage()
            mainViewModel.text.value = ""}) {
                Text(text = "Send")
            }
        }
    }
