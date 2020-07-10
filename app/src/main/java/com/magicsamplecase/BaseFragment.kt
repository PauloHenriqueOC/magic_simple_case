package com.magicsamplecase

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.magicsamplecase.presentation.navigator.LoginScreen
import kotlinx.android.synthetic.main.custom_app_bar.*
import kotlinx.android.synthetic.main.loading_indicator_layout.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

abstract class BaseFragment : Fragment(), BasePresenterView, DisposeView by DisposeDelegate(), HandleBackButtom {

    abstract val cicerone: Cicerone<Router>
    abstract val presenter: BasePresenter

    private var isCreatedController = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isCreatedController) presenter.bindView()
        setHasOptionsMenu(true)

        isCreatedController = true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        custom_app_bar.inflateMenu(R.menu.app_bar_menu)
        custom_app_bar.setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_login -> {
            cicerone.router.navigateTo( LoginScreen() )
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun setupAppBar(title: String) {
        custom_app_bar?.let { toolbar ->
            (activity as (AppCompatActivity)).setSupportActionBar(toolbar)
            toolbar.title = title
            toolbar.setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
            toolbar.menu.findItem(R.id.action_login)?.isVisible = false
        }
    }

    override fun displayLoading(value: Boolean) {
        loading_indicator?.visibility = if (value) View.VISIBLE else View.GONE
    }

    fun displayToast(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

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
                setTitle(getString(R.string.default_error_title))

                val posText = if (!positiveButtom.isNullOrEmpty()) positiveButtom else getString(R.string.ok)
                setPositiveButton(posText) { _, _ -> positiveAction()}

                val negText = if (!negativeButtom.isNullOrEmpty()) negativeButtom else getString(R.string.cancel)
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

    override fun onBackPressed() = cicerone.router.exit()
}