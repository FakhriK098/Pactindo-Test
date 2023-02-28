package id.fakhri_khairi.data.repo.contract

import id.fakhri_khairi.domain.Product

interface ProductRepository {
    suspend fun getProductList() : List<Product>
    suspend fun getProductDetail(id: Int) : Product
}