package com.magicsamplecase.presentation.scenes.login

import com.magicsamplecase.dagger.PresentationComponent
import com.magicsamplecase.dagger.ViewScope
import dagger.Component
import dagger.Module
import dagger.Provides

@ViewScope
@Component(dependencies = [PresentationComponent::class], modules = [LoginModule::class])
interface LoginComponent {
    fun inject(fragment: LoginFragment)
}

@Module
class LoginModule(private val view: LoginView) {
    @Provides
    fun providesPresenter() = view
}