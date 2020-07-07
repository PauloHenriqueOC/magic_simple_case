package com.magicsamplecase.presentation.scenes.card_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magicsamplecase.*
import kotlinx.android.synthetic.main.fragment_card_details.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CardDetailsFragment(private val cardId: String) : BaseFragment(), CardDetailsView, HandleBackButtom {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    override lateinit var presenter: CardDetailsPresenter

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