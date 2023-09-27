import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:q42plugin/q42plugin.dart';

void main() {
  runApp(const MyApp());
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

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initQ42StatsState() async {
    String q42Stats;
    try {
      print(context);
      q42Stats =
          await _q42pluginPlugin.getQ42Stats() ?? 'Unknown Q42 Stats';
    } on PlatformException {
      q42Stats = 'Failed to get Q42 Stats.';
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
          title: const Text('Q42 Stats example app'),
        ),
        body: Center(
          child: Text('$_q42Stats\n'),
        ),
      ),
    );
  }
}
