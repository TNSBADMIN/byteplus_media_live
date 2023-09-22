package my.com.teknoniaga.byteplus_media_live.models

interface PushEngineController {

    fun startVideoCapture()
    fun stopVideoCapture()
    fun startAudioCapture()
    fun stopAudioCapture()
    fun startPublish(url: String)
    fun stopPublish()
    val audioCaptureStatus: Boolean
    val videoCaptureStatus: Boolean
}