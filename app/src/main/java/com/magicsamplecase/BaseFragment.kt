package com.magicsamplecase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.loading_indicator_layout.*

abstract class BaseFragment : Fragment(), BasePresenterView, DisposeView by DisposeDelegate() {

    abstract val presenter: BasePresenter

    private var isCreatedController = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isCreatedController) presenter.bindView()

        isCreatedController = true
    }

    override fun onDestroy() {
        disposeAll()
        presenter.disposeAll()
        super.onDestroy()
    }

    override fun displayLoading(value: Boolean) {
        loading_indicator?.visibility = if (value) View.VISIBLE else View.GONE
    }
}