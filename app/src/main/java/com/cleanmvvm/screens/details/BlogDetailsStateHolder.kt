package com.cleanmvvm.screens.details

import com.domain.model.Blog

data class BlogDetailsStateHolder(
    val isLoading: Boolean = false,
    val data: Blog? = null,
    val error: String = ""
)