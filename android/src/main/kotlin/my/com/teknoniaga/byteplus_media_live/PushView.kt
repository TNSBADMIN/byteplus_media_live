package my.com.teknoniaga.byteplus_media_live

import android.annotation.SuppressLint
import android.content.Context
import android.view.SurfaceView
import androidx.annotation.UiThread
import androidx.constraintlayout.widget.ConstraintLayout
import com.pandora.ttsdk.newapi.LiveCoreBuilder
import com.ss.avframework.livestreamv2.Constants.MSG_INFO_AUDIO_STARTED_CAPTURE
import com.ss.avframework.livestreamv2.Constants.MSG_INFO_AUDIO_STOPED_CAPTURE
import com.ss.avframework.livestreamv2.Constants.MSG_INFO_VIDEO_STARTED_CAPTURE
import com.ss.avframework.livestreamv2.Constants.MSG_INFO_VIDEO_STOPED_CAPTURE
import com.ss.avframework.livestreamv2.core.LiveCore
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import my.com.teknoniaga.byteplus_media_live.models.PushEngineController
import my.com.teknoniaga.byteplus_media_live.models.PushPlayerOption
import io.flutter.plugin.common.EventChannel


@SuppressLint("ViewConstructor")
class PushView(
    context: Context,
    viewId: Int,
    binaryMessenger: BinaryMessenger,
    option: PushPlayerOption
) : ConstraintLayout(context), PushEngineController, MethodCallHandler, EventChannel.StreamHandler {

    private var sink: EventChannel.EventSink? = null
    private var pushEngine: LiveCore? = null

    private var isAudioCaptured: Boolean = false
    private var isVideoCaptured: Boolean = false
    private var channel: MethodChannel;

    init {
        inflate(context, R.layout.layout_push_view, this)
        val channelName = "push_engine_$viewId";

        //create a unique method channel for this instance
        channel = MethodChannel(binaryMessenger, channelName)
        channel.setMethodCallHandler(this)
        val messageChannel = EventChannel(binaryMessenger, "xxx")
        messageChannel.setStreamHandler(this)

        setupPushEngine(option)
        setListener()
    }


    override val audioCaptureStatus: Boolean
        get() = isAudioCaptured

    override val videoCaptureStatus: Boolean
        get() = isVideoCaptured


    @UiThread
    private fun setListener() {


        pushEngine?.setInfoListener { i, _, _ ->

            println("listener di setup $i sink  : ${sink == null}")
//            channel.invokeMethod(i.toString(), null)
            sink?.success("adadw");

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


    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        println("ooooooooooooooo: " + call.method);


        when (call.method) {
            "setUpListener" -> {
//                setListener()
                return result.success("setUpListener")
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

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {

        println("lahdkladlkwad $events");
        sink = events;

    }

    override fun onCancel(arguments: Any?) {
        sink = null
    }


}