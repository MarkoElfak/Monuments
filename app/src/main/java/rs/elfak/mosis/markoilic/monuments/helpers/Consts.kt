package rs.elfak.mosis.markoilic.monuments.helpers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

object Consts {

    val auth = FirebaseAuth.getInstance()

    val database = Firebase.database.reference
    val userCollection = database.child("users")
    val storage = Firebase.storage.reference

    val currentUserId = auth.currentUser?.uid

    const val USER = "user"
}