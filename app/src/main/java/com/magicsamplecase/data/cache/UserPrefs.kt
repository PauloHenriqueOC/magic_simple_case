package com.magicsamplecase.data.cache

import io.paperdb.Paper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserPrefs @Inject constructor() {

    fun saveUserCredentials(email: String): Completable = Completable.fromAction {
        Paper.book(BOOKS.USER_BOOK).write(KEYS.USER_EMAIL, email)
    }

    fun getUserCredentials(): Single<String> = Single.just(
        Paper.book(BOOKS.USER_BOOK).read(KEYS.USER_EMAIL, "")
    )

    fun getLoggedUser(): Single<Boolean> = Single.just(
        Paper.book(BOOKS.USER_BOOK).read(KEYS.LOGGED_USER, false)
    )

    fun setLoggedUser(value: Boolean): Completable = Completable.fromAction {
        Paper.book(BOOKS.USER_BOOK).write(KEYS.LOGGED_USER, value)
    }

    private object BOOKS {
        const val USER_BOOK = "user_book"
    }

    private object KEYS {
        const val USER_EMAIL = "user_email"
        const val LOGGED_USER = "logged_user"
    }
}