package com.magicsamplecase.presentation.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.jakewharton.rxbinding4.view.clicks
import com.magicsamplecase.DisposeDelegate
import com.magicsamplecase.DisposeView
import com.magicsamplecase.R
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.card_item_layout.view.*

class CardListAdapter(private var data: MutableList<CardViewModel>) : Adapter<CardListAdapter.ViewHolder>() {

    val onClickSubject: PublishSubject<String> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item_layout, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(textView)
    }

    override fun getItemCount() =
        data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(data[position])

    fun updateData(newData: List<CardViewModel>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), DisposeDelegate by DisposeView() {

        fun bindItem(item: CardViewModel) {
            itemView.card_name_txt.text = item.cardName

            itemView.item_container.clicks().subscribe {
                onClickSubject.onNext(item.id)
            }.addTo(disposeBag)
        }
    }
}