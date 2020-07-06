package com.magicsamplecase.data.remote.models

import com.google.gson.annotations.SerializedName
import com.magicsamplecase.domain.models.Card
import com.magicsamplecase.domain.models.Cards

class CardsRemoteModel(
    @SerializedName("cards")
    var cardRemoteModels: List<CardRemoteModel>
)

class CardRemoteModel (
    @SerializedName("name")
    var cardName: String,

    @SerializedName("id")
    var id: String
)

fun CardsRemoteModel.mapToDomain() =
    Cards( this.cardRemoteModels.map {
        it.mapToDomain()
    } )

fun CardRemoteModel.mapToDomain() =
    Card(this.cardName, this.id)