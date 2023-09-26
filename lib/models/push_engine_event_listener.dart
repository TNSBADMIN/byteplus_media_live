import 'package:byteplus_media_live/models/push_engine_controller.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

/// A listener for [PushEngineController] for Flutter side to implement
/// a flutter logic base on the event emitted.
abstract class MediaLivePushEngineEventListener {
  /// This is a view id from a Android native view binding.
  int? viewId;
  bool isAudioCaptured = false;
  bool isVideoCaptured = false;
  late EventChannel channel;
  late final PushEngineController controller;

  /// This listener are called after the [PushEngineController]
  /// have been fully initialized by the [PushView] widget.
  @mustCallSuper
  void onInitialized() {
    channel.receiveBroadcastStream().listen((event) {
      print("dengar message dari kotlin ${event.toString()}");

      final MediaLivePushEngineEventName target = MediaLivePushEngineEventName
          .values
          .firstWhere((element) => event == element.name,
              orElse: () => MediaLivePushEngineEventName.others);

      print("dengar message dari kotlin xxx $target");
      switch (target) {
        case MediaLivePushEngineEventName.onCaptureVideoFrame:
          isVideoCaptured = true;
          onCaptureVideoFrame();
        case MediaLivePushEngineEventName.onCaptureAudioFrame:
          isAudioCaptured = true;
          onCaptureAudioFrame();
        case MediaLivePushEngineEventName.onFirstVideoFrame:
          isVideoCaptured = true;
          onFirstVideoFrame();

        case MediaLivePushEngineEventName.onFirstAudioFrame:
          isAudioCaptured = true;
          onFirstAudioFrame();
        case MediaLivePushEngineEventName.others:
          other();
        case MediaLivePushEngineEventName.veLivePushStatusConnecting:
          onStatusConnection();
        case MediaLivePushEngineEventName.veLivePushStatusConnectSuccess:
          onStatusConnected();
      }
    });
  }

  void onStatusConnection() {}

  void onStatusConnected() {}

  void onCaptureVideoFrame() {}

  void onCaptureAudioFrame() {}

  void onFirstAudioFrame() {}

  void onFirstVideoFrame() {}

  void other() {}
}

enum MediaLivePushEngineEventName {
  onCaptureVideoFrame('onCaptureVideoFrame'),
  onCaptureAudioFrame('onCaptureAudioFrame'),
  onFirstVideoFrame('onFirstVideoFrame'),
  onFirstAudioFrame('onFirstAudioFrame'),
  veLivePushStatusConnecting("VeLivePushStatusConnecting"),
  veLivePushStatusConnectSuccess("VeLivePushStatusConnectSuccess"),
  others("others");

  const MediaLivePushEngineEventName(this.name);

  final String name;
}
