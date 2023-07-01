package com.example.csc557

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.csc557.ui.theme.model.AccountUser
import com.example.csc557.ui.theme.model.CarData
import com.example.csc557.ui.theme.model.Rent
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class SharedViewModel() : ViewModel() {

    fun cartPayment(
        listPay: List<String>
    ){
        for (i in listPay){
            val firestoreRef = Firebase
                .firestore
                .collection("rent")
                .document(i)

            try{
                val updateData = hashMapOf<String, Any>(
                    "paid" to true
                )
                firestoreRef
                    .update(updateData)
            }catch (e: Exception){
                Log.d("theres a catch", e.message.toString())
            }
        }
    }

    fun deleteRent(
        context: Context,
        rentID: String
    ) {
        var db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("rent")

        collectionRef.whereEqualTo("rentID", rentID)
            .get()
            .addOnSuccessListener {
                val batch = FirebaseFirestore.getInstance().batch()
                val documentRef = collectionRef.document(rentID)
                batch.delete(documentRef)

                batch.commit().addOnSuccessListener {
                    Toast.makeText(context, "successful delete rent", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun fetchRent(
        googleUID: String,
        boolResult: (Boolean) -> Unit
    ): SnapshotStateList<Rent> {
        var rentFound = mutableStateListOf<Rent>()

        var db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("rent")
            .whereEqualTo("googleUID", googleUID)
            .whereEqualTo("paid", false)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: Rent? = d.toObject(Rent::class.java)
                        rentFound.add(c as Rent)
                    }
                    boolResult(rentFound.isEmpty())
                } else {
                    boolResult(rentFound.isEmpty())
                }
            }
        return rentFound

    }

    fun rentCar(
        rent: Rent
    ) {
        val firestoreRef = Firebase
            .firestore
            .collection("rent")
            .document()

        try {
            firestoreRef
                .set(rent)
                .addOnSuccessListener {
                    val id = firestoreRef.id
                    val updates = hashMapOf<String, Any>(
                        "rentID" to id,
                    )
                    firestoreRef.update(updates)
                }
        } catch (e: Exception) {
        }
    }

    fun saveUserData(
        userUID: String,
        account: AccountUser,
        context: Context
    ) {
        val firestoreRef = Firebase
            .firestore
            .collection("user")
            .document(userUID)
        try {

            firestoreRef.set(account)
                .addOnSuccessListener {
                    Toast.makeText(context, "successful adding user", Toast.LENGTH_LONG).show()
                }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun checkIfUserHasDetail(
        userUID: String,
        data: (Boolean) -> Unit
    ) {
        val firestoreRef = Firebase
            .firestore
            .collection("user")
            .document(userUID)

        try {
            firestoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val userData = it.toObject<AccountUser>()
                        if (userData != null) {
                            data(true)
                        }
                    }
                }
        } catch (e: Exception) {
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun retrieveSpecificUserData(
//        context: Context,
        userUID: String,
        data: (AccountUser) -> Unit,
    ) {
        Log.d("user uid", userUID)
        val firestoreRef = Firebase
            .firestore
            .collection("user")
            .document(userUID)

        try {
            firestoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val userData = it.toObject<AccountUser>()
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
                } else {
//                            Log.d("TAG", searchesFound.isEmpty().toString())
                    boolResult(searchesFound.isEmpty())

                }
            }
//        Log.d("TAG", elements.size.toString())
        return searchesFound
    }

    fun fetchAllCars(
        arrayListCars: (ArrayList<String>) -> Unit
    ): SnapshotStateList<CarData> {
//        var allCars = mutableStateListOf<String>()
        var carList = mutableStateListOf<CarData>()

        var allCars = ArrayList<String>()
        var db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("cars")
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: CarData? = d.toObject(CarData::class.java)
                        carList.add(c as CarData)
                        if (!allCars.contains(c!!.brand)) {
                            allCars.add(c!!.brand)
                        }
                    }
                    arrayListCars(allCars)
//                    Log.d("TAG", allCars.size.toString())
                }
            }
        return carList
    }

}