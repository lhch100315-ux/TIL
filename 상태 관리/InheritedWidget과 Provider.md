# InheritedWidget과 Provider

# 1. InheritedWidget

- Flutter 프레임워크에 내장된 위젯
- 위젯 트리의 하위 항목들에게 데이터를 효율적으로 전달할 수 있게 한다.
- 위젯 트리 깊숙한 곳에 있는 위젯이 상위 위젯의 데이터를 접근해야 할 때 유용하다.

---

## 2. InheritedWidget 작동 원리

### ① 데이터 저장
- `InheritedWidget`은 공유하려는 데이터를 저장한다.

### ② 위젯 트리 전파
- 저장된 데이터는 위젯 트리 아래로 자동으로 전파된다.

### ③ BuildContext 접근
- 하위 위젯들은 `BuildContext`를 통해 상위의 `InheritedWidget`에 접근할 수 있다.

### ④ 변경 알림
- `InheritedWidget`이 업데이트되면 이에 의존하는 위젯들이 자동으로 재빌드된다.

---

## 3. InheritedWidget의 한계

### ① 상태 변경 메커니즘 없음
- 데이터를 공유할 수 있지만 데이터를 변경할 수 있는 메커니즘은 제공하지 않는다.

### ② 복잡한 구현
- 직접 구현하려면 작성해야 하는 코드가 많다.

### ③ 변경 관리 번거로움
- 상태 변경 시 새로운 `InheritedWidget`을 생성하고 위젯 트리를 다시 빌드해야 한다.

---

# 4. Provider 패키지

- `InheritedWidget`을 기반으로 구축된 상태 관리 패키지
- 코드를 단순화하고 상태 관리를 더 쉽게 만들어준다.

---

## 5. Provider의 주요 특징

### ① 편리한 API
- `InheritedWidget`을 직접 구현하는 것보다 간단한 API를 제공한다.

### ② 여러 Provider 유형
- 다양한 사용 사례에 맞는 여러 종류의 Provider를 제공한다.

### ③ 상태 변경 통합
- 상태 변경 메커니즘이 내장되어 있다.

### ④ 의존성 주입
- 테스트와 재사용을 위한 의존성 주입 패턴을 지원한다.

---

# 6. Provider의 종류

### ① Provider
- 가장 기본적인 Provider
- 변경되지 않는 데이터를 제공한다.

### ② ChangeNotifierProvider
- `ChangeNotifier`를 사용하여 변경 가능한 상태를 관리한다.

### ③ FutureProvider
- `Future`로부터 값을 제공한다.

### ④ StreamProvider
- `Stream`으로부터 값을 제공한다.

### ⑤ ProxyProvider
- 다른 Provider의 값에 의존하는 값을 제공한다.

### ⑥ MultiProvider
- 여러 Provider를 한 번에 제공한다.

---

# 7. Provider 사용 시 Best Practices

### ① 모델 캡슐화
- 상태 모델 내부 구현을 캡슐화하여 불변성을 유지한다.

### ② UI에서 비즈니스 로직 분리
- 비즈니스 로직을 모델 클래스에 위치시킨다.

### ③ 위젯 재빌드 최적화
- 필요한 부분만 재빌드되도록 설계한다.

### ④ 범위에 맞는 Provider 위치 선택
- Provider의 위치를 적절하게 선택하여 범위를 제한한다.

### ⑤ Provider 조합 패턴
- 여러 상태가 함께 작동해야 할 때 `ProxyProvider`를 활용한다.