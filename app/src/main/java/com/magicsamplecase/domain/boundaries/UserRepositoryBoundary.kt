package com.magicsamplecase.domain.boundaries

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserRepositoryBoundary {
    fun doLogin(email: String, pass: String): Completable

    fun saveUserCredentials(email: String): Completable
    fun getUserCredentials(): Single<String>
    fun setLoggedUser(value: Boolean): Completable
    fun getLoggedUser(): Single<Boolean>
}