FROM eclipse-temurin:17-jre-alpine
WORKDIR /home/sulsul
COPY sulsul-api/build/libs/application.jar application.jar
ENTRYPOINT ["java", "-jar", "-Duser.timezone=\"Asia/Seoul\"", "-Dspring.profiles.active=prod", "./application.jar"]
