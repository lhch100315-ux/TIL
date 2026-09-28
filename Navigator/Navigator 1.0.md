# Navigator 1.0

- 명령어 스타일로 화면 전환을 구현한다.
- 스택 기반의 네비게이션을 제공한다.

---

# 1. Navigator의 개념

- 앱의 화면들을 스택 형태로 관리하는 위젯이다.
- 대부분의 앱에서는 사용자가 새 화면으로 이동하면 이전 화면 위에 새 화면이 쌓인다.
- 뒤로 가기를 하면 가장 위에 있는 화면이 제거되는 구조이다.

---

# 2. Navigator 위젯의 주요 메서드

### `push`
- 새로운 화면을 스택의 맨 위에 추가한다.

### `pop`
- 스택의 맨 위에 있는 화면을 제거한다.

### `pushReplacement`
- 현재 화면을 새로운 화면으로 교체한다.

### `pushNamedAndRemoveUntil`
- 이름으로 새로운 화면을 추가한다.
- 특정 조건이 만족될 때까지 이전 화면들을 제거한다.

---

# 3. 기본 사용법

## ① 직접 라우팅 (익명 라우팅)

- 가장 기본적인 화면 전환 방법이다.
- `Navigator.push()`와 `Navigator.pop()`을 사용한다.

---

## ② 명명된 라우팅

- 앱 시작 시 라우트 맵을 정의하고 이름으로 화면을 전환하는 방식이다.
- 화면 전환을 보다 체계적으로 관리할 수 있다.

---

# 4. 라우트 전환 시 데이터 전달

## ① 생성자를 통한 데이터 전달

- 화면을 전환할 때 생성자를 통해 데이터를 전달할 수 있다.

### 데이터 전달하며 화면 전환

    // 데이터 전달하며 화면 전환
    ElevatedButton(
      onPressed: () {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => DetailScreen(item: item),
          ),
        );
      },
      child: Text('상세 화면으로 이동'),
    );

### 데이터를 받는 화면

    class DetailScreen extends StatelessWidget {
      final Item item;

      DetailScreen({required this.item});

      @override
      Widget build(BuildContext context) {
        return Scaffold(
          appBar: AppBar(title: Text(item.title)),
          body: Center(
            child: Text(item.description),
          ),
        );
      }
    }

---

## ② 명명된 라우트에 인수 전달

- `pushNamed()`를 사용하여 화면을 전환하면서 인수를 전달할 수 있다.

### 인수를 전달하며 명명된 라우트로 이동

    Navigator.pushNamed(
      context,
      '/detail',
      arguments: {'id': 123, 'title': '상품 제목'},
    );

### 인수를 받는 화면

    class DetailScreen extends StatelessWidget {
      @override
      Widget build(BuildContext context) {
        final args =
            ModalRoute.of(context)!.settings.arguments
                as Map<String, dynamic>;

        final id = args['id'];
        final title = args['title'];

        return Scaffold(
          appBar: AppBar(title: Text(title)),
          body: Center(
            child: Text('ID: $id'),
          ),
        );
      }
    }

---

## ③ 결과를 반환하기

- 화면 전환 후 이전 화면으로 결과 값을 반환받을 수도 있다.
- `Navigator.push()`의 결과를 `await`으로 받을 수 있다.
- 결과를 반환하는 화면에서는 `Navigator.pop()`에 값을 전달한다.

### 결과를 받기 위해 비동기로 화면 전환

    ElevatedButton(
      onPressed: () async {
        final result = await Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => SelectionScreen(),
          ),
        );

        // 결과 처리
        if (result != null) {
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(
              content: Text('선택된 항목: $result'),
            ),
          );
        }
      },
      child: Text('항목 선택하기'),
    );

### 결과를 반환하는 화면

    class SelectionScreen extends StatelessWidget {
      @override
      Widget build(BuildContext context) {
        return Scaffold(
          appBar: AppBar(title: Text('항목 선택')),
          body: ListView(
            children: [
              ListTile(
                title: Text('항목 1'),
                onTap: () {
                  Navigator.pop(context, '항목 1');
                },
              ),
              ListTile(
                title: Text('항목 2'),
                onTap: () {
                  Navigator.pop(context, '항목 2');
                },
              ),
            ],
          ),
        );
      }
    }

---

# 5. 화면 전환 애니메이션 커스터마이징

## ① 내장 애니메이션 사용

- Flutter는 여러 종류의 화면 전환 애니메이션을 제공한다.

### 오른쪽에서 왼쪽으로 슬라이드

    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => SecondScreen(),
      ),
    );

### 아래에서 위로 슬라이드

    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => SecondScreen(),
        fullscreenDialog: true,
      ),
    );

### 페이드 인/아웃 효과

    Navigator.push(
      context,
      PageRouteBuilder(
        pageBuilder: (
          context,
          animation,
          secondaryAnimation,
        ) => SecondScreen(),
        transitionsBuilder: (
          context,
          animation,
          secondaryAnimation,
          child,
        ) {
          return FadeTransition(
            opacity: animation,
            child: child,
          );
        },
      ),
    );

---

## ② 커스텀 애니메이션 생성

- 원하는 전환 효과가 없다면 직접 만들 수 있다.

### 스케일 애니메이션

- 화면이 확대되거나 축소되는 효과를 구현한다.

    Navigator.push(
      context,
      PageRouteBuilder(
        pageBuilder: (
          context,
          animation,
          secondaryAnimation,
        ) => SecondScreen(),
        transitionsBuilder: (
          context,
          animation,
          secondaryAnimation,
          child,
        ) {
          return ScaleTransition(
            scale: animation,
            child: child,
          );
        },
        transitionDuration: Duration(milliseconds: 500),
      ),
    );

### 회전 애니메이션

    Navigator.push(
      context,
      PageRouteBuilder(
        pageBuilder: (
          context,
          animation,
          secondaryAnimation,
        ) => SecondScreen(),
        transitionsBuilder: (
          context,
          animation,
          secondaryAnimation,
          child,
        ) {
          return RotationTransition(
            turns: Tween<double>(
              begin: 0.0,
              end: 1.0,
            ).animate(animation),
            child: child,
          );
        },
        transitionDuration: Duration(seconds: 1),
      ),
    );

### 여러 애니메이션 조합

- 여러 전환 효과를 함께 사용할 수 있다.
- 페이드 효과와 슬라이드 효과를 조합할 수 있다.

    Navigator.push(
      context,
      PageRouteBuilder(
        pageBuilder: (
          context,
          animation,
          secondaryAnimation,
        ) => SecondScreen(),
        transitionsBuilder: (
          context,
          animation,
          secondaryAnimation,
          child,
        ) {
          return FadeTransition(
            opacity: animation,
            child: SlideTransition(
              position: Tween<Offset>(
                begin: const Offset(1.0, 0.0),
                end: Offset.zero,
              ).animate(animation),
              child: child,
            ),
          );
        },
        transitionDuration: Duration(milliseconds: 500),
      ),
    );

---

# 6. Hero 애니메이션

- 두 화면 간에 동일한 위젯이 있을 때 해당 위젯이 한 화면에서 다른 화면으로 자연스럽게 이동하는 효과를 구현할 수 있다.
- 두 `Hero`의 `tag`는 동일해야 한다.
- `tag`는 고유해야 한다.

### 첫 번째 화면

    Hero(
      tag: 'imageHero',
      child: Image.network(
        'https://example.com/image.jpg',
      ),
    );

### 두 번째 화면

    Hero(
      tag: 'imageHero',
      child: Image.network(
        'https://example.com/image.jpg',
      ),
    );

---

# 7. 중첩 네비게이션

- 앱의 일부 영역에서만 별도의 네비게이션 스택을 관리하고 싶을 때 사용한다.
- 하나의 화면 안에 별도의 `Navigator`를 둘 수 있다.

### 중첩 네비게이터 예시

    class MyHomePage extends StatelessWidget {
      @override
      Widget build(BuildContext context) {
        return Scaffold(
          appBar: AppBar(
            title: Text('중첩 네비게이션'),
          ),
          body: Row(
            children: [
              // 사이드바
              Container(
                width: 200,
                color: Colors.grey[200],
                child: ListView(
                  children: [
                    ListTile(
                      title: Text('항목 1'),
                      onTap: () {
                        // 중첩 네비게이터 접근
                        Navigator.of(
                          context,
                          rootNavigator: false,
                        ).pushReplacementNamed('/item1');
                      },
                    ),
                    ListTile(
                      title: Text('항목 2'),
                      onTap: () {
                        Navigator.of(
                          context,
                          rootNavigator: false,
                        ).pushReplacementNamed('/item2');
                      },
                    ),
                  ],
                ),
              ),

              // 메인 콘텐츠 영역
              Expanded(
                child: Navigator(
                  initialRoute: '/item1',
                  onGenerateRoute: (settings) {
                    Widget page;

                    switch (settings.name) {
                      case '/item1':
                        page = Item1Screen();
                        break;

                      case '/item2':
                        page = Item2Screen();
                        break;

                      default:
                        page = Item1Screen();
                    }

                    return MaterialPageRoute(
                      builder: (_) => page,
                    );
                  },
                ),
              ),
            ],
          ),
        );
      }
    }

---

# 8. Navigator 1.0의 한계

## ① 딥 링크 처리의 어려움
- 앱 외부에서 특정 화면으로 직접 접근하는 딥 링크를 처리하기 어렵다.

## ② 웹 통합의 제한
- 웹 기반 애플리케이션에서 URL과 `Navigator`의 상태를 동기화하는 데 어려움이 있다.

## ③ 상태 관리의 복잡성
- 여러 계층의 네비게이션 스택이 있는 경우 상태 관리가 복잡해진다.

## ④ 선언적 스타일 부재
- Flutter의 대부분은 선언적 스타일이지만 `Navigator 1.0`은 명령형 API를 사용한다.