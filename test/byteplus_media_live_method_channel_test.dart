import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:byteplus_media_live/byteplus_media_live_method_channel.dart';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();

  MethodChannelByteplusMediaLive platform = MethodChannelByteplusMediaLive();
  const MethodChannel channel = MethodChannel('byteplus_media_live');

  setUp(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(
      channel,
      (MethodCall methodCall) async {
        return '42';
      },
    );
  });

  tearDown(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(channel, null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
