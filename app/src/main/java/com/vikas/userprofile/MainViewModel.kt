package com.vikas.userprofile

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val FireBaseRepo = FireBaseRepo()
    val profile = Profile()
    var listOfProfile = mutableStateOf(listOf(Profile()))
    var name = mutableStateOf("")
    var gmailId = mutableStateOf("")
    var mobile = mutableStateOf("")
    var dob = mutableStateOf("")
    var text = mutableStateOf(" ")


    var getMychat = mutableStateOf(false)
    var message = mutableStateOf(Message())
    var listOfMessage = mutableStateOf(listOf<Message>())

    var usermobile = mutableStateOf("")
    var friendsMobile = mutableStateOf("")
    var msg = mutableStateOf("")
    var groupId = mutableStateOf("${usermobile} and ${friendsMobile}")
    var groupIdReverse = mutableStateOf("${friendsMobile} and ${usermobile}")


    var finalname = mutableStateOf("")
    var finalgmailId = mutableStateOf("")
    var finalmobile = mutableStateOf("")
    var finaldob = mutableStateOf("")

    var submit = mutableStateOf(false)

    fun sentData() {
        FireBaseRepo.sendData(
            finalname.value,
            finalgmailId.value,
            finalmobile.value,
            finaldob.value,

            )
    }

    fun getData(mobile: String): MutableState<List<Profile>> {
//        : MutableState<List<Profile>>
        viewModelScope.launch {
//            Log.e("LIST", profile.toString())
//            profile.value = FireBaseRepo.fatchData(finalmobile.value)!!
            Log.e("FETCH", profile.toString())
//            name.value = profile.value.name
//            mobile.value = profile.value.mobile
//            gmailId.value = profile.value.gmailId
//            dob.value = profile.value.dob
            FireBaseRepo.getUserData(mobile).also {
                if (it != null) {
                    listOfProfile.value = it
                    Log.e("NAME", name.value)
                }
            }
//            FireBaseRepo.fatchData(mobile).also {
//                if (it != null) {
//                profiles.value = it
//                    Log.e("LIST", profiles.toString())
        }
        return listOfProfile
    }
fun sendmessage (){
    message.value = message.value.copy(
        message = msg.value
    )
    viewModelScope.launch {
    FireBaseRepo.sendMessage(message.value, groupId.value, groupIdReverse.value)
    }
}
fun getMessage(){
    viewModelScope.launch {
        FireBaseRepo.getMessage(groupId.value, groupIdReverse.value).also {
            if (it != null){
                listOfMessage.value = it.messagelist
            }
        }
    }
}
}




