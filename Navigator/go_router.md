# go_router

- Flutter 팀이 공식적으로 지원하는 라우팅 패키지이다.
- `Navigator 2.0`의 기능을 더 쉽게 사용할 수 있게 해준다.
- 복잡한 라우팅 시나리오를 처리하면서도 간결한 API를 제공하여 개발자 경험을 향상시킨다.

---

# 1. go_router의 목표

### ① 간결한 API
- `Navigator 2.0`의 복잡성을 줄이고 더 직관적인 API를 제공한다.

### ② 선언적 라우팅
- 앱의 모든 라우트를 한 곳에서 선언적으로 정의한다.

### ③ 딥 링크 지원
- 모바일 앱의 딥 링크와 웹 URL을 지원한다.

### ④ 중첩 라우팅
- 중첩된 네비게이션 시나리오를 지원한다.

### ⑤ 페이지 전환 애니메이션
- 커스텀 페이지 전환 효과를 지원한다.

---

# 2. go_router 설치하기

`pubspec.yaml` 파일에 `go_router` 패키지를 추가한다.

    dependencies:
      flutter:
        sdk: flutter
      go_router: ^15.1.2

패키지를 추가한 후 다음 명령어를 실행한다.

    flutter pub get

---

# 3. go_router의 기본 개념

## ① GoRouter 설정

- 앱의 라우팅을 설정하는 `GoRouter` 인스턴스를 생성한다.
- `initialLocation`을 통해 앱이 시작될 때 표시할 경로를 설정할 수 있다.
- `routes`에 앱에서 사용할 라우트를 정의한다.

    final GoRouter _router = GoRouter(
      initialLocation: '/',
      routes: [
        GoRoute(
          path: '/',
          builder: (context, state) => HomeScreen(),
        ),
        GoRoute(
          path: '/details/:id',
          builder: (context, state) => DetailsScreen(
            id: state.pathParameters['id']!,
          ),
        ),
      ],
    );

### 앱에 라우터 적용

- `MaterialApp.router`의 `routerConfig`에 `GoRouter`를 연결한다.

    class MyApp extends StatelessWidget {
      @override
      Widget build(BuildContext context) {
        return MaterialApp.router(
          routerConfig: _router,
          title: 'GoRouter Example',
        );
      }
    }

---

# 4. 경로 정의와 매개변수

`go_router`에서는 URL 경로에 매개변수를 포함할 수 있다.

## ① 경로 매개변수

- `/user/:id`와 같이 콜론으로 시작하는 세그먼트이다.
- URL 경로에 포함된 값을 전달할 때 사용한다.

## ② 쿼리 매개변수

- `/search?query=flutter`와 같이 URL에 추가되는 키-값 쌍이다.

### 경로 및 쿼리 매개변수 사용

    GoRoute(
      path: '/user/:userId/post/:postId',
      builder: (context, state) {
        // 경로 매개변수 추출
        final userId = state.pathParameters['userId']!;
        final postId = state.pathParameters['postId']!;

        // 쿼리 매개변수 추출
        final filter = state.queryParameters['filter'];

        return PostScreen(
          userId: userId,
          postId: postId,
          filter: filter,
        );
      },
    );

---

# 5. 화면 이동

`go_router`는 다양한 방법으로 화면 간 이동을 지원한다.

### `context.go()`

- 명시적인 경로로 이동한다.

    context.go('/details/123');

### `context.push()`

- 현재 스택에 새로운 화면을 추가한다.

    context.push('/details/123');

### `context.pushReplacement()`

- 현재 화면을 새로운 화면으로 대체한다.

    context.pushReplacement('/details/123');

### `context.pushAndRemoveUntil()`

- 해당 경로까지 모든 화면을 제거한 후 새로운 화면을 추가한다.

    context.pushAndRemoveUntil(
      '/details/123',
      predicate,
    );

### `context.pop()`

- 이전 화면으로 돌아간다.

    context.pop();

---

# 6. go_router와 다른 라우팅 방식의 차이

## ① go_router와 Navigator 2.0 직접 사용

### go_router
- 간결한 API
- 적은 보일러플레이트 코드
- 직관적인 사용법

### Navigator 2.0
- 더 많은 유연성
- 더 많은 보일러플레이트 코드 필요

---

## ② go_router와 auto_route

### go_router
- 공식 지원
- 간단한 설정
- 코드 생성 불필요

### auto_route
- 코드 생성 기반
- 타입 안정성
- 더 많은 설정 필요