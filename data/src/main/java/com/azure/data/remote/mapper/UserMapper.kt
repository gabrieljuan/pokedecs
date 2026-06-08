package com.azure.data.remote.mapper

import com.azure.domain.model.User
import com.couchbase.lite.Document

fun Document.toUserDomain(): User = User(
    username = getString("username").orEmpty(),
    phone = getString("phone").orEmpty(),
    email = getString("email").orEmpty(),
    about = getString("about").orEmpty(),
)