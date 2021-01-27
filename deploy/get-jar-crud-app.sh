#!/bin/bash

# exit when any command fails
set -e
# keep track of the last executed command
trap 'last_command=$current_command; current_command=$BASH_COMMAND' DEBUG
# echo an error message before exiting
trap 'echo "\"${last_command}\" command failed with exit code $?." cleanup' ERR

# deletes the temp directory
function cleanup {
  rm -rf $tmpdir
  echo "Deleted temp directory $tmpdir"
}

#cleanup before exiting
trap cleanup EXIT

workdir=$(pwd)

if [ -n "$2" ]; then
    gitRepoUrl="-b $1 $2"
else
    gitRepoUrl=$1
fi

echo "Creating tmp directory..."

tmpdir=$(mktemp -d -t simple-crud-app-XXXXXXXXXX)
CRUD_PATH=$tmpdir/crud
CRUD_JAR_PATH=$CRUD_PATH/target/CRUD-Application-1.0.jar

# Step 1.
# Cloning cloud-pipeline(CP) API from git repository,
# where $1 is URL(with possible specified branch(-b)), and
# $2 is where the CP will be copied.
echo "Cloning git-repo from $gitRepoUrl"
git clone $gitRepoUrl $CRUD_PATH;

# Step 2.
# Build jar file from crud-app module(TES-PATH)
echo "Building jar-file..."

if [ -d "$CRUD_PATH" ]; then
    cd $CRUD_PATH
    chmod +x mvnw
     ./mvnw package -DskipTests=true
else
   echo "The $CRUD_PATH directory doesn't exist!" 2>&1; exit 1
fi

# Step 3
echo "Copying jar-file from crud-app to $workdir"
cp -rf $CRUD_JAR_PATH $workdir

echo "Success! You can find the corresponding jar-file in the folder $workdir"