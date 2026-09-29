# TextEditingController

오늘은 전에 만들어 둔 로그인 화면에 `TextEditingController`를 적용해보았다.

---

## TextEditingController 적용 전

    import 'package:flutter/material.dart';

    void main(){
      runApp(
          MaterialApp(
            debugShowCheckedModeBanner: false,
            home: Login(),
          )
      );
    }

    class Login extends StatelessWidget{
      const Login({super.key});

      @override
      Widget build(BuildContext context) {
        return Scaffold(
          backgroundColor: Colors.white,
          body: SingleChildScrollView(
            child: Padding(
              padding: const EdgeInsets.all(100),

              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Text(
                    '로그인',
                    style: TextStyle(
                      fontSize: 35,
                      fontWeight: FontWeight.bold,

                    ),
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        '계정에 로그인하고 계속하세요',
                        style: TextStyle(
                            fontSize: 15,
                            fontWeight: FontWeight.bold,
                            color: Colors.grey
                        ),
                      ),
                    ],
                  ),
                  SizedBox(height: 40),

                  Column(
                    children: [
                      SizedBox(
                        width: 450,
                        height: 55,
                        child: TextField(
                          decoration: InputDecoration(
                            hintText: '이메일',
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(8),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                  SizedBox(height: 20),

                  Column(
                    children: [
                      SizedBox(
                        width: 450,
                        height: 55,
                        child: TextField(
                          decoration: InputDecoration(
                            hintText: '비밀번호',
                            suffixIcon: Icon(Icons.remove_red_eye_outlined),
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(8),
                            ),
                          ),
                        ),
                      )
                    ],
                  ),
                  SizedBox(height: 20),

                  Column(
                    children: [
                      ElevatedButton(
                        onPressed: () {
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(content: Text('로그인 성공'),
                              duration: Duration(seconds: 2),
                            ),
                          );
                        },
                        style: ElevatedButton.styleFrom(
                            minimumSize: const Size(450, 55),
                            backgroundColor: Colors.blueAccent,
                            foregroundColor: Colors.white,
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadiusGeometry.circular(8),
                            )
                        ),
                        child: const Text('로그인'),
                      )
                    ],
                  ),
                  SizedBox(height: 10),

                  Column(
                    children: [
                      TextButton(
                        onPressed: () {},
                        child: Text(
                          '비밀번호를 잊으셨나요?',
                          style: TextStyle(
                            color: Colors.blueAccent,
                          ),
                        ),
                      ),
                    ],
                  ),
                  SizedBox(height: 30),

                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      SizedBox(
                        width: 180,
                        child: Divider(),
                      ),

                      Padding(
                        padding: const EdgeInsets.symmetric(horizontal: 15),
                        child: Text('또는'),
                      ),

                      SizedBox(
                        width: 180,
                        child: Divider(),
                      ),
                    ],
                  ),
                  SizedBox(height: 20),

                  Column(
                    children: [
                      ElevatedButton(
                        onPressed: (){},
                        style: ElevatedButton.styleFrom(
                          minimumSize: const Size(450, 55),
                          side: BorderSide(
                            color: Colors.grey,
                            width: 1.0,
                          ),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadiusGeometry.circular(8),
                          ),

                          backgroundColor: Colors.white,
                          foregroundColor: Colors.black,
                        ),
                        child: const Text('회원가입'),
                      ),
                    ],
                  )
                ],
              ),
            ),
          ),
        );
      }
    }

---

## 핵심적으로 추가된 부분

### 1. Stateless -> StatefulWidget

    class Login extends StatefulWidget {

- `TextEditingController`의 생명주기를 관리하기 위해 바꿨다.

---

### 2. Controller 생성

    final TextEditingController emailController = TextEditingController();
    final TextEditingController passwordController = TextEditingController();

---

### 3. TextField 연결

- 이메일

    TextField(
      controller: emailController,
    )

- 비밀번호

    TextField(
      controller: passwordController,
    )

---

### 4. 입력된 값 가져오기

    String email = emailController.text;
    String password = passwordController.text;

- `.text`를 사용하면 현재 사용자가 입력한 내용을 가져올 수 있다.

---

### 5. Controller 정리

    @override
    void dispose() {
      emailController.dispose();
      passwordController.dispose();

      super.dispose();
    }

- `TextEditingController`는 사용이 끝났을 때 `dispose()`를 호출해주는 게 중요하다.

---

# TextEditingController 적용 후 코드

    import 'package:flutter/material.dart';

    void main(){
      runApp(
          MaterialApp(
            debugShowCheckedModeBanner: false,
            home: Login(),
          )
      );
    }

    class Login extends StatefulWidget {
      const Login({super.key});

      @override
      State<Login> createState() => _LoginState();
    }

    class _LoginState extends State<Login> {

      // 이메일 입력을 관리하는 Controller
      final TextEditingController emailController = TextEditingController();

      // 비밀번호 입력을 관리하는 Controller
      final TextEditingController passwordController = TextEditingController();

      @override
      void dispose() {
        // Controller 사용이 끝나면 메모리에서 제거
        emailController.dispose();
        passwordController.dispose();

        super.dispose();
      }

      @override
      Widget build(BuildContext context) {
        return Scaffold(
          backgroundColor: Colors.white,
          body: SingleChildScrollView(
            child: Padding(
              padding: const EdgeInsets.all(100),

              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Text(
                    '로그인',
                    style: TextStyle(
                      fontSize: 35,
                      fontWeight: FontWeight.bold,
                    ),
                  ),

                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        '계정에 로그인하고 계속하세요',
                        style: TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.bold,
                          color: Colors.grey,
                        ),
                      ),
                    ],
                  ),

                  SizedBox(height: 40),

                  Column(
                    children: [
                      SizedBox(
                        width: 450,
                        height: 55,
                        child: TextField(
                          // 이메일 Controller 연결
                          controller: emailController,

                          decoration: InputDecoration(
                            hintText: '이메일',
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(8),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),

                  SizedBox(height: 20),

                  Column(
                    children: [
                      SizedBox(
                        width: 450,
                        height: 55,
                        child: TextField(
                          // 비밀번호 Controller 연결
                          controller: passwordController,

                          // 비밀번호를 가려서 표시
                          obscureText: true,

                          decoration: InputDecoration(
                            hintText: '비밀번호',
                            suffixIcon: Icon(Icons.remove_red_eye_outlined),
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(8),
                            ),
                          ),
                        ),
                      )
                    ],
                  ),

                  SizedBox(height: 20),

                  Column(
                    children: [
                      ElevatedButton(
                        onPressed: () {

                          // 입력된 이메일 가져오기
                          String email = emailController.text;

                          // 입력된 비밀번호 가져오기
                          String password = passwordController.text;

                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(
                              content: Text(
                                '이메일: $email\n비밀번호: $password',
                              ),
                              duration: Duration(seconds: 2),
                            ),
                          );
                        },

                        style: ElevatedButton.styleFrom(
                          minimumSize: const Size(450, 55),
                          backgroundColor: Colors.blueAccent,
                          foregroundColor: Colors.white,

                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(8),
                          ),
                        ),

                        child: const Text('로그인'),
                      )
                    ],
                  ),

                  SizedBox(height: 10),

                  Column(
                    children: [
                      TextButton(
                        onPressed: () {},
                        child: Text(
                          '비밀번호를 잊으셨나요?',
                          style: TextStyle(
                            color: Colors.blueAccent,
                          ),
                        ),
                      ),
                    ],
                  ),

                  SizedBox(height: 30),

                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      SizedBox(
                        width: 180,
                        child: Divider(),
                      ),

                      Padding(
                        padding: const EdgeInsets.symmetric(horizontal: 15),
                        child: Text('또는'),
                      ),

                      SizedBox(
                        width: 180,
                        child: Divider(),
                      ),
                    ],
                  ),

                  SizedBox(height: 20),

                  Column(
                    children: [
                      ElevatedButton(
                        onPressed: () {},

                        style: ElevatedButton.styleFrom(
                          minimumSize: const Size(450, 55),

                          side: BorderSide(
                            color: Colors.grey,
                            width: 1.0,
                          ),

                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(8),
                          ),

                          backgroundColor: Colors.white,
                          foregroundColor: Colors.black,
                        ),

                        child: const Text('회원가입'),
                      ),
                    ],
                  )
                ],
              ),
            ),
          ),
        );
      }
    }

---

# TextEditingController 알게된 점

## TextEditingController

- Flutter에서 `TextField`에 입력되는 텍스트를 관리하는 클래스
- 사용자가 입력한 값을 가져오거나 변경할 때 사용
- 로그인, 회원가입, 검색창 등 사용자의 입력을 받아야 하는 화면에서 사용

## Controller 생성

- `TextEditingController`를 변수로 만들어 사용
- 일반적으로 `final`을 사용하여 Controller 자체가 다른 객체로 변경되지 않도록 한다.

## TextField에 연결

- `TextField`의 `controller` 속성에 Controller를 연결한다.
- 연결하면 해당 Controller가 `TextField`의 입력 내용을 관리한다.

## 입력된 값 가져오기

- Controller의 `.text`를 사용하면 현재 `TextField`에 입력된 문자열을 가져올 수 있다.

    String email = emailController.text;

- 비밀번호도 같은 방식으로 가져올 수 있다.

    String password = passwordController.text;

## StatefulWidget 사용

- `TextEditingController`를 사용할 때는 Controller의 생명주기를 관리해야 한다.
- 일반적으로 `StatefulWidget`에서 사용한다.

    class Login extends StatefulWidget {
      const Login({super.key});

      @override
      State<Login> createState() => _LoginState();
    }

## dispose()

- `TextEditingController`의 사용이 끝나면 `dispose()`를 호출해야 한다.
- 사용하던 Controller를 정리하여 불필요한 리소스 사용을 방지한다.

    @override
    void dispose() {
      emailController.dispose();
      passwordController.dispose();

      super.dispose();
    }

## 주요 기능

- `TextField`의 입력값 가져오기
- `TextField`의 입력값 변경하기
- `TextField`의 현재 텍스트 처리
- 여러 `TextField`의 값을 각각 관리할 수 있음