package com.magicsamplecase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.magicsamplecase.dagger.*
import com.magicsamplecase.presentation.navigator.CardListScreen
import com.magicsamplecase.presentation.navigator.Navigator
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

open class MainContainerFragment : Fragment(), HandleBackButtom {

    @Inject
    lateinit var  navigator: Navigator
    @Inject
    lateinit var cicerone: Cicerone<Router>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main_container, container, false)


    val presentationComponent: PresentationComponent? by lazy {
        context?.let {
            DaggerPresentationComponent.builder()
                .contextModule(ContextModule(it))
                .navigatorModule(
                    NavigatorModule(
                        activity as FragmentActivity,
                        childFragmentManager,
                        R.id.main_fragment_container
                ))
                .routerModule(RouterModule())
                .build()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presentationComponent?.injectMainFragment(this)

        cicerone.router.newRootChain(CardListScreen())
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        cicerone.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        // get current Fragment this way
        val childFragment =
            childFragmentManager.findFragmentById(R.id.main_fragment_container) as? HandleBackButtom

        childFragment?.onBackPressed()
    }
}