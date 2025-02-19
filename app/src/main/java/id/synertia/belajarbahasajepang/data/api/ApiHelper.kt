package id.synertia.belajarbahasajepang.data.api

import id.synertia.belajarbahasajepang.base.BaseResponse
import id.synertia.belajarbahasajepang.data.responseModel.BabBunpoResponse
import id.synertia.belajarbahasajepang.data.responseModel.BabKosaResponse
import id.synertia.belajarbahasajepang.data.responseModel.BunpoResponse
import id.synertia.belajarbahasajepang.data.responseModel.KanjiResponse
import id.synertia.belajarbahasajepang.data.responseModel.KosaResponse
import id.synertia.belajarbahasajepang.data.responseModel.PenjelasanResponse
import id.synertia.belajarbahasajepang.data.responseModel.ProfileResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getProfile(): Response<BaseResponse<ProfileResponse>>
    suspend fun getPenjelasan(id:String): Response<PenjelasanResponse>
    suspend fun getBabBunpo(id:String): Response<BabBunpoResponse>
    suspend fun getBubpo(id:String): Response<BunpoResponse>
    suspend fun getKosa(id:String): Response<KosaResponse>
    suspend fun getKanji(id:String): Response<KanjiResponse>
    suspend fun getBabKosa(id:String): Response<BabKosaResponse>


}