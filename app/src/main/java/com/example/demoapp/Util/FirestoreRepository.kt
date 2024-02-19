package com.example.demoapp.Util

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {
    private val firestoreDB: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun queryDataFromDatabase(): CollectionReference {
        var collectionReference = firestoreDB.collection("House")
        return collectionReference
    }
}