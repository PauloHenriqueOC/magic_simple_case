package com.magicsamplecase

import io.reactivex.rxjava3.disposables.CompositeDisposable

interface DisposeDelegate {
    val disposeBag: CompositeDisposable
}