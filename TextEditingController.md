# TextEditingController

- Flutter에서 텍스트 필드를 제어하는 핵심 클래스
- 이 클래스를 사용하면 텍스트 필드의 텍스트를 편집하고 가져오고 상태를 변경하는 등 다양한 작업을 수행할 수 있다.
- 주로 로그인 화면을 구현할 때 자주 사용한다.

---

# 1. 주요 기능

## ① 텍스트 편집

- 텍스트 추가
- 텍스트 삭제
- 텍스트 변경
- 선택 영역 설정 및 조작
- 커서 위치 제어

## ② 값 가져오기

- 현재 입력된 텍스트 가져오기
- 변경 사항 감지 및 리스닝

## ③ 상태 변경

- 텍스트 필드 활성화 / 비활성화
- 텍스트 입력 방식 설정
  - 숫자
  - 이메일 등
- 오류 표시 및 제거

---

# 2. 사용법

## ① 생성

`TextEditingController()` 인스턴스를 생성한다.

```dart
final controller = TextEditingController();
```

## ② 연결

`TextField` 위젯의 `controller` 속성에 연결한다.

```dart
TextField(
  controller: controller,
)
```

## ③ 제어

### `text`

`text` 속성을 사용하여 텍스트를 설정하거나 가져온다.

```dart
controller.text
```

### `addListener()`

`addListener()` 메서드를 사용하여 값 변경을 감지한다.

```dart
controller.addListener(() {
  print(controller.text);
});
```

### `selection`

`selection` 속성을 사용하여 선택 영역을 설정한다.

```dart
controller.selection
```

### `clear()`

`clear()` 메서드를 사용하여 텍스트를 지운다.

```dart
controller.clear();
```

---

# 3. 활용 예시

- 사용자 입력 내용 검증
- 실시간 텍스트 필터링
- 자동 완성 기능 구현
- 텍스트 필드 값을 기반으로 다른 위젯 동작 제어

---

# 4. `dispose()`

텍스트 편집 컨트롤러를 생성할 때 위젯이 더 이상 필요하지 않으면 Flutter에게 컨트롤러를 지우라고 해야 한다.

이것을 방지하기 위해 `dispose()`를 사용한다.

---

# 5. TextEditingController에서 `dispose()`를 사용해야 하는 이유

## 메모리 누수 방지

`TextEditingController`는 메모리에서 관리되는 여러 리소스를 사용한다.

`dispose()`를 사용하지 않으면 이러한 리소스가 해제되지 않아 메모리 누수가 발생할 수 있다.

---

## ① 텍스트 리스너

- 텍스트 필드의 값이 변경될 때마다 호출되는 리스너를 추가할 수 있다.
- `dispose()`를 사용하지 않으면 필드가 더 이상 사용되지 않더라도 리스너가 계속 메모리에 유지될 수 있다.

---

## ② 스크롤 컨트롤러

- 텍스트 필드가 스크롤 가능한 경우 스크롤 컨트롤러도 메모리에 유지된다.
- `dispose()`를 사용하지 않으면 스크롤 컨트롤러가 해제되지 않아 메모리 누수가 발생할 수 있다.

---

## ③ 포커스 노드

- 텍스트 필드에 포커스가 있을 때 포커스 노드가 메모리에 유지된다.
- `dispose()`를 사용하지 않으면 포커스 노드가 해제되지 않아 메모리 누수가 발생할 수 있다.

---

## 정리

따라서 메모리 누수를 방지하기 위해서는 텍스트 필드가 더 이상 사용되지 않을 때 `dispose()`를 사용하여 리소스를 해제해야 한다.

---

# 6. 사용 방법

```dart
final controller = TextEditingController();

@override
void dispose() {
  controller.dispose();
  super.dispose();
}
```