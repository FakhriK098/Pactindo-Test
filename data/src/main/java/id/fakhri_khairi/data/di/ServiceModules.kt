package id.fakhri_khairi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fakhri_khairi.data.service.ProductService
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModules {
    @Singleton
    @Provides
    fun provideProductService(
        @Named(DataModules.NAMED_RETROFIT) retrofit: Retrofit
    ): ProductService {
        return retrofit.create(ProductService::class.java)
    }
}