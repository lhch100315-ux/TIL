# Riverpod

- `Provider`의 애너그램이다.
- `Provider`의 제한사항을 해결하기 위해 처음부터 다시 설계된 상태 관리 라이브러리이다.
- `Provider`가 `InheritedWidget`을 기반으로 하는 반면, `Riverpod`은 위젯 트리와 완전히 독립적으로 작동한다.

---

# 1. Riverpod가 Provider와 비교하여 갖는 주요 장점

### ① 컴파일 타임 안전성
- 존재하지 않는 Provider를 참조하면 컴파일 오류가 발생한다.

### ② 위젯 트리 독립성
- `BuildContext` 없이도 Provider에 접근할 수 있다.

### ③ Provider 결합
- 여러 Provider를 쉽게 결합할 수 있다.

### ④ 자동 캐싱 및 중복 제거
- 동일한 Provider에 대한 요청이 중복되지 않는다.

### ⑤ 강력한 비동기 지원
- `Future`와 `Stream` 처리를 위한 기본 지원을 제공한다.

### ⑥ 테스트 용이성
- Provider의 값을 쉽게 오버라이드하여 테스트할 수 있다.

---

# 2. Riverpod의 주요 개념

## Provider와 ref

### ① Provider
- 상태를 정의하고 외부에서 노출하는 객체이다.

### ② ref
- Provider에 접근하고 상호 작용하는 객체이다.