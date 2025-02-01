# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
# Usar una imagen base con JDK preinstalado

FROM ghcr.io/graalvm/jdk-community:21

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR compilado al contenedor
COPY target/jettraframeworkclient-jar-with-dependencies.jar /app/jettraframeworkclient-jar-with-dependencies.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "jettraframeworkclient-jar-with-dependencies.jar"]