package com.magicsamplecase

import io.reactivex.rxjava3.disposables.CompositeDisposable

interface BasePresenterView {
    val disposeBag: CompositeDisposable
}