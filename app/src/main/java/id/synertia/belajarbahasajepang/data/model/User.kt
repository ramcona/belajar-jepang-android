package id.synertia.belajarbahasajepang.data.model

import java.io.Serializable

class User: Serializable {
    var id:String = ""
    var id_karyawan:String = ""
    var nama:String = ""
    var nik:String = ""
    var nis:String = ""
    var jenis_kelamin:String? = ""
    var no_ktp:String = ""
    var password:String = ""
    var uuid:String = ""
    var username:String = ""
    var nama_jabatan:String = ""
    var nana_jabatan_pendidik:String = ""
    var nama_lokasi_kerja:String = ""
    var tanggal_masuk:String = ""

    var nama_karyawan:String = ""
    var nik_karyawan:String = ""
}
