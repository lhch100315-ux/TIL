#  Spring 구성 요소 정리 (Entity, Controller, Service, Repository, DTO)

##  개요
Spring 기반 웹 애플리케이션은 역할에 따라 코드를 나누어 작성하는 구조를 사용한다.  
이를 통해 유지보수성과 확장성을 높일 수 있으며, 대표적으로 Entity, Controller, Service, Repository, DTO로 구성된다.



##  1. Entity
- 데이터베이스의 테이블과 매핑되는 클래스
- JPA를 사용하여 객체와 DB를 연결

###  특징
- `@Entity` 어노테이션 사용
- 테이블의 컬럼과 필드가 1:1 대응
- 데이터 저장의 기준이 되는 객체

###  예시
java
@Entity
public class Post {
    @Id
    private Long id;

    private String title;
    private String content;
}





##  2. Controller
- 사용자의 요청을 받아 처리하는 역할
- URL 요청을 받아서 적절한 기능을 실행

###  특징
- `@Controller` 또는 `@RestController` 사용
- HTTP 요청(GET, POST 등) 처리
- 결과를 View 또는 데이터로 반환

###  예시
java
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}




##  3. Service
- 비즈니스 로직을 처리하는 계층
- Controller와 Repository 사이에서 중간 역할

###  특징
- `@Service` 어노테이션 사용
- 핵심 기능(로직) 구현
- 코드 재사용성과 구조 분리 가능

###  예시
java
@Service
public class PostService {

    public void savePost(Post post) {
        // 비즈니스 로직 처리
    }
}




##  4. Repository
- 데이터베이스와 직접 연결되는 계층
- CRUD 작업 수행

###  특징
- `@Repository` 또는 인터페이스로 작성
- JPA를 통해 자동 구현
- SQL 없이 데이터 처리 가능

###  예시
java
public interface PostRepository extends JpaRepository<Post, Long> {
}




##  5. DTO (Data Transfer Object)
- 데이터 전달을 위한 객체
- 계층 간 데이터 이동 시 사용

###  특징
- Entity와 분리하여 사용
- 필요한 데이터만 전달
- 보안 및 구조 분리에 유리

###  예시
java
public class PostDTO {
    private String title;
    private String content;
}

