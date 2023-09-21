#ifndef FLUTTER_PLUGIN_Q42PLUGIN_PLUGIN_H_
#define FLUTTER_PLUGIN_Q42PLUGIN_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace q42plugin {

class Q42pluginPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  Q42pluginPlugin();

  virtual ~Q42pluginPlugin();

  // Disallow copy and assign.
  Q42pluginPlugin(const Q42pluginPlugin&) = delete;
  Q42pluginPlugin& operator=(const Q42pluginPlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace q42plugin

#endif  // FLUTTER_PLUGIN_Q42PLUGIN_PLUGIN_H_
