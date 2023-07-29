package com.domain.use_cases

import com.commonn.Resource
import com.domain.model.Blog
import com.domain.repository.GetBlogDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetBlogDetailsUseCase @Inject constructor(private val getBlogDetailsRepo: GetBlogDetailRepository) {


    operator fun invoke(id: String): Flow<Resource<Blog>> = flow {
        emit(Resource.Loading(""))
        try {
            val response = getBlogDetailsRepo.getBlogDetailPage(id)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }


    }

}