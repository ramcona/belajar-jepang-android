package id.synertia.belajarbahasajepang.networkUtils

enum class HttpErrorMessage(val code: Int, val message: String) {
    HTTP_400_BAD_REQUEST(400, "Permintaan tidak valid."),
    HTTP_401_UNAUTHORIZED(401, "Tidak diizinkan untuk mengakses data ini."),
    HTTP_403_FORBIDDEN(403, "Akses ditolak."),
    HTTP_404_NOT_FOUND(404, "Data tidak ditemukan."),
    HTTP_422_UNPROCESSABLE_ENTITY(422, "Periksa kembali data anda."),
    HTTP_500_INTERNAL_SERVER_ERROR(500, "Server mengalami kesalahan internal."),
    HTTP_502_BAD_GATEWAY(502, "Gateway tidak valid."),
    HTTP_503_SERVICE_UNAVAILABLE(503, "Layanan tidak tersedia."),
    HTTP_504_GATEWAY_TIMEOUT(504, "Waktu habis saat menghubungkan ke gateway.");

    companion object {
        fun getMessageByCode(code: Int): String {
            values().forEach {
                if (it.code == code) {
                    return it.message
                }
            }
            return "Kesalahan tidak dikenal dengan kode: $code"
        }
    }
}
