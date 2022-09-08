package rs.elfak.mosis.markoilic.monuments.data.remote

import kotlinx.coroutines.flow.Flow
import rs.elfak.mosis.markoilic.monuments.data.model.UserModel

object RemoteRepository {

    suspend fun login(email: String, password: String): Flow<UserModel> {
        return FirebaseWrapper.login(email, password)
    }
}