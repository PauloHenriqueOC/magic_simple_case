package com.magicsamplecase

import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseFragment : Fragment() {

    val baseDisposeBag: CompositeDisposable
        get() = CompositeDisposable()

    private val basePresenter = BasePresenter()
    private val disposeView = DisposeView()

    override fun onDestroy() {
        disposeView.disposeAll()
        basePresenter.disposeBag.clear()
        baseDisposeBag.clear()
        super.onDestroy()
    }
}