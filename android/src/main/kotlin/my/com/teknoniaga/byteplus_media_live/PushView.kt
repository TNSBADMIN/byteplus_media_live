package my.com.teknoniaga.byteplus_media_live

import android.content.Context
import android.view.SurfaceView
import androidx.constraintlayout.widget.ConstraintLayout
import com.pandora.ttsdk.newapi.LiveCoreBuilder
import com.ss.avframework.livestreamv2.core.LiveCore
import my.com.teknoniaga.byteplus_media_live.models.PushPlayerOption

class PushView(context: Context, url: String, option: PushPlayerOption) : ConstraintLayout(context) {

    private var pushEngine: LiveCore? = null

    init {
        inflate(context, R.layout.layout_push_view, this)
        setupPushEngine(url, option)
    }

    private fun setupPushEngine(url: String, option: PushPlayerOption) {
        val builder = LiveCoreBuilder()
        // setup live parameters, just for reference
        builder.videoWidth = option.videoWidth
        builder.videoHeight = option.videoHeight
        // fps
        builder.videoFps = option.videoFps
        builder.videoBitrate = option.videoBitrate
        builder.videoMaxBitrate = option.videoMaxBitrate
        builder.videoMinBitrate = option.videoMinBitrate
        // init pushEngine
        pushEngine = builder.liveCoreEngine.liveCore
        // setup preview
        val surfaceView = findViewById<SurfaceView>(R.id.live_push_surface_view)
        pushEngine?.setDisplay(surfaceView)
        // video capture
        pushEngine?.startVideoCapture()
        // audio capture
        pushEngine?.startAudioCapture()
        // start publish
        pushEngine?.start(url)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        pushEngine?.stopAudioCapture()
        pushEngine?.stopVideoCapture()
        pushEngine?.stop()
        pushEngine?.release()
    }
}