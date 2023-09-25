import 'package:byteplus_media_live/models/push_engine_controller.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

/// A listener for [PushEngineController] for Flutter side to implement
/// a flutter logic base on the event emitted.
abstract class MediaLivePushEngineEventListener {
  /// This is a view id from a Android native view binding.
  int? viewId;
  late EventChannel channel;
  late final PushEngineController controller;

  void xxxxx(a) {
    print("ada error dalm android listen $a");
  }

  /// This listener are called after the [PushEngineController]
  /// have been fully initialized by the [PushView] widget.
  @mustCallSuper
  void onInitialized() {
    channel.receiveBroadcastStream().listen((event) {
      print("dengar message dari kotlin ${event.toString()}");
    });
  }

  void onStartedPublish() {
    print('start publish from listener');
  }
}
