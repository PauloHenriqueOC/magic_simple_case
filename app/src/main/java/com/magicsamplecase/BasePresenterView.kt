package com.magicsamplecase

import com.magicsamplecase.presentation.utils.MappedError

interface BasePresenterView {
    fun displayLoading(value: Boolean)
    fun handleError(mappedError: MappedError)
}