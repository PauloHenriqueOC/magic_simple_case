package com.magicsamplecase.domain

import com.magicsamplecase.domain.boundaries.UserRepositoryBoundary
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DoLoginLogout @Inject constructor(private val boundary: UserRepositoryBoundary) {
    fun getSource(email: String, pass: String): Single<Boolean> =

        boundary.getLoggedUser().flatMap { isLogged ->
            if(isLogged) {
                boundary.setLoggedUser(false)
                    .toSingle { false }

            } else {
                boundary.doLogin(email, pass)
                    .andThen( boundary.saveUserCredentials(email) )
                    .andThen( boundary.setLoggedUser(true) )
                    .toSingle { true }
            }
        }
}