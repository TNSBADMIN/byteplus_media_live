import 'package:flutter/services.dart';

class PushEngineController {
  MethodChannel? _methodChannelI;

  PushEngineController();

  MethodChannel get _methodChannel {
    if (_methodChannelI == null) throw Exception('need initialization first');
    return _methodChannelI!;
  }

  void initialize(int viewId) {
    _methodChannelI = MethodChannel('push_engine_$viewId');
  }

  Future<void> test() async {
    final res = await _methodChannel.invokeMethod('test');

    print("get data from native $res");
  }

  Future<void> startVideoCapture() async {
    final res = await _methodChannel.invokeMethod('startVideoCapture');

    print("get data from native $res");
  }

  Future<void> startAudioCapture() async {
    final res = await _methodChannel.invokeMethod('startAudioCapture');

    print("get data from native $res");
  }

  /// start the publish process to byteplus,
  /// before this method are call you need to call
  /// [startVideoCapture] and [startAudioCapture] first, else
  /// this will not work.
  Future<void> startPublish(Uri uri) async {
    final res =
        await _methodChannel.invokeMethod('startPublish', uri.toString());

    print("get data from native $res");
  }
}
