package io.km.compose.ui.app.music.component

import com.google.android.exoplayer2.Player

/**
 *
 * @author pengfei.huang
 * create on 2022/9/24
 */
interface XPlayer {
    /** play  pause start and pause playback.
     *
     */
    fun play(): Boolean

    fun pause(): Boolean

    fun stop(): Boolean

    /** seekTo allows seeking within the media.
     *
     */
    fun seekTo(): Boolean

    /**
     *  hasPrevious, hasNext, previous and next allow navigating through the playlist.
     */
    fun hasPrevious(): Boolean
    fun hasNext(): Boolean

    fun previous(): Boolean

    fun next(): Boolean

    /**
     * setRepeatMode controls if and how media is looped.
     */
    fun setRepeatMode(): Boolean

    /**
     * setPlaybackParameters adjusts playback speed and audio pitch.
     */
    fun setPlaybackParameters(): Boolean

    /**
     *
     */
    fun setRepeatMode(mode: RepeatMode)

    /**
     * release resource & objects
     */
    fun release()

    /**
     *
     */
    interface Listener {


    }

}