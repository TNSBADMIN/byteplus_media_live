import 'package:byteplus_media_live/byteplus_media_live.dart';
import 'package:byteplus_media_live/byteplus_media_live_method_channel.dart';
import 'package:byteplus_media_live/byteplus_media_live_platform_interface.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockByteplusMediaLivePlatform
    with MockPlatformInterfaceMixin
    implements ByteplusMediaLivePlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<Uri> createPullUri(String livePullUrl) {
    // TODO: implement createPullUri
    throw UnimplementedError();
  }

  @override
  Future<void> initTtSdk(String appId, String appName, String version,
      String appChanel, Uri licenseUrl) {
    // TODO: implement initTtSdk
    throw UnimplementedError();
  }
}

void main() {
  final ByteplusMediaLivePlatform initialPlatform =
      ByteplusMediaLivePlatform.instance;

  test('$MethodChannelByteplusMediaLive is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelByteplusMediaLive>());
  });

  test('getPlatformVersion', () async {
    ByteplusMediaLive byteplusMediaLivePlugin = ByteplusMediaLive();
    MockByteplusMediaLivePlatform fakePlatform =
        MockByteplusMediaLivePlatform();
    ByteplusMediaLivePlatform.instance = fakePlatform;

    expect(await byteplusMediaLivePlugin.getPlatformVersion(), '42');
  });
}
