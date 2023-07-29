package com.data.network.di

import android.content.Context
import com.commonn.Constant
import com.data.ApiService
import com.data.repository.BlogRepositoryImpl
import com.data.repository.GetBlogDetailRepoImpl
import com.data.repository.GetPagerBlogsRepoImpl
import com.data.room.BlogDAO
import com.data.room.BlogDataBase
import com.domain.repository.BlogsRepository
import com.domain.repository.GetBlogDetailRepository
import com.domain.repository.GetPagerBlogsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideBlogRepository(apiService: ApiService): BlogsRepository {
        return BlogRepositoryImpl(apiService)
    }

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BlogDataBase {
        return BlogDataBase.getInstance(context)
    }

    @Provides
    fun provideDAO(blogDataBase: BlogDataBase): BlogDAO {
        return blogDataBase.getBlogDAO()
    }

    @Provides
    fun provideGetPagerRepo(apiService: ApiService): GetPagerBlogsRepo {
        return GetPagerBlogsRepoImpl(apiService)
    }

    @Provides
    fun provideGetBlogDetailRepo(apiService: ApiService): GetBlogDetailRepository {
        return GetBlogDetailRepoImpl(apiService)
    }
}