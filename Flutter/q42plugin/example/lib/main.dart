import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:q42plugin/q42plugin.dart';

void main() {
  runApp(const MyApp());
  WidgetsFlutterBinding.ensureInitialized();
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _q42Stats = 'Unknown';
  final _q42pluginPlugin = Q42plugin();

  @override
  void initState() {
    super.initState();
    initQ42StatsState();
  }

  Future<void> initQ42StatsState() async {
    String q42Stats;
    try {
      q42Stats =
          await _q42pluginPlugin.getQ42Stats() ?? 'Unknown q42 stats';
    } on PlatformException {
      q42Stats = 'Failed to get Q42 Stats';
    }
    if (!mounted) return;

    setState(() {
      _q42Stats = q42Stats;
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
          child: Text('Running on: $_q42Stats\n'),
        ),
      ),
    );
  }
}
