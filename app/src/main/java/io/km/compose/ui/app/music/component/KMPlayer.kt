package io.km.compose.ui.app.music.component

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import timber.log.Timber

/**
 * exo player for audio & video
 *
 * @author pengfei.huang
 * create on 2022/8/9
 */
open class KMPlayer : XPlayer {

    private val mListeners = mutableListOf<Player.Listener>()
    private val internalListener = DefaultExoListener(mListeners)

    lateinit var exo: ExoPlayer

    fun create(context: Context) {
        exo = ExoPlayer.Builder(context).build()

        exo.addListener(internalListener)
    }

    fun createPlayerList(vararg url: String) {
        val items = url.map { MediaItem.fromUri(it) }
        // add to play list
        exo.addMediaItems(items)

    }

    fun registerListener(listener: Player.Listener) {
        if (mListeners.contains(listener)) return
        // Add a listener to receive events from the player.
        val rt = mListeners.add(listener)

        if (rt) Timber.i("listener size:${mListeners.size}")
    }

    fun unRegisterListener(listener: Player.Listener) {
        val rt = mListeners.remove(listener)
        if (rt) Timber.i("listener size:${mListeners.size}")
    }

    override fun play(): Boolean {
        TODO("Not yet implemented")
    }

    override fun pause(): Boolean {
        TODO("Not yet implemented")
    }

    override fun stop(): Boolean {
        TODO("Not yet implemented")
    }

    override fun seekTo(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPrevious(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasNext(): Boolean {
        TODO("Not yet implemented")
    }

    override fun previous(): Boolean {
        TODO("Not yet implemented")
    }

    override fun next(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setRepeatMode(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setRepeatMode(mode: RepeatMode) {
        when (mode) {
            is RepeatMode.RepeatAll -> exo.repeatMode = ExoPlayer.REPEAT_MODE_ALL
            is RepeatMode.RepeatOne -> exo.repeatMode = ExoPlayer.REPEAT_MODE_ONE
            is RepeatMode.RepeatNone -> {
                exo.repeatMode = ExoPlayer.REPEAT_MODE_OFF
                exo.shuffleModeEnabled = false
            }
            is RepeatMode.RepeatShuffle -> exo.shuffleModeEnabled = true
        }
    }

    override fun setPlaybackParameters(): Boolean {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }
}

