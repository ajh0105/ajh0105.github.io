# 청도군 문화관광 웹사이트 — Claude 작업 문서

> 이 파일은 다른 환경에서 Claude와 작업을 이어가기 위한 컨텍스트 문서입니다.
> **작업 지침: 설계/기획은 Opus, 코딩/구현은 Sonnet 사용**

---

## 프로젝트 개요

- **목적**: 경북 청도군 문화관광 정적 HTML 웹사이트 전면 리뉴얼
- **경로**: `c:\jh\ajh0105.github.io\web01\`
- **기술 스택**: 순수 HTML / CSS Custom Properties / Vanilla JS (프레임워크 없음)
- **폰트**: Freesentation(FT), GmarketSans(GS) — `font2.css` 로드
- **아이콘**: xeicon (`xi-*`) — `iconset.css` 로드

---

## CSS 파일 구조 (로드 순서)

| 파일 | 역할 |
|---|---|
| `reset.css` | 브라우저 초기화 (기존) |
| `font2.css` | 웹폰트 (FT, GS) |
| `iconset.css` | xeicon 아이콘 셋 |
| `tokens.css` | **디자인 토큰** — 색상, 폰트, 간격, 반경, 그림자, 모션 변수 |
| `base.css` | 기본 유틸리티 — `.u-container`, `.u-section`, `.sec-hd`, `.eyebrow`, `.crumb` |
| `components.css` | 버튼, 카드, 폼, 배지, 탭, 페이지네이션, 레거시 컴포넌트 |
| `layout.css` | GNB 헤더, 드롭다운, 푸터, `.page-hero`, `.subnav`, `.sub-body--grid` |
| `home.css` | 메인 인덱스 전용 — 히어로 슬라이더, stat-bar, nine-grid, fest-grid, quick-grid, news-grid |
| `sub.css` | 서브페이지 전용 레이아웃 |
| `auth.css` | 로그인/회원가입 분할 패널 레이아웃 |
| `responsive.css` | 반응형 5단계 (1280 / 1024 / 820 / 640 / 480px) |

> **서브페이지 CSS 링크 경로**: `../../reset.css` 등 2단계 상위 경로 사용

---

## 주요 디자인 토큰 (`tokens.css`)

```css
/* 색상 */
--c-navy-900:#071026;  --c-navy-800:#0a173e;  --c-primary:#35528C;
--c-green:#5FA65D;     --c-red:#BF3434;        --c-persimmon:#e8732c;

/* 폰트 */
--ff-display:'GS','Apple SD Gothic Neo',sans-serif;
--ff-body:'FT','Apple SD Gothic Neo',sans-serif;

/* 간격 (일부) */
--sp-7:32px; --sp-8:40px; --sp-9:48px; --sp-10:56px;
--sp-12:64px; --sp-14:80px; --sp-16:88px;

/* 기타 */
--section-pad:88px; --container:1280px;
--t-fast:0.25s; --t-base:0.3s; --t-slow:0.6s;
--ease:cubic-bezier(0.22,0.61,0.36,1);
```

---

## 페이지 목록

### 메인 / 공통
| 파일 | 내용 |
|---|---|
| `index.html` | 메인 홈 (히어로 슬라이더, 9경, 축제, 빠른메뉴, 뉴스, 푸터) |
| `log.html` | 로그인 (auth.css — navy 분할 패널) |
| `member.html` | 회원가입 (auth.css — green 분할 패널, 3단계 바) |
| `contact.html` | 문의하기 (4개 info-card + 폼 + 구글지도) |

### 서브페이지 (`sub/` 폴더)
| 폴더 | 페이지 | 설명 |
|---|---|---|
| `sub/tour/` | sub21.html | 청도관광9경 (card-grid--3, 9장) |
| | sub22.html | 테마관광 |
| | sub23.html | 추천관광코스 (course_steps) |
| `sub/festival/` | sub31.html | 정기축제 (event-grid) |
| | sub32.html | 이벤트/행사 (notice-list) |
| `sub/stay/` | sub41.html | 숙박 (stay-grid) |
| | sub42.html | 맛집 |
| | sub43.html | 쇼핑 (shop_grid) |
| `sub/info/` | sub51.html | 교통/접근 (kpi-strip + transport-grid + 지도) |
| | sub52.html | 날씨/계절 |
| | sub53.html | 지도 |
| `sub/intro/` | sub11.html | 청도이야기 (intro-grid + kpi-strip) |
| | sub12.html | 역사/문화 (timeline) |
| | sub13.html | 인물/인재 (person_grid) |
| `sub/community/` | sub61.html | 공지사항 (list-table + pagination) |
| | sub62.html | 자유게시판 |
| | sub63.html | 여행후기 (review_list) |
| | sub64.html | 여행 Q&A (qna_item) |

---

## 주요 HTML 패턴

### GNB 헤더
```html
<header class="gnb-header">
  <div class="u-container">
    <a class="gnb-logo" href="index.html"><img src="./images/logo.png" alt="청도군 문화관광"></a>
    <ul class="gnb-nav">
      <li>
        <a href="...">메뉴명</a>
        <div class="gnb-drop">
          <a href="...">서브메뉴</a>
        </div>
      </li>
      <!-- 활성 메뉴: <li class="is-active"> -->
    </ul>
    <div class="gnb-util">
      <a href="log.html">로그인</a>
      <span class="sep">|</span>
      <a href="member.html">회원가입</a>
      <span class="sep">|</span>
      <a href="contact.html">Contact</a>
    </div>
  </div>
</header>
```

### 서브페이지 공통 구조
```html
<!-- 헤더 (is-active 설정) -->
<!-- 페이지 히어로 -->
<div class="page-hero" style="background-image:url(../../images/XXX.jpg)">
  <div class="page-hero__veil"></div>
  <div class="u-container page-hero__inner">
    <nav class="crumb">HOME <i class="xi-angle-right-min"></i> 상위메뉴 <i class="xi-angle-right-min"></i> 현재</nav>
    <h1 class="page-hero__title">페이지 제목</h1>
    <p class="page-hero__sub">설명</p>
  </div>
</div>
<!-- 서브내비 -->
<nav class="subnav">
  <div class="u-container subnav__scroll">
    <a class="subnav__item is-active" href="...">현재 메뉴</a>
    <a class="subnav__item" href="...">다른 메뉴</a>
  </div>
</nav>
<!-- 본문 -->
<div class="u-container sub-body">
  <main class="sub-main">...</main>
  <aside class="sub-aside">
    <div class="info-card">
      <div class="info-card__head">제목</div>
      <div class="info-card__list">
        <div><dt>항목</dt><dd>내용</dd></div>
      </div>
    </div>
  </aside>
</div>
<!-- 간소화 푸터 -->
<footer class="site-footer">
  <div class="footer-bottom"><div class="u-container">
    <p class="footer-copyright">© 2025 청도군 문화관광.</p>
    <nav class="footer-fnb"><a href="../../index.html">메인으로</a></nav>
  </div></div>
</footer>
```

### 섹션 헤더 패턴
```html
<!-- 밝은 배경 섹션 -->
<div class="sec-hd">
  <div class="sec-hd__echo">ENGLISH</div>        <!-- 96px 배경 텍스트 -->
  <h2 class="sec-hd__title">한글 제목</h2>
  <p class="sec-hd__sub">설명 문구</p>
</div>

<!-- 어두운 배경 섹션 -->
<div class="sec-hd">
  <div class="sec-hd__echo sec-hd__echo--light">ENGLISH</div>
  <h2 class="sec-hd__title sec-hd__title--light">한글 제목</h2>
  <p class="sec-hd__sub sec-hd__sub--light">설명</p>
</div>
```

---

## 히어로 슬라이더 구조 (JS 기반 무한 루프)

```html
<section class="hero" id="heroSlider">
  <div class="hero__track" id="heroTrack">
    <!-- 3개 슬라이드 (JS가 앞뒤에 클론 자동 삽입) -->
    <div class="hero__slide">
      <img class="hero__slide-bg" src="./images/vs0N.jpg" alt="...">
      <div class="hero__slide-veil"></div>
      <div class="hero__slide-content">
        <span class="hero__badge">ENGLISH BADGE</span>
        <h1 class="hero__title">제목<br><em>강조</em></h1>
        <p class="hero__desc">설명</p>
        <div class="hero__btns">
          <a class="btn btn--primary" href="...">버튼1</a>
          <a class="btn btn--ghost" href="...">버튼2</a>
        </div>
      </div>
    </div>
  </div>
  <button class="hero__arrow hero__arrow--prev" id="heroPrev" aria-label="이전 슬라이드">
    <i class="xi-angle-left"></i>
  </button>
  <button class="hero__arrow hero__arrow--next" id="heroNext" aria-label="다음 슬라이드">
    <i class="xi-angle-right"></i>
  </button>
  <div class="hero__dots" id="heroDots">
    <button class="hero__dot is-active" data-index="0" aria-label="슬라이드 1"></button>
    <button class="hero__dot" data-index="1" aria-label="슬라이드 2"></button>
    <button class="hero__dot" data-index="2" aria-label="슬라이드 3"></button>
  </div>
  <div class="hero__count" id="heroCount">01 / 03</div>
</section>
```

**슬라이더 동작 원리**:
- JS가 첫/마지막 슬라이드를 클론해 앞뒤에 삽입 → 5개 슬라이드
- `pos=1`이 실제 첫 슬라이드 (pos=0, pos=4가 클론)
- `transform:translateX(-${pos * 100}vw)` 로 이동
- `transitionend` 이벤트에서 클론 위치 감지 후 무전환(transition:none) 실제 위치 점프
- `.hero__slide { flex:0 0 100vw; width:100vw; }` — vw 단위 필수 (% 사용 시 트랙 기준이라 오류)
- 자동 슬라이드: 5000ms, 수동 조작 시 타이머 리셋

---

## 메인 인덱스 섹션 순서

1. **GNB Header** — 6개 메뉴 + 드롭다운
2. **Hero Slider** — 3슬라이드 + 이전/다음 버튼 + 인디케이터 + 카운터
3. **Stat Bar** — 9경 / 4+ / 1000년 / 30분 (`.stat-bar`, navy 배경)
4. **청도 관광 9경** — 다크 섹션, 3×3 그리드 (`.nine-grid`)
5. **축제 & 행사** — 흰 배경, 메인 1 + 서브 2 그리드 (`.fest-grid`)
6. **청도 여행 바로가기** — alt 배경, 8개 아이콘 메뉴 (`.quick-grid`, `box_icon1-8.png`)
7. **청도 최신 소식** — 흰 배경, 3열 뉴스 카드 (`.news-grid`)
8. **Footer** — footer-banner (4개 링크) + footer-body (4열) + footer-bottom

---

## 버튼 클래스 조합

```html
<a class="btn btn--primary">파란 채움</a>
<a class="btn btn--ghost">반투명 테두리 (어두운 배경용)</a>
<a class="btn btn--line">테두리 (밝은 배경용)</a>
<a class="btn btn--green">초록 채움</a>
<a class="btn btn--sm">작은 크기</a>
<a class="btn btn--lg">큰 크기</a>
```

---

## 이미지 파일 (주요)

| 파일명 | 용도 |
|---|---|
| `logo.png` | GNB/푸터 로고 |
| `vs01.jpg` ~ `vs03.jpg` | 히어로 슬라이드 배경 |
| `bigthum01.jpg` | 축제 메인 카드 |
| `row01.jpg` ~ `row4.jpg`, `row02.jpg`, `row03.jpg` | 축제/뉴스 카드 |
| `row03_1.jpg` ~ `row03_4.jpg` | 카드 이미지 |
| `box_icon1.png` ~ `box_icon8.png` | 빠른 메뉴 아이콘 |
| `sub1_1_1.jpg` ~ `sub3_1_3.jpg` | 9경 카드 이미지 |
| `sub2_1_3.jpg` 등 | 서브 페이지 히어로 배경 |
| `bg_visual-9.jpg` | 커뮤니티 페이지 히어로 |

---

## 알려진 이슈 / 주의사항

1. **Write 도구 사용 시**: 기존 파일은 반드시 Read 후 Write 또는 Edit 사용
2. **`flex:0 0 100%` 금지**: `.hero__slide`는 반드시 `flex:0 0 100vw; width:100vw`
3. **히어로 JS `<script>` 위치**: `</section>` 직후 (히어로 섹션 바로 아래)
4. **서브페이지 CSS 경로**: `../../tokens.css` 등 2단계 상위 경로
5. **`--sp-11`, `--sp-13` 없음**: 토큰은 --sp-12(64px), --sp-14(80px), --sp-16(88px) 사용
6. **레거시 파일 존재**: `main.css`, `idx.css`, `content.css`, `sub_common.css`, `common.css` 등 구버전 파일은 신규 HTML에서 로드하지 않음

---

---

## 백엔드 (`backend/` 폴더)

### 기술 스택
- **Spring Boot 3.5.0** / Java 21 / Gradle
- **PostgreSQL** (DB) + Spring Data JPA + Hibernate
- **Thymeleaf** + thymeleaf-layout-dialect
- **BCrypt** (Spring Security crypto) — Spring Security 전체 미사용, 세션 기반 인터셉터로 인증
- **Swagger (springdoc-openapi 2.5.0)** — `/swagger-ui.html`

### 백엔드 구조
```
backend/
├── build.gradle / settings.gradle
├── Dockerfile
├── docker-compose.yml   (app + PostgreSQL)
└── src/main/
    ├── java/com/cheongdo/tourism/
    │   ├── CheongdoApplication.java
    │   ├── config/          PasswordConfig, WebConfig
    │   ├── controller/      HomeController, MemberController, SubPageController, CommunityController
    │   ├── dto/             PostForm
    │   ├── entity/          Member, Post, Role(enum), BoardType(enum)
    │   ├── interceptor/     LoginCheckInterceptor
    │   ├── repository/      MemberRepository, PostRepository
    │   └── service/         MemberService, PostService
    └── resources/
        ├── application.yml
        └── templates/
            ├── fragments/   head.html, header.html, footer.html
            ├── index.html
            ├── contact.html
            ├── member/      login.html, signup.html
            ├── tour/        sub21~23.html
            ├── festival/    sub31~32.html
            ├── stay/        sub41~43.html
            ├── info/        sub51~53.html
            ├── intro/       sub11~13.html
            └── community/   sub61~64.html, detail.html, write.html, edit.html
```

### URL 라우팅
| URL | 설명 |
|---|---|
| `/` | 메인 홈 |
| `/login`, `/signup`, `/logout` | 인증 |
| `/tour/9gyeong`, `/tour/theme`, `/tour/course` | 청도관광 |
| `/festival/regular`, `/festival/event` | 축제/행사 |
| `/stay/accommodation`, `/stay/restaurant`, `/stay/shopping` | 숙박/맛집 |
| `/info/transport`, `/info/weather`, `/info/map` | 여행정보 |
| `/intro/story`, `/intro/history`, `/intro/people` | 청도소개 |
| `/community/notice`, `/community/board`, `/community/review`, `/community/qna` | 게시판 목록 |
| `/community/{type}/{id}` | 게시글 상세 |
| `/community/{type}/write` | 글쓰기 (로그인 필요) |
| `/contact` | 문의하기 |

### 정적 파일 서빙 방식
- **로컬 개발**: `application.yml`의 `spring.web.resources.static-locations: file:../` 으로 web01 루트 직접 참조
- **Docker**: `docker-compose.yml`에서 `../:/app/static:ro` 볼륨 마운트

### Docker 실행 방법
```bash
# web01/backend/ 디렉터리에서
docker-compose up -d

# 또는 web01/ 루트에서
docker-compose -f backend/docker-compose.yml up -d
```
- 앱: `http://localhost:8080`
- DB: `localhost:5433` (postgres / cheongdo / cheongdo1004)

### 로컬 개발 실행 방법
```bash
# PostgreSQL 먼저 실행 후 (또는 Docker DB만 실행)
docker-compose up -d db

# backend/ 디렉터리에서
./gradlew bootRun
```

### Thymeleaf 공통 패턴
```html
<!-- CSS/이미지 경로 -->
<link rel="stylesheet" th:href="@{/tokens.css}">
<img th:src="@{/images/logo.png}">

<!-- 헤더/푸터 fragment 삽입 -->
<th:block th:replace="~{fragments/header :: header('tour')}"></th:block>
<th:block th:replace="~{fragments/footer :: footer}"></th:block>

<!-- 로그인 여부 체크 -->
<th:block th:if="${session.loginMember != null}">...</th:block>

<!-- 동적 배경이미지 -->
<div class="page-hero" th:style="'background-image:url(' + @{/images/xxx.jpg} + ')'">
```

---

## 작업 이력 (주요)

| 시점 | 작업 내용 |
|---|---|
| 초기 | CSS 8파일 아키텍처 설계 및 전면 재작성 |
| 초기 | index.html, log.html, member.html, contact.html 재작성 |
| 초기 | sub/ 폴더 서브페이지 17개 신규 생성 |
| 중기 | 인덱스에서 go5-strip, feature-split 섹션 삭제 |
| 중기 | 축제&행사 ↔ 청도관광9경 섹션 순서 교체 |
| 중기 | 푸터 관련사이트(footer-rel) 탭 삭제 |
| 최근 | 히어로 슬라이더: SCROLL 텍스트 삭제, 이전/다음 버튼 추가, 5초 자동슬라이드 |
| 최근 | 히어로 무한루프: 클론 삽입 방식으로 3→1 자연스러운 전환 |
| 최근 | `.hero__slide` `flex:0 0 100vw` 수정 (이미지 확대 버그 수정) |
| 최근 | `gnb-lang-btn` (언어 선택 버튼) 삭제 |
