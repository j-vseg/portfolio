import Flutter
import UIKit

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
      var collected : [String:String]? = Q42Stats(options: .all).collect(window: UIWindow())
      let resultString = collected?.description ?? "nil"
      print(resultString)
      result(resultString)
      //result("iOS - Getting Q42 Stats")
    default:
      result(FlutterMethodNotImplemented)
    }
  }
}
