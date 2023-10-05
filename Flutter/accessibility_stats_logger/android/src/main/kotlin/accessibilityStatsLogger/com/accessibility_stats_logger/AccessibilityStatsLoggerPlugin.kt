package accessibilityStatsLogger.com.accessibility_stats_logger

import android.content.Context
import android.os.Build
import android.util.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import accessibilityStatsLogger.com.accessibility_stats_logger.collectors.AccessibilityCollector
import accessibilityStatsLogger.com.accessibility_stats_logger.collectors.SystemCollector
import accessibilityStatsLogger.com.accessibility_stats_logger.collectors.PreferencesCollector
import java.io.Serializable

class AccessibilityStatsLoggerPlugin: FlutterPlugin, MethodCallHandler {
  private lateinit var channel : MethodChannel
  private var context: Context? = null

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "accessibility_stats_logger")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } 
    else if (call.method == "getAccessibilityStats") {
      val collection = mutableMapOf<String, Serializable>()
      collection.putAll(AccessibilityCollector.collect(context!!))
      collection.putAll(SystemCollector.collect(context!!))
      collection.putAll(PreferencesCollector.collect(context!!))

      Log.d("AccessibilityCollector", AccessibilityCollector.collect(context!!).toString())
      Log.d("SystemCollector", SystemCollector.collect(context!!).toString())
      Log.d("PreferencesCollector: ", PreferencesCollector.collect(context!!).toString())

      var accessibilityData = collection.toString()
      accessibilityData = accessibilityData.replace("=", "\": \"");
      accessibilityData = accessibilityData.replace(", ", "\", \"");
      accessibilityData = accessibilityData.replace("{", "{\"");
      accessibilityData = accessibilityData.replace("}", "\"}");

      result.success(accessibilityData)
    }
    else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
