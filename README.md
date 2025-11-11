## Instalación de Jenkins in Docker


### Paso 1: Descargar la imagen oficial de Jenkins
```
docker pull jenkins/jenkins:lts-jdk17
```

### Paso 2: Crear un contenedor de Jenkins
```
docker run -d  -p 8080:8080  -p 50000:50000  -v jenkins_home:/var/jenkins_home  --name jenkins  jenkins/jenkins:jdk17
```

### Paso 3: Acceder a Jenkins
Ingresa a  http://localhost:8080/

### Paso 4: Obtener la contraseña inicial
```
docker exec -it jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```