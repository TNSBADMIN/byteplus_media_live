package my.com.teknoniaga.byteplus_media_live.models

import com.ss.avframework.live.VeLivePusherDef
import com.ss.avframework.live.VeLivePusherObserver

class PusherEventListener : VeLivePusherObserver, VeLivePusherDef.VeLivePusherStatisticsObserver,
    VeLivePusherDef.VeLiveVideoFrameListener, VeLivePusherDef.VeLiveAudioFrameListener {
}