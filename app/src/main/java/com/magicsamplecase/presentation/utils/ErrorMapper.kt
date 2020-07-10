package com.magicsamplecase.presentation.utils

import android.content.Context
import com.magicsamplecase.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ErrorMapper @Inject constructor(val context: Context) {

    fun mapException(exception: Throwable): MappedError = when (exception) {
        is HttpException -> mapHttpException(exception.code())
        is SocketTimeoutException -> mapSocketTimeoutException()
        else ->  mapUnknownException()
    }

    private fun mapHttpException(code: Int) : MappedError {
        return when (code) {
            400 -> MappedError(context.getString(R.string.error_400_message))
            403 -> MappedError(context.getString(R.string.error_403_message))
            404 -> MappedError(context.getString(R.string.error_404_message))
            500 -> MappedError(context.getString(R.string.error_500_message))
            503 -> MappedError(context.getString(R.string.error_503_message))
            else -> MappedError(context.getString(R.string.unknown_error_message))
        }
    }

    private fun mapSocketTimeoutException() = MappedError(context.getString(R.string.timeout_error_message))
    private fun mapUnknownException() = MappedError(context.getString(R.string.unknown_error_message))

    inner class MappedError(val message: String)
}