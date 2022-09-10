package rs.elfak.mosis.markoilic.monuments.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import rs.elfak.mosis.markoilic.monuments.data.model.UserModel
import rs.elfak.mosis.markoilic.monuments.helpers.Consts

object PrefUtility {

    private val gson = Gson()

    private val Context.defaultSharedPreferences: SharedPreferences?
        get() = PreferenceManager.getDefaultSharedPreferences(this)
    private lateinit var sharedPreferences: SharedPreferences

    fun getUserInfo(context: Context): UserModel {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val user = sharedPreferences.getString(Consts.USER, null)

        return gson.fromJson(user, UserModel::class.java)
    }

    fun saveUserInfo(context: Context, user: UserModel) {
        context.defaultSharedPreferences?.edit()?.putString(Consts.USER, Gson().toJson(user))
            ?.apply()
    }
}