import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'byteplus_media_live_platform_interface.dart';

/// An implementation of [ByteplusMediaLivePlatform] that uses method channels.
class MethodChannelByteplusMediaLive extends ByteplusMediaLivePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('byteplus_media_live');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<void> initTtSdk(String appId, String appName, String version,
      String appChanel, String licensePath) async {
    return await methodChannel.invokeMethod<void>('iniTtSdk', {
      "appId": appId,
      "appName": appName,
      "version": version,
      "appChanel": appChanel,
      "licensePath": licensePath
    });
  }

  @override
  Future<Uri> createPullUri(String livePullUrl) async {
    final url = await methodChannel
        .invokeMethod<String>('createPullUrl', {"livePullUrl": livePullUrl});

    print("909090");
    print(url);
    if (url == null) {
      throw Exception('Url given by method chanel is null');
    }

    return Uri.parse(url);
  }
}
