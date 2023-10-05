import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'accessibility_stats_logger_platform_interface.dart';

class MethodChannelAccessibilityStatsLogger
    extends AccessibilityStatsLoggerPlatform {
  @visibleForTesting
  final methodChannel = const MethodChannel('accessibility_stats_logger');

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
