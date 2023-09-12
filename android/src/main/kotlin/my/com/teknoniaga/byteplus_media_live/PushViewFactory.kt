package my.com.teknoniaga.byteplus_media_live

import android.content.Context
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import com.google.gson.Gson
import io.flutter.plugin.common.BinaryMessenger
import my.com.teknoniaga.byteplus_media_live.models.PushPlayerOption

class PushViewFactory(private val binaryMessenger: BinaryMessenger) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    override fun create(context: Context, viewId: Int, args: Any?): PlatformView {
        println(" sini still ajalan")
        val gson = Gson()
        println(" sini still ajalan")
        val jsonString: String = args as String
        println(" sini still ajalan")
        val data: PushPlayerOption = gson.fromJson(jsonString, PushPlayerOption::class.java)

        return PushViewFlutter(context, viewId, binaryMessenger, data)
    }
}