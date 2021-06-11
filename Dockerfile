FROM java:8
EXPOSE 8081
ADD /target/stock-quote-manager-01.00.00.jar stock-quote-manager.jar
ENTRYPOINT ["java","-jar","stock-quote-manager.jar"]