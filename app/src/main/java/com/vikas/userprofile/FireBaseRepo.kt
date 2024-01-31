package com.vikas.userprofile

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FireBaseRepo {
    val dataBase = Firebase.firestore


    fun sendData(
        name: String,
        gmailId: String,
        mobile: String,
        dob: String
    ) {
        val profile = Profile(
            name, gmailId, mobile, dob
        )
        dataBase.collection("Users")
            .document(mobile.toString())
            .set(profile)

    }
    suspend fun getUserData(mail:String): List<Profile> {
        return  dataBase.collection("Users")
            .get()
            .await()
            .toObjects(Profile::class.java)


    }

    suspend fun sendMessage(message: Message, groupId: String, groupIdReverse : String) {
        val doc = dataBase.collection("Chat")
            .document(groupId)
            .get()
            .await()

        val doc2 = dataBase.collection("Chat")
            .document(groupIdReverse)
            .get()
            .await()

        if (doc.exists()) {
            dataBase.collection("Chat")
                .document(groupId)
                .update("messagelist", FieldValue.arrayUnion(message))
        }
        else if (doc2.exists()){
            dataBase.collection("Chat")
                .document(groupIdReverse)
                .update("messagelist", FieldValue.arrayUnion(message))
        }
    }

    suspend fun getMessage(groupId: String, groupIdReverse: String) : ArrayOfMessage?  {

        val doc = dataBase.collection("Chat")
            .document(groupId)
            .get()
            .await()

        val doc2 = dataBase.collection("Chat")
            .document()
            .get()
            .await()
        if (doc.exists()){
            return dataBase.collection("Chat")
                .document(groupId)
                .get()
                .addOnSuccessListener {document ->
                    Log.e("success", document.toString())
                    Log.e("success", document.data?.values.toString())
                }
                .await()
                .toObject(ArrayOfMessage::class.java)
        }
        else{
            return dataBase.collection("Chat")
                .document(groupIdReverse)
                .get()
                .addOnSuccessListener { document ->
                    Log.e("success", document.toString())
                    Log.e("success", document.data?.values.toString())
                }
                .await()
                .toObject(ArrayOfMessage::class.java)
        }
    }
}

