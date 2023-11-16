#!/bin/bash

CURRENT_DATETIME=$(TZ=Asia/Seoul date +'%Y-%m-%d %H:%M:%S')
echo "Current date and time in KST: $CURRENT_DATETIME"
LOG_NAME=$(TZ=Asia/Seoul date +'%Y%m%d')
LOG_NAME="20231102"
FILE_EXT=".md"
TITLE=$LOG_NAME$FILE_EXT
CURRENT_MONTH=$(TZ=Asia/Seoul date +'%Y%m')
DIR="Y${CURRENT_MONTH}"
echo $DIR

## 적절한 경로에 해당 데일리 로그가 있는지 확인
if [ ! -f "./DailyLog/"$DIR"/"$TITLE ]; then
    echo "Error: '"$TITLE"' not found"
    exit 1  # Exit with status code 1 indicating an error
fi

# 해당 slack url에 POST 요청 보내기
tempURL=$1
AUTHOR_NAME=$2
CONTENT=$(sed -n '/Daily Check/ q;p' "./DailyLog/"$DIR"/"$TITLE | grep [X] | sed 's/\[X\]//g' | sed 's/([^)]*)//g' | sed 's/\[//g' | sed 's/\]//g') 

curl --location ${tempURL} \
--header 'Content-Type: application/json' \
--data '{
  "attachments": [
      {
          "fallback": "요청이 실패했습니다.",
          "color": "#2eb886",
          "pretext": "TIL 작성했습니다!",
          "author_name": "'"$AUTHOR_NAME"'",
          "author_link": "https://github.com/kmularise",
          "title": "'"$TITLE"'",
          "title_link": "https://github.com/kmularise/TIL/blob/main/DailyLog/'"$DIR"'/'"$TITLE"'",
          "text": "'"$CONTENT"'",
      }
  ]
}'