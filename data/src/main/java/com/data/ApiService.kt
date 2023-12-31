package com.data

import com.commonn.Constant
import com.data.network.model.BlogDTO
import com.data.network.model.BlogsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("post")
    suspend fun getBlogs(@Header("app-id") appId: String = Constant.APP_ID): Response<BlogsDTO>

    @GET("post")
    suspend fun getBlogsPagination(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<BlogsDTO>

    @GET("post/{id}")
    suspend fun getBlogDetail(
        @Header("app-id") appId: String = Constant.APP_ID, @Path("id") id: String
    ): Response<BlogDTO>
}