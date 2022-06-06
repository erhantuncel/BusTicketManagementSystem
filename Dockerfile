FROM tomcat:8.5.41-jre8-alpine
WORKDIR /usr/local/tomcat/webapps
COPY ./wait-for ./wait-for
COPY ./target/*.war app.war
RUN rm -rf ROOT && mv app.war ROOT.war
#CMD ["catalina.sh", "run"]
