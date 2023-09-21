//
//  Generated file. Do not edit.
//

// clang-format off

#include "generated_plugin_registrant.h"

#include <q42plugin/q42plugin_plugin.h>

void fl_register_plugins(FlPluginRegistry* registry) {
  g_autoptr(FlPluginRegistrar) q42plugin_registrar =
      fl_plugin_registry_get_registrar_for_plugin(registry, "Q42pluginPlugin");
  q42plugin_plugin_register_with_registrar(q42plugin_registrar);
}
