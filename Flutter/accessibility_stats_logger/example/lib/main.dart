import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:accessibility_stats_logger/accessibility_stats_logger.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  Map<String, Object?> _accessibilityStatsLogger = {};
  final _accessibilityStatsLoggerPlugin = AccessibilityStatsLogger();

  @override
  void initState() {
    super.initState();
    initAccessibilityStatsLoggerState();
  }

  Future<void> initAccessibilityStatsLoggerState() async {
    Map<String, Object?> accessibilityStatsLogger;
    try {
      accessibilityStatsLogger =
          await _accessibilityStatsLoggerPlugin.getAccessibilityStats();
    } on PlatformException {
      accessibilityStatsLogger = {};
    }
    if (!mounted) return;

    setState(() {
      _accessibilityStatsLogger = accessibilityStatsLogger;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_accessibilityStatsLogger\n'),
        ),
      ),
    );
  }
}
