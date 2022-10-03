package rs.elfak.mosis.markoilic.monuments.data.remote

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import rs.elfak.mosis.markoilic.monuments.data.model.RegisterObject
import rs.elfak.mosis.markoilic.monuments.data.model.UserModel

object RemoteRepository {

    suspend fun login(email: String, password: String): Flow<UserModel> {
        return FirebaseWrapper.login(email, password)
    }

    suspend fun register(username: String, password: String, email: String, dateOfBirth: String): Flow<RegisterObject> {
        return FirebaseWrapper.registerUser(username, password, email, dateOfBirth)
    }

    suspend fun updateUserWithPicture(user: UserModel, imageUri: Uri) {
        FirebaseWrapper.updateUserWithPicture(user, imageUri)
    }
}