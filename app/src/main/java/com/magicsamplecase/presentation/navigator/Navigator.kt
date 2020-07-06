package com.magicsamplecase.presentation.navigator

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class Navigator (fragment: FragmentActivity, fragmentManager: FragmentManager, containerId: Int) :
    SupportAppNavigator(fragment, fragmentManager, containerId)
