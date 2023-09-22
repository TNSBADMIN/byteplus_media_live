import 'package:byteplus_media_live/models/pull_engine_controller.dart';

/// A listener for [PullEngineController] for Flutter side to implement
/// a flutter logic base on the event emitted.
abstract class MediaLivePullEngineEventListener {
  /// This is a view id from a Android native view binding.
  int? viewId;
  late final PullEngineController controller;

  /// This listener are called after the [PullEngineController]
  /// have been fully initialized.
  void onInitialized() {}
}
