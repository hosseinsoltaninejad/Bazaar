package ir.hosseinsoltani.bazaar.model.repository.user

import android.content.SharedPreferences
import com.google.gson.JsonObject
import ir.hosseinsoltani.bazaar.model.net.ApiService
import ir.hosseinsoltani.bazaar.model.repository.TokenInMemory
import ir.hosseinsoltani.bazaar.util.VALUE_SUCCESS

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences,
) : UserRepository {

    override suspend fun signUp(name: String, userName: String, password: String): String {

        val jsonObject = JsonObject().apply {
            addProperty("name", name)
            addProperty("email", userName)
            addProperty("password", password)
        }
        val result = apiService.signUp(jsonObject)
        if (result.success) {
            TokenInMemory.refreshToken(userName, result.token)
            saveToken(result.token)
            saveUserName(userName)
            saveUserLoginTime()
            return VALUE_SUCCESS
        } else {
            return result.message
        }
    }

    override suspend fun signIn(userName: String, password: String): String {
        val jsonObject = JsonObject().apply {
            addProperty("email", userName)
            addProperty("password", password)
        }
        val result = apiService.signIn(jsonObject)
        if (result.success) {
            TokenInMemory.refreshToken(userName, result.token)
            saveToken(result.token)
            saveUserName(userName)
            saveUserLoginTime()
            return VALUE_SUCCESS
        } else {
            return result.message
        }
    }

    override fun signOut() {
        TokenInMemory.refreshToken(null, null)
        sharedPreferences.edit().clear().apply()
    }

    override fun loadToken() {
        TokenInMemory.refreshToken(getUserName(), getToken())
    }

    override fun saveToken(newToken: String) {
        sharedPreferences.edit().putString("token", newToken).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    override fun saveUserName(userName: String) {
        sharedPreferences.edit().putString("username", userName).apply()
    }

    override fun getUserName(): String? {
        return sharedPreferences.getString("username", null)
    }

    override fun saveUserLocation(address: String, postalCode: String) {
        sharedPreferences.edit().putString("address", address).apply()
        sharedPreferences.edit().putString("postalCode", postalCode).apply()
    }

    override fun getUserLocation(): Pair<String, String> {
        val address = sharedPreferences.getString("address", "click to add")!!
        val postalCode = sharedPreferences.getString("postalCode", "click to add")!!

        return Pair(address, postalCode)
    }

    override fun saveUserLoginTime() {
        val now = System.currentTimeMillis()
        sharedPreferences.edit().putString("login_time", now.toString()).apply()
    }

    override fun getUserLoginTime(): String {
        return sharedPreferences.getString("login_time", "0")!!
    }
}