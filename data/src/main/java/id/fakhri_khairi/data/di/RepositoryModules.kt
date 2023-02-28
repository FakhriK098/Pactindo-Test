package id.fakhri_khairi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fakhri_khairi.data.mapper.ProductMapper
import id.fakhri_khairi.data.repo.contract.ProductRepository
import id.fakhri_khairi.data.repo.implement.ProductRepositoryImpl
import id.fakhri_khairi.data.service.ProductService

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModules {

    @Provides
    fun provideProductRepository(
        productMapper: ProductMapper,
        productService: ProductService
    ) : ProductRepository {
        return ProductRepositoryImpl(
            productMapper,
            productService
        )
    }
}