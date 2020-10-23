package com.yuvrajpatel.thescoretask.model

data class Player(
    val id: Int,
    val first_name: String?,
    val last_name: String?,
    val position: String?,
    val number: Int
)

data class Team(
    val wins: Int,
    val losses: Int,
    val full_name: String?,
    val id: Int,
    val players: List<Player>?
)
