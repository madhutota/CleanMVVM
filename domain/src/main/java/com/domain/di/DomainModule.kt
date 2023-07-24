package com.domain.di

import com.domain.repository.BlogsRepository
import com.domain.use_cases.GetBlogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {
    @Provides
    fun provideGetBlogUserCase(blogsRepository: BlogsRepository): GetBlogsUseCase {
        return GetBlogsUseCase(blogsRepository)
    }
}