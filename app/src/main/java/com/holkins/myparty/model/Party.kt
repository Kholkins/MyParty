package com.holkins.myparty.model

data class Party (
    val title: String,
    val picture: String,
    var ownerName: String,
    var ownerPictureURL: String,
    var guests: List<Person>
)