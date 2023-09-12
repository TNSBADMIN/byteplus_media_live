import 'package:byteplus_media_live/models/render_fill_mode.dart';
import 'package:flutter/services.dart';

class PullEngineController {
  MethodChannel? _internalMethodChannel;

  PullEngineController();

  MethodChannel get _methodChannel {
    if (_internalMethodChannel == null) {
      throw Exception('need initialization first');
    }
    return _internalMethodChannel!;
  }

  void initialize(int viewId) {
    print("pul xxx init");
    _internalMethodChannel = MethodChannel('pull_engine_$viewId');
  }

  Future<void> test() async {
    final res = await _methodChannel.invokeMethod('test');

    print("get data from native $res");
  }

  Future<void> setPlayUrl(Uri uri) async {
    final res = await _methodChannel.invokeMethod('setPlayUrl', uri.toString());
    print("get data from native $res");
  }

  Future<void> setRenderFillMode(ByteplusRenderFillMode fillMode) async {
    final res = await _methodChannel.invokeMethod('setRenderFillMode');
    print("get data from native $res");
  }

  Future<void> play() async {
    final res = await _methodChannel.invokeMethod('play');
    print("get data from native $res");
  }

  Future<void> pause() async {
    final res = await _methodChannel.invokeMethod('pause');
    print("get data from native $res");
  }

  Future<void> stop() async {
    final res = await _methodChannel.invokeMethod('stop');
    print("get data from native $res");
  }
}
