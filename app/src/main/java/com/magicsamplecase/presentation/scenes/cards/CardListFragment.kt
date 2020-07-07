package com.magicsamplecase.presentation.scenes.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.magicsamplecase.*
import com.magicsamplecase.presentation.navigator.CardDetailsScreen
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.android.synthetic.main.fragment_card_list.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CardListFragment : BaseFragment(),
    CardListView, HandleBackButtom {

    private lateinit var viewManager: LinearLayoutManager
    private lateinit var viewAdapter: CardListAdapter

    private val cardList = mutableListOf<CardViewModel>()

    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    override lateinit var presenter: CardListPresenter

    companion object {
        fun getInstance() = CardListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerCardListComponent.builder()
            .cardListModule(CardListModule(this))
            .presentationComponent((parentFragment as MainContainerFragment).presentationComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_card_list, container, false)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        viewAdapter = CardListAdapter(cardList)

        card_list_rc_view.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewAdapter.onClickSubject.subscribe { id ->
            cicerone.router.navigateTo(CardDetailsScreen(id))
        }.addTo(disposeBag)
    }
    override fun updateCardList(data: CardsViewModel) {
        cardList.addAll(data.cards)
        viewAdapter.updateData(cardList)
    }

    override fun onBackPressed() =
        cicerone.router.exit()
}