# Accessibilty Plugin

This Flutter plugin is able to gather Accessibility data from iOS and Android. It gathers data from the ScreenReader, VoiceOver, font size and many more. 

## Getting Started
All you need to gather the data is to make an instance to this plugin and call the method ```getAccessibiltyStats()```. This method will return a String with the accessiblity data based on what device and platform you are using.

## The responses
Android: 
```
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
    "applicationId": "com.philips.lighting.hue2.daily", 
    "defaultLanguage": "en-US", 
    "sdkVersion": "33", 
    "manufacturer": "Google", 
    "modelName": "sdk_gphone64_x86_64", 
    "isNightModeEnabled": "false", 
    "daytime": "day"
}
```

iOS:
```
{
    "Accessibility_isSpeakScreenEnabled": "false", "Accessibility_isSpeakSelectionEnabled": "false",
    "Watch_supported": "true",
    "Accessibility_uses_any_accessibility_setting":
    "System_model_name": "Simulator",
    "Screen_in_split_screen": "false",
    "Accessibility_isBoldTextEnabled": "false", 
    "Stats_version": "iOS 2022-04-15", "Screen_device_idiom": "phone",
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