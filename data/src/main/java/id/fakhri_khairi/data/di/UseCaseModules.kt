package id.fakhri_khairi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fakhri_khairi.data.repo.contract.ProductRepository
import id.fakhri_khairi.data.usecase.contract.GetProductDetail
import id.fakhri_khairi.data.usecase.contract.GetProductList
import id.fakhri_khairi.data.usecase.contract.GetProductSearch
import id.fakhri_khairi.data.usecase.implement.GetProductDetailImpl
import id.fakhri_khairi.data.usecase.implement.GetProductListImpl
import id.fakhri_khairi.data.usecase.implement.GetProductSearchImpl

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModules {

    @Provides
    fun provideGetProductList(repository: ProductRepository) : GetProductList {
        return GetProductListImpl(repository)
    }

    @Provides
    fun provideGetProductDetail(repository: ProductRepository) : GetProductDetail {
        return GetProductDetailImpl(repository)
    }

    @Provides
    fun provideGetProductSearch() : GetProductSearch {
        return GetProductSearchImpl()
    }
}