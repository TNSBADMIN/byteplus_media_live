package my.com.teknoniaga.byteplus_media_live.models

import com.ss.avframework.live.VeLiveAudioFrame
import com.ss.avframework.live.VeLivePusherDef
import com.ss.avframework.live.VeLivePusherObserver
import com.ss.avframework.live.VeLiveVideoFrame

class PusherEventListener(val sendStream: (message: Any) -> Unit) : VeLivePusherObserver,
    VeLivePusherDef.VeLivePusherStatisticsObserver, VeLivePusherDef.VeLiveVideoFrameListener,
    VeLivePusherDef.VeLiveAudioFrameListener {

    override fun onStatusChange(status: VeLivePusherDef.VeLivePusherStatus) {
        sendStream(status.toString())
    }

    override fun onCaptureVideoFrame(frame: VeLiveVideoFrame?) {
        sendStream("onCaptureVideoFrame")
    }

    override fun onCaptureAudioFrame(frame: VeLiveAudioFrame?) {
        sendStream("onCaptureAudioFrame")
    }

    override fun onFirstVideoFrame(type: VeLivePusherDef.VeLiveFirstFrameType?, timestampMs: Long) {
        sendStream("onFirstVideoFrame")
    }

    override fun onFirstAudioFrame(type: VeLivePusherDef.VeLiveFirstFrameType?, timestampMs: Long) {
        sendStream("onFirstAudioFrame")
    }


}