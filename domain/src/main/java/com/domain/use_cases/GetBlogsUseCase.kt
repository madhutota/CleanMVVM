package com.domain.use_cases

import com.commonn.Resource
import com.domain.model.Blog
import com.domain.repository.BlogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetBlogsUseCase @Inject constructor(private val blogsRepository: BlogsRepository) {
    operator fun invoke(): Flow<Resource<List<Blog>>> = flow {
        emit(Resource.Loading(null))

        try {
            val response = blogsRepository.getBlogs()
            emit(Resource.Success(data = response))

        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }


    }
}