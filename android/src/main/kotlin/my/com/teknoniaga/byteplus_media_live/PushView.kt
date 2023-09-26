package my.com.teknoniaga.byteplus_media_live

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.annotation.UiThread
import androidx.constraintlayout.widget.ConstraintLayout
import com.ss.avframework.live.VeLivePusher
import com.ss.avframework.live.VeLivePusherConfiguration
import com.ss.avframework.live.VeLivePusherDef
import com.ss.avframework.live.VeLivePusherDef.VeLiveAudioCaptureConfiguration
import com.ss.avframework.live.VeLivePusherDef.VeLiveAudioEncoderConfiguration
import com.ss.avframework.live.VeLivePusherDef.VeLiveVideoCaptureConfiguration
import com.ss.avframework.live.VeLivePusherDef.VeLiveVideoEncoderConfiguration
import com.ss.avframework.live.VeLivePusherDef.VeLiveVideoResolution.VeLiveVideoResolution720P
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import my.com.teknoniaga.byteplus_media_live.models.PushEngineController
import my.com.teknoniaga.byteplus_media_live.models.PushPlayerOption
import my.com.teknoniaga.byteplus_media_live.models.PusherEventListener


@SuppressLint("ViewConstructor")
class PushView(
    context: Context, viewId: Int, binaryMessenger: BinaryMessenger, option: PushPlayerOption
) : ConstraintLayout(context), PushEngineController, MethodCallHandler, EventChannel.StreamHandler {

    private var sink: EventChannel.EventSink? = null
    private var mLivePusher: VeLivePusher? = null

    private var isAudioCaptured: Boolean = false
    private var isVideoCaptured: Boolean = false
    private var channel: MethodChannel;

    private val uiThreadHandler: Handler = Handler(Looper.getMainLooper())

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
//        start new style
        val listener = PusherEventListener();
        // Set an observer to listen for live pusher events.
        mLivePusher?.setObserver(listener)

        // Sets an observer to periodically report push-stream statistics.
        mLivePusher?.setStatisticsObserver(listener, 3);

        // Adds a listener for video frames captured by the live pusher.
        mLivePusher?.addVideoFrameListener(listener);
        // Adds a listener for audio frames captured by the live pusher.
        mLivePusher?.addAudioFrameListener(listener);

    }

    private fun setupPushEngine(option: PushPlayerOption) {
//        new version
        val videoCaptureConfig = VeLiveVideoCaptureConfiguration()
        videoCaptureConfig.width = option.videoWidth
        videoCaptureConfig.height = option.videoHeight
        videoCaptureConfig.fps = option.videoFps

        // Create a VeLiveAudioCaptureConfiguration instance.
        // Create a VeLiveAudioCaptureConfiguration instance.
        val audioCaptureConfig = VeLiveAudioCaptureConfiguration()
// Set the sample rate. The default value is VeLiveAudioSampleRate44100.
// Set the sample rate. The default value is VeLiveAudioSampleRate44100.
        audioCaptureConfig.sampleRate =
            VeLivePusherDef.VeLiveAudioSampleRate.VeLiveAudioSampleRate44100
// Set the number of audio channels. The default value is VeLiveAudioChannelStereo.
// Set the number of audio channels. The default value is VeLiveAudioChannelStereo.
        audioCaptureConfig.channel = VeLivePusherDef.VeLiveAudioChannel.VeLiveAudioChannelStereo


        // Create a VeLivePusherConfiguration instance.
        // Create a VeLivePusherConfiguration instance.
        val config = VeLivePusherConfiguration()
        // Set the context.
        // Set the context.
        config.context = context
        // The number of attempts to reconnect after the initial attempt fails. The default value is 3.
        // The number of attempts to reconnect after the initial attempt fails. The default value is 3.
        config.reconnectCount = 3
        // The time interval between each attempt to reconnect, in seconds. The default value is 5.
        // The time interval between each attempt to reconnect, in seconds. The default value is 5.
        config.reconnectIntervalSeconds = 5
        // Configure video capture settings.
        // Configure video capture settings.
        config.videoCaptureConfig = videoCaptureConfig
        // Configure audio capture settings.
        // Configure audio capture settings.
        config.audioCaptureConfig = audioCaptureConfig

        mLivePusher = config.build()

//        bitrate
        val videoEncoderConfig = VeLiveVideoEncoderConfiguration()
        // Set the video resolution. The SDK provides default settings based on different video resolutions.
        // Set the video resolution. The SDK provides default settings based on different video resolutions.
        videoEncoderConfig.resolution = VeLiveVideoResolution720P
        // The video codec. The default value is VeLiveVideoCodecH264.
        // The video codec. The default value is VeLiveVideoCodecH264.
        videoEncoderConfig.codec = VeLivePusherDef.VeLiveVideoCodec.VeLiveVideoCodecH264
        // The target encoding bitrate, in Kbps.
        // The target encoding bitrate, in Kbps.
        videoEncoderConfig.bitrate = 1200
        // The minimum video encoding bitrate, in Kbps, when the adaptive bitrate (ABR) feature is enabled.
        // The minimum video encoding bitrate, in Kbps, when the adaptive bitrate (ABR) feature is enabled.
        videoEncoderConfig.minBitrate = 800
        // The maximum video encoding bitrate, in Kbps, when the adaptive bitrate (ABR) feature is enabled.
        // The maximum video encoding bitrate, in Kbps, when the adaptive bitrate (ABR) feature is enabled.
        videoEncoderConfig.maxBitrate = 1900
        // The video GOP size, in seconds. The default value is 2.
        // The video GOP size, in seconds. The default value is 2.
        videoEncoderConfig.gopSize = 2
        // The encoded frame rate, in fps. The default value is 15.
        // The encoded frame rate, in fps. The default value is 15.
        videoEncoderConfig.fps = 15
        // Whether to enable B frames. The default value is false.
        // Whether to enable B frames. The default value is false.
        videoEncoderConfig.isEnableBFrame = false
        // Whether to enable hardware encoding. The default value is true.
        // Whether to enable hardware encoding. The default value is true.
        videoEncoderConfig.isEnableAccelerate = true

//        audio bitrate
        val audioEncoderConfig = VeLiveAudioEncoderConfiguration()
        // The audio encoding bitrate, in Kbps. The default value is 64.
        // The audio encoding bitrate, in Kbps. The default value is 64.
        audioEncoderConfig.bitrate = 64
        // The encoding sample rate. The default value is VeLiveAudioSampleRate44100.
        // The encoding sample rate. The default value is VeLiveAudioSampleRate44100.
        audioEncoderConfig.sampleRate =
            VeLivePusherDef.VeLiveAudioSampleRate.VeLiveAudioSampleRate44100
        // The number of audio channels. The default value is VeLiveAudioChannelStereo.
        // The number of audio channels. The default value is VeLiveAudioChannelStereo.
        audioEncoderConfig.channel = VeLivePusherDef.VeLiveAudioChannel.VeLiveAudioChannelStereo
        // The AAC encoding format. The default value is VeLiveAudioAACProfileLC.
        // The AAC encoding format. The default value is VeLiveAudioAACProfileLC.
        audioEncoderConfig.profile = VeLivePusherDef.VeLiveAudioProfile.VeLiveAudioAACProfileLC


        // Pass in the above settings to the live pusher.
        // Pass in the above settings to the live pusher.
        mLivePusher?.videoEncoderConfiguration = videoEncoderConfig
        mLivePusher?.audioEncoderConfiguration = audioEncoderConfig

        //        TODO create 3 more enum from dart to pass for this data
        mLivePusher?.setVideoMirror(
            VeLivePusherDef.VeLiveVideoMirrorType.VeLiveVideoMirrorCapture, true
        )


        // bind with UI
        mLivePusher?.setRenderView(findViewById(R.id.live_push_surface_view))


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
//        new
        mLivePusher?.release()
        mLivePusher = null

    }

    override fun startVideoCapture() {
//        new
        // Start video capture.
        mLivePusher?.startVideoCapture(VeLivePusherDef.VeLiveVideoCaptureType.VeLiveVideoCaptureFrontCamera)


    }

    override fun stopVideoCapture() {

//        new
        // Start video capture.
        mLivePusher?.stopVideoCapture()

    }

    override fun startAudioCapture() {
        // Start audio capture.
        mLivePusher?.startAudioCapture(VeLivePusherDef.VeLiveAudioCaptureType.VeLiveAudioCaptureMicrophone)


    }

    override fun stopAudioCapture() {
        mLivePusher?.stopAudioCapture()

    }

    override fun startPublish(url: String) {
        mLivePusher?.startPush(url);

    }

    override fun stopPublish() {
        mLivePusher?.stopPush()

    }

    fun test() {
        sink?.success("test")
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        println("ooooooooooooooo: " + call.method);


        when (call.method) {

            "test" -> {
                test()
                return result.success("test run");
            }

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