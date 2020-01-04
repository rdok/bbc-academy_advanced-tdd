#!/bin/bash

java () {
  docker-compose run --rm java java "$@"
}

javac () {
  docker-compose run --rm java javac "$@"
}
