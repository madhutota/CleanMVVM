package com.data.network.di

import com.commonn.Constant
import com.data.ApiService
import com.data.repository.BlogRepositoryImpl
import com.domain.repository.BlogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideBlogRepository(apiService: ApiService): BlogsRepository {
        return BlogRepositoryImpl(apiService)
    }
}