import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'q42plugin_method_channel.dart';

abstract class Q42pluginPlatform extends PlatformInterface {
  Q42pluginPlatform() : super(token: _token);

  static final Object _token = Object();

  static Q42pluginPlatform _instance = MethodChannelQ42plugin();

  static Q42pluginPlatform get instance => _instance;

  static set instance(Q42pluginPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<String?> getQ42Stats() {
    throw UnimplementedError('getQ42Stats() has not been implemented.');
  }
}
