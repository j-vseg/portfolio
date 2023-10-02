import 'accessibilty_platform_interface.dart';

class Accessibilty {
  Future<String?> getPlatformVersion() {
    return AccessibiltyPlatform.instance.getPlatformVersion();
  }

  Future<String?> getAccessibilityStats() {
    return AccessibiltyPlatform.instance.getAccessibilityStats();
  }
}
