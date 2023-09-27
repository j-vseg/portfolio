import 'package:flutter_test/flutter_test.dart';
import 'package:q42plugin/q42plugin.dart';
import 'package:q42plugin/q42plugin_platform_interface.dart';
import 'package:q42plugin/q42plugin_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockQ42pluginPlatform
    with MockPlatformInterfaceMixin
    implements Q42pluginPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final Q42pluginPlatform initialPlatform = Q42pluginPlatform.instance;

  test('$MethodChannelQ42plugin is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelQ42plugin>());
  });

  test('getPlatformVersion', () async {
    Q42plugin q42pluginPlugin = Q42plugin();
    MockQ42pluginPlatform fakePlatform = MockQ42pluginPlatform();
    Q42pluginPlatform.instance = fakePlatform;

    expect(await q42pluginPlugin.getPlatformVersion(), '42');
  });
}
