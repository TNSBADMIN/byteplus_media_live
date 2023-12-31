import 'package:byteplus_media_live/models/push_engine_event_listener.dart';
import 'package:byteplus_media_live/models/push_player_option.dart';
import 'package:byteplus_media_live/models/utils/media_live_exception.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';
import 'package:permission_handler/permission_handler.dart';

part '../widgets/push_view.dart';

class PushEngineController {
  bool _initialized = false;
  MethodChannel? _methodChannelI;

  /// A listener to bind with a class that you build
  /// with extends of [MediaLivePushEngineEventListener]
  MediaLivePushEngineEventListener? listener;

  PushEngineController([this.listener]);

  MethodChannel get _methodChannel {
    if (_methodChannelI == null) throw Exception('need initialization first');
    return _methodChannelI!;
  }

  void _initialize(int viewId) {
    if (!_initialized) {
      final channelName = "push_engine_$viewId";
      _initialized = true;
      listener?.viewId = viewId;
      listener?.controller = this;
      _methodChannelI = MethodChannel(channelName);
      const eventChannel = EventChannel('xxx');
      listener?.channel = eventChannel;
      listener?.onInitialized();
    }
  }

  Future<void> test() async {
    final res = await _methodChannel.invokeMethod('test');

    print("get data from native test $res");
  }

  Future<void> startVideoCapture() async {
    final status = await Permission.camera.status;

    if (status != PermissionStatus.granted) {
      throw MediaLiveException('camera permission are not yet granted');
    }

    final res = await _methodChannel.invokeMethod('startVideoCapture');

    print("get data from native $res");
  }

  Future<void> stopVideoCapture() async {
    final res = await _methodChannel.invokeMethod('stopVideoCapture');

    print("get data from native $res");
  }

  Future<void> startAudioCapture() async {
    final status = await Permission.microphone.status;

    if (status != PermissionStatus.granted) {
      throw MediaLiveException('microphone permission are not yet granted');
    }

    final res = await _methodChannel.invokeMethod('startAudioCapture');

    print("get data from native $res");
  }

  Future<void> stopAudioCapture() async {
    final res = await _methodChannel.invokeMethod('stopAudioCapture');

    print("get data from native $res");
  }

  /// start the publish process to byteplus,
  /// before this method are call you need to call
  /// [startVideoCapture] and [startAudioCapture] first, else
  /// this will not work.
  ///
  /// this method will only check for video and audio status if
  /// you attach some [listener] to it, else you will need to
  /// check it by your self manually
  Future<void> startPublish(Uri uri) async {
    // TODO better exception throw
    if (listener != null) {
      assert(listener!.isAudioCaptured && listener!.isVideoCaptured);
    }
    final res =
        await _methodChannel.invokeMethod('startPublish', uri.toString());

    print("get data from native $res");
  }

  Future<void> stopPublish() async {
    final res = await _methodChannel.invokeMethod('stopPublish');

    print("get data from native $res");
  }

  /// A method to request a permission for microphone
  /// and camera.
  ///
  /// [loop] if value are true, this method will loop the process
  /// of requesting permission until the user accept the permission.
  Future<bool> requestRequiredPermission({bool loop = false}) async {
    await _handleRequiredPermission(loop);
    return await checkRequiredPermission;
  }

  Future<bool> get checkRequiredPermission async {
    final micStatus = await Permission.microphone.status;
    final camStatus = await Permission.camera.status;

    return micStatus == PermissionStatus.granted &&
        camStatus == PermissionStatus.granted;
  }

  Future<void> _handleRequiredPermission(bool loop) async {
    final cameraStatus = await Permission.camera.status;
    final microphoneStatus = await Permission.microphone.status;
    if (!(cameraStatus.isGranted && microphoneStatus.isGranted)) {
      await Permission.camera.request();
      await Permission.microphone.request();
      if (loop) _handleRequiredPermission(true);
    }
  }
}
