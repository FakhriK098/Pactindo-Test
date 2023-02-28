package id.fakhri_khairi.data.usecase.implement

import id.fakhri_khairi.data.repo.contract.ProductRepository
import id.fakhri_khairi.data.usecase.BaseUseCase
import id.fakhri_khairi.data.usecase.contract.GetProductList
import id.fakhri_khairi.domain.Product
import id.fakhri_khairi.domain.Result
import javax.inject.Inject

internal class GetProductListImpl @Inject constructor(
    private val repository: ProductRepository
) : BaseUseCase<List<Product>>(), GetProductList {
    override suspend fun execute(): Result<List<Product>> = getSuspendResult {
        repository.getProductList()
    }
}