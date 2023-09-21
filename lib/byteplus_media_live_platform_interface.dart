import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'byteplus_media_live_method_channel.dart';

abstract class ByteplusMediaLivePlatform extends PlatformInterface {
  /// Constructs a ByteplusMediaLivePlatform.
  ByteplusMediaLivePlatform() : super(token: _token);

  static final Object _token = Object();

  static ByteplusMediaLivePlatform _instance = MethodChannelByteplusMediaLive();

  /// The default instance of [ByteplusMediaLivePlatform] to use.
  ///
  /// Defaults to [MethodChannelByteplusMediaLive].
  static ByteplusMediaLivePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [ByteplusMediaLivePlatform] when
  /// they register themselves.
  static set instance(ByteplusMediaLivePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<void> initTtSdk(String appId, String appName, String version,
      String appChanel, Uri licenseUrl);

  Future<Uri> createPullUri(String livePullUrl);
}
