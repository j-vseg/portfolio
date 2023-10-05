import Flutter
import UIKit

public class AccessibiltyPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "accessibilty", binaryMessenger: registrar.messenger())
    let instance = AccessibiltyPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch call.method {
    case "getPlatformVersion":
      result("iOS " + UIDevice.current.systemVersion)
    case "getAccessibilityStats":
      var collected: [String: String]? = AccessibilityStats(options: .all).collect(window: UIWindow())
      var accessibilityData = collected?.description ?? "[]"
      accessibilityData = accessibilityData.replacingOccurrences(of: "[", with: "{")
      accessibilityData = accessibilityData.replacingOccurrences(of: "]", with: "}")

      result(accessibilityData)
    default:
      result(FlutterMethodNotImplemented)
    }
  }
}
