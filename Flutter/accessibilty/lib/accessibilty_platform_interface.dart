import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'accessibilty_method_channel.dart';

abstract class AccessibiltyPlatform extends PlatformInterface {
  /// Constructs a AccessibiltyPlatform.
  AccessibiltyPlatform() : super(token: _token);

  static final Object _token = Object();

  static AccessibiltyPlatform _instance = MethodChannelAccessibilty();

  /// The default instance of [AccessibiltyPlatform] to use.
  ///
  /// Defaults to [MethodChannelAccessibilty].
  static AccessibiltyPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AccessibiltyPlatform] when
  /// they register themselves.
  static set instance(AccessibiltyPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<String?> getAccessibilityStats() {
    throw UnimplementedError(
        'getAccessibilityStats() has not been implemented.');
  }
}
