
# Configuración de Usuarios en la Base de Datos H2

## Introducción

Este proyecto utiliza la base de datos en memoria H2 para gestionar la persistencia de datos. Para que el sistema funcione correctamente, es necesario crear usuarios con roles específicos en la base de datos. A continuación, te proporcionamos los pasos para acceder a la consola de H2 y realizar las inserciones necesarias.

## Requisitos Previos

- Asegúrate de tener la aplicación Spring Boot corriendo.
- Verifica que la configuración de la base de datos H2 esté correctamente configurada en el archivo `application.properties` o `application.yml`.

## Configuración de H2 en `application.properties`

Asegúrate de tener la configuración correcta en tu archivo `application.properties` para habilitar la consola de H2 y definir la URL de conexión:

```properties
# Configuración de H2

spring.datasource.url=jdbc:h2:mem:clinica
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Acceso a la Consola de H2

1. **Inicia la Aplicación**: Corre tu aplicación Spring Boot.
   
2. **Accede a la Consola de H2**:
   - Abre tu navegador y ve a: [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
   
3. **Configura la Conexión**:
   - **JDBC URL**: Asegúrate de que esté configurada correctamente (ejemplo: `jdbc:h2:mem:clinica`).
   - **User Name**: `sa`
   - **Password**: (deja este campo en blanco si no has configurado una contraseña)
   
4. **Conéctate a la Base de Datos**: Haz clic en "Connect" para acceder a la consola de la base de datos.

## Inserción de Usuarios en la Base de Datos

Una vez conectado a la consola de H2, sigue estos pasos para insertar los usuarios:

1. **Abre la Consola de SQL**: Haz clic en la pestaña "SQL" en la parte superior.

2. **Inserta los Usuarios**:
   - Copia y pega las siguientes sentencias SQL en el área de consultas y ejecuta:

   ```sql
   INSERT INTO USUARIO (username, password, rol) VALUES ('admin', '$2b$12$dR16Q33zmQMmVkgwrXB53OCE62qERfLOn.mMjEwXRyIIwbk9e8S16', 'ROLE_ADMIN');
   INSERT INTO USUARIO (username, password, rol) VALUES ('user', '$2b$12$dR16Q33zmQMmVkgwrXB53OCE62qERfLOn.mMjEwXRyIIwbk9e8S16', 'ROLE_USER');
   ```

   - Estas sentencias crearán dos usuarios:
     - **Admin**: Usuario con rol de administrador (`ROLE_ADMIN`).
     - **User**: Usuario con rol de usuario estándar (`ROLE_USER`).

## Verificación de Usuarios

1. **Verifica los Usuarios Insertados**:
   - Ejecuta la siguiente consulta SQL para verificar que los usuarios se hayan insertado correctamente:

   ```sql
   SELECT * FROM USUARIO;
   ```

   - Deberías ver los registros de los usuarios `admin` y `user` con los roles respectivos.

## Notas Adicionales

- **Contraseñas Cifradas**: Las contraseñas están cifradas utilizando bcrypt. Asegúrate de que tu configuración de seguridad en Spring Security está configurada para manejar estas contraseñas cifradas correctamente.
  
- **Roles de Usuario**: Los roles definidos en las inserciones (`ROLE_ADMIN` y `ROLE_USER`) deben coincidir con los roles utilizados en tu configuración de seguridad para controlar el acceso a diferentes partes de la aplicación.

## Soporte

Si encuentras algún problema o necesitas ayuda adicional, por favor revisa la documentación de H2 o consulta al equipo de desarrollo.
