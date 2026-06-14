# ── 1단계: 빌드 ─────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Gradle Wrapper + 의존성 캐시
COPY gradle/ gradle/
COPY gradlew build.gradle settings.gradle ./
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon 2>/dev/null || true

# 소스 빌드
COPY src/ src/
RUN ./gradlew bootJar --no-daemon -x test

# ── 2단계: 정적 파일 복사 (web01 루트) ────────
FROM eclipse-temurin:21-jdk-alpine AS static
WORKDIR /assets

COPY ../reset.css ../font2.css ../iconset.css ../tokens.css \
     ../base.css ../components.css ../layout.css ../home.css \
     ../sub.css ../auth.css ../responsive.css ./
COPY ../images/ images/

# ── 3단계: 실행 이미지 ────────────────────────
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# JAR
COPY --from=builder /app/build/libs/*.jar app.jar

# 정적 파일 → static/ (Spring Boot가 서빙)
COPY --from=static /assets/ static/

# xeicon, 폰트 파일이 있으면 추가
# (web01/ 루트의 폰트, 아이콘 파일이 있을 경우 Dockerfile 빌드 컨텍스트를 web01/로 설정)

ENV JAVA_OPTS="-Xms256m -Xmx512m"
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
