package com.example.csc557

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.csc557.ui.theme.model.CarData
import com.example.csc557.ui.theme.model.UserData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class SharedViewModel() : ViewModel() {

    fun saveUserData(
        userData: UserData,
        context: Context
    ){
        val firestoreRef = Firebase
            .firestore
            .collection("user")
            .document(userData.userID)

        try {

            firestoreRef.set(userData)
                .addOnSuccessListener {
                    Toast.makeText(context, "successful adding user", Toast.LENGTH_LONG).show()
                }

        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun retrieveSpecificUserData(
        context: Context,
        data: (CarData) -> Unit
    ){
        val firestoreRef = Firebase
            .firestore
            .collection("cars")
            .document("2ndjJGlVBGLT9l7NKk6F")

        try {

            firestoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()){
                        val userData = it.toObject<CarData>()
                        if (userData != null) {
                            data(userData)

                            Toast.makeText(context, "${userData.model}", Toast.LENGTH_LONG).show()
                        }
                    }

                }

        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun idk(
        context: Context,
    ): SnapshotStateList<CarData> {
        var carList = mutableStateListOf<CarData>()
        var db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("cars").get()
            .addOnSuccessListener {
                queryDocumentSnapshots ->
                if(!queryDocumentSnapshots.isEmpty){
                    val list = queryDocumentSnapshots.documents
                    for (d in list){
                        val c: CarData? = d.toObject<CarData>(CarData::class.java)
                        carList.add(c as CarData)
                    }
//                    Toast.makeText(context, "No data found in database", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "No data found in database", Toast.LENGTH_SHORT).show()
                }

            }
            .addOnFailureListener{
                Toast.makeText(context, "Fail to get the data", Toast.LENGTH_SHORT).show()
            }
        return carList
    }

}