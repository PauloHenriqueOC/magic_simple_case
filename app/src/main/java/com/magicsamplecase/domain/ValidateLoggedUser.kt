package com.magicsamplecase.domain

import com.magicsamplecase.domain.errors.NoLoggedUserException
import com.magicsamplecase.domain.boundaries.UserRepositoryBoundary
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ValidateLoggedUser @Inject constructor(private val boundary: UserRepositoryBoundary) {
    fun getSource(): Single<Boolean> = boundary.getLoggedUser()
}