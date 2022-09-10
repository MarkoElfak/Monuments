package rs.elfak.mosis.markoilic.monuments.data

import android.content.Context
import com.boopro.btracker.data.local.LocalRepository
import kotlinx.coroutines.flow.Flow
import rs.elfak.mosis.markoilic.monuments.data.model.RegisterObject
import rs.elfak.mosis.markoilic.monuments.data.model.UserModel
import rs.elfak.mosis.markoilic.monuments.data.remote.FirebaseWrapper
import rs.elfak.mosis.markoilic.monuments.data.remote.RemoteRepository

object Repository {

    suspend fun login(email:String, password: String): Flow<UserModel> {
        return RemoteRepository.login(email, password)
    }

    suspend fun register(username: String, password: String, email: String, dateOfBirth: String): Flow<RegisterObject> {
        return RemoteRepository.register(username, password, email, dateOfBirth)
    }


    //PrefUtility
    fun getUserInfo(context: Context): UserModel {
        return LocalRepository.getUserInfo(context)
    }

    fun saveUserInfo(context: Context, user: UserModel) {
        return LocalRepository.saveUserInfo(context, user)
    }
}