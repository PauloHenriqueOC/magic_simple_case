package com.magicsamplecase.presentation.card_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magicsamplecase.BaseFragment
import com.magicsamplecase.HandleBackButtom
import com.magicsamplecase.MainContainerFragment
import com.magicsamplecase.R
import kotlinx.android.synthetic.main.fragment_card_details.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CardDetailsFragment(val cardId: String) : BaseFragment(), CardDetailsView, HandleBackButtom {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var presenter: CardDetailsPresenter

    companion object {
        fun getInstance(id: String) = CardDetailsFragment(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerCardDetailsComponent.builder()
            .cardDetailsModule(CardDetailsModule(this, cardId))
            .presentationComponent((parentFragment as MainContainerFragment).presentationComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_card_details, container, false)

    override fun onBackPressed() {
        cicerone.router.exit()
    }

    override fun initView(cardDetailsVM: CardDetailsVM) {
        card_id_text.text = cardDetailsVM.type
    }
}