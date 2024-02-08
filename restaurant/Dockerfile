FROM amazoncorretto:21-alpine
ADD build/libs/restaurant-0.0.1-SNAPSHOT.jar /home/root/restaurant.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","/home/root/restaurant.jar"]