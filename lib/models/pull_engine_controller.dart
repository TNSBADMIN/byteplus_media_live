import 'package:byteplus_media_live/models/pull_engine_event_listener.dart';
import 'package:byteplus_media_live/models/render_fill_mode.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';

part '../widgets/pull_view.dart';

class PullEngineController {
  bool _initialized = false;
  MethodChannel? _internalMethodChannel;

  /// A listener to bind with a class that you build
  /// with extends of [MediaLivePullEngineEventListener]
  MediaLivePullEngineEventListener? listener;

  PullEngineController([this.listener]);

  MethodChannel get _methodChannel {
    if (_internalMethodChannel == null) {
      throw Exception('need initialization first');
    }
    return _internalMethodChannel!;
  }

  /// A method that are called by internal logic to initialize this controller.
  ///
  /// This method do not need to call by normal implementation because
  /// init the bridge between flutter and Android native view.
  void _initialize(int viewId) {
    listener?.viewId = viewId;
    if (!_initialized) listener?.controller = this;
    _initialized = true;
    print("pul xxx init");
    _internalMethodChannel = MethodChannel('pull_engine_$viewId');

    listener?.onInitialized();
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
