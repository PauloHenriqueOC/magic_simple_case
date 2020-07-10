package com.magicsamplecase.domain

import com.magicsamplecase.domain.boundaries.UserRepositoryBoundary
import javax.inject.Inject

class SaveUserCredentials @Inject constructor(private val boundary: UserRepositoryBoundary) {
    fun getCompletable(email: String, pass: String) = boundary.saveUserCredentials(email)
}