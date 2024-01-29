package com.vikas.userprofile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editScreen(nav: NavController, viewModel: MainViewModel) {


    Column {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            TopAppBar(
                title = { Text(text = "Enter Your Details") })


            TextField(viewModel.name.value,
                onValueChange = { viewModel.name.value = it
                                viewModel.finalname.value = viewModel.name.value},
                label = { Text("Name ") })
            TextField(viewModel.gmailId.value,
                onValueChange = { viewModel.gmailId.value = it
                                viewModel.finalgmailId.value = viewModel.gmailId.value},
                label = { Text("Gmail ID ") })
            TextField(viewModel.mobile.value,
                onValueChange = { viewModel.mobile.value = it
                                viewModel.finalmobile.value = viewModel.mobile.value},
                label = { Text("Mobile Number") })
            TextField(viewModel.dob.value,
                onValueChange = { viewModel.dob.value = it
                                viewModel.finaldob.value = viewModel.dob.value},
                label = { Text("Date of Birth") })
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {viewModel.submit.value = true
            viewModel.sentData()
            }) {
                Text(text = "Submit")
            }
            if (viewModel.submit.value){
                AlertDialog(
                    onDismissRequest = {viewModel.submit.value = false},
                    confirmButton = {
                        Text(text = "OK",
                            modifier = Modifier.clickable {
                                viewModel.submit.value =false
                            })
                    },
                    text = { Text(text = "Submit successfully") }
                )

            }
            Button(onClick = {
              nav.navigate("user_profile")
            }) {
                Text(text = "Home Screen")
            }
        }
    }
}