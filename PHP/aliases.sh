#!/bin/bash

composer () {
  docker-compose run --rm php composer "$@"
}

phpunit () {
  docker-compose run --rm php ./vendor/bin/phpunit "$@"
}
