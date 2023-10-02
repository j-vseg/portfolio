import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'accessibilty_platform_interface.dart';

/// An implementation of [AccessibiltyPlatform] that uses method channels.
class MethodChannelAccessibilty extends AccessibiltyPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('accessibilty');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<String?> getAccessibilityStats() async {
    final version =
        await methodChannel.invokeMethod<String>('getAccessibilityStats');
    return version;
  }
}
