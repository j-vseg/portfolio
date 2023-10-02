import 'package:flutter_test/flutter_test.dart';
import 'package:accessibilty/accessibilty.dart';
import 'package:accessibilty/accessibilty_platform_interface.dart';
import 'package:accessibilty/accessibilty_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAccessibiltyPlatform
    with MockPlatformInterfaceMixin
    implements AccessibiltyPlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<String?> getAccessibilityStats() {
    // TODO: implement getAccessibilityStats
    throw UnimplementedError();
  }
}

void main() {
  final AccessibiltyPlatform initialPlatform = AccessibiltyPlatform.instance;

  test('$MethodChannelAccessibilty is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAccessibilty>());
  });

  test('getPlatformVersion', () async {
    Accessibilty accessibiltyPlugin = Accessibilty();
    MockAccessibiltyPlatform fakePlatform = MockAccessibiltyPlatform();
    AccessibiltyPlatform.instance = fakePlatform;

    expect(await accessibiltyPlugin.getPlatformVersion(), '42');
  });
}
