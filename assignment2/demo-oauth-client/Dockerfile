# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk:8
ADD target/demo.jar demo.jar
EXPOSE 8082
entrypoint ["java", "-jar", "demo.jar"]