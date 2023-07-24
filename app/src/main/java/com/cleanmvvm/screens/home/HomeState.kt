package com.cleanmvvm.screens.home

import com.domain.model.Blog

data class HomeState(
    var isLoading: Boolean = false, var data: List<Blog>? = null, var error: String = ""
)