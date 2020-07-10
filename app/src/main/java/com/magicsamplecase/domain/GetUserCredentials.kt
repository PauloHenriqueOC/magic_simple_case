package com.magicsamplecase.domain

import com.magicsamplecase.domain.boundaries.UserRepositoryBoundary
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserCredentials @Inject constructor(private val boundary: UserRepositoryBoundary) {
    fun getSource(): Single<String> = boundary.getUserCredentials()
}