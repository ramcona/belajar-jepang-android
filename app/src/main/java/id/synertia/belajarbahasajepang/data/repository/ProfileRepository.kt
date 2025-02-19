package id.synertia.belajarbahasajepang.data.repository

import id.synertia.belajarbahasajepang.data.api.ApiHelper
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getProfile() = apiHelper.getProfile()

}