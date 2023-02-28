package id.fakhri_khairi.data.usecase.contract

import id.fakhri_khairi.domain.Product

interface GetProductList {
    suspend fun execute() : id.fakhri_khairi.domain.Result<List<Product>>
}

interface GetProductDetail {
    suspend fun execute(id: Int) : id.fakhri_khairi.domain.Result<Product>
}

interface GetProductSearch {
    suspend fun execute(keyword: String, data: List<Product>) : id.fakhri_khairi.domain.Result<List<Product>>
}