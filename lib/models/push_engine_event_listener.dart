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

  /// This listener are called after the [PushEngineController]
  /// have been fully initialized by the [PushView] widget.
  @mustCallSuper
  void onInitialized() {
    channel.receiveBroadcastStream().listen((event) {
      print("dengar message dari kotlin ${event.toString()}");

      switch (event) {
        case 1:
          onStartingPublish();
        case 2:
          onStartedPublish();
        case 3:
          onStoppedPublish();
        case 4:
          onVideoStartingCapture();
        case 5:
          onVideoStartedCapture();
        case 6:
          onVideoStoppedCapture();
        case 7:
          onAudioStartingCapture();
        case 8:
          onAudioStartedCapture();
        case 9:
          onAudioStoppedCapture();
        case 10:
          onRTMPConnecting();
        case 11:
          onRTMPConnected();
        case 12:
          onRTMPConnectFail();
        case 13:
          onRTMPSendSlow();
        case 14:
          onRTMPDisconnected();
        case 15:
          onRTMPReconnecting();
        case 16:
          onVideoEncoderFormatChanged();
        case 17:
          onAudioChannelChange();
        case 18:
          onADMPlayerStarted();
        case 19:
          onADMPlayerStopped();
        case 20:
          onADMRecordingStart();
        case 21:
          onADMRecordingStop();
        case 22:
          onADMAECChange();
        case 23:
          onADMEchoChange();
        case 24:
          onADMMixChange();
        case 25:
          onADMAgcChange();
        case 26:
          onADMNSChange();
        case 27:
          onResolutionChanged();
        case 28:
          onReceiveLocalVideoFirstFrame();
        case 29:
          onAudioEncoderFormatChanged();
        case 30:
          onCaptureFirstFrame();
        case 31:
          onADMEarMonitor();
        case 32:
          onADMActualAudioCaptureChange();
        case 33:
          onADMActualAudioPlayerChange();
        case 34:
          onVideoFpsChangeInternal();
        case 35:
          onAutoBrightenStatusChange();
        case 36:
          onMicIsSilenced();
        case 37:
          onVideoCaptureResolutionFallback();
        case 38:
          onVideoCaptureResolutionChange();
        case 39:
          onReceiveSDKControlMessage();
        case 40:
          onResetOriginVideoTrack();
        case 41:
          onResetOriginAudioTrack();
        case 42:
          onRTMPPublishSuccess();
        case 43:
          onVideoEncodeFrameSizeInvalid();
        case 101:
          onReportNetworkQuality();
        case 102:
          onReportTransportFPS();
        case 103:
          onReportTransportBPS();
        case 201:
          onNotifyToggleCamera();
        case -100:
          onErrorWhileStartAudio();
        case -300:
          onAudioStartCaptureError();
        case -301:
          onAudioStartStatusError();
        case -302:
          onAudioAudioRecordReadError();
        case -303:
          onAudioOpenSLStartError();
        case -304:
          onAudioCaptureStatusError();
        case -305:
          onAudioCaptureCreateError();
        case -306:
          onAudioCaptureLaterStatusError();
        case -307:
          onAudioRestartIllegalError();
        case -600:
          onAudioCreateEncoderError();
        case -101:
          onErrorWhileStartVideo();
        case -200:
          onVideoCaptureBufferError();
        case -201:
          onVideoCaptureMediaProjectionError();
        case -202:
          onVideoCaptureGetInstanceError();
        case -203:
          onVideoCaptureInstanceNullError();
        case -204:
          onVideoStartNullCameraError();
        case -205:
          onVideoSwitchNullCameraError();
        case -206:
          onVideoInvalidTextureError();
        case -207:
          onScreenVideoCaptureInnerAudioError();
        case -102:
          onVideoLiveStatusError();
        case -500:
          onVideoCreateEncoderError();
        case -103:
          onRTMPConnectingWhileLiveStatusError();
        case -10007:
          onUnionPublisherErrorQUICLoadLibraryFail();
        case -30000:
          onUnionPublisherErrorQUICBase();
        case -31002:
          onUnionPublisherErrorQUICConnectTimeout();
        case -31007:
          onUnionPublisherErrorQUICHandshakeNotConfirmed();
        default:
          onOtherEvent(event);
      }
    });
  }

  void onStartingPublish() {
    print('Starting Publish');
  }

  void onIsMicOpen() {
    print('Microphone is open');
  }

  void onStartedPublish() {
    print('Started Publish');
  }

  void onStoppedPublish() {
    print('Stopped Publish');
  }

  void onVideoStartingCapture() {
    print('Video Starting Capture');
  }

  void onVideoStartedCapture() {
    print('Video Started Capture');
  }

  void onVideoStoppedCapture() {
    print('Video Stopped Capture');
  }

  void onAudioStartingCapture() {
    print('Audio Starting Capture');
  }

  void onAudioStartedCapture() {
    print('Audio Started Capture');
  }

  void onAudioStoppedCapture() {
    print('Audio Stopped Capture');
  }

  void onRTMPConnecting() {
    print('RTMP Connecting');
  }

  void onRTMPConnected() {
    print('RTMP Connected');
  }

  void onRTMPConnectFail() {
    print('RTMP Connect Fail');
  }

  void onRTMPSendSlow() {
    print('RTMP Send Slow');
  }

  void onRTMPDisconnected() {
    print('RTMP Disconnected');
  }

  void onRTMPReconnecting() {
    print('RTMP Reconnecting');
  }

  void onVideoEncoderFormatChanged() {
    print('Video encoder format changed');
  }

  void onAudioChannelChange() {
    print('Audio channel change');
  }

  void onADMPlayerStarted() {
    print('ADM player started');
  }

  void onADMPlayerStopped() {
    print('ADM player stopped');
  }

  void onADMRecordingStart() {
    print('ADM recording started');
  }

  void onADMRecordingStop() {
    print('ADM recording stopped');
  }

  void onADMAECChange() {
    print('ADM AEC change');
  }

  void onADMEchoChange() {
    print('ADM echo change');
  }

  void onADMMixChange() {
    print('ADM mix change');
  }

  void onADMAgcChange() {
    print('ADM AGC change');
  }

  void onADMNSChange() {
    print('ADM NS change');
  }

  void onResolutionChanged() {
    print('Resolution changed');
  }

  void onReceiveLocalVideoFirstFrame() {
    print('Received local video first frame');
  }

  void onAudioEncoderFormatChanged() {
    print('Audio encoder format changed');
  }

  void onCaptureFirstFrame() {
    print('Capture first frame');
  }

  void onADMEarMonitor() {
    print('ADM ear monitor');
  }

  void onADMActualAudioCaptureChange() {
    print('ADM actual audio capture change');
  }

  void onADMActualAudioPlayerChange() {
    print('ADM actual audio player change');
  }

  void onVideoFpsChangeInternal() {
    print('Video FPS change (internal)');
  }

  void onAutoBrightenStatusChange() {
    print('Auto brighten status change');
  }

  void onMicIsSilenced() {
    print('Microphone is silenced');
  }

  void onVideoCaptureResolutionFallback() {
    print('Video capture resolution fallback');
  }

  void onVideoCaptureResolutionChange() {
    print('Video capture resolution change');
  }

  void onReceiveSDKControlMessage() {
    print('Received SDK control message');
  }

  void onResetOriginVideoTrack() {
    print('Reset origin video track');
  }

  void onResetOriginAudioTrack() {
    print('Reset origin audio track');
  }

  void onRTMPPublishSuccess() {
    print('RTMP publish success');
  }

  void onVideoEncodeFrameSizeInvalid() {
    print('Video encode frame size invalid');
  }

  void onReportNetworkQuality() {
    print('Report network quality');
  }

  void onReportTransportFPS() {
    print('Report transport FPS');
  }

  void onReportTransportBPS() {
    print('Report transport BPS');
  }

  void onNotifyToggleCamera() {
    print('Notify toggle camera');
  }

  void onStatusException() {
    print('Status exception');
  }

  void onErrorVideoCapture() {
    print('Error in video capture');
  }

  void onErrorAudioCapture() {
    print('Error in audio capture');
  }

  void onErrorRTMP() {
    print('Error in RTMP');
  }

  void onErrorVideoEncoder() {
    print('Error in video encoder');
  }

  void onErrorAudioEncoder() {
    print('Error in audio encoder');
  }

  void onTransportBufferDuration() {
    print('Transport buffer duration');
  }

  void onTransportDNSTime() {
    print('Transport DNS time');
  }

  void onTransportLastAudioDTS() {
    print('Transport last audio DTS');
  }

  void onTransportLastVideoDTS() {
    print('Transport last video DTS');
  }

  void onTransportBWEInfo() {
    print('Transport BWE info');
  }

  void onTransportLastRealAudioTS() {
    print('Transport last real audio TS');
  }

  void onTransportLastRealVideoTS() {
    print('Transport last real video TS');
  }

  void onTransportAfterVsyncAudioTS() {
    print('Transport after Vsync audio TS');
  }

  void onTransportAfterVsyncVideoTS() {
    print('Transport after Vsync video TS');
  }

  void onEncoderLastGOPMaxContBFrames() {
    print('Encoder last GOP max continuous B-frames');
  }

  void onVideoNetInfoBWE() {
    print('Video network info BWE');
  }

  void onVideoNetInfoRTT() {
    print('Video network info RTT');
  }

  void onVideoNetInfoLossRate() {
    print('Video network info loss rate');
  }

  void onVideoNetInfoCount() {
    print('Video network info count');
  }

  void onVideoNetQuality() {
    print('Video network quality');
  }

  void onTransportNetworkQualityUnknown() {
    print('Transport network quality unknown');
  }

  void onTransportNetworkQualityBad() {
    print('Transport network quality bad');
  }

  void onTransportNetworkQualityPoor() {
    print('Transport network quality poor');
  }

  void onTransportNetworkQualityModerate() {
    print('Transport network quality moderate');
  }

  void onTransportNetworkQualityGood() {
    print('Transport network quality good');
  }

  void onVideoComplexityPerformance() {
    print('Video complexity performance');
  }

  void onDataAudioPower() {
    print('Data audio power');
  }

  void onWarningInvalidOperation() {
    print('Warning invalid operation');
  }

  void onNetworkQualityUnknown() {
    print('Network quality unknown');
  }

  void onNetworkQualityGood() {
    print('Network quality good');
  }

  void onNetworkQualityPoor() {
    print('Network quality poor');
  }

  void onNetworkQualityBad() {
    print('Network quality bad');
  }

  void onStatusStreamNormalPublish() {
    print('Status stream normal publish');
  }

  void onStatusInteractClientMixer() {
    print('Status interact client mixer');
  }

  void onStatusInteractServerMixer() {
    print('Status interact server mixer');
  }

  void onEGlSurfaceDestroyed() {
    print('EGL surface destroyed');
  }

  void onEGlSurfaceWillBeCreated() {
    print('EGL surface will be created');
  }

  void onVideoResolutionQuirksForceUsingAdaptedResolution() {
    print('Video resolution quirks: Force using adapted resolution');
  }

  void onVideoResolutionQuirksMask() {
    print('Video resolution quirks mask');
  }

  void onErrorWhileStartAudio() {
    print('Error while starting audio');
  }

  void onAudioStartCaptureError() {
    print('Audio start capture error');
  }

  void onAudioStartStatusError() {
    print('Audio start status error');
  }

  void onAudioAudioRecordReadError() {
    print('Audio audio record read error');
  }

  void onAudioOpenSLStartError() {
    print('Audio OpenSL start error');
  }

  void onAudioCaptureStatusError() {
    print('Audio capture status error');
  }

  void onAudioCaptureCreateError() {
    print('Audio capture create error');
  }

  void onAudioCaptureLaterStatusError() {
    print('Audio capture later status error');
  }

  void onAudioRestartIllegalError() {
    print('Audio restart illegal error');
  }

  void onAudioCreateEncoderError() {
    print('Audio create encoder error');
  }

  void onErrorWhileStartVideo() {
    print('Error while starting video');
  }

  void onVideoCaptureBufferError() {
    print('Video capture buffer error');
  }

  void onVideoCaptureMediaProjectionError() {
    print('Video capture media projection error');
  }

  void onVideoCaptureGetInstanceError() {
    print('Video capture get instance error');
  }

  void onVideoCaptureInstanceNullError() {
    print('Video capture instance null error');
  }

  void onVideoStartNullCameraError() {
    print('Video start null camera error');
  }

  void onVideoSwitchNullCameraError() {
    print('Video switch null camera error');
  }

  void onVideoInvalidTextureError() {
    print('Video invalid texture error');
  }

  void onVideoTECameraStartError() {
    print('Video TE camera start error');
  }

  void onScreenVideoCaptureInnerAudioError() {
    print('Screen video capture inner audio error');
  }

  void onVideoLiveStatusError() {
    print('Video live status error');
  }

  void onVideoCreateEncoderError() {
    print('Video create encoder error');
  }

  void onRTMPConnectingWhileLiveStatusError() {
    print('RTMP connecting while live status error');
  }

  void onUnionPublisherErrorQUICLoadLibraryFail() {
    print('Union publisher error QUIC load library fail');
  }

  void onUnionPublisherErrorQUICBase() {
    print('Union publisher error QUIC base');
  }

  void onUnionPublisherErrorQUICConnectTimeout() {
    print('Union publisher error QUIC connect timeout');
  }

  void onUnionPublisherErrorQUICHandshakeNotConfirmed() {
    print('Union publisher error QUIC handshake not confirmed');
  }

  void onInteractEventStartConnect() {
    print('Interact event start connect');
  }

  void onInteractEventStopConnect() {
    print('Interact event stop connect');
  }

  void onLiveCoreEventUpdateMetadataAuthInfo() {
    print('Live core event update metadata auth info');
  }

  void onLiveCoreEventUpdateRTCSI() {
    print('Live core event update RTC SEI');
  }

  void onInteractEventStartServerMix() {
    print('Interact event start server mix');
  }

  void onInteractEventStopServerMix() {
    print('Interact event stop server mix');
  }

  void onInteractEventReportNetworkInfo() {
    print('Interact event report network info');
  }

  void onInteractEventSwitchOfSEI() {
    print('Interact event switch of SEI');
  }

  void onInteractFrameTypeDefaultOccupy() {
    print('Interact frame type default occupy');
  }

  void onInteractFrameTypeMetadata() {
    print('Interact frame type metadata');
  }

  void onInteractFrameTypeSEI() {
    print('Interact frame type SEI');
  }

  void onInteractFrameTypeMetadataSEI() {
    print('Interact frame type metadata SEI');
  }

  void onVideoAlgorithmBMFBright() {
    print('Video algorithm BMF bright');
  }

  void onVideoAlgorithmBMFNoiseEvaluate() {
    print('Video algorithm BMF noise evaluate');
  }

  void onVideoAlgorithmOneKeyProcess() {
    print('Video algorithm one key process');
  }

  void onVideoAlgorithmBMFVideoDenoise() {
    print('Video algorithm BMF video denoise');
  }

  void onVideoAlgorithmBMFVQScore() {
    print('Video algorithm BMF VQ score');
  }

  void onLogEventIndex() {
    print('Log event index');
  }

  void onLogUUID() {
    print('Log UUID');
  }

  void onLogEventKeyPushStream() {
    print('Log event key push stream');
  }

  void onLogEventKeyLiveSDKLog() {
    print('Log event key live SDK log');
  }

  void onOtherEvent(int eventId) {
    print("event id from byeplus setInfoListener is $eventId");
  }
}
