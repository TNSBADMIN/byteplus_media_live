class MediaLiveException implements Exception {
  final String message;

  MediaLiveException(this.message);

  @override
  String toString() => message;
}
