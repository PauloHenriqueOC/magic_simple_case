package com.magicsamplecase.data.remote

import com.magicsamplecase.domain.errors.IncorrectEmailException
import com.magicsamplecase.domain.errors.IncorrectPasswordException
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UserRemoteDS @Inject constructor() {

    private val mockedEmail = "teste123@gmail.com"
    private val mockedPass = "123456"

    fun doLogin(email: String, pass: String) : Completable = Completable.fromAction {
        if (email != mockedEmail)
            throw IncorrectEmailException()

        if (pass != mockedPass)
            throw IncorrectPasswordException()
    }
}