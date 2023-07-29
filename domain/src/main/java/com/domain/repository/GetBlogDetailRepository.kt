package com.domain.repository

import com.domain.model.Blog
import retrofit2.Response

interface GetBlogDetailRepository {

    suspend fun getBlogDetailPage(id: String): Blog
}