import 'byteplus_media_live_platform_interface.dart';

class ByteplusMediaLive {
  Future<String?> getPlatformVersion() {
    return ByteplusMediaLivePlatform.instance.getPlatformVersion();
  }

  Future<void> iniTtSdk(
      {required String appId,
      required String appName,
      required String version,
      required String appChanel,
      required Uri licenseUrl}) {
    return ByteplusMediaLivePlatform.instance
        .initTtSdk(appId, appName, version, appChanel, licenseUrl);
  }

  Future<Uri> createPullUri(String livePullUrl) {
    return ByteplusMediaLivePlatform.instance.createPullUri(livePullUrl);
  }
}
