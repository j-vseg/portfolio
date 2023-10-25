import Flutter
import UIKit
import XCTest

@testable import accessibility_stats_logger

class RunnerTests: XCTestCase {

  func testStringFormatter() {
    var inputString = "{\"test1\": \"value1\", \"test2\": \"value2\"}"
    var expectedString = "[\"test1\": \"value1\", \"test2\": \"value2\"]"

    assertEquals(expectedString, Formatter.formattedString(inputString))
  }

}
