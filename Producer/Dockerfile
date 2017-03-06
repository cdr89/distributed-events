FROM tomcat:8.0.20-jre8

# Copy all jar libs needed into tomcat lib folder
COPY lib/*.jar /usr/local/tomcat/lib/
# Deploy the web application
COPY /producer-rest-1.0.0.war /usr/local/tomcat/webapps/