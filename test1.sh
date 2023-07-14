#!/bin/bash

CURRENT_DATETIME=$(TZ=Asia/Seoul date +'%Y-%m-%d %H:%M:%S')
echo "Current date and time in KST: $CURRENT_DATETIME"
LOG_NAME=$(TZ=Asia/Seoul date +'%Y%m%d')
FILE_EXT=".md"
TITLE=$LOG_NAME$FILE_EXT

## 적절한 경로에 해당 데일리 로그가 있는지 확인

## 해당 slack url에 POST 요청 보내기
tempURL=$1
curl --location ${tempURL} \
--header 'Content-Type: application/json' \
--data '{
  "attachments": [
      {
          "fallback": "요청이 실패했습니다.",
          "color": "#2eb886",
          "pretext": "TIL 작성했습니다!",
          "author_name": "김의진",
          "author_link": "https://github.com/kmularise",
          "title": "'"$TITLE"'",
          "title_link": "https://github.com/kmularise/TIL/blob/main/DailyLog/'"$TITLE"'",
          "text": "업로드 시간 : '"$CURRENT_DATETIME"'",
      }
  ]
}'