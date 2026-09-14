# Flutter API 통신

## 1. API란?

API는 프로그램끼리 데이터를 주고받을 수 있게 해주는 방법이다.

Flutter 앱에서 서버의 데이터를 가져오거나 서버에 데이터를 보낼 때 API를 사용한다.

```text
Flutter 앱
    ↓
   API
    ↓
  서버
    ↓
데이터베이스
```

Flutter 앱이 데이터베이스에 직접 접근하는 것이 아니라 보통 서버를 통해 데이터를 주고받는다.

---

## 2. 클라이언트와 서버

### 클라이언트

사용자가 사용하는 프로그램이다.

Flutter로 만든 앱이 클라이언트에 해당한다.

서버에 데이터를 요청하거나 데이터를 보낼 수 있다.

### 서버

클라이언트에서 보낸 요청을 처리하는 역할을 한다.

필요하면 데이터베이스에서 데이터를 가져오고 그 결과를 다시 클라이언트에 보내준다.

```text
클라이언트
    ↓ 요청
서버
    ↓
데이터베이스
    ↓
서버
    ↓ 응답
클라이언트
```

---

## 3. Request와 Response

API 통신은 크게 Request와 Response로 나눌 수 있다.

### Request

클라이언트가 서버에 보내는 요청이다.

Request에는 여러 정보가 들어갈 수 있다.

* URL
* Method
* Header
* Query Parameter
* Body
* 인증 정보

### Response

서버가 클라이언트에게 보내는 응답이다.

Response에는 다음과 같은 정보가 들어갈 수 있다.

* 상태 코드
* Header
* Body
* 데이터

---

## 4. HTTP

HTTP는 클라이언트와 서버가 데이터를 주고받을 때 사용하는 통신 규칙이다.

Flutter에서 API 서버와 통신할 때 HTTP를 많이 사용한다.

HTTPS는 HTTP에 암호화가 추가된 것이다.

보통 실제 서비스에서는 HTTPS를 사용한다.

---

## 5. HTTP Method

HTTP에서는 요청의 목적에 따라 Method가 달라진다.

| Method | 설명           |
| ------ | ------------ |
| GET    | 데이터 조회       |
| POST   | 데이터 생성 또는 전달 |
| PUT    | 데이터 전체 수정    |
| PATCH  | 데이터 일부 수정    |
| DELETE | 데이터 삭제       |

### GET

서버에서 데이터를 가져올 때 사용한다.

### POST

서버에 데이터를 보내거나 새로운 데이터를 만들 때 사용한다.

### PUT

기존 데이터를 전체적으로 수정할 때 사용한다.

### PATCH

기존 데이터 중 일부만 수정할 때 사용한다.

### DELETE

데이터를 삭제할 때 사용한다.

---

## 6. URL

URL은 API 서버에 요청을 보낼 때 사용하는 주소이다.

어떤 서버에 요청할 것인지와 어떤 데이터를 사용할 것인지 나타낸다.

API에서는 특정 기능을 사용할 수 있도록 여러 URL을 만들어 놓을 수 있다.

---

## 7. Endpoint

Endpoint는 API에서 특정 기능에 접근하기 위한 주소이다.

하나의 서버에서도 여러 기능을 제공하기 때문에 기능마다 Endpoint가 다를 수 있다.

예를 들어 서버에서 사용자, 게시글, 상품 등의 기능을 각각 제공할 수 있다.

---

## 8. Header

Header는 HTTP 요청이나 응답에 대한 추가 정보를 넣는 부분이다.

API 통신에서는 다음과 같은 정보를 넣을 수 있다.

* 데이터 형식
* 인증 정보
* 언어
* 기타 요청 정보

API Key나 Token을 Header에 넣어서 인증하는 경우도 있다.

---

## 9. Content-Type

Content-Type은 Body에 들어있는 데이터의 형식을 알려주는 것이다.

JSON 데이터를 사용할 경우 보통 다음과 같이 사용한다.

```text
application/json
```

이것을 통해 서버에 전달하는 데이터가 JSON 형식이라는 것을 알려준다.

---

## 10. Body

Body는 실제 데이터를 넣는 부분이다.

특히 POST, PUT, PATCH 같은 요청에서 서버에 데이터를 보낼 때 많이 사용한다.

Response에서도 서버가 보내주는 실제 데이터가 Body에 들어간다.

```text
Request
├── Header
└── Body

Response
├── Header
└── Body
```

---

## 11. Query Parameter

Query Parameter는 URL 뒤에 추가해서 서버에 조건을 전달하는 방법이다.

주로 다음과 같은 용도로 사용한다.

* 검색
* 정렬
* 페이지 번호
* 데이터 개수
* 필터

여러 개를 사용할 수도 있다.

---

## 12. Path Parameter

Path Parameter는 URL 경로에 값을 넣어서 특정 데이터를 지정하는 방법이다.

주로 특정 데이터를 하나 지정하거나 식별할 때 사용한다.

Query Parameter와 비슷해 보이지만 사용하는 목적이 조금 다르다.

---

## 13. JSON

JSON은 API 통신에서 많이 사용하는 데이터 형식이다.

JSON은 데이터를 정리된 형태로 표현할 수 있어서 서버와 앱 사이에서 많이 사용한다.

JSON에서 사용할 수 있는 데이터는 다음과 같다.

* 문자열
* 숫자
* Boolean
* 배열
* 객체
* null

JSON은 사람이 읽기도 비교적 쉽고 여러 프로그래밍 언어에서 사용할 수 있다.

---

## 14. JSON Decode

서버에서 받은 JSON 데이터를 Dart에서 사용할 수 있는 형태로 바꾸는 것이다.

```text
JSON 데이터
    ↓
Decode
    ↓
Dart 데이터
```

Dart에서는 `dart:convert`를 사용해서 JSON을 변환할 수 있다.

---

## 15. JSON Encode

Dart에서 사용하는 데이터를 JSON 형식으로 바꾸는 것이다.

```text
Dart 데이터
    ↓
Encode
    ↓
JSON 데이터
```

서버에 JSON 데이터를 보내야 할 때 사용한다.

---

## 16. HTTP 상태 코드

서버가 요청을 처리한 결과를 숫자로 알려주는 것이다.

### 2xx

요청이 정상적으로 처리된 경우이다.

| 코드  | 의미                |
| --- | ----------------- |
| 200 | 요청 성공             |
| 201 | 생성 성공             |
| 204 | 성공했지만 전달할 데이터가 없음 |

### 4xx

클라이언트의 요청에 문제가 있는 경우이다.

| 코드  | 의미           |
| --- | ------------ |
| 400 | 잘못된 요청       |
| 401 | 인증 필요        |
| 403 | 접근 권한 없음     |
| 404 | 데이터를 찾을 수 없음 |

### 5xx

서버에서 문제가 발생한 경우이다.

| 코드  | 의미           |
| --- | ------------ |
| 500 | 서버 내부 오류     |
| 502 | 잘못된 게이트웨이    |
| 503 | 서버를 사용할 수 없음 |

---

## 17. Flutter에서 HTTP 통신

Flutter에서는 API 통신을 할 때 `http` 패키지를 많이 사용한다.

`http` 패키지를 사용하면 HTTP 요청을 쉽게 보낼 수 있다.

주로 사용하는 요청은 다음과 같다.

* GET
* POST
* PUT
* PATCH
* DELETE

---

## 18. 비동기 처리

API 통신은 서버와 인터넷을 통해 데이터를 주고받기 때문에 시간이 걸릴 수 있다.

그래서 Flutter에서는 비동기 처리를 사용한다.

주로 다음을 사용한다.

* Future
* async
* await

### Future

나중에 결과가 나오는 작업을 나타낸다.

API 통신 결과도 바로 나오지 않을 수 있기 때문에 Future를 사용한다.

### async

함수를 비동기 함수로 사용할 수 있게 한다.

### await

비동기 작업이 끝날 때까지 결과를 기다린다.

```text
API 요청
    ↓
응답 기다림
    ↓
서버 응답
    ↓
결과 처리
```

---

## 19. Future

Future는 나중에 받을 값을 나타내는 타입이다.

API 통신에서는 서버에서 데이터가 돌아올 때까지 시간이 걸리기 때문에 Future를 자주 사용한다.

데이터의 타입에 따라서 다음과 같이 사용할 수 있다.

```text
Future<String>
Future<int>
Future<List<T>>
Future<Object>
```

---

## 20. 예외 처리

API 통신은 항상 성공하는 것이 아니다.

다음과 같은 문제가 발생할 수 있다.

* 인터넷 연결 문제
* 서버 오류
* 잘못된 URL
* 인증 오류
* 요청 시간 초과
* JSON 변환 오류

그래서 API 통신을 할 때 예외 처리를 해주는 것이 좋다.

Dart에서는 주로 다음을 사용한다.

```text
try
catch
finally
```

### try

실행할 코드를 넣는다.

### catch

오류가 발생했을 때 처리한다.

### finally

오류가 발생했는지와 상관없이 실행할 코드를 넣는다.

---

## 21. API 인증

API에 따라 아무나 데이터를 사용할 수 없도록 인증이 필요한 경우가 있다.

대표적인 인증 방법은 다음과 같다.

* API Key
* Token
* Bearer Token
* JWT
* OAuth

인증 정보는 보통 Header에 넣어서 서버에 전달한다.

---

## 22. API Key

API를 사용할 수 있는 권한을 확인하기 위해 사용하는 키이다.

API 서버에서 API Key를 확인하고 요청을 허용하거나 거부할 수 있다.

API Key는 다른 사람이 볼 수 있게 공개하면 안 된다.

특히 GitHub 같은 공개 저장소에 API Key를 그대로 올리면 안 된다.

---

## 23. Token

Token은 인증된 사용자인지 확인하기 위해 사용하는 값이다.

로그인 등의 과정을 거친 후 서버에서 Token을 발급하고 앱에서 API 요청을 할 때 Token을 함께 보내는 방식으로 사용할 수 있다.

---

## 24. Model

Model은 API에서 받은 데이터를 Dart 클래스 형태로 관리하기 위해 사용한다.

JSON 데이터를 그대로 사용하는 것보다 Model로 만들어서 사용하는 것이 데이터를 관리하기 편하다.

```text
JSON
 ↓
Model
 ↓
Flutter 화면
```

Model을 사용하면 데이터의 타입을 확인하기 쉽고 코드도 정리하기 편하다.

---

## 25. Service

Service는 API와 직접 통신하는 부분을 따로 관리하기 위해 사용한다.

화면 코드에 API 통신 코드를 전부 작성하는 것보다 Service로 분리하면 코드 관리가 편해진다.

```text
화면
 ↓
Service
 ↓
API
```

---

## 26. Repository

Repository는 데이터를 가져오는 과정을 관리하는 역할을 한다.

API에서 데이터를 가져올 수도 있고 나중에는 로컬 저장소에서 데이터를 가져올 수도 있다.

```text
화면
 ↓
Repository
 ↓
Service
 ↓
API
```

프로젝트에 따라서 Repository와 Service를 나누지 않고 하나로 사용하는 경우도 있다.

---

## 27. API 통신 전체 흐름

Flutter에서 API 통신은 보통 다음과 같은 순서로 진행된다.

```text
1. Flutter에서 API 요청
        ↓
2. HTTP 요청 전송
        ↓
3. 서버에서 요청 처리
        ↓
4. 필요한 경우 데이터베이스 사용
        ↓
5. 서버에서 결과 생성
        ↓
6. Response 전달
        ↓
7. 상태 코드 확인
        ↓
8. JSON 데이터 확인
        ↓
9. JSON Decode
        ↓
10. Model로 변환
        ↓
11. Flutter 화면에서 사용
```

---

# 28. API 통신에서 알아야 하는 것

```text
API
→ 프로그램끼리 데이터를 주고받기 위한 방법

Client
→ 서버에 요청을 보내는 프로그램

Server
→ 요청을 처리하고 결과를 보내는 프로그램

Request
→ 클라이언트가 서버에 보내는 요청

Response
→ 서버가 클라이언트에 보내는 응답

HTTP
→ 클라이언트와 서버가 통신하는 규칙

GET
→ 데이터 조회

POST
→ 데이터 생성 또는 전달

PUT
→ 데이터 전체 수정

PATCH
→ 데이터 일부 수정

DELETE
→ 데이터 삭제

Header
→ 요청이나 응답에 대한 추가 정보

Body
→ 실제 데이터가 들어가는 부분

Query Parameter
→ URL에 조건을 추가하는 방법

Path Parameter
→ URL 경로를 이용해 특정 데이터를 지정하는 방법

JSON
→ API에서 많이 사용하는 데이터 형식

Encode
→ Dart 데이터를 JSON으로 변환

Decode
→ JSON을 Dart 데이터로 변환

Status Code
→ 서버에서 요청 처리 결과를 알려주는 코드

Future
→ 나중에 결과가 나오는 작업

async
→ 비동기 함수

await
→ 비동기 작업의 결과를 기다림

Model
→ API 데이터를 Dart 객체로 관리

Service
→ API 통신을 담당하는 부분

Repository
→ 데이터 가져오기와 관리를 담당하는 부분
```

# 29. 전체 구조

Flutter에서 API를 사용할 때 전체적인 구조를 간단하게 보면 다음과 같다.

```text
Flutter UI
    ↓
Repository
    ↓
Service
    ↓
HTTP
    ↓
API Server
    ↓
Database
```

데이터가 다시 앱으로 돌아올 때는 반대 방향으로 전달된다.

```text
Database
    ↓
API Server
    ↓
JSON Response
    ↓
Service
    ↓
Repository
    ↓
Model
    ↓
Flutter UI
```