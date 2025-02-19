package id.synertia.belajarbahasajepang.data.responseModel


import id.synertia.belajarbahasajepang.data.model.Bunpo
import java.io.Serializable

class BunpoResponse() : Serializable {
    var bunpo: List<Bunpo> = ArrayList()
    var code: Int = 0
    var error: Boolean = false
}