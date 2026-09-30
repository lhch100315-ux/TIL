# Flutter 빌드 모드

Flutter에서는 앱을 실행하거나 빌드할 때 목적에 따라 `Debug`, `Profile`, `Release` 모드를 사용할 수 있다.

---

## 1. Debug 모드

- Debug 모드는 개발 과정에서 주로 사용하는 모드이다.

### 특징

- **Hot Reload / Restart**
  - 코드 변경 사항을 빠르게 확인할 수 있다.
- **디버깅 도구**
  - 콘솔 로그, 디버거 연결, 인스펙터 등 개발 도구를 사용할 수 있다.
- **확인용 배너**
  - 앱 우측 상단에 `DEBUG` 배너가 표시된다.
- **비최적화 빌드**
  - 성능이 최적화되지 않고 디버깅 정보가 포함되어 있다.

### 실행 방법

    # 명시적으로 Debug 모드로 실행
    flutter run --debug

    # 기본값이므로 일반적으로 다음과 같이 실행
    flutter run

### 사용 시나리오

- 앱 개발 및 기능 테스트
- 코드 디버깅
- UI 구현 및 확인

---

## 2. Profile 모드

- Profile 모드는 성능 분석과 프로파일링을 위한 모드이다.

### 특징

- **성능 트래킹**
  - Timeline, DevTools 등을 통한 성능 측정이 가능하다.
- **일부 디버깅 비활성화**
  - Hot Reload 및 일부 디버깅 기능은 비활성화된다.
- **실제 성능과 유사**
  - Release 모드와 유사한 성능 특성을 가지지만 프로파일링 도구를 사용할 수 있다.
- **Flutter Inspector**
  - UI 레이아웃 및 렌더링 분석이 가능하다.

### 실행 방법

    flutter run --profile

### 사용 시나리오

- 앱 성능 분석
- 병목 현상 파악
- 메모리 사용량 및 프레임 드롭 확인
- 실제 기기에서의 사용자 경험 검증

---

## 3. Release 모드

- Release 모드는 최종 사용자에게 배포하기 위한 최적화된 빌드 모드이다.

### 특징

- **최적화된 성능**
  - 모든 성능 최적화 기능이 활성화된다.
- **코드 최소화**
  - 사용하지 않는 코드 제거 및 최소화
- **디버깅 기능 비활성화**
  - 모든 디버깅 도구와 코드가 제거된다.
- **R8 / ProGuard (Android)**
  - 코드 축소, 난독화 및 최적화

### 실행 방법

    flutter run --release

### 빌드 방법

    # Android APK 빌드
    flutter build apk --release

    # Android App Bundle 빌드
    flutter build appbundle --release

    # iOS 빌드
    flutter build ios --release

### 사용 시나리오

- 앱 스토어 제출
- 사용자 배포
- 최종 성능 테스트
- 배포 전 검증

---

# 모드 전환 시 주의사항

## Debug에서 Release로 전환 시 확인 사항

### 1. assert문

- `Debug` 모드에서만 동작한다.
- `Release` 모드에서는 무시된다.

### 2. 환경 변수

- `kDebugMode`
- `kProfileMode`
- `kReleaseMode`

- 위 플래그를 사용한 조건부 코드가 있는지 확인한다.

### 3. 로그 출력

- 불필요한 `print()`문을 제거할지 검토한다.

### 4. 플랫폼 채널

- 네이티브 코드와의 통신이 제대로 작동하는지 확인한다.

### 5. 타이밍 차이

- Debug 모드보다 Release 모드에서 실행 속도가 빠를 수 있음을 고려한다.

---

# 빌드 모드 활용 팁

## 다양한 모드 테스트

- 개발 과정에서 정기적으로 Profile 및 Release 모드로 앱을 테스트하여 실제 사용자 경험을 확인하는 것이 좋다.

## Flavor와 함께 사용

- 빌드 모드는 Flavor와 함께 사용하여 개발, 스테이징, 프로덕션 환경을 구분할 수 있다.