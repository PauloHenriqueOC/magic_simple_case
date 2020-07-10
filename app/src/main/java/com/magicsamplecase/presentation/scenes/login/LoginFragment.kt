package com.magicsamplecase.presentation.scenes.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding4.view.clicks
import com.magicsamplecase.*
import com.magicsamplecase.presentation.utils.ErrorMapper.MappedError
import com.magicsamplecase.presentation.utils.toEditable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.custom_app_bar.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.logged_layout.*
import kotlinx.android.synthetic.main.login_layout.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class LoginFragment : BaseFragment(), LoginView, HandleBackButtom {

    override val onLoginButtonClick: PublishSubject<LoginRequestVM> = PublishSubject.create()
    override val onLogoutButtonClick: PublishSubject<Unit> = PublishSubject.create()

    companion object {
        fun getInstance() = LoginFragment()
    }

    @Inject
    override lateinit var cicerone: Cicerone<Router>

    @Inject
    override lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerLoginComponent.builder()
            .loginModule(LoginModule(this))
            .presentationComponent((parentFragment as MainContainerFragment).presentationComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAppBar(getString(R.string.login_app_bar_title))

        // queria ter mais tempo pra lidar com as configuraçoes da Action App Bar
        // custom_app_bar.menu.findItem(R.id.action_login).isVisible = false

        login_logout_button.clicks().subscribe {
            val email = email_input_txt.text.toString()
            val pass = password_input_txt.text.toString()

            onLoginButtonClick.onNext(LoginRequestVM(email = email, pass = pass))
        }.addTo(disposeBag)
    }

    override fun displayLoginScreen(email: String) {
        login_layout_container.visibility = View.VISIBLE
        logged_layout_container.visibility = View.GONE

        login_logout_button.text = getText(R.string.login)
        email_input_txt.text = email.toEditable()
    }

    override fun displayLoggedScreen(email: String) {
        login_layout_container.visibility = View.GONE
        logged_layout_container.visibility = View.VISIBLE

        login_logout_button.text = getText(R.string.logout)
        logged_user_text.text = email
    }

    override fun displayEmailError() = displayToast(getString(R.string.incorrect_email_message))

    override fun displayPasswordError() = displayToast(getString(R.string.incorrect_passw_message))

    override fun handleError(error: MappedError) {
        displayErrorDialog(
            error.message,
            positiveButtom = getString(R.string.try_again),
            positiveAction = { presenter.bindView() },
            negativeAction = {}
        )
    }
}