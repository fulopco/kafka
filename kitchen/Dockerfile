FROM amazoncorretto:21-alpine
ADD build/libs/kitchen-0.0.1-SNAPSHOT.jar /home/root/kitchen.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar","/home/root/kitchen.jar"]