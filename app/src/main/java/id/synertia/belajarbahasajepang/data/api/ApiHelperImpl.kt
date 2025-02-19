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
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getProfile(): Response<BaseResponse<ProfileResponse>> = apiService.getProfile()
    override suspend fun getPenjelasan(id: String): Response<PenjelasanResponse> = apiService.getPenjelasan(id)
    override suspend fun getBabBunpo(id: String): Response<BabBunpoResponse> = apiService.getBabBunpo(id)
    override suspend fun getBubpo(id: String): Response<BunpoResponse> = apiService.getBubpo(id)
    override suspend fun getKosa(id: String): Response<KosaResponse> = apiService.getKosa(id)
    override suspend fun getKanji(id: String): Response<KanjiResponse> = apiService.getKanji(id)
    override suspend fun getBabKosa(id: String): Response<BabKosaResponse> = apiService.getBabKosa(id)

}