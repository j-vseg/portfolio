import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'q42plugin_platform_interface.dart';

/// An implementation of [Q42pluginPlatform] that uses method channels.
class MethodChannelQ42plugin extends Q42pluginPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('q42plugin');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
