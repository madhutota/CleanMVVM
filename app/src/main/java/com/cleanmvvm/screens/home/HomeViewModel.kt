package com.cleanmvvm.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commonn.Resource
import com.domain.model.Blog
import com.domain.use_cases.GetBlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val blogsUseCase: GetBlogsUseCase) : ViewModel() {

    private val _blogs = mutableStateOf<HomeState>(HomeState())
    val blogs: State<HomeState> = _blogs

    init {
        getBlogs()
    }
    private fun getBlogs() {
        blogsUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _blogs.value = HomeState(isLoading = true)
                }

                is Resource.Success -> {
                    _blogs.value = HomeState(data = it.data)
                }

                is Resource.Error -> {
                    _blogs.value = HomeState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}