package id.synertia.belajarbahasajepang.helper

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.synertia.belajarbahasajepang.data.model.User

class SharedPref(context : Context) {

    private val mypref = "MAIN_PREF"
    private val sp: SharedPreferences = context.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sp.edit()

    private val keyAccessToken = "accessToken"
    private val keyFCMToken = "keyFCMToken"
    private val keyUserData = "keyUserData"
    private val keyIsLogin = "isLogin"
    private val keyApd = "keyApd"
    private val keyDashboardPeriode = "keyDashboardPeriode"
    private val keyDashboardAgendaSikap = "keyDashboardAgendaSikap"
    private val keyDashboardKaryawan = "keyDashboardKaryawan"


//
    var users: User?
        get() {
            val data = sp.getString(keyUserData, null)
            return Gson().fromJson(data, User::class.java)
        }
        set(value) {
            val json = Gson().toJson(value)
            sp.edit().putString(keyUserData, json).apply()
        }



    var isLogin: Boolean
        get() {
            return sp.getBoolean(keyIsLogin, false)
        }
        set(value) {
            sp.edit().putBoolean(keyIsLogin, value).apply()
        }

    var dashboardkaryawans: List<User>
        get() {
            val data = sp.getString(keyDashboardKaryawan, null)
            return if (data != null) {
                Gson().fromJson(data, object : TypeToken<List<User>>() {}.type)
            } else {
                listOf()
            }
        }
        set(value) {
            val json = Gson().toJson(value)
            sp.edit().putString(keyDashboardKaryawan, json).apply()
        }

    var fcmToken: String
        get() {
            return sp.getString(keyFCMToken, "token") ?: ""
        }
        set(value) {
            sp.edit().putString(keyFCMToken, value).apply()
        }

    var accessToken: String
        get() {
            return sp.getString(keyAccessToken, "") ?: ""
        }
        set(value) {
            sp.edit().putString(keyAccessToken, value).apply()
        }

    // Save a single object or list
    fun <T> saveData(key: String, data: T) {
        val json = Gson().toJson(data)
        sp.edit().putString(key, json).apply()
    }

    // Load a single object
    fun <T> loadData(key: String, clazz: Class<T>): T? {
        val json = sp.getString(key, null)
        return if (json != null) {
            Gson().fromJson(json, clazz)
        } else {
            null
        }
    }

    // Save a list of objects
    fun <T> saveListData(key: String, list: List<T>) {
        val json = Gson().toJson(list)
        sp.edit().putString(key, json).apply()
    }

    // Load a list of objects (no inline)
    fun <T> loadListData(key: String, typeToken: TypeToken<List<T>>): List<T>? {
        val json = sp.getString(key, null)
        return if (json != null) {
            Gson().fromJson(json, typeToken.type)
        } else {
            null
        }
    }




    fun clearAll() {
        editor.clear()
        editor.commit()
    }

}