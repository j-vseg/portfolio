package accessibility.plugin.com.accessibilty

import android.content.Context
import android.os.Build
import android.util.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import accessibility.plugin.com.accessibilty.collectors.AccessibilityCollector
import accessibility.plugin.com.accessibilty.collectors.SystemCollector
import accessibility.plugin.com.accessibilty.collectors.PreferencesCollector
import java.io.Serializable


/** AccessibiltyPlugin */
class AccessibiltyPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private var context: Context? = null

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "accessibilty")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } 
    else if (call.method == "getAccessibilityStats") {
      //val test = call.hasArgument("context")
      //val list: List<Map<String, String>>? = call.arguments()
      //Log.i("MyTag", "value 1 = " + list!![0]["key1"]) // prints Apple

      //val context = call.argument<Context>("context")

      val collection = mutableMapOf<String, Serializable>()
      collection.putAll(AccessibilityCollector.collect(context!!))
      collection.putAll(SystemCollector.collect(context!!))
      collection.putAll(PreferencesCollector.collect(context!!))

      Log.d("AccessibilityCollector", AccessibilityCollector.collect(context!!).toString())
      Log.d("SystemCollector", SystemCollector.collect(context!!).toString())
      Log.d("PreferencesCollector: ", PreferencesCollector.collect(context!!).toString())

      var accessibilityData = collection.toString()
      accessibilityData = accessibilityData.replace("=", "': '");
      accessibilityData = accessibilityData.replace(", ", "', '");
      accessibilityData = accessibilityData.replace("{", "{'");
      accessibilityData = accessibilityData.replace("}", "'}");

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
