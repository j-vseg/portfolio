package accessibilityStatsLogger.com.accessibility_stats_logger.collectors

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import accessibilityStatsLogger.com.accessibility_stats_logger.util.DayTimeUtil
import java.io.Serializable

internal object PreferencesCollector {
    fun collect(context: Context) = mutableMapOf<String, Serializable>().apply {
        val configuration = context.resources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(
                "isNightModeEnabled",
                configuration.uiMode
                    .and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
            )
        }
        put("daytime", DayTimeUtil.dayNight().serverValue)
    }
}