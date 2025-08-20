#!/bin/bash
makeDir() { if [ ! -d $1 ]; then mkdir -pm 755 $1; fi }

VERSION=${pom.version}
LOG_DIR=logs; makeDir $LOG_DIR
VAR_DIR=var; makeDir $VAR_DIR
DATE=`date +%Y%m%d-%H%m%S`

if [ $# -ne 0 ]
then
    echo "Usage: $0"
    exit
else
    if [ -f $VAR_DIR/blade.pid ] && kill -0 `cat $VAR_DIR/blade.pid` 2>/dev/null
    then
        echo BLADE data platform is running, please shut it down before attempting to start ...
    else
        CWD=`pwd`
        java -Dlog4j.configuration=file:///$CWD/etc/log4j.properties -jar $CWD/lib/platform-$VERSION.jar >> $LOG_DIR/blade-$DATE.out 2>> $LOG_DIR/blade-$DATE.err &
        echo $! > $VAR_DIR/blade.pid
    fi
fi