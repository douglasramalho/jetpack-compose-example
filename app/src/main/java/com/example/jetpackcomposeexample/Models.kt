package com.example.jetpackcomposeexample

import androidx.compose.Model

data class Story(
    val image: Int,
    val username: String
)

data class Feed(
    val username: String,
    val userImage: Int,
    val feedImage: Int,
    val like: Like
)

@Model
data class Like(
    var qtd: Int = 0,
    var isLiked: Boolean = false,
    var iconState: Int = R.drawable.ic_heart
)

// Fake stories
val stories = listOf(
    Story(R.drawable.user_01, "user01"),
    Story(R.drawable.user_02, "user02"),
    Story(R.drawable.user_03, "user03"),
    Story(R.drawable.user_04, "user04"),
    Story(R.drawable.user_05, "user05"),
    Story(R.drawable.user_06, "user06"),
    Story(R.drawable.user_07, "user07"),
    Story(R.drawable.user_08, "user08"),
    Story(R.drawable.user_09, "user09"),
    Story(R.drawable.user_10, "user10")
)

// Fake feeds
val feeds = listOf(
    Feed("user02", R.drawable.user_02, R.drawable.header, Like()),
    Feed("user03", R.drawable.user_03, R.drawable.header, Like()),
    Feed("user04", R.drawable.user_04, R.drawable.header, Like())
)