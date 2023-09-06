import 'dart:convert';

import 'package:json_annotation/json_annotation.dart';

part 'push_player_option.g.dart';

@JsonSerializable()
class PushPlayerOption {
  final int videoWidth;
  final int videoHeight;
  final int videoFps;
  final int videoBitrate;
  final int videoMaxBitrate;
  final int videoMinBitrate;

  const PushPlayerOption(
      {this.videoWidth = 720,
      this.videoHeight = 12800,
      this.videoFps = 15,
      this.videoBitrate = 1200 * 1000,
      this.videoMaxBitrate = 1900 * 1000,
      this.videoMinBitrate = 800 * 1000});

  /// A necessary factory constructor for creating a new User instance
  /// from a map. Pass the map to the generated `_$PushPlayerOptionFromJson()` constructor.
  /// The constructor is named after the source class, in this case, User.
  factory PushPlayerOption.fromJson(Map<String, dynamic> json) =>
      _$PushPlayerOptionFromJson(json);

  /// `toJson` is the convention for a class to declare support for serialization
  /// to JSON. The implementation simply calls the private, generated
  /// helper method `_$PushPlayerOptionToJson`.
  Map<String, dynamic> toJson() => _$PushPlayerOptionToJson(this);

  String toJsonRaw() => json.encode(toJson());

  factory PushPlayerOption.fromJsonRaw(String source) =>
      PushPlayerOption.fromJson(json.decode(source));
}
