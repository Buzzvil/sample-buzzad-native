
LIB_VERSION=$1

if [[ -z "$LIB_VERSION" ]]; then
    echo "usage: $0 [LIBRARY_VERSION]"
    exit 1
fi

echo $LIB_VERSION

TARGET_DIR=./app/libs
BINTRAY_HOST=https://bintray.com
BINTRAY_DOWNLOAD_PATH=$BINTRAY_HOST/buzzvil/maven/download_file

PACKAGE_NAME=com/buzzvil/mediation

# mediation-skds

export IFS=";"
# appnext minSdkVersion 17: SDK_NAMES="sdk-appnext;sdk-outbrain;sdk-baidu;sdk-mopub;buzzad-sdk"
SDK_NAMES="sdk-outbrain;sdk-baidu;sdk-mopub;sdk-admob;buzzad-sdk"
for SDK_NAME in $SDK_NAMES; do
    SRC_URL=$BINTRAY_DOWNLOAD_PATH?file_path=$PACKAGE_NAME/$SDK_NAME/$LIB_VERSION/$SDK_NAME-$LIB_VERSION.aar
    echo $SRC_URL
    wget --show-progress -O $TARGET_DIR/$SDK_NAME-$LIB_VERSION.aar $BINTRAY_DOWNLOAD_PATH?file_path=$PACKAGE_NAME/$SDK_NAME/$LIB_VERSION/$SDK_NAME-$LIB_VERSION.aar
done

# buzzad-sdk

SDK_NAME=buzzad-sdk
PACKAGE_NAME=com/buzzvil/buzzad

SRC_URL=$BINTRAY_DOWNLOAD_PATH?file_path=$PACKAGE_NAME/$SDK_NAME/$LIB_VERSION/$SDK_NAME-$LIB_VERSION.aar

echo $SRC_URL
wget --show-progress -O $TARGET_DIR/$SDK_NAME-$LIB_VERSION.aar $BINTRAY_DOWNLOAD_PATH?file_path=$PACKAGE_NAME/$SDK_NAME/$LIB_VERSION/$SDK_NAME-$LIB_VERSION.aar
