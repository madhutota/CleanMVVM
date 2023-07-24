package com.domain.repository

import com.domain.model.Blog
import retrofit2.Response

interface BlogsRepository {

   suspend fun getBlogs(): List<Blog>
}