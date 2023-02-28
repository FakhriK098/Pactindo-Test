package id.fakhri_khairi.data.service

import id.fakhri_khairi.data.model.responses.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET(value = "products")
    suspend fun getProductList() : List<ProductResponse>

    @GET(value = "products/{product_id}")
    suspend fun getProductDetail(
        @Path("product_id") productId: Int
    ) : ProductResponse
}