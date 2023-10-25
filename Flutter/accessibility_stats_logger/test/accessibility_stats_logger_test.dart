import 'package:flutter_test/flutter_test.dart';
import 'package:accessibility_stats_logger/accessibility_stats_logger.dart';
import 'package:accessibility_stats_logger/accessibility_stats_logger_platform_interface.dart';
import 'package:accessibility_stats_logger/accessibility_stats_logger_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAccessibilityStatsLoggerPlatform
    with MockPlatformInterfaceMixin
    implements AccessibilityStatsLoggerPlatform {
  @override
  Future<Map<String, Object?>> getAccessibilityStats() {
    Map<String, Object?> result = {};
    result.addAll({"test1": "value1", "test2": "value2"});
    return Future.value(result);
  }
}

void main() {
  final AccessibilityStatsLoggerPlatform initialPlatform =
      AccessibilityStatsLoggerPlatform.instance;
  final AccessibilityStatsLogger accessibilityStatsLoggerPlugin =
      AccessibilityStatsLogger();

  setUp(() {
    MockAccessibilityStatsLoggerPlatform fakePlatform =
        MockAccessibilityStatsLoggerPlatform();
    AccessibilityStatsLoggerPlatform.instance = fakePlatform;
  });

  test('$MethodChannelAccessibilityStatsLogger is the default instance', () {
    expect(
        initialPlatform, isInstanceOf<MethodChannelAccessibilityStatsLogger>());
  });

  test('getAccessibilityStats returns map', () async {
    expect(
        await accessibilityStatsLoggerPlugin
            .getAccessibilityStats()
            .then((value) => value.toString()),
        "[test1, test2, test3, test4]");
  });
}
