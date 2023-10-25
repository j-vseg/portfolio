import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'accessibility_stats_logger_method_channel.dart';

abstract class AccessibilityStatsLoggerPlatform extends PlatformInterface {
  AccessibilityStatsLoggerPlatform() : super(token: _token);

  static final Object _token = Object();

  static AccessibilityStatsLoggerPlatform _instance =
      MethodChannelAccessibilityStatsLogger();

  static AccessibilityStatsLoggerPlatform get instance => _instance;

  static set instance(AccessibilityStatsLoggerPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<Map<String, Object?>> getAccessibilityStats();
}
