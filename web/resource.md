# 리소스

## 정적 리소스(정적 자원)
* 프로그램 실행 시 변경되는 정보가 거의 없는 리소스
* 고정된 HTML 파일, CSS, JS, 이미지, 영상 등을 제공
* 주로 웹 브라우저에서 요청

![image](https://github.com/kmularise/TIL/assets/106499310/989cbc83-653b-4542-ad2a-1d6d0d6791e2)

## 동적 리소스(동적 자원)
* 파일을 바꾸지 않아도 조건에 따라 다른 응답 데이터를 전송하는 URL에 해당하는 리소스
* 시간이나 특정 조건에 따라 응답 데이터가 달라지는 리소스

## HTML 페이지
* 동적으로 필요한 HTML 파일을 생성해서 전달
* 웹 브라우저 : HTML 해석

![image](https://github.com/kmularise/TIL/assets/106499310/8e64f9b3-fdc2-4946-8369-ffbd414617f0)

## HTTP API
* HTML이 아니라 데이터를 전달
* 주로 JSON 형식 사용
* 다양한 시스템에서 호출

![image](https://github.com/kmularise/TIL/assets/106499310/e21238f2-55e4-4300-8fd1-7606eb820dc7)

* 다양한 시스템에서 호출
* 데이터만 주고 받음, UI 화면이 필요하면, 클라이언트가 별도 처리
* 앱, 웹 클라이언트, 서버 to 서버

![image](https://github.com/kmularise/TIL/assets/106499310/f6908cd4-2b76-460a-a289-e8da8f399f81)

* 주로 JSON 형태로 데이터 통신
* UI 클라이언트 접점
    * 앱 클라이언트(아이폰, 안드로이드, PC 앱)
    * 웹 브라우저에서 자바스크립트를 통한 HTTP API 호출
    * React, Vue.js 같은 웹 클라이언트
* 서버 to 서버
    * 주문 서버 -> 결제 서버
    * 기업 간 데이터 통신
