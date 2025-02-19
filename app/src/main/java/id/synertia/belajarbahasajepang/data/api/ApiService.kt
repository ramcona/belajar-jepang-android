package id.synertia.belajarbahasajepang.data.api

import id.synertia.belajarbahasajepang.base.BaseResponse
import id.synertia.belajarbahasajepang.data.model.Penjelasan
import id.synertia.belajarbahasajepang.data.responseModel.BabBunpoResponse
import id.synertia.belajarbahasajepang.data.responseModel.BabKosaResponse
import id.synertia.belajarbahasajepang.data.responseModel.BunpoResponse
import id.synertia.belajarbahasajepang.data.responseModel.KanjiResponse
import id.synertia.belajarbahasajepang.data.responseModel.KosaResponse
import id.synertia.belajarbahasajepang.data.responseModel.PenjelasanResponse
import id.synertia.belajarbahasajepang.data.responseModel.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("profile")
    suspend fun getProfile(
    ): Response<BaseResponse<ProfileResponse>>

    @GET("penjelasan/{id}")
    suspend fun getPenjelasan(
        @Path("id") id:String
    ): Response<PenjelasanResponse>

    @GET("getsubbunpo/{id}")
    suspend fun getBabBunpo(
        @Path("id") id:String
    ): Response<BabBunpoResponse>

    @GET("bunpo/{id}")
    suspend fun getBubpo(
        @Path("id") id:String
    ): Response<BunpoResponse>

    @GET("kosa/{id}")
    suspend fun getKosa(
        @Path("id") id:String
    ): Response<KosaResponse>

    @GET("kanji/{id}")
    suspend fun getKanji(
        @Path("id") id:String
    ): Response<KanjiResponse>

    @GET("getsubkosa/{id}")
    suspend fun getBabKosa(
        @Path("id") id:String
    ): Response<BabKosaResponse>


}