package id.fakhri_khairi.data.usecase.implement

import id.fakhri_khairi.data.usecase.BaseUseCase
import id.fakhri_khairi.data.usecase.contract.GetProductSearch
import id.fakhri_khairi.domain.Product
import id.fakhri_khairi.domain.Result
import kotlinx.coroutines.delay
import javax.inject.Inject

internal class GetProductSearchImpl @Inject constructor() : BaseUseCase<List<Product>>(), GetProductSearch {
    override suspend fun execute(keyword: String, data: List<Product>): Result<List<Product>> = getSuspendResult {
        delay(5000)
        data.filter {
            val productNameWords = it.title.lowercase().split(" ")

            productNameWords.contains(keyword.lowercase())
        }
    }
}