package id.fakhri_khairi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fakhri_khairi.data.mapper.ProductMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModules {

    @Singleton
    @Provides
    fun provideProductMapper() : ProductMapper {
        return ProductMapper()
    }
}