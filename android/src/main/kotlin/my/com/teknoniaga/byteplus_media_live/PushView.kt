package my.com.teknoniaga.byteplus_media_live

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceView
import androidx.constraintlayout.widget.ConstraintLayout
import com.pandora.ttsdk.newapi.LiveCoreBuilder
import com.ss.avframework.livestreamv2.core.LiveCore

class PushView(context: Context, url: String) : ConstraintLayout(context) {

    private var pushEngine: LiveCore? = null

    init {
        inflate(context, R.layout.layout_push_view, this)
        setupPushEngine(url)
    }

    private fun setupPushEngine(url: String) {
        val builder = LiveCoreBuilder()
        // setup live parameters, just for reference
        builder.videoWidth = 720
        builder.videoHeight = 1280
        // fps
        builder.videoFps = 15
        builder.videoBitrate = 1200 * 1000
        builder.videoMaxBitrate = 1900 * 1000
        builder.videoMinBitrate = 800 * 1000
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