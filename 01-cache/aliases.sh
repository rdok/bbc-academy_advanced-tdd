#!/bin/bash

workbench() {
  docker-compose run --rm workbench "$@"
}

java() {
  docker-compose run --rm workbench java "$@"
}

javac() {
  docker-compose run --rm workbench javac "$@"
}

ant() {
  docker-compose run --rm workbench ant "$@"
}

junit() {
  java org.junit.runner.JUnitCore "dev.rdok.TestSuite"
}
