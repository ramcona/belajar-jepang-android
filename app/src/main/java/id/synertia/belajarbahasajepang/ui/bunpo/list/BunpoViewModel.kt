package id.synertia.belajarbahasajepang.ui.bunpo.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.synertia.belajarbahasajepang.data.model.Bunpo
import id.synertia.belajarbahasajepang.data.repository.APIRepository
import id.synertia.belajarbahasajepang.networkUtils.HttpErrorMessage
import id.synertia.belajarbahasajepang.networkUtils.NetworkHelper
import id.synertia.belajarbahasajepang.networkUtils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BunpoViewModel @Inject constructor(
    private val repository: APIRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _response = MutableLiveData<Resource<List<Bunpo>>>()
    val response: LiveData<Resource<List<Bunpo>>>
        get() = _response

    fun get(id:String) {
        viewModelScope.launch {
            _response.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                repository.getBubpo(id).let { response ->
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null && !body.error) {
                            val data = body.bunpo

                            if (data.isNotEmpty()) {
                                _response.postValue(Resource.success(data))
                            } else {
                                _response.postValue(Resource.error("Terjadi kesalahan coba beberapa saat lagi", null))
                            }
                        } else {
                            _response.postValue(Resource.error( "Terjadi kesalahan coba beberapa saat lagi", null))
                        }
                    } else {
                        _response.postValue(Resource.error(HttpErrorMessage.getMessageByCode(response.code()), null))
                    }
                }
            } else {
                _response.postValue(Resource.error("Tidak dapat terhubung ke server, coba beberapa saat lagi", null))
            }
        }
    }

    
}