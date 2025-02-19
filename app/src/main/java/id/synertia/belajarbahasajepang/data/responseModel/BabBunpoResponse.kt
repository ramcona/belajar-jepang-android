package id.synertia.belajarbahasajepang.data.responseModel


import id.synertia.belajarbahasajepang.data.model.BabBunpo
import id.synertia.belajarbahasajepang.data.model.Penjelasan
import java.io.Serializable

class BabBunpoResponse() : Serializable {
    var subbunpo: List<BabBunpo> = ArrayList()
    var code: Int = 0
    var error: Boolean = false
}