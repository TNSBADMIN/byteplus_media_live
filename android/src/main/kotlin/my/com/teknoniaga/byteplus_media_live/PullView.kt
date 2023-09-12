package my.com.teknoniaga.byteplus_media_live

import android.annotation.SuppressLint
import android.content.Context
import android.view.SurfaceView
import androidx.constraintlayout.widget.ConstraintLayout
import com.pandora.common.env.Env
import com.ss.videoarch.liveplayer.VeLivePlayer
import com.ss.videoarch.liveplayer.VeLivePlayerConfiguration
import com.ss.videoarch.liveplayer.VeLivePlayerDef.VeLivePlayerFillMode
import com.ss.videoarch.liveplayer.VideoLiveManager
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import my.com.teknoniaga.byteplus_media_live.models.PullEngineController


@SuppressLint("ViewConstructor")
class PullView(context: Context, viewId: Int, binaryMessenger: BinaryMessenger) : ConstraintLayout(context), PullEngineController, MethodChannel.MethodCallHandler {

    private var mLivePlayer: VeLivePlayer = VideoLiveManager(Env.getApplicationContext())

    init {
        println("native pull masuk view init")
        inflate(context, R.layout.pull_view, this)
        println("native pull masuk view init after inflate")


        //create a unique method channel for this instance
        val channel = MethodChannel(binaryMessenger, "pull_engine_$viewId")
        println("native pull masuk view init after create chanel")
        channel.setMethodCallHandler(this)
        println("native pull masuk view init after set method handler class")

        setupPullEngine()

        println("native pull masuk view init after setup engine")
    }

    private fun setupPullEngine() {
        // Create a VeLivePlayerConfiguration instance.
        // Create a VeLivePlayerConfiguration instance.
        val config = VeLivePlayerConfiguration()
        // Enable periodic callbacks that provide playback information.
        // Enable periodic callbacks that provide playback information.
        config.enableStatisticsCallback = true
        // Enable local DNS prefetch.
        // Enable local DNS prefetch.
        config.enableLiveDNS = true
        // Configure the initial settings of the player.
        // Configure the initial settings of the player.
        mLivePlayer.setConfig(config)

        val surfaceView = findViewById<SurfaceView>(R.id.live_pull_surface_view)
        mLivePlayer.setSurfaceHolder(surfaceView.holder)
    }


    override fun setPlayUrl(url: String) {
        mLivePlayer.setPlayUrl(url)

    }

    override fun setRenderFillMode(mode: VeLivePlayerFillMode) {
        VeLivePlayerFillMode.VeLivePlayerFillModeAspectFit
        mLivePlayer.setRenderFillMode(mode)
    }

    fun test() {
        println("test dari pull engine native")
    }

    override fun play() {
        mLivePlayer.play()
    }

    override fun pause() {
        mLivePlayer.pause()
    }

    override fun stop() {
        mLivePlayer.stop()
    }


    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "test" -> {
                test()
                return result.success("test from pull engine")
            }

            "setPlayUrl" -> {
                val url = call.arguments as String;
                setPlayUrl(url)

                return result.success("set play url complete")
            }

            "setRenderFillMode" -> {
                val fillMode: VeLivePlayerFillMode = when (call.arguments as String) {
                    "aspectFill" -> VeLivePlayerFillMode.VeLivePlayerFillModeAspectFill
                    "fullFill" -> VeLivePlayerFillMode.VeLivePlayerFillModeFullFill
                    "aspectFit" -> VeLivePlayerFillMode.VeLivePlayerFillModeAspectFit
                    else -> return result.notImplemented()
                }


                setRenderFillMode(fillMode)
                return result.success("set render fill mode complete");
            }

            "play" -> {
                play()
                return result.success("play pull stream")
            }

            "pause" -> {
                pause()
                return result.success("pause pull stream")
            }

            "stop" -> {
                stop()
                return result.success("stop pull stream")
            }

            else -> result.notImplemented()
        }
    }

}