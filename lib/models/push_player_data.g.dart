// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'push_player_data.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PushPlayerData _$PushPlayerDataFromJson(Map<String, dynamic> json) =>
    PushPlayerData(
      json['url'] as String,
      PushPlayerOption.fromJson(json['option'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$PushPlayerDataToJson(PushPlayerData instance) =>
    <String, dynamic>{
      'url': instance.url,
      'option': instance.option,
    };
