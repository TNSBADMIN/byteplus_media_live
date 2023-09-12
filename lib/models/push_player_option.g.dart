// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'push_player_option.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PushPlayerOption _$PushPlayerOptionFromJson(Map<String, dynamic> json) =>
    PushPlayerOption(
      videoWidth: json['videoWidth'] as int? ?? 720,
      videoHeight: json['videoHeight'] as int? ?? 12800,
      videoFps: json['videoFps'] as int? ?? 15,
      videoBitrate: json['videoBitrate'] as int? ?? 1200 * 1000,
      videoMaxBitrate: json['videoMaxBitrate'] as int? ?? 1900 * 1000,
      videoMinBitrate: json['videoMinBitrate'] as int? ?? 800 * 1000,
      enablePreview: json['enablePreview'] as bool? ?? true,
    );

Map<String, dynamic> _$PushPlayerOptionToJson(PushPlayerOption instance) =>
    <String, dynamic>{
      'videoWidth': instance.videoWidth,
      'videoHeight': instance.videoHeight,
      'videoFps': instance.videoFps,
      'videoBitrate': instance.videoBitrate,
      'videoMaxBitrate': instance.videoMaxBitrate,
      'videoMinBitrate': instance.videoMinBitrate,
      'enablePreview': instance.enablePreview,
    };
