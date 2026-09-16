# Flutter 레이아웃 위젯

## 1. Flutter의 레이아웃 시스템

Flutter의 레이아웃은 **위젯 트리**를 통해 UI를 구성한다.

기본적인 동작 방식은 다음과 같다.

1. 부모 위젯이 자식 위젯에게 **제약 조건(Constraints)**을 전달
2. 자식 위젯이 제약 조건 안에서 자신의 크기를 결정
3. 부모 위젯이 자식 위젯의 크기를 바탕으로 위치를 결정

### 핵심

> 부모가 제약을 전달하고 → 자식이 크기를 결정하고 → 부모가 위치를 결정한다.

---

# 2. 기본 레이아웃 위젯

## Container

`Container`는 크기, 여백, 정렬, 배경색, 테두리 등을 설정할 수 있는 대표적인 레이아웃 위젯이다.

```dart
Container(
  width: 200,
  height: 100,
  margin: EdgeInsets.all(10),
  padding: EdgeInsets.symmetric(
    horizontal: 16,
    vertical: 8,
  ),
  alignment: Alignment.center,
  child: Text('Container'),
)
```

### 주요 속성

| 속성           | 설명                 |
| ------------ | ------------------ |
| `width`      | 너비                 |
| `height`     | 높이                 |
| `margin`     | 바깥쪽 여백             |
| `padding`    | 안쪽 여백              |
| `decoration` | 배경, 테두리, 그림자 등 스타일 |
| `alignment`  | 자식 위젯 정렬           |
| `child`      | 자식 위젯              |

### Container 동작

* 자식이 없으면 최대한 크게 확장
* 자식이 있으면 자식의 크기에 맞춤
* `width`, `height`를 지정하면 지정된 크기를 사용

---

## SizedBox

`SizedBox`는 **고정된 크기의 공간**을 만들거나 위젯 사이에 **간격**을 추가할 때 사용한다.

### 고정 크기

```dart
SizedBox(
  width: 100,
  height: 50,
  child: Container(),
)
```

### 간격

```dart
Column(
  children: [
    Text('첫 번째'),
    SizedBox(height: 16),
    Text('두 번째'),
  ],
)
```

### 전체 영역 확장

```dart
SizedBox.expand(
  child: Container(),
)
```

---

## Padding

`Padding`은 자식 위젯의 주변에 **안쪽 여백**을 추가한다.

```dart
Padding(
  padding: EdgeInsets.all(16.0),
  child: Text('패딩이 있는 텍스트'),
)
```

### 자주 사용하는 값

```dart
EdgeInsets.all(16)
```

→ 모든 방향에 16

```dart
EdgeInsets.symmetric(
  horizontal: 16,
  vertical: 8,
)
```

→ 가로 16, 세로 8

---

# 3. 단일 자식 레이아웃 위젯

## Center

자식 위젯을 부모 영역의 **가운데**에 배치한다.

```dart
Center(
  child: Text('중앙'),
)
```

---

## Align

자식 위젯을 원하는 위치에 정렬한다.

```dart
Align(
  alignment: Alignment.topRight,
  child: Text('오른쪽 위'),
)
```

### 주요 Alignment

| 값                        | 위치      |
| ------------------------ | ------- |
| `Alignment.topLeft`      | 왼쪽 위    |
| `Alignment.topCenter`    | 가운데 위   |
| `Alignment.topRight`     | 오른쪽 위   |
| `Alignment.centerLeft`   | 왼쪽 가운데  |
| `Alignment.center`       | 정중앙     |
| `Alignment.centerRight`  | 오른쪽 가운데 |
| `Alignment.bottomLeft`   | 왼쪽 아래   |
| `Alignment.bottomCenter` | 가운데 아래  |
| `Alignment.bottomRight`  | 오른쪽 아래  |

### 직접 위치 지정

```dart
Align(
  alignment: Alignment(0.5, -0.5),
  child: Text('텍스트'),
)
```

`Alignment`의 x, y 값은 **-1.0 ~ 1.0** 범위에서 지정한다.

---

## FractionallySizedBox

부모 크기를 기준으로 **비율**을 지정한다.

```dart
FractionallySizedBox(
  widthFactor: 0.7,
  heightFactor: 0.5,
  child: Container(),
)
```

* `widthFactor: 0.7` → 부모 너비의 70%
* `heightFactor: 0.5` → 부모 높이의 50%

---

## AspectRatio

자식 위젯의 **가로세로 비율**을 지정한다.

```dart
AspectRatio(
  aspectRatio: 16 / 9,
  child: Container(),
)
```

`16 / 9` → 가로 : 세로 = **16 : 9**

---

# 4. 다중 자식 레이아웃 위젯

## Row

자식 위젯을 **가로 방향**으로 배치한다.

```dart
Row(
  children: [
    Text('1'),
    Text('2'),
    Text('3'),
  ],
)
```

## Column

자식 위젯을 **세로 방향**으로 배치한다.

```dart
Column(
  children: [
    Text('1'),
    Text('2'),
    Text('3'),
  ],
)
```

---

## Row와 Column의 주요 속성

### mainAxisAlignment

**주축 방향**으로 자식 위젯을 정렬한다.

* Row → 가로 방향
* Column → 세로 방향

```dart
Row(
  mainAxisAlignment: MainAxisAlignment.center,
  children: [],
)
```

### crossAxisAlignment

**교차축 방향**으로 자식 위젯을 정렬한다.

```dart
Column(
  crossAxisAlignment: CrossAxisAlignment.start,
  children: [],
)
```

### mainAxisSize

주축 방향으로 차지하는 공간을 결정한다.

기본값:

```dart
MainAxisSize.max
```

---

# 5. Expanded와 Flexible

## Expanded

Row 또는 Column에서 남은 공간을 **최대한 차지**하도록 한다.

```dart
Row(
  children: [
    Expanded(
      flex: 1,
      child: Container(),
    ),
    Expanded(
      flex: 2,
      child: Container(),
    ),
  ],
)
```

`flex` 비율에 따라 공간을 나눈다.

위 예제에서는

* 첫 번째 → 1
* 두 번째 → 2

따라서 **1 : 2 비율**로 공간을 차지한다.

### 핵심

```text
Expanded = 사용 가능한 공간을 최대한 차지
```

`Expanded`는 `FlexFit.tight`와 동일하다.

---

## Flexible

자식 위젯이 필요한 만큼 공간을 사용하도록 한다.

```dart
Flexible(
  flex: 1,
  fit: FlexFit.loose,
  child: Container(
    width: 50,
  ),
)
```

### Expanded vs Flexible

| 위젯         | 특징                    |
| ---------- | --------------------- |
| `Expanded` | 사용 가능한 공간을 최대한 차지     |
| `Flexible` | 자식이 원하는 크기를 우선 사용     |
| `Expanded` | `FlexFit.tight`       |
| `Flexible` | 기본적으로 `FlexFit.loose` |

---

# 6. Spacer

`Row`나 `Column`에서 **빈 공간**을 만들 때 사용한다.

```dart
Row(
  children: [
    Text('좌측'),
    Spacer(),
    Text('우측'),
  ],
)
```

`Spacer()`는 남아 있는 공간을 차지한다.

### flex 사용

```dart
Row(
  children: [
    Text('좌측'),
    Spacer(flex: 1),
    Text('중앙'),
    Spacer(flex: 2),
    Text('우측'),
  ],
)
```

`flex` 값을 이용하면 빈 공간의 비율을 조절할 수 있다.

---

# 7. Wrap

공간이 부족하면 자식 위젯을 **다음 줄 또는 다음 열로 자동으로 넘긴다.**

```dart
Wrap(
  spacing: 8.0,
  runSpacing: 12.0,
  children: [
    Chip(label: Text('Flutter')),
    Chip(label: Text('Dart')),
    Chip(label: Text('Firebase')),
    Chip(label: Text('Android')),
    Chip(label: Text('iOS')),
  ],
)
```

### 주요 속성

| 속성           | 설명        |
| ------------ | --------- |
| `spacing`    | 주축 방향 간격  |
| `runSpacing` | 교차축 방향 간격 |
| `alignment`  | 정렬 방식     |
| `children`   | 자식 위젯     |

---

# 8. Stack

여러 위젯을 **겹쳐서 배치**할 때 사용한다.

```dart
Stack(
  children: [
    Container(
      width: 300,
      height: 200,
    ),
    Container(
      width: 200,
      height: 100,
    ),
  ],
)
```

`Stack`에서는 먼저 작성한 위젯이 뒤쪽에 있고, 나중에 작성한 위젯이 위쪽에 배치된다.

### alignment

```dart
Stack(
  alignment: Alignment.center,
  children: [],
)
```

`Positioned`가 없는 자식의 기본 정렬 위치를 지정한다.

---

# 9. Positioned

`Stack` 내부에서 자식 위젯의 **정확한 위치**를 지정한다.

```dart
Stack(
  children: [
    Positioned(
      top: 20,
      left: 20,
      width: 100,
      height: 100,
      child: Container(),
    ),
  ],
)
```

### 주요 속성

| 속성       | 설명         |
| -------- | ---------- |
| `top`    | 위에서부터 거리   |
| `bottom` | 아래에서부터 거리  |
| `left`   | 왼쪽에서부터 거리  |
| `right`  | 오른쪽에서부터 거리 |
| `width`  | 너비         |
| `height` | 높이         |

### Positioned.fill

전체 영역을 채우도록 한다.

```dart
Positioned.fill(
  child: Container(),
)
```

---

# 10. 스크롤 위젯

## SingleChildScrollView

하나의 자식 위젯을 **스크롤 가능하게** 만든다.

```dart
SingleChildScrollView(
  scrollDirection: Axis.vertical,
  child: Column(
    children: [
      Text('항목 1'),
      Text('항목 2'),
      Text('항목 3'),
    ],
  ),
)
```

### 주요 속성

```dart
scrollDirection: Axis.vertical
```

→ 세로 스크롤

```dart
scrollDirection: Axis.horizontal
```

→ 가로 스크롤

---

# 11. ListView

여러 항목을 **스크롤 가능한 목록**으로 표시한다.

## 기본 ListView

```dart
ListView(
  children: [
    ListTile(title: Text('항목 1')),
    ListTile(title: Text('항목 2')),
    ListTile(title: Text('항목 3')),
  ],
)
```

## ListView.builder

많은 항목을 효율적으로 표시할 때 사용한다.

```dart
ListView.builder(
  itemCount: 100,
  itemBuilder: (context, index) {
    return ListTile(
      title: Text('항목 $index'),
    );
  },
)
```

### 핵심

```text
ListView.builder
= 필요한 항목을 효율적으로 생성
```

## ListView.separated

항목 사이에 구분선을 넣을 수 있다.

```dart
ListView.separated(
  itemCount: 20,
  separatorBuilder: (context, index) {
    return Divider();
  },
  itemBuilder: (context, index) {
    return ListTile(
      title: Text('항목 $index'),
    );
  },
)
```

---

# 12. GridView

여러 항목을 **격자 형태**로 표시한다.

## GridView.count

```dart
GridView.count(
  crossAxisCount: 3,
  mainAxisSpacing: 4.0,
  crossAxisSpacing: 4.0,
  children: [],
)
```

### 주요 속성

| 속성                 | 설명        |
| ------------------ | --------- |
| `crossAxisCount`   | 열의 개수     |
| `mainAxisSpacing`  | 주축 방향 간격  |
| `crossAxisSpacing` | 교차축 방향 간격 |
| `padding`          | 바깥 여백     |

## GridView.builder

많은 항목을 효율적으로 생성할 때 사용한다.

```dart
GridView.builder(
  gridDelegate:
      SliverGridDelegateWithFixedCrossAxisCount(
    crossAxisCount: 2,
    childAspectRatio: 1.5,
    mainAxisSpacing: 10,
    crossAxisSpacing: 10,
  ),
  itemCount: 100,
  itemBuilder: (context, index) {
    return Container(
      child: Center(
        child: Text('항목 $index'),
      ),
    );
  },
)
```

---

# 13. 레이아웃 최적화 위젯

## ConstrainedBox

자식 위젯에 **추가적인 크기 제약 조건**을 적용한다.

```dart
ConstrainedBox(
  constraints: BoxConstraints(
    minWidth: 100,
    maxWidth: 200,
    minHeight: 50,
    maxHeight: 100,
  ),
  child: Container(),
)
```

### BoxConstraints

| 속성          | 의미    |
| ----------- | ----- |
| `minWidth`  | 최소 너비 |
| `maxWidth`  | 최대 너비 |
| `minHeight` | 최소 높이 |
| `maxHeight` | 최대 높이 |

---

## IntrinsicWidth / IntrinsicHeight

자식 위젯의 내부 크기에 맞춰 너비 또는 높이를 조정한다.

```dart
IntrinsicWidth(
  child: Column(
    children: [
      Container(width: 100, height: 50),
      Container(width: 150, height: 50),
      Container(width: 75, height: 50),
    ],
  ),
)
```

`IntrinsicWidth`는 자식들의 필요한 너비를 기준으로 크기를 결정한다.

---

# 14. LayoutBuilder

부모 위젯이 제공하는 **제약 조건에 따라 다른 레이아웃**을 구성할 때 사용한다.

```dart
LayoutBuilder(
  builder: (context, constraints) {
    if (constraints.maxWidth > 600) {
      return Row(
        children: [
          Expanded(child: Container()),
          Expanded(child: Container()),
        ],
      );
    } else {
      return Column(
        children: [
          Container(height: 100),
          Container(height: 200),
        ],
      );
    }
  },
)
```

### 핵심

```text
LayoutBuilder
= 현재 부모가 제공하는 크기를 확인하고
  그 크기에 맞춰 레이아웃을 변경
```

예:

```text
넓은 화면 → Row
좁은 화면 → Column
```

---

# 15. 반응형 레이아웃

## MediaQuery

화면의 크기, 방향, 패딩 등의 **미디어 정보**를 가져올 수 있다.

```dart
final mediaQuery = MediaQuery.of(context);

final screenWidth = mediaQuery.size.width;
final screenHeight = mediaQuery.size.height;
final orientation = mediaQuery.orientation;
final padding = mediaQuery.padding;
```

### 화면 크기 확인

```dart
final isTablet = screenWidth > 600;
```

화면 너비를 기준으로 휴대폰과 태블릿 등의 레이아웃을 구분할 수 있다.

### 비율로 크기 지정

```dart
Container(
  width: screenWidth * 0.8,
  height: screenHeight * 0.2,
)
```

→ 화면 너비의 80%

→ 화면 높이의 20%

---

# 16. OrientationBuilder

기기의 **화면 방향**에 따라 다른 레이아웃을 구성할 때 사용한다.

```dart
OrientationBuilder(
  builder: (context, orientation) {
    return GridView.count(
      crossAxisCount:
          orientation == Orientation.portrait ? 2 : 3,
      children: [],
    );
  },
)
```

### 화면 방향

```text
portrait
= 세로 모드

landscape
= 가로 모드
```

예:

```text
세로 → 2열
가로 → 3열
```

---

# 17. 레이아웃 디버깅

## debugPaintSizeEnabled

레이아웃의 경계를 시각적으로 확인할 수 있다.

```dart
import 'package:flutter/rendering.dart';

void main() {
  debugPaintSizeEnabled = true;

  runApp(MyApp());
}
```

---

## LayoutBuilder로 제약 조건 확인

현재 위젯에 전달되는 크기를 출력할 수 있다.

```dart
LayoutBuilder(
  builder: (context, constraints) {
    print(
      'Width: ${constraints.maxWidth}, '
      'Height: ${constraints.maxHeight}',
    );

    return YourWidget();
  },
)
```

---

## Flutter DevTools

Flutter DevTools의 **Widget Inspector**를 사용하면

* 위젯 트리 확인
* 위젯 속성 확인
* 레이아웃 구조 확인

등을 할 수 있다.

---

# 18. 자주 발생하는 레이아웃 오류

## Unbounded 높이 오류

대표적인 오류:

```text
Vertical viewport was given unbounded height.
```

높이 제약이 없는 상태에서 `ListView` 등을 사용할 때 발생할 수 있다.

### 해결 방법 1: 크기 제한

```dart
Container(
  height: 300,
  child: ListView(),
)
```

### 해결 방법 2: Expanded

`Column` 내부에서 사용할 경우:

```dart
Column(
  children: [
    Expanded(
      child: ListView(),
    ),
  ],
)
```

### 해결 방법 3: shrinkWrap

```dart
ListView(
  shrinkWrap: true,
  children: [],
)
```

단, `shrinkWrap`은 성능에 주의해야 한다.

---

# 19. Column이 화면을 넘어가는 문제

## 잘못된 방법

```dart
Column(
  children: [
    // 많은 위젯
  ],
)
```

내용이 화면보다 많으면 화면을 넘어갈 수 있다.

## 해결 방법

```dart
SingleChildScrollView(
  child: Column(
    children: [
      // 많은 위젯
    ],
  ),
)
```

내용이 많아 화면을 넘어갈 수 있다면 `SingleChildScrollView`를 사용하여 스크롤할 수 있도록 한다.

---

# 20. 레이아웃 위젯 핵심 정리

| 위젯                      | 핵심 기능              |
| ----------------------- | ------------------ |
| `Container`             | 크기, 여백, 스타일 등을 설정  |
| `SizedBox`              | 고정 크기 또는 간격        |
| `Padding`               | 내부 여백              |
| `Center`                | 중앙 정렬              |
| `Align`                 | 원하는 위치에 정렬         |
| `FractionallySizedBox`  | 부모 크기의 비율로 크기 지정   |
| `AspectRatio`           | 가로세로 비율 지정         |
| `Row`                   | 가로 배치              |
| `Column`                | 세로 배치              |
| `Expanded`              | 남은 공간을 최대한 차지      |
| `Flexible`              | 필요한 만큼 공간 사용       |
| `Spacer`                | 빈 공간 생성            |
| `Wrap`                  | 공간 부족 시 다음 줄/열로 이동 |
| `Stack`                 | 위젯을 겹쳐 배치          |
| `Positioned`            | Stack 내부 위치 지정     |
| `SingleChildScrollView` | 단일 자식 스크롤          |
| `ListView`              | 스크롤 가능한 목록         |
| `GridView`              | 격자 형태 목록           |
| `ConstrainedBox`        | 크기 제약 추가           |
| `IntrinsicWidth`        | 자식 기준 너비 조정        |
| `IntrinsicHeight`       | 자식 기준 높이 조정        |
| `LayoutBuilder`         | 부모 제약에 따라 레이아웃 변경  |
| `MediaQuery`            | 화면 크기 및 미디어 정보 확인  |
| `OrientationBuilder`    | 화면 방향에 따라 레이아웃 변경  |

---

변경
MediaQuery     → 화면 크기/방향 등 확인
OrientationBuilder → 화면 방향에 따른 레이아웃 변경
```
