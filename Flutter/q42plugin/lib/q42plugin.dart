
import 'q42plugin_platform_interface.dart';

class Q42plugin {
  Future<String?> getPlatformVersion() {
    return Q42pluginPlatform.instance.getPlatformVersion();
  }
}
