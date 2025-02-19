package id.synertia.belajarbahasajepang.data.repository

import id.synertia.belajarbahasajepang.data.api.ApiHelper
import javax.inject.Inject

class APIRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPenjelasan(id:String) = apiHelper.getPenjelasan(id)
    suspend fun getBabBunpo(id:String) = apiHelper.getBabBunpo(id)
    suspend fun getBabKosa(id:String) = apiHelper.getBabKosa(id)
    suspend fun getBubpo(id:String) = apiHelper.getBubpo(id)
    suspend fun getKosa(id:String) = apiHelper.getKosa(id)
    suspend fun getKanji(id:String) = apiHelper.getKanji(id)

}