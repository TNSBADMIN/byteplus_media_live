import 'package:byteplus_media_live/models/push_engine_controller.dart';

/// A listener for [PushEngineController] for Flutter side to implement
/// a flutter logic base on the event emitted.
abstract class MediaLivePushEngineEventListener {
  /// This is a view id from a Android native view binding.
  int? viewId;
  late final PushEngineController controller;

  /// This listener are called after the [PushEngineController]
  /// have been fully initialized by the [PushView] widget.
  void onInitialized() {}
}
