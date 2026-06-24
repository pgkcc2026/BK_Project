FROM tomcat:9.0-jdk17-temurin AS build

WORKDIR /app

COPY . .

RUN mkdir -p build/classes \
    && javac -encoding UTF-8 \
       -cp "/usr/local/tomcat/lib/*:src/main/webapp/WEB-INF/lib/*" \
       -d build/classes \
       $(find src/main/java -name "*.java") \
    && mkdir -p war/WEB-INF/classes \
    && cp -r src/main/webapp/* war/ \
    && cp -r build/classes/* war/WEB-INF/classes/ \
    && cd war \
    && jar -cvf /tmp/Bank_p.war .

FROM tomcat:9.0-jdk17-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /tmp/Bank_p.war /usr/local/tomcat/webapps/Bank_p.war

CMD ["catalina.sh", "run"]
