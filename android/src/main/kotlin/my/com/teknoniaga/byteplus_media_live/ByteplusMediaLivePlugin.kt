package my.com.teknoniaga.byteplus_media_live

import android.content.Context
import android.util.Log
import com.pandora.common.env.Env
import com.pandora.common.env.config.Config
import com.pandora.common.utils.TimeUtil
import com.pandora.common.utils.Times
import com.pandora.live.player.been.TTLiveURLComposer
import com.pandora.live.player.been.TTLiveURLComposer.MEDIA_ENCODE_TYPE_BYTEVC1
import com.pandora.live.player.been.TTLiveURLComposer.MEDIA_FORMAT_FLV
import com.pandora.live.player.been.TTLiveURLComposer.MEDIA_SOURCE_TYPE_MAIN
import com.pandora.ttlicense2.LicenseManager
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


/** ByteplusMediaLivePlugin */
class ByteplusMediaLivePlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private lateinit var context: Context;

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "byteplus_media_live")
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext

        flutterPluginBinding.platformViewRegistry.registerViewFactory("byteplus_push", PushViewFactory(flutterPluginBinding.binaryMessenger))
        flutterPluginBinding.platformViewRegistry.registerViewFactory("byteplus_pull", PullViewFactory(flutterPluginBinding.binaryMessenger))
    }

    override fun onMethodCall(call: MethodCall, result: Result) {

        println("xxx yyy" + call.method);

        when (call.method) {
            "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
            "iniTtSdk" -> initTtSdkMethodChanel(call, result)
            "createPullUrl" -> createPullUrlMethodChanel(call, result)
            else -> result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }


    private fun initTtSdkMethodChanel(call: MethodCall, result: Result) {
        println("xxx yyy masuk init")
        println("xxx yyy " + call.argument<Any?>("appId") as String)

        var appId: String = call.argument<Any?>("appId") as String;
        println("xxx yyy $appId")
        var appName: String = call.argument<String>("appName") as String;
        println("xxx yyy $appName")
        var appVersion: String = call.argument<String>("version") as String;
        println("xxx yyy $appVersion")
        var appChanel: String = call.argument<String>("appChanel") as String;
        println("xxx yyy $appChanel")
        var licenseUrl: String = call.argument<String>("licenseUrl") as String;
        println("xxx yyy $licenseUrl")

        println("xxx yyy masuk init 2")

        initTTSDK(appId, appName, appVersion, appChanel, licenseUrl);

        println("xxx yyy masuk init 2")

        result.success(null);
    }

    private fun createPullUrlMethodChanel(call: MethodCall, result: Result) {

        println("masuk create url wrapper")
        var livePullUrl: String = call.argument<String>("livePullUrl") as String

        println("masuk create url wrapper$livePullUrl")

        var generatedUrl: String = createPullUrl(livePullUrl)

        result.success(generatedUrl)

    }

    private fun getLicense(licenseId: String) {

        println("masuk get license chanel")
        // Get the license information.
        val license = LicenseManager.getInstance().getLicense(licenseId) // You can get the license ID through mLicenseCallback.

        if (license != null) {
            val builder = StringBuilder()
            builder.append("License id:" + license.id).append("\n")
                    .append("License package:" + license.packageName).append("\n")
                    .append("License test:" + license.type).append("\n")
                    .append("License version:" + license.version).append("\n")
            if (license.modules != null) {
                var names = ""
                for (module in license.modules) {
                    names = """
                module name:${module.name}, start time:${TimeUtil.format(module.startTime, Times.YYYY_MM_DD_KK_MM_SS)}, expire time:${TimeUtil.format(module.expireTime, Times.YYYY_MM_DD_KK_MM_SS)}
                
                """.trimIndent()
                    builder.append("License modules:$names")
                }
            }

            Log.i("license", builder.toString())
        } else {
            throw Exception("No license founded")
        }


    }


    private fun initTTSDK(appId: String, appName: String, appVersion: String, appChanel: String, licenseUrl: String) {
        Env.init(Config.Builder()
                .setApplicationContext(context)
                .setAppID(appId).setAppName(appName)
                .setAppVersion(appVersion)
                .setAppChannel(appChanel)
                .setLicenseUri(licenseUrl)
//                .setLicenseUri("https://vod-license-m.bytepluscdn.com/obj/vod-license-sgcom/l-261-ch-fcdn_byteplus-a-521914.lic?x-expires=4846208832&x-signature=aYyu%2BhhINZ68Xp8ykfyBlgaiabw%3D")
                .setLicenseCallback(mLicenseCallback).build())
    }

    private val mLicenseCallback: LicenseManager.Callback = object : LicenseManager.Callback {
        override fun onLicenseLoadSuccess(licenseUri: String, licenseId: String) {
            Log.e("a", "")
            getLicense(licenseId)
        }

        override fun onLicenseLoadError(licenseUri: String, e: Exception, retryAble: Boolean) {}
        override fun onLicenseLoadRetry(licenseUri: String) {}
        override fun onLicenseUpdateSuccess(licenseUri: String, licenseId: String) {}
        override fun onLicenseUpdateError(licenseUri: String, e: Exception, retryAble: Boolean) {}
        override fun onLicenseUpdateRetry(licenseUri: String) {}
    }

    private fun createPullUrl(livePullUrl: String): String {
        println("masuk create pull url : $livePullUrl")
        val composer = TTLiveURLComposer()
        composer.addUrl(livePullUrl, MEDIA_ENCODE_TYPE_BYTEVC1, MEDIA_SOURCE_TYPE_MAIN, MEDIA_FORMAT_FLV) // RTM format should select 'LLS'

        println("masuk create pull url 2 : ${composer.streamInfo}")

        return composer.streamInfo

    }


}
