import 'accessibility_stats_logger_platform_interface.dart';

class AccessibilityStatsLogger {
  Future<Map<String, Object?>> getAccessibilityStats() {
    return AccessibilityStatsLoggerPlatform.instance.getAccessibilityStats();
  }
}
