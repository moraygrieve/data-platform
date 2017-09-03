#!/bin/bash
VAR_DIR=var

if [ -f $VAR_DIR/blade.pid ] && kill -0 `cat $VAR_DIR/blade.pid` 2>/dev/null
then
    kill -9 `cat $VAR_DIR/blade.pid`
    rm $VAR_DIR/blade.pid
else
    echo BLADE data platform is not running ...
fi