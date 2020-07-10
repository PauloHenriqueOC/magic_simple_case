package com.magicsamplecase.presentation.scenes.login

import com.magicsamplecase.BasePresenter
import com.magicsamplecase.domain.errors.IncorrectEmailException
import com.magicsamplecase.domain.errors.IncorrectPasswordException
import com.magicsamplecase.domain.DoLoginLogout
import com.magicsamplecase.domain.GetUserCredentials
import com.magicsamplecase.domain.ValidateLoggedUser
import com.magicsamplecase.presentation.utils.ErrorMapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val view: LoginView,
    private val doLoginLogout: DoLoginLogout,
    private val validateLoggedUser: ValidateLoggedUser,
    private val userCredentials: GetUserCredentials,
    private val errorMapper: ErrorMapper
) : BasePresenter() {


    override fun bindView() {
        view.displayLoading(true)
        Single.zip(
            validateLoggedUser.getSource(),
            userCredentials.getSource(),
            BiFunction<Boolean, String, LoginVM>{ isLogged, email -> LoginVM(email, isLogged) }
        ).subscribe(
            { model ->
                view.displayLoading(false)

                if (model.isLogged)
                    view.displayLoggedScreen(model.email)
                else
                    view.displayLoginScreen(model.email)
            },

            { error ->
                view.displayLoading(false)
                view.handleError(errorMapper.mapException(error))
            }
        )

        view.onLoginButtonClick.flatMapCompletable { request ->
            doLoginLogout.getSource(email = request.email, pass = request.pass)
                .flatMapCompletable { isLogged -> Completable.fromAction {

                    if(isLogged)
                        view.displayLoggedScreen(request.email)
                    else
                        view.displayLoginScreen(request.email)
                    }
                }
                .doOnError { error ->
                    when (error) {
                        is IncorrectEmailException -> view.displayEmailError()
                        is IncorrectPasswordException -> view.displayPasswordError()
                        else -> view.handleError(errorMapper.mapException(error))
                    }
                }
                .onErrorComplete()

        }.subscribe().addTo(disposeBag)
    }
}