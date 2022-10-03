package rs.elfak.mosis.markoilic.monuments.data.remote

import android.net.Uri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import rs.elfak.mosis.markoilic.monuments.data.model.RegisterObject
import rs.elfak.mosis.markoilic.monuments.data.model.UserModel
import rs.elfak.mosis.markoilic.monuments.data.model.UserModelDTO
import rs.elfak.mosis.markoilic.monuments.helpers.Consts
import java.util.HashMap

object FirebaseWrapper {
    suspend fun login(email: String, password: String): Flow<UserModel> = callbackFlow {
        val login = Consts.auth.signInWithEmailAndPassword(email, password).await()
        val uid = login.user?.uid

        var user = UserModel()

        if (uid != null) {
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userData = snapshot.child(uid)
                        val u = userData.getValue(UserModel::class.java)

                        if (u != null) {
                            user = u
                        }
                    }
                    trySend(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            }

            Consts.userCollection.addValueEventListener(listener)
            awaitClose { Consts.userCollection.removeEventListener(listener) }
        } else {
            trySend(user)
        }
    }

    suspend fun registerUser(username: String, password: String, email: String, dateOfBirth: String): Flow<RegisterObject> = flow {
        val regUser = Consts.auth.createUserWithEmailAndPassword(email, password).await()
        val userID = regUser.user?.uid

        if (userID != null) {
            val u = RegisterObject()

            u.username = username
            u.email = email
            u.dateOfBirth = dateOfBirth

            val userRef = Consts.userCollection.child(userID)

            val user: MutableMap<String, Any> = HashMap()
            user["email"] = u.email
            user["username"] = u.username
            user["dateOfBirth"] = u.dateOfBirth
            userRef.setValue(user)

            emit(u)
        }
    }

    suspend fun updateUserWithPicture(user: UserModel, imageURI: Uri?) {
        if (imageURI!=null) {
            val storageReference = Consts.storage.child("profilePictures/${Consts.currentUserId}")
            storageReference.delete()
            storageReference.putFile(imageURI).await()

            storageReference.downloadUrl.addOnCompleteListener { user.imageURL = it.result.toString() }.addOnFailureListener { }.await()
        }

        Consts.currentUserId?.let { Consts.userCollection.child(it).setValue(UserModelDTO(user)).await() }
    }
}
