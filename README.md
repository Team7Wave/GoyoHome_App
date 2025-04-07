# GoyoHome_App

층간소음 노이즈캔슬링 기기 개발을 위한 프로젝트입니다.
프론트엔드는 **Android Studio**로 개발하였으며, 백엔드는 **Spring Boot 기반**으로 개발되었습니다.

---

## 📌 프로젝트 개요

`GoyoHome`은 사용자 스마트폰과 임베디드 기기를 연결하여 노이즈캔슬링 환경을 제공하도록 기능을 제공한다. 
이 백엔드는 사용자 인증, 기기 상태 저장, 명령 처리 등을 담당합니다.

---

## ⚙️ 프로젝트 생성 정보(백엔드)

- **생성 사이트**: [Spring Initializr](https://start.spring.io)
- **빌드 도구**: Gradle - Groovy
- **언어**: Java
- **Spring Boot 버전**: 3.4.4
- **Java 버전**: 17
- **프로젝트명**: GoyoHome
- **패키지명**: `com.example.goyohome`

---

## 📦 사용한 Dependencies

| Dependency | 설명 |
|------------|------|
| Spring Web | REST API 및 웹 기능 제공 |
| Spring Boot DevTools | 개발 중 자동 리로딩 및 편의 기능 |
| Lombok | 코드 간결화를 위한 어노테이션 |
| Spring Configuration Processor | `application.yml` 자동완성 지원 |
| Spring Data JPA | DB와 객체 매핑 (ORM) |
| MySQL Driver | MySQL 데이터베이스 연동 |
| Validation | 사용자 입력 검증 |
| OAuth2 Client | 구글 로그인 연동 |
| Spring Security | (옵션) 사용자 인증 및 인가 처리 |

---

## 📱 프론트엔드(Android) 연동 개요

- 개발 툴: **Android Studio**
- 앱 기능: 사용자 로그인, 기기 상태 확인 및 제어, 알림 등
- 통신 방식: REST API (JSON)
- 인증 방식: JWT 토큰 기반 인증(예정)

---

## 🗂️ 향후 작업 계획

- [ ] 데이터베이스 설정 및 연동
- [ ] REST API 기본 구조 구성
- [ ] JWT 기반 인증 구현
- [ ] 어플 제어어 API 설계
- [ ] 테스트 코드 작성 및 배포 환경 구축

---
