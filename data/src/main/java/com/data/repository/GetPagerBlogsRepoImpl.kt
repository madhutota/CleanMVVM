package com.data.repository

import com.commonn.Resource
import com.data.ApiService
import com.data.mapper.toDomain
import com.domain.model.Blog
import com.domain.repository.GetPagerBlogsRepo
import javax.inject.Inject

class GetPagerBlogsRepoImpl @Inject constructor(private val apiService: ApiService) :
    GetPagerBlogsRepo {

    override suspend fun getPagerBlogs(page: Int, limit: Int): Resource<List<Blog>> {
        return try {
            val response = apiService.getBlogsPagination(page = page, limit = limit)
            if (response.isSuccessful) {
                val body = response.body()?.data?.toDomain()
                Resource.Success(body)

            } else {
                Resource.Error(response.errorBody()?.string())
            }

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }
}