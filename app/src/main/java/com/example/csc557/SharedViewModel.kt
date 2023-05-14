package com.example.csc557

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.csc557.ui.theme.data.carsAvailable
import com.example.csc557.ui.theme.model.Car
import com.example.csc557.ui.theme.model.CarData
import com.example.csc557.ui.theme.model.UserData
import com.google.android.gms.common.api.BooleanResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SharedViewModel() : ViewModel() {

//    fun saveUserData(
//        userData: UserData,
//        context: Context
//    ) {
//        val firestoreRef = Firebase
//            .firestore
//            .collection("user")
//            .document(userData.userID)
//
//        try {
//
//            firestoreRef.set(userData)
//                .addOnSuccessListener {
//                    Toast.makeText(context, "successful adding user", Toast.LENGTH_LONG).show()
//                }
//
//        } catch (e: Exception) {
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
//        }
//    }

//    fun retrieveSpecificUserData(
//        context: Context,
//        data: (CarData) -> Unit
//    ) {
//        val firestoreRef = Firebase
//            .firestore
//            .collection("cars")
//            .document("2ndjJGlVBGLT9l7NKk6F")
//
//        try {
//
//            firestoreRef.get()
//                .addOnSuccessListener {
//                    if (it.exists()) {
//                        val userData = it.toObject<CarData>()
//                        if (userData != null) {
//                            data(userData)
//
//                            Toast.makeText(context, "${userData.model}", Toast.LENGTH_LONG).show()
//                        }
//                    }
//
//                }
//
//        } catch (e: Exception) {
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
//        }
//    }

    //used in cardetails
    fun fetchSpecificCar(
        carModel: String,
        data: (CarData) -> Unit
    ) {
        val firestoreRef = Firebase
            .firestore
            .collection("cars")
            .document(carModel)

        try {

            firestoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val userData = it.toObject<CarData>()
                        if (userData != null) {
                            data(userData)

//                            Toast.makeText(context, "${userData.model}", Toast.LENGTH_LONG).show()
                        }
                    }

                }

        } catch (e: Exception) {
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    //used in home for hotdeals car
    fun fetchCarsHotDealsList(
//        context: Context,
    ): SnapshotStateList<CarData> {
        var carList = mutableStateListOf<CarData>()
        var db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("cars").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: CarData? = d.toObject(CarData::class.java)
                        carList.add(c as CarData)
                    }
//                    Toast.makeText(context, "No data found in database", Toast.LENGTH_SHORT).show()
                }
//                else{
//                    Toast.makeText(context, "No data found in database", Toast.LENGTH_SHORT).show()
//                }

            }
//            .addOnFailureListener{
//                Toast.makeText(context, "Fail to get the data", Toast.LENGTH_SHORT).show()
//            }
//        Log.d("bb", carList.size.toString())
        return carList
    }

    //used in fetch car model searches
    fun fetchCarSearches(
        searches: String,
        boolResult: (Boolean) -> Unit
    ): SnapshotStateList<CarData> {

        var searchesFound = mutableStateListOf<CarData>()
//        val elements: List<CarData> = searchesFound
        var db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("cars")
            .whereEqualTo("model", searches)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: CarData? = d.toObject(CarData::class.java)
                        searchesFound.add(c as CarData)
                    }
//                    Log.d("emem", searchesFound.isEmpty().toString())
//                    Toast.makeText(context, "No data found in database", Toast.LENGTH_SHORT).show()
                    boolResult(searchesFound.isEmpty())
                }else{
//                            Log.d("TAG", searchesFound.isEmpty().toString())
                    boolResult(searchesFound.isEmpty())

                }
            }
//        Log.d("TAG", elements.size.toString())
        return searchesFound
    }

    fun fetchAllCars(
        arrayListCars: (ArrayList<String>) -> Unit
    ) {
//        var allCars = mutableStateListOf<String>()
        var allCars = ArrayList<String>()
        var db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("cars")
            .get()
            .addOnSuccessListener {  queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: CarData? = d.toObject(CarData::class.java)
                        if(!allCars.contains(c!!.brand)){
                            allCars.add(c!!.brand)
                        }
                    }
                    arrayListCars(allCars)
                    Log.d("TAG", allCars.size.toString())
                }
            }
    }

}