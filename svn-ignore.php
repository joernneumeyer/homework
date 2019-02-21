#!/usr/bin/php
<?php

function accumulate_ignores(string $base): array {
  $result = [];
  if (file_exists($ignore_path = realpath($base . '/.gitignore'))) {
    $content = file_get_contents($ignore_path);
    $result = explode("\n", $content);
  }
  if (!file_exists(realpath($base . '/.git'))) {
    $upper_ignores = accumulate_ignores(realpath($base . '/..'));
    foreach ($upper_ignores as $ignore) {
      $result[] = $ignore;
    }
    return $result;
  }
  return $result;
}
// svn propset svn:ignore "ignoreThis.txt" .
$ignores = accumulate_ignores($argv[1]);
foreach ($ignores as $ignore) {
  if (!$ignore) continue;
  shell_exec("svn propset --recursive svn:ignore \"{$ignore}\" {$argv[1]}");
}