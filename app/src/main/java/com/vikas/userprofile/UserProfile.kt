package com.vikas.userprofile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun userProfile(navController: NavController, viewModel: MainViewModel) {
    Column {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Name: ${viewModel.name.value}")
//
//            Text(text = "Gmail Id: ${viewModel.gmailId.value}")
//
//            Text(text = "Mobile: ${viewModel.mobile.value}")
//
//            Text(text = "Date Of Birth: ${viewModel.dob.value}")
//
//
//        }

        LazyColumn{
            viewModel.listOfProfile = viewModel.getData(viewModel.finalmobile.value)
            items(viewModel.listOfProfile.value){
                card(user = it, navController)
            }
        }

        /*Row (horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()){


            Button(onClick = {
                navController.navigate("edit_screen")
            }) {
                Text(text = "EditScreen")
            }

            Button(onClick = {
//                viewModel.getData(viewModel.finalmobile.value)
//            GlobalScope.launch {
            }
//                Log.e("FETCH", viewModel.finalmobile.value)
//            }
            ) {
                Text(text = "Fetch")
            }
        }*/
    }
}

@Composable
fun card(user: Profile,
         navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("ChatScreen")
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "User Profile Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Name: ${user.name}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Email: ${user.gmailId}",
                    fontSize = 15.sp,
                )
            }
        }
    }
}

