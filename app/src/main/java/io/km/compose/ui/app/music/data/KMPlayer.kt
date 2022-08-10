package io.km.compose.ui.app.music.data

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri

/**
 *
 * @author pengfei.huang
 * create on 2022/8/9
 */
class KMPlayer {

    fun create(context: Context) {
        // initialize Uri here
        val myUri: Uri = Uri.parse("/sdcard/feiya/action/F0591V21.mp4")
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(context, myUri)
            prepare()
            start()
        }
    }
}