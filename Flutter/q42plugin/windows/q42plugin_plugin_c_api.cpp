#include "include/q42plugin/q42plugin_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "q42plugin_plugin.h"

void Q42pluginPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  q42plugin::Q42pluginPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
