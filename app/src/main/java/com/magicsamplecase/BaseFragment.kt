package com.magicsamplecase

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.magicsamplecase.presentation.utils.MappedError
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


    override fun displayLoading(value: Boolean) {
        loading_indicator?.visibility = if (value) View.VISIBLE else View.GONE
    }

    fun displayErrorDialog(
        message: String,
        positiveButtom: String? = null,
        negativeButtom: String? = null,
        positiveAction: () -> Unit,
        negativeAction: (() -> Unit)) // pass a empty function if you just need to close
    {
        context?.let { context ->
            AlertDialog.Builder(context).apply {
                setMessage(message)
                setTitle("Eita... =(")

                val posText = if (!positiveButtom.isNullOrEmpty()) positiveButtom else "Ok"
                setPositiveButton(posText) { _, _ -> positiveAction()}

                val negText = if (!negativeButtom.isNullOrEmpty()) negativeButtom else "Cancelar"
                setNegativeButton(negText) { _, _ -> negativeAction() }

                create()
                show()
            }
        }
    }

    override fun onDestroy() {
        disposeAll()
        presenter.disposeAll()
        super.onDestroy()
    }
}