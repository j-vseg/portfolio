# Accessibility Statistics Logger

This Flutter plugin is able to gather Accessibility data from iOS and Android. It gathers data from the ScreenReader, VoiceOver, font size and many more. 

## Getting Started
### Installation
```console
flutter pub add accessibility_stats_logger
```
```properties
dependencies:
  accessibility_stats_logger: ^1.1.0
```

### Import
```dart
import 'package:accessibility_stats_logger/accessibility_stats_logger.dart';
```

### Usage
All you need to gather the data is to make an instance to this plugin and call the method ```getAccessibiltyStats()```. This method will return a ```Map<String, Object?>``` with the accessiblity data based on what device and platform you are using.
```dart
Map<String, Object?> accessibilityMap = AccessibilityStatsLogger().getAccessibiltyStats(); 
```

## The responses
### Android
```json
{
    "isTouchExplorationEnabled": "false", 
    "isTalkBackEnabled": "false", 
    "isSamsungTalkBackEnabled": "false", 
    "isSelectToSpeakEnabled": "false", 
    "isSwitchAccessEnabled": "false", 
    "isBrailleBackEnabled": "false", 
    "isVoiceAccessEnabled": "false", 
    "fontScale": "1.3", 
    "fontWeightAdjustment": "0", 
    "displayScale": "1.1892857142857143", 
    "isClosedCaptioningEnabled": "false", 
    "isAnimationsDisabled": "false", 
    "enabledAccessibilityServices": "[]", 
    "screenOrientation": "portrait", 
    "applicationId": "ExampleApp", 
    "defaultLanguage": "en-US", 
    "sdkVersion": "33", 
    "manufacturer": "Google", 
    "modelName": "sdk_gphone64_x86_64", 
    "isNightModeEnabled": "false", 
    "daytime": "day"
}
```

### iOS:
```json
{
    "Accessibility_isSpeakScreenEnabled": "false", 
    "Accessibility_isSpeakSelectionEnabled": "false",
    "Watch_supported": "true",
    "Accessibility_uses_any_accessibility_setting": "false",
    "System_model_name": "Simulator",
    "Screen_in_split_screen": "false",
    "Accessibility_isBoldTextEnabled": "false", 
    "Stats_version": "iOS 2022-04-15", 
    "Screen_device_idiom": "phone",
    "Accessibility_isGrayscaleEnabled": "false",
    "Screen_zoomed": "false",
    "Preference_preferred_content_size": "large",
    "Screen_window_width": "393",
    "Accessibility_isSwitchControlRunning": "false",
    "Accessibility_isShakeToUndoEnabled": "true",
    "Screen_scale": "@3x",
    "Accessibility_isClosedCaptioningEnabled": "false",
    "Accessibility_islnvertColorsEnabled": "false",
    "Preference_daytime": "day", 
    "Screen_width": "393",
    "Accessibility_isReduceTransparencyEnabled": "false",
    "System_model_id": "x86_64", 
    "Stats_timestamp": "1696490514.6148028", 
    "App_bundle_identifier": "accessibility.plugin.com.accessibiltyExample",
    "System_OS_major_version": "16",
    "Accessibility_isDarkerSystemColorsEnabled": "false", 
    "Accessibility_isAssistiveTouchRunning_with_isGuidedAccessEnabled": "Unknown", 
    "System_Preferred_language": "nl-US", 
    "System_Dutch_region": "false",
    "Preference_UI_style": "light", 
    "Screen_display_gamut": "P3", 
    "Accessibility_isMonoAudioEnabled": "false",
    "Screen_orientation": "portrait",
    "Accessibility_isVoiceOverRunning": "false",
    "Accessibility_isGuidedAccessEnabled": "false"
}
```

## License 
[MIT License](./LICENSE)

## Acknowledgments
Inspiration, code snippets, etc.

- [Q42 Library for Android](https://github.com/Q42/Q42Stats.Android)
- [Q42 Library for iOS](https://github.com/Q42/Q42Stats)