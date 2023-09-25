import Flutter
import UIKit
//import Q42Stats

public class Q42pluginPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "q42plugin", binaryMessenger: registrar.messenger())
    let instance = Q42pluginPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch call.method {
    case "getPlatformVersion":
      result("iOS " + UIDevice.current.systemVersion)
    case "getQ42Stats":
      result("iOS - Getting Q42 Stats")
      //Q42Stats.collect()
    default:
      result(FlutterMethodNotImplemented)
    }
  }
}
