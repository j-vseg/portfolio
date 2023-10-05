import 'package:flutter_test/flutter_test.dart';
import 'package:accessibility_stats_logger/accessibility_stats_logger.dart';
import 'package:accessibility_stats_logger/accessibility_stats_logger_platform_interface.dart';
import 'package:accessibility_stats_logger/accessibility_stats_logger_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAccessibilityStatsLoggerPlatform
    with MockPlatformInterfaceMixin
    implements AccessibilityStatsLoggerPlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<String?> getAccessibilityStats() {
    // TODO: implement getAccessibilityStats
    throw UnimplementedError();
  }
}

void main() {
  final AccessibilityStatsLoggerPlatform initialPlatform =
      AccessibilityStatsLoggerPlatform.instance;

  test('$MethodChannelAccessibilityStatsLogger is the default instance', () {
    expect(
        initialPlatform, isInstanceOf<MethodChannelAccessibilityStatsLogger>());
  });

  test('getPlatformVersion', () async {
    AccessibilityStatsLogger accessibilityStatsLoggerPlugin =
        AccessibilityStatsLogger();
    MockAccessibilityStatsLoggerPlatform fakePlatform =
        MockAccessibilityStatsLoggerPlatform();
    AccessibilityStatsLoggerPlatform.instance = fakePlatform;

    expect(await accessibilityStatsLoggerPlugin.getPlatformVersion(), '42');
  });
}
