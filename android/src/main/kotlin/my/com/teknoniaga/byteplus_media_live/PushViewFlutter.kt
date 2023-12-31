package my.com.teknoniaga.byteplus_media_live

import android.content.Context
import android.view.View
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.platform.PlatformView
import my.com.teknoniaga.byteplus_media_live.models.PushPlayerOption

class PushViewFlutter(context: Context, viewId: Int, binaryMessenger: BinaryMessenger, option: PushPlayerOption) : PlatformView {
    private val viewPort: PushView

    override fun getView(): View {
        return viewPort
    }

    override fun dispose() {
    }

    init {
        viewPort = PushView(context, viewId, binaryMessenger, option)
    }
}