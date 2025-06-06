<?xml version="1.0" encoding="UTF-8"?>

<!-- NOTE logback 01: 설정파일 -->
<configuration>
  <property name="LOG_PATTERN"
            value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"/>

  <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>${LOG_PATTERN}</pattern>
    </encoder>
  </appender>

  <!-- NOTE logback 02: 로깅 레벨
    TRACE > DEBUG > INFO > WARN > ERROR > OFF
    설정한 로깅레벨 이상의 심각도를 지닌 로그 메시지만 출력
  -->
  <root level="DEBUG">
    <appender-ref ref="CONSOLE"/>
  </root>

  <logger name="com.grepp.spring" level="DEBUG" additivity="false">
    <appender-ref ref="CONSOLE"/>
  </logger>

</configuration>