package com.azure.pokedecs.navigation

import android.net.Uri

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"

    object Home {
        const val ARGS_USERNAME = "username"
        const val ROUTE = "home/{$ARGS_USERNAME}"
        fun createRoute(username: String) =
            "home/${Uri.decode(username)}"
    }

    object PokeDetail {
        const val ARGS_NAME = "name"
        const val ROUTE = "detail/{$ARGS_NAME}"
        fun createRoute(name: String) =
            "detail/${Uri.decode(name)}"
    }
}