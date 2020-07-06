package com.magicsamplecase

import io.reactivex.rxjava3.disposables.CompositeDisposable

class DisposeView : DisposeDelegate {
    override val disposeBag: CompositeDisposable
        get() = CompositeDisposable()

    fun disposeAll() = disposeBag.clear()
}