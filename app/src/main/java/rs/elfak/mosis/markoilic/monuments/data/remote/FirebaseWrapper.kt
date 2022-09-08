package rs.elfak.mosis.markoilic.monuments.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import rs.elfak.mosis.markoilic.monuments.data.model.UserModel
import rs.elfak.mosis.markoilic.monuments.helpers.Consts

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
}