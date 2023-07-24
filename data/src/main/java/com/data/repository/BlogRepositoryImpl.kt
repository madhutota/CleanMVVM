package com.data.repository

import com.data.ApiService
import com.data.mapper.toDomain
import com.data.network.utils.SafeApiRequest
import com.domain.model.Blog
import com.domain.repository.BlogsRepository
import retrofit2.Response
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(private val apiService: ApiService) : BlogsRepository,
    SafeApiRequest() {
    override suspend fun getBlogs(): List<Blog> {
        val response = safeApiRequest {
            apiService.getBlogs()
        }
        return response?.data?.toDomain() ?: emptyList()
    }


}