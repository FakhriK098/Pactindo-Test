package id.fakhri_khairi.data.model.responses

import com.squareup.moshi.Json

data class ProductResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "price")
    val price: Float,

    @Json(name = "description")
    val description: String,

    @Json(name = "category")
    val category: String,

    @Json(name = "image")
    val image: String,

    @Json(name = "rating")
    val rating: RatingResponse
) {
    data class RatingResponse(
        @Json(name = "rate")
        val rate: Float
    )
}
