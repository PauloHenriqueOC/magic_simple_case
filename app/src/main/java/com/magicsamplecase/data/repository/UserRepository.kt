package com.magicsamplecase.data.repository

import com.magicsamplecase.data.cache.UserPrefs
import com.magicsamplecase.data.remote.UserRemoteDS
import com.magicsamplecase.domain.boundaries.UserRepositoryBoundary
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDS: UserRemoteDS,
                                         private val userPrefs: UserPrefs) : UserRepositoryBoundary {

    override fun doLogin(email: String, pass: String): Completable =
        userRemoteDS.doLogin(email, pass)

    override fun saveUserCredentials(email: String): Completable =
        userPrefs.saveUserCredentials(email)

    override fun getUserCredentials(): Single<String> =
        userPrefs.getUserCredentials()

    override fun setLoggedUser(value: Boolean): Completable =
        userPrefs.setLoggedUser(value)

    override fun getLoggedUser(): Single<Boolean> =
        userPrefs.getLoggedUser()
}