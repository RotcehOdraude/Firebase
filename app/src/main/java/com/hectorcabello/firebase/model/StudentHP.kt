package com.hectorcabello.firebase.model

import com.google.gson.annotations.SerializedName

data class StudentHP(
    @SerializedName("id"               ) var id              : String?           = null,
    @SerializedName("name"             ) var name            : String?           = null,
    @SerializedName("actor"            ) var actor           : String?           = null,
    @SerializedName("image"            ) var image           : String?           = null
)
