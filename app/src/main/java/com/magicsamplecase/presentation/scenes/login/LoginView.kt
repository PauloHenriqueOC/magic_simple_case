package com.magicsamplecase.presentation.scenes.login

import com.magicsamplecase.BasePresenterView
import io.reactivex.rxjava3.subjects.PublishSubject

interface LoginView : BasePresenterView {
    val onLoginButtonClick: PublishSubject<LoginRequestVM>
    val onLogoutButtonClick: PublishSubject<Unit>

    fun displayLoginScreen(email: String)
    fun displayLoggedScreen(email: String)
    fun displayEmailError()
    fun displayPasswordError()
}