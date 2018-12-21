# BookSearchApp
It 관련 책을 검색하여 상세 화면을 볼 수 있는 안드로이드 어플리케이션

## APK 
BookSearchApp.apk를 받아서 설치하실 수 있습니다.

## 개발환경
IDE : Android Studio 3.2.0
minSdkVersion : 15
targetSdkVersion : 27

## 적용 패턴 : MVP, Contract

## 사용 라이브러리
- RxJava, RxBinding
- Retrofit
- Dagger
- Glide
- Anko

## 미처리 사항
1. 네트워크가 불안정할 떄 (ex. 엘리베이터 등) 검색하려는 키워드 보관처리 하지 않음 (DB 사용 X)
2. 검색 이벤트가 발생할 때 키보드 hide X
3. Material Design 적용 X
4. 네트워크 logging interceptor 적용 X
5. Test Code 작성 X

미처리이슈가 발생하게 된 이유 : 변명 같지만.. 토요일에 과제를 할 시간이 없어서 현재까지 완료된 사항만 제출하도록 하겠습니다.

