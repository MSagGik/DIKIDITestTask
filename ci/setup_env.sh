#!/bin/bash

envs="
apiAccessToken=${API_ACCESS_TOKEN}
"

(echo "$envs" | grep -E '.+=.+') >> develop.properties
