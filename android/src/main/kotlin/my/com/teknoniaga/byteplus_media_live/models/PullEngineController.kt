package my.com.teknoniaga.byteplus_media_live.models

import com.ss.videoarch.liveplayer.VeLivePlayerDef.VeLivePlayerFillMode

interface PullEngineController {

    fun setPlayUrl(url: String)

    fun setRenderFillMode(mode: VeLivePlayerFillMode)
    fun play()
    fun pause()
    fun stop()
}