# Android / iOS 배포 절차

## 배포 준비 체크리스트

- 모든 주요 기능 테스트 완료
- 앱 아이콘 및 스플래시 스크린 구현
- 다양한 화면 크기 및 해상도 테스트
- 접근성 지원 확인
- 개인정보 처리방침 준비
- 앱 스크린샷 및 설명 준비

---

# Android 앱 배포 절차

## 1. 배포용 키스토어 생성

- Android 앱을 서명하기 위한 키스토어 파일을 생성해야 한다.
- 앱 업데이트 시 동일한 키로 서명해야 하므로 키스토어를 안전하게 보관해야 한다.

---

## 2. 키스토어 설정

- `android/app/build.gradle` 파일에 키스토어 정보를 추가한다.
- 보안을 위해 키스토어 정보를 별도의 파일로 관리한다.

### 1) `android/key.properties` 파일 생성

    storePassword=<키스토어 비밀번호>
    keyPassword=<키 비밀번호>
    keyAlias=upload
    storeFile=<키스토어 파일 경로, 예: /Users/username/upload-keystore.jks>

### 2) `android/app/build.gradle` 파일 수정

- 파일 상단에 키스토어 정보를 불러오는 코드를 추가한다.

    def keystoreProperties = new Properties()
    def keystorePropertiesFile = rootProject.file('key.properties')
    if (keystorePropertiesFile.exists()) {
        keystoreProperties.load(new FileInputStream(keystorePropertiesFile))
    }

- `android` 설정 안에 `signingConfigs`를 추가한다.

    android {
        // 기존 코드 ...

        signingConfigs {
            release {
                keyAlias keystoreProperties['keyAlias']
                keyPassword keystoreProperties['keyPassword']
                storeFile keystoreProperties['storeFile'] ? file(keystoreProperties['storeFile']) : null
                storePassword keystoreProperties['storePassword']
            }
        }

        buildTypes {
            release {
                signingConfig signingConfigs.release
                // 기타 릴리즈 설정 ...
            }
        }
    }

---

## 3. 앱 버전 설정

- `pubspec.yaml` 파일에서 앱 버전을 설정한다.

    version: 1.0.0+1

- 형식

    version: <버전 이름>+<빌드 번호>

---

## 4. 앱 매니페스트 설정

- `android/app/src/main/AndroidManifest.xml` 파일에서 필요한 권한과 설정을 확인한다.

    <manifest ...>
        <!-- 필요한 권한 설정 -->
        <uses-permission android:name="android.permission.INTERNET" />

        <!-- 기타 필요한 권한들 -->

        <application
            android:label="앱 이름"
            android:icon="@mipmap/ic_launcher"
            ...>
            <!-- 앱 설정 -->
        </application>
    </manifest>

---

## 5. 앱 번들 / APK 생성

- Google Play에서는 Android App Bundle 형식을 권장한다.

### App Bundle 생성

    flutter build appbundle

### APK 생성

    flutter build apk --release

---

## 6. Google Play Console에 앱 등록

1. Google Play Console에 로그인한다.
2. `새 앱 만들기`를 선택한다.
3. 앱 정보를 입력한다.
4. 개인정보 처리방침 URL을 제공한다.

---

## 7. 앱 번들 업로드

1. `앱 릴리즈` → `프로덕션` 트랙을 선택한다.
2. `새 릴리즈 만들기`를 클릭한다.
3. 생성한 App Bundle 파일을 업로드한다.
4. 릴리즈 노트를 작성한다.
5. 내용을 검토한 후 출시한다.

---

## 8. 출시 및 검토

- Google Play 검토 프로세스는 보통 몇 시간에서 며칠까지 소요될 수 있다.
- 검토가 완료되면 앱이 출시된다.

---

# iOS 앱 배포 절차

## 1. Apple Developer Program 가입

- iOS 앱을 App Store에 배포하려면 Apple Developer Program에 가입해야 한다.
- 사용자 자료 기준으로 연간 `$99`의 비용이 기재되어 있다.

---

## 2. Xcode에서 인증서 및 프로비저닝 프로필 설정

1. Xcode에서 프로젝트를 연다.

       open ios/Runner.xcworkspace

2. `Signing & Capabilities` 탭에서 팀을 선택한다.
3. 자동 서명을 활성화한다.
4. Bundle ID를 설정한다.

---

## 3. 앱 버전 및 빌드 번호 설정

- `pubspec.yaml` 파일에서 버전을 설정한다.
- iOS 관련 버전은 Xcode의 `Runner` 프로젝트 설정이나 `ios/Runner/Info.plist` 파일에서도 확인 및 수정할 수 있다.

---

## 4. iOS 앱 설정

- `ios/Runner/Info.plist` 파일에서 필요한 설정을 확인한다.

    <key>CFBundleDisplayName</key>
    <string>앱 이름</string>

    <!-- 필요한 권한 설명 추가 -->
    <key>NSCameraUsageDescription</key>
    <string>카메라 사용 이유 설명</string>

---

## 5. 앱 아이콘 설정

- `ios/Runner/Assets.xcassets/AppIcon.appiconset`에 다양한 크기의 앱 아이콘을 추가한다.

---

## 6. 릴리즈 빌드 생성

    flutter build ios --release

---

## 7. Xcode에서 Archive 생성

1. Xcode에서 `Product` → `Destination` → `Any iOS Device`를 선택한다.
2. `Product` → `Archive`를 선택한다.
3. Archive가 완료되면 Xcode Organizer가 자동으로 열린다.

---

## 8. TestFlight를 통한 테스트

1. Xcode Organizer에서 Archive를 선택한다.
2. `Distribute App` → `App Store Connect` → `Upload`를 선택한다.
3. 앱 배포 옵션을 설정한다.
4. 업로드 완료 후 App Store Connect에서 TestFlight를 구성한다.
5. 내부 및 외부 테스터를 추가한다.

---

## 9. App Store Connect에서 앱 정보 설정

1. App Store Connect에 로그인한다.
2. `내 앱` → `+` → `새로운 앱`을 선택한다.
3. 다음 정보를 입력한다.

- 앱 이름
- 기본 언어
- Bundle ID
- SKU
- 사용자 액세스 설정

---

## 10. 앱 정보 등록

App Store 정보에서 다음 항목을 작성한다.

- 프로모션 텍스트
- 설명
- 키워드
- 지원 URL 및 마케팅 URL
- 스크린샷
- 앱 미리보기 영상
- 앱 아이콘
- 연령 등급
- 개인정보 처리방침 URL
- 가격 및 가용성

---

## 11. 앱 심사 제출

1. 앱 버전 섹션에서 제출할 빌드를 선택한다.
2. 필요한 수출 규정 준수 정보를 제공한다.
3. `심사를 위해 제출`을 클릭한다.

---

## 12. 앱 심사 및 출시

- Apple 앱 심사는 보통 1~3일 소요된다.
- 거부될 경우 이유가 제공되며 수정 후 재제출할 수 있다.
- 승인되면 출시 준비됨 상태가 되고 수동 또는 자동으로 출시할 수 있다.

---

# CI/CD를 활용한 자동화 배포

- 배포 과정을 자동화하기 위해 CI/CD 도구를 활용할 수 있다.
- 대표적인 도구
  - Codemagic
  - Fastlane
  - GitHub Actions