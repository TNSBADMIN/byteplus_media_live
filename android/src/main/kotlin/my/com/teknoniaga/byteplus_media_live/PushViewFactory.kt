package my.com.teknoniaga.byteplus_media_live

import android.content.Context
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import my.com.teknoniaga.byteplus_media_live.models.PushPlayerData
import com.google.gson.Gson

class PushViewFactory : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    override fun create(context: Context, viewId: Int, args: Any?): PlatformView {
        val gson = Gson();
        val jsonString : String = args as String;
        val data : PushPlayerData = gson.fromJson(jsonString, PushPlayerData::class.java)

        return PushViewFlutter(context, data.url, data.option)
    }
}