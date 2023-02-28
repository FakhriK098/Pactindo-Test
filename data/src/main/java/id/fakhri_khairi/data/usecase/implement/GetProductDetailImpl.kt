package id.fakhri_khairi.data.usecase.implement

import id.fakhri_khairi.data.repo.contract.ProductRepository
import id.fakhri_khairi.data.usecase.BaseUseCase
import id.fakhri_khairi.data.usecase.contract.GetProductDetail
import id.fakhri_khairi.domain.Product
import id.fakhri_khairi.domain.Result
import javax.inject.Inject

internal class GetProductDetailImpl @Inject constructor(
    private val repository: ProductRepository
) : BaseUseCase<Product>(), GetProductDetail {
    override suspend fun execute(id: Int): Result<Product> = getSuspendResult {
        repository.getProductDetail(id)
    }
}