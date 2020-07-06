package com.magicsamplecase.data.remote

import com.magicsamplecase.data.remote.models.CardDetailsResponseRM
import com.magicsamplecase.data.remote.models.CardsRemoteModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CardsRemoteDS {
    @GET("cards")
    fun getCards() : Single<CardsRemoteModel>

    @GET("cards/{id}")
    fun getCardDetails(@Path("id") id: String) : Single<CardDetailsResponseRM>
}