package io.km.compose.ui.app

/**
 *
 * @author pengfei.huang
 * create on 2022/8/10
 */
sealed class Router(val path: String) {

    override fun toString(): String {
        return path
    }

    interface Music {
        object Home : Router("home")
        object Search : Router("search")
        object Pin : Router("pin")
    }


    interface Chat {}
    interface Instagram {}
    interface News {}
}