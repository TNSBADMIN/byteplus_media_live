import 'dart:convert';

import 'package:byteplus_media_live/models/push_player_option.dart';
import 'package:json_annotation/json_annotation.dart';

part 'push_player_data.g.dart';

@JsonSerializable()
class PushPlayerData {
  final String url;
  final PushPlayerOption option;

  PushPlayerData(this.url, this.option);

  /// A necessary factory constructor for creating a new User instance
  /// from a map. Pass the map to the generated `_$PushPlayerDataFromJson()` constructor.
  /// The constructor is named after the source class, in this case, User.
  factory PushPlayerData.fromJson(Map<String, dynamic> json) =>
      _$PushPlayerDataFromJson(json);

  /// `toJson` is the convention for a class to declare support for serialization
  /// to JSON. The implementation simply calls the private, generated
  /// helper method `_$PushPlayerDataToJson`.
  Map<String, dynamic> toJson() => _$PushPlayerDataToJson(this);

  String toJsonRaw() => json.encode(toJson());

  factory PushPlayerData.fromJsonRaw(String source) =>
      PushPlayerData.fromJson(json.decode(source));
}
