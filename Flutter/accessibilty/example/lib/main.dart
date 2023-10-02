import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:accessibilty/accessibilty.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _accessibilityStats = 'Unknown';
  final _accessibiltyPlugin = Accessibilty();

  @override
  void initState() {
    super.initState();
    initAccessibilityStatsState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initAccessibilityStatsState() async {
    String accessibilityStats;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      accessibilityStats = await _accessibiltyPlugin.getAccessibilityStats() ??
          'Unknown accessibility stats';
    } on PlatformException {
      accessibilityStats = 'Failed to get accessbility stats.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _accessibilityStats = accessibilityStats;
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
          child: Text('Running on: $_accessibilityStats\n'),
        ),
      ),
    );
  }
}
