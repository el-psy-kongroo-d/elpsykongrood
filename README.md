# elpsykongrood

개발 기록과 프로젝트를 소개하는 개인 사이트입니다.

Java 17 · Spring Boot · Spring Data JPA · Spring Security · Supabase (PostgreSQL) · Thymeleaf

## 주요 기능

- 글 조회, 제목 검색, 날짜순 정렬, 페이지네이션
- Markdown 본문과 댓글 작성
- 프로젝트 목록과 PDF 미리보기

## 실행

- JDK: 17 이상
- DB: PostgreSQL (운영: Supabase)
- 포트: 8080

### 애플리케이션 실행

```sh
./mvnw spring-boot:run
```

기본 주소: `http://localhost:8080`

| 환경변수 | 기본값 |
| --- | --- |
| `DB_URL` | 필수 |
| `DB_USERNAME` | 필수 |
| `DB_PASSWORD` | 필수 |
| `PORT` | `8080` |
| `SITE_URL` | `http://localhost:8080` |
| `DOCUMENTS_DIRECTORY` | `./documents` |

### 빌드

```sh
./mvnw clean package
java -jar target/elpsykongrood-1.0.2.jar
```
