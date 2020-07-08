package com.magicsamplecase.presentation.utils

import retrofit2.HttpException
import java.net.SocketTimeoutException

class MappedError(val message: String) {

    companion object {
        fun mapException(exception: Throwable) = when (exception) {
            is HttpException -> mapHttpException(exception.code())
            is SocketTimeoutException -> mapSocketTimeoutException()
            else ->  mapUnknowExcpetion()
        }

        private fun mapHttpException(code: Int) : MappedError {
            return when (code) {
                400 -> MappedError("Nao conseguimos processa a ação.")
                403 -> MappedError("Excedeu o numero de requisições diarias.")
                404 -> MappedError("Card não encontrado.")
                500 -> MappedError("Houve um problema em nosso servido... tente mais tarde.")
                503 -> MappedError("Estamos em manutenção... tente mais tarde.")

                else -> MappedError("Erro Desconhecido.")
            }
        }

        private fun mapSocketTimeoutException() = MappedError("Tempo de tentativa excedido... tente mais tarde")
        private fun mapUnknowExcpetion() = MappedError("Erro Desconhecido")
    }
}