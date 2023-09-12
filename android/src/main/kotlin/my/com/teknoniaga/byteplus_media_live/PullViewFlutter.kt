package my.com.teknoniaga.byteplus_media_live

import android.content.Context
import android.view.View
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.platform.PlatformView

class PullViewFlutter(context: Context, viewId: Int, binaryMessenger: BinaryMessenger) : PlatformView {

    private val viewPort: PullView

    init {
        println("native pull masuk flutter init")
        viewPort = PullView(context, viewId, binaryMessenger)
    }

    override fun getView(): View {
        return viewPort
    }

    override fun dispose() {

    }

}