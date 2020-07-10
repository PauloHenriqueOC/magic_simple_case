package com.magicsamplecase

import com.magicsamplecase.presentation.utils.ErrorMapper.MappedError

interface BasePresenterView {
    fun displayLoading(value: Boolean)
    fun handleError(error: MappedError)
}