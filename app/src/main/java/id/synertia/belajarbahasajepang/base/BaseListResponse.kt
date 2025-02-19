package id.synertia.belajarbahasajepang.base

data class BaseListResponse<T>(
    val statusCode: Int,
    val message: String,
    val data: DataResponse<T>
)

data class DataResponse<T>(
    val data: T,
    val firstPageUrl:String,
    val hasNextPage:Boolean,
    val hasPreviousPage:Boolean,
    val lastPageUrl:String,
    val nextPage:String,
    val nextPageUrl:String,
    val page:Int,
    val perPage:Int,
    val totalPages:Int,
)