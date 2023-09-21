import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'q42plugin_method_channel.dart';

abstract class Q42pluginPlatform extends PlatformInterface {
  /// Constructs a Q42pluginPlatform.
  Q42pluginPlatform() : super(token: _token);

  static final Object _token = Object();

  static Q42pluginPlatform _instance = MethodChannelQ42plugin();

  /// The default instance of [Q42pluginPlatform] to use.
  ///
  /// Defaults to [MethodChannelQ42plugin].
  static Q42pluginPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [Q42pluginPlatform] when
  /// they register themselves.
  static set instance(Q42pluginPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
