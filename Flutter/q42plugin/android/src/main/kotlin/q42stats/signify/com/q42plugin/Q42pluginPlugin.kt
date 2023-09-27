package q42stats.signify.com.q42plugin

import android.content.Context
import android.os.Build
import android.util.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import q42stats.signify.com.q42plugin.collectors.AccessibilityCollector
import q42stats.signify.com.q42plugin.collectors.PreferencesCollector
import q42stats.signify.com.q42plugin.collectors.SystemCollector

/** Q42pluginPlugin */
class Q42pluginPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private var context: Context? = null

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "q42plugin")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${Build.VERSION.RELEASE}")
    }
    else if (call.method == "getQ42Stats") {
      //val test = call.hasArgument("context")
      //val list: List<Map<String, String>>? = call.arguments()
      //Log.i("MyTag", "value 1 = " + list!![0]["key1"]) // prints Apple

      //val context = call.argument<Context>("context")
      Log.d("AccessibilityCollector", AccessibilityCollector.collect(context!!).toString())
      Log.d("SystemCollector", SystemCollector.collect(context!!).toString())
      Log.d("PreferencesCollector: ", PreferencesCollector.collect(context!!).toString())
      result.success(
        "AccessibilityCollector: " + AccessibilityCollector.collect(context!!).toString() + " | " +
              "SystemCollector: " + SystemCollector.collect(context!!).toString() + " | " +
              "PreferencesCollector: " + PreferencesCollector.collect(context!!).toString())
      //result.success("Get Q42 Stats")
    }
    else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
