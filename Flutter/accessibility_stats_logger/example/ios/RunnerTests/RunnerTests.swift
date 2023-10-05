import Flutter
import UIKit
import XCTest

// swiftlint:disable force_cast

@testable import accessibility_stats_logger

class RunnerTests: XCTestCase {

  func testGetPlatformVersion() {
    let plugin = AccessibilityStatsLoggerPlugin()

    let call = FlutterMethodCall(methodName: "getPlatformVersion", arguments: [])

    let resultExpectation = expectation(description: "result block must be called.")
    plugin.handle(call) { result in
      XCTAssertEqual(result as! String, "iOS " + UIDevice.current.systemVersion)
      resultExpectation.fulfill()
    }
    waitForExpectations(timeout: 1)
  }

}

// swiftlint:enable force_cast
