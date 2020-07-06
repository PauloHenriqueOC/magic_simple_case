package com.magicsamplecase

import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BasePresenter : BasePresenterView {
    override val disposeBag: CompositeDisposable
        get() = CompositeDisposable()
}