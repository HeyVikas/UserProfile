package com.vikas.userprofile

import android.util.Log
import com.google.firebase.Firebase
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




//    suspend fun fatchData(mobile: String): List<Profile> {
////        var profile: Profile? = Profile()
//        return dataBase.collection("Users")
////            .document("${Profile()}")
//            .get()
////        .addOnSuccessListener {
//////            if (document != null) {
//////            Log.d("ABC", "DocumentSnapshot Fetch successfully ! ${it.documents.size}")
////                //profile = document.getData()
//////            } else {
//////                Log.e("FBA", "Data is Null")
////            }
//            //document.data.toString()
////        .addOnFailureListener {
////                e -> Log.w("ABCD", "Error writing document", e) }
////             .await()
////            .toObjects(Profile::class.java)
//
////        Log.e("FBB", profile.toString())
////        : List<Profile>
////        return  dataBase.collection("Users")
////            .get()
////            .addOnSuccessListener {
////                    Log.d("ABC", "DocumentSnapshot data: ${it.documents.size}")
////            }
////            .addOnFailureListener { exception ->
////                Log.d("ABCDE", "get failed with ", exception)
////            }
////            .await()
//            .toObjects(Profile::class.java)
////    }
//
//    }
}

