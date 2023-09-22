package my.com.teknoniaga.byteplus_media_live

import android.annotation.SuppressLint
import android.content.Context
import android.view.SurfaceView
import androidx.constraintlayout.widget.ConstraintLayout
import com.pandora.ttsdk.newapi.LiveCoreBuilder
import com.ss.avframework.livestreamv2.Constants.MSG_INFO_AUDIO_STARTED_CAPTURE
import com.ss.avframework.livestreamv2.Constants.MSG_INFO_AUDIO_STOPED_CAPTURE
import com.ss.avframework.livestreamv2.Constants.MSG_INFO_STARTING_PUBLISH
import com.ss.avframework.livestreamv2.Constants.MSG_INFO_VIDEO_STARTED_CAPTURE
import com.ss.avframework.livestreamv2.Constants.MSG_INFO_VIDEO_STOPED_CAPTURE
import com.ss.avframework.livestreamv2.core.LiveCore
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import my.com.teknoniaga.byteplus_media_live.models.PushEngineController
import my.com.teknoniaga.byteplus_media_live.models.PushPlayerOption
import com.ss.avframework.livestreamv2.ILiveStream
import com.ss.avframework.livestreamv2.ILiveStream.ILiveStreamErrorListener
import com.ss.videoarch.liveplayer.model.LiveInfoSource
import com.ss.videoarch.liveplayer.model.LiveStreamInfo


@SuppressLint("ViewConstructor")
class PushView(
    context: Context,
    viewId: Int,
    binaryMessenger: BinaryMessenger,
    option: PushPlayerOption
) : ConstraintLayout(context), PushEngineController, MethodCallHandler {

    private var pushEngine: LiveCore? = null

    private var isAudioCaptured: Boolean = false
    private var isVideoCaptured: Boolean = false

    init {
        inflate(context, R.layout.layout_push_view, this)

        //create a unique method channel for this instance
        val channel = MethodChannel(binaryMessenger, "push_engine_$viewId")
        channel.setMethodCallHandler(this)

        setupPushEngine(option)
        setListener()
    }


    override val audioCaptureStatus: Boolean
        get() = isAudioCaptured

    override val videoCaptureStatus: Boolean
        get() = isVideoCaptured


    private fun setListener() {

        pushEngine?.setInfoListener { i, _, _ ->

            when (i) {
                MSG_INFO_AUDIO_STARTED_CAPTURE -> isAudioCaptured = true
                MSG_INFO_AUDIO_STOPED_CAPTURE -> isAudioCaptured = false
                MSG_INFO_VIDEO_STARTED_CAPTURE -> isVideoCaptured = true
                MSG_INFO_VIDEO_STOPED_CAPTURE -> isVideoCaptured = false

            }

        }

        pushEngine?.setErrorListener { _, _, e -> println("error mesej is: $e") }
    }

    private fun setupPushEngine(option: PushPlayerOption) {
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
        if (option.enablePreview) {
            val surfaceView = findViewById<SurfaceView>(R.id.live_push_surface_view)
            pushEngine?.setDisplay(surfaceView)
        }


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        pushEngine?.stopAudioCapture()
        pushEngine?.stopVideoCapture()
        pushEngine?.stop()
        pushEngine?.release()
    }

    override fun startVideoCapture() {
        // video capture
        pushEngine?.startVideoCapture()

    }

    override fun stopVideoCapture() {
        pushEngine?.stopVideoCapture()
    }

    override fun startAudioCapture() {
        // audio capture
        pushEngine?.startAudioCapture()

    }

    override fun stopAudioCapture() {
        pushEngine?.stopAudioCapture()
    }

    override fun startPublish(url: String) {
        // start publish
        pushEngine?.start(url)
    }

    override fun stopPublish() {
        pushEngine?.stop()
    }

    fun test() {
        println("berjaya lari");
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        println("ooooooooooooooo: " + call.method);


        when (call.method) {
            "test" -> {
                test()
                return result.success("ada")
            }

            "startVideoCapture" -> {
                startVideoCapture()
                return result.success("startVideoCapture")
            }

            "stopVideoCapture" -> {
                stopVideoCapture()
                return result.success("stopVideoCapture")
            }

            "startAudioCapture" -> {
                startAudioCapture()
                return result.success("startAudioCapture")
            }

            "stopAudioCapture" -> {
                stopAudioCapture()
                return result.success("stopAudioCapture")
            }

            "startPublish" -> {
                val url = call.arguments as String;
                startPublish(url)

                return result.success("start live")
            }

            "stopPublish" -> {
                stopPublish()
                return result.success("stop")
            }

            "videoCaptureStatus" -> result.success(videoCaptureStatus)
            "audioCaptureStatus" -> result.success(audioCaptureStatus)


            else -> result.notImplemented()
        }
    }


}