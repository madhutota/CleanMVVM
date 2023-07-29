package com.domain.repository

import com.commonn.Resource
import com.domain.model.Blog

interface GetPagerBlogsRepo {

    suspend fun getPagerBlogs(page: Int, limit: Int): Resource<List<Blog>>

}