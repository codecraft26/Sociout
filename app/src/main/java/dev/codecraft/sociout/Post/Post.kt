package dev.codecraft.sociout.Post

import dev.codecraft.sociout.data.User


class Post(
    val text: String = "",
    val createdBy: User = User(),
    val createdAt: Long = 0L,
)