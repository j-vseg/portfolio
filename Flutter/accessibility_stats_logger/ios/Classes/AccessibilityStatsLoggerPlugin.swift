import Flutter
import UIKit

public class AccessibilityStatsLoggerPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "accessibility_stats_logger", binaryMessenger: registrar.messenger())
    let instance = AccessibilityStatsLoggerPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch call.method {
    case "getAccessibilityStats":
      var collected: [String: String]? = AccessibilityStats(options: .all).collect(window: UIWindow())
      result(Formatter.stringFormatter(string: collected?.description ?? "[]"))
    default:
      result(FlutterMethodNotImplemented)
    }
  }
}

enum Formatter {
  public static func stringFormatter(string: String) -> String {
    var formattedString = string
      formattedString = formattedString.replacingOccurrences(of: "[", with: "{")
      formattedString = formattedString.replacingOccurrences(of: "]", with: "}")
    return formattedString
  }
}
