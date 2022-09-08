package rs.elfak.mosis.markoilic.monuments.data

import kotlinx.coroutines.flow.Flow
import rs.elfak.mosis.markoilic.monuments.data.model.UserModel
import rs.elfak.mosis.markoilic.monuments.data.remote.RemoteRepository

object Repository {

    suspend fun login(email:String, password: String): Flow<UserModel> {
        return RemoteRepository.login(email, password)
    }
}