package com.magicsamplecase

import io.reactivex.rxjava3.disposables.CompositeDisposable

interface DisposeView {
    var disposeBag: CompositeDisposable
    fun disposeAll()
}

class DisposeDelegate : DisposeView {
    override var disposeBag = CompositeDisposable()

    override fun disposeAll() {
        disposeBag.dispose()
        disposeBag = CompositeDisposable()
    }
}