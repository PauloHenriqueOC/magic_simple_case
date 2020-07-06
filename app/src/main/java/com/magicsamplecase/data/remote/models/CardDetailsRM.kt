package com.magicsamplecase.data.remote.models

import com.google.gson.annotations.SerializedName
import com.magicsamplecase.domain.models.CardDetails

class CardDetailsResponseRM (
    @SerializedName("card")
    val card: CardDetailsRM
)

class CardDetailsRM (
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)

fun CardDetailsResponseRM.mapToDomain() = CardDetails(
    this.card.name,
    this.card.type
)