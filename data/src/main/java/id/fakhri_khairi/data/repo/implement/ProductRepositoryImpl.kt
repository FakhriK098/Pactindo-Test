package id.fakhri_khairi.data.repo.implement

import id.fakhri_khairi.data.mapper.ProductMapper
import id.fakhri_khairi.data.repo.BaseRepository
import id.fakhri_khairi.data.repo.contract.ProductRepository
import id.fakhri_khairi.data.service.ProductService
import id.fakhri_khairi.domain.Product
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val productMapper: ProductMapper,
    private val service: ProductService
) : BaseRepository(), ProductRepository {
    override suspend fun getProductList(): List<Product> {
        return getData {
            val result = service.getProductList()

            result.map { productMapper.convert(it) }
        }
    }

    override suspend fun getProductDetail(id: Int): Product {
        return getData {
            val result = service.getProductDetail(id)

            productMapper.convert(result)
        }
    }
}