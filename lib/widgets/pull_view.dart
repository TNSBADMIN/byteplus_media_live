part of '../models/pull_engine_controller.dart';

class PullView extends StatelessWidget {
  const PullView({super.key, this.controller});

  final PullEngineController? controller;

  @override
  Widget build(BuildContext context) {
    // This is used in the platform side to register the view.
    const String viewType = 'byteplus_pull';

    return PlatformViewLink(
      viewType: viewType,
      surfaceFactory: (context, viewController) {
        print("jalan sini pull");
        controller?._initialize(viewController.viewId);

        return AndroidViewSurface(
          controller: viewController as AndroidViewController,
          gestureRecognizers: const <Factory<OneSequenceGestureRecognizer>>{},
          hitTestBehavior: PlatformViewHitTestBehavior.opaque,
        );
      },
      onCreatePlatformView: (params) {
        return PlatformViewsService.initSurfaceAndroidView(
          id: params.id,
          viewType: viewType,
          layoutDirection: TextDirection.ltr,
          // creationParams: option.toJsonRaw(),
          creationParamsCodec: const StandardMessageCodec(),
          onFocus: () {
            params.onFocusChanged(true);
          },
        )
          ..addOnPlatformViewCreatedListener(params.onPlatformViewCreated)
          ..create();
      },
    );
  }
}
