package accessibilityStatsLogger.com.accessibility_stats_logger

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import kotlin.test.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

/*
 * This demonstrates a simple unit test of the Kotlin portion of this plugin's implementation.
 *
 * Once you have built the plugin's example app, you can run these tests from the command
 * line by running `./gradlew testDebugUnitTest` in the `example/android/` directory, or
 * you can run them directly from IDEs that support JUnit such as Android Studio.
 */

internal class AccessibilityStatsLoggerPluginTest {
  @Test
  fun formatString_returnsExpectedValue() {
    val inputString = "{test1=value1, test2=value2}"
    val expectedString = "{\"test1\": \"value1\", \"test2\": \"value2\"}"

    assertEquals(expectedString, Formatter.formatString(inputString))
  }
}
