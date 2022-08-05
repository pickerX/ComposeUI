package io.km.compose.ui.config

import io.km.compose.ui.R
import io.km.compose.ui.app.news.KMNewsActivity

/**
 *  all enable app demo entry here
 */
val Entries = arrayOf(
    Entry("News App", KMNewsActivity::class.java, iconLottieRes = R.raw.lottie_news),
    Entry("Music App", KMNewsActivity::class.java, R.mipmap.ic_music_heart),
    Entry("Instagram", KMNewsActivity::class.java, iconLottieRes = R.raw.lottie_instagram),
    Entry("Chat App", KMNewsActivity::class.java, R.mipmap.ic_chat),
)

data class Entry(
    val name: String,
    val clazz: Class<*>,
    val iconRes: Int? = null,
    val iconLottieRes: Int? = null,
)