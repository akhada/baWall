#!/bin/bash
TEST_SERVER_PORT=34777
MOBILE_APP_PACKAGE=$1
if [ -z "$MOBILE_APP_PACKAGE" ]; then
   echo 'The parameter about your mobile app package is missing: e.g. com.springsource.greenhouse.test'
   exit 1
else
   adb shell am instrument -e class sh.calaba.instrumentationbackend.InstrumentationBackend -w $MOBILE_APP_PACKAGE/android.test.InstrumentationTestRunner &
   sleep 7
   IRBRC=.irbrc TEST_SERVER_PORT=$TEST_SERVER_PORT irb
fi
