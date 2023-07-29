package com.data.repository

import com.data.ApiService
import com.data.mapper.toDomain
import com.data.network.utils.SafeApiRequest
import com.domain.model.Blog
import com.domain.model.Owner
import com.domain.repository.GetBlogDetailRepository
import javax.inject.Inject

class GetBlogDetailRepoImpl @Inject constructor(private val apiService: ApiService) :
    GetBlogDetailRepository, SafeApiRequest() {
    override suspend fun getBlogDetailPage(id: String): Blog {
        var response = safeApiRequest {
            apiService.getBlogDetail(id = id)
        }

        return Blog(
            id = response?.id ?: "",
            image = response?.image ?: "",
            likes = response?.likes ?: 0,
            owner = response?.owner?.toDomain() ?: Owner("", "", "", "", ""),
            publishDate = response?.publishDate ?: "",
            tags = response?.tags ?: emptyList(),
            text = response?.text ?: ""
        )

    }
}