import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'dart:convert';

import 'accessibility_stats_logger_platform_interface.dart';

class MethodChannelAccessibilityStatsLogger
    extends AccessibilityStatsLoggerPlatform {
  @visibleForTesting
  final methodChannel = const MethodChannel('accessibility_stats_logger');

  @override
  Future<Map<String, Object?>> getAccessibilityStats() async {
    try {
      final accessibilityString =
          await methodChannel.invokeMethod<String>('getAccessibilityStats');
      final accessiblityData =
          jsonDecode(accessibilityString.toString()) as Map<String, Object?>;
      return accessiblityData;
    } catch (e) {
      return {};
    }
  }
}
