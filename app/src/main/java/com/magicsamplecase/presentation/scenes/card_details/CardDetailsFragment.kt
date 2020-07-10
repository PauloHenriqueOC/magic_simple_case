package com.magicsamplecase.presentation.scenes.card_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magicsamplecase.*
import com.magicsamplecase.presentation.utils.ErrorMapper.MappedError
import kotlinx.android.synthetic.main.fragment_card_details.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CardDetailsFragment(private val cardId: String) : BaseFragment(), CardDetailsView {

    private var model: CardDetailsVM? = null

    @Inject
    override lateinit var cicerone: Cicerone<Router>

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAppBar(getString(R.string.card_details_app_bar_title))
    }

    override fun onResume() {
        super.onResume()

        // TODO melhorar isso...
        model?.let {
            card_id_text.text = it.type
        }
    }

    override fun initView(cardDetailsVM: CardDetailsVM) {
        model = cardDetailsVM
        card_id_text.text = cardDetailsVM.type
    }

    override fun handleError(error: MappedError) {
        displayErrorDialog(
            error.message,
            positiveButtom = getString(R.string.try_again),
            positiveAction = { presenter.bindView() },
            negativeAction = {}
        )
    }
}