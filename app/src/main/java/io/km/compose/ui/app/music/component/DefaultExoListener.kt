package io.km.compose.ui.app.music.component

import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.text.Cue
import com.google.android.exoplayer2.text.CueGroup
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException
import com.google.android.exoplayer2.video.VideoSize
import timber.log.Timber


/**
 *
 * @author pengfei.huang
 * create on 2022/9/24
 */
class DefaultExoListener(private val listeners: List<Player.Listener>) :
    Player.Listener {

    override fun onPlayerError(error: PlaybackException) {
        val cause = error.cause
        Timber.e(cause)

        if (cause is HttpDataSourceException) {
            // An HTTP error occurred.
            // It's possible to find out more about the error both by casting and by
            // querying the cause.
            if (cause is InvalidResponseCodeException) {
                // Cast to InvalidResponseCodeException and retrieve the response code,
                // message and headers.
            } else {
                // Try calling httpError.getCause() to retrieve the underlying cause,
                // although note that it may be null.
            }
        }
        listeners.forEach { it.onPlayerError(error) }
    }

    override fun onPlayerErrorChanged(error: PlaybackException?) {
        listeners.forEach { it.onPlayerErrorChanged(error) }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        listeners.forEach { it.onPlaybackStateChanged(playbackState) }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        listeners.forEach { it.onIsPlayingChanged(isPlaying) }
    }

    override fun onEvents(player: Player, events: Player.Events) {
        listeners.forEach { it.onEvents(player, events) }
    }

    override fun onTimelineChanged(timeline: Timeline, reason: Int) {
        // Detecting when the playlist changes：When a media item is added, removed or moved
        if (reason == Player.TIMELINE_CHANGE_REASON_PLAYLIST_CHANGED) {
            // Update the UI according to the modified playlist (add, move or remove).
            // updateUiForPlaylist(timeline);
        }
        listeners.forEach { it.onTimelineChanged(timeline, reason) }
    }

    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        // Playlist transitions Whenever the player changes to a new media item in the playlist
        // The reason indicates whether this was an automatic transition, a seek (for example after calling player.next()),
        // a repetition of the same item, or caused by a playlist change (e.g., if the currently playing item is removed).
        listeners.forEach { it.onMediaItemTransition(mediaItem, reason) }
    }

    override fun onTracksChanged(tracks: Tracks) {
        listeners.forEach { it.onTracksChanged(tracks) }
    }

    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
        listeners.forEach { it.onMediaMetadataChanged(mediaMetadata) }
    }

    override fun onPlaylistMetadataChanged(mediaMetadata: MediaMetadata) {
        listeners.forEach { it.onPlaylistMetadataChanged(mediaMetadata) }
    }

    override fun onIsLoadingChanged(isLoading: Boolean) {
        listeners.forEach { it.onIsLoadingChanged(isLoading) }
    }

    override fun onLoadingChanged(isLoading: Boolean) {
        listeners.forEach { it.onLoadingChanged(isLoading) }
    }

    override fun onAvailableCommandsChanged(availableCommands: Player.Commands) {
        listeners.forEach { it.onAvailableCommandsChanged(availableCommands) }
    }

    override fun onTrackSelectionParametersChanged(parameters: TrackSelectionParameters) {
        listeners.forEach { it.onTrackSelectionParametersChanged(parameters) }
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        listeners.forEach { it.onPlayerStateChanged(playWhenReady, playbackState) }
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        listeners.forEach { it.onPlayWhenReadyChanged(playWhenReady, reason) }
    }

    override fun onPlaybackSuppressionReasonChanged(playbackSuppressionReason: Int) {
        listeners.forEach { it.onPlaybackSuppressionReasonChanged(playbackSuppressionReason) }
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
        listeners.forEach { it.onRepeatModeChanged(repeatMode) }
    }

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
        listeners.forEach { it.onShuffleModeEnabledChanged(shuffleModeEnabled) }
    }

    override fun onPositionDiscontinuity(reason: Int) {
        listeners.forEach { it.onPositionDiscontinuity(reason) }
    }

    override fun onPositionDiscontinuity(
        oldPosition: Player.PositionInfo,
        newPosition: Player.PositionInfo,
        reason: Int
    ) {
        listeners.forEach { it.onPositionDiscontinuity(oldPosition, newPosition, reason) }
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
        listeners.forEach { it.onPlaybackParametersChanged(playbackParameters) }
    }

    override fun onSeekBackIncrementChanged(seekBackIncrementMs: Long) {
        listeners.forEach { it.onSeekBackIncrementChanged(seekBackIncrementMs) }
    }

    override fun onSeekForwardIncrementChanged(seekForwardIncrementMs: Long) {
        listeners.forEach { it.onSeekForwardIncrementChanged(seekForwardIncrementMs) }
    }

    override fun onMaxSeekToPreviousPositionChanged(maxSeekToPreviousPositionMs: Long) {
        listeners.forEach { it.onMaxSeekToPreviousPositionChanged(maxSeekToPreviousPositionMs) }
    }

    override fun onSeekProcessed() {
        listeners.forEach { it.onSeekProcessed() }
    }

    override fun onAudioSessionIdChanged(audioSessionId: Int) {
        listeners.forEach { it.onAudioSessionIdChanged(audioSessionId) }
    }

    override fun onAudioAttributesChanged(audioAttributes: AudioAttributes) {
        listeners.forEach { it.onAudioAttributesChanged(audioAttributes) }
    }

    override fun onVolumeChanged(volume: Float) {
        listeners.forEach { it.onVolumeChanged(volume) }
    }

    override fun onSkipSilenceEnabledChanged(skipSilenceEnabled: Boolean) {
        listeners.forEach { it.onSkipSilenceEnabledChanged(skipSilenceEnabled) }
    }

    override fun onDeviceInfoChanged(deviceInfo: DeviceInfo) {
        listeners.forEach { it.onDeviceInfoChanged(deviceInfo) }
    }

    override fun onDeviceVolumeChanged(volume: Int, muted: Boolean) {
        listeners.forEach { it.onDeviceVolumeChanged(volume, muted) }
    }

    override fun onVideoSizeChanged(videoSize: VideoSize) {
        listeners.forEach { it.onVideoSizeChanged(videoSize) }
    }

    override fun onSurfaceSizeChanged(width: Int, height: Int) {
        listeners.forEach { it.onSurfaceSizeChanged(width, height) }
    }

    override fun onRenderedFirstFrame() {
        listeners.forEach { it.onRenderedFirstFrame() }
    }

    override fun onCues(cues: MutableList<Cue>) {
        listeners.forEach { it.onCues(cues) }
    }

    override fun onCues(cueGroup: CueGroup) {
        listeners.forEach { it.onCues(cueGroup) }
    }

    override fun onMetadata(metadata: Metadata) {
        listeners.forEach { it.onMetadata(metadata) }
    }
}