#!/bin/bash

if [ -z "$1" ]; then
    echo "ERROR: No tag supplied"
    exit 1
fi

TAG=$1
curl --location --request POST "https://api.bitrise.io/v0.1/apps/$ANDROID_COMPONENTS_APP_SLUG/builds" \
--header 'accept: application/json' \
--header "Authorization: $BITRISE_TOKEN" \
--header 'Content-Type: application/json' \
--data-raw '{
  "build_params": {
    "tag": "'$TAG'",
    "workflow_id": "'$ANDROID_COMPONENTS_COMPOSE_RELEASE_WORKFLOW_ID'"
  },
  "hook_info": {
    "type": "bitrise"
  }
}'