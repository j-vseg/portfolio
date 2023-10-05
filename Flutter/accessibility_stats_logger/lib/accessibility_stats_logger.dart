import 'accessibility_stats_logger_platform_interface.dart';

class AccessibilityStatsLogger {
  Future<String?> getPlatformVersion() {
    return AccessibilityStatsLoggerPlatform.instance.getPlatformVersion();
  }

  Future<String?> getAccessibilityStats() {
    return AccessibilityStatsLoggerPlatform.instance.getAccessibilityStats();
  }
}
