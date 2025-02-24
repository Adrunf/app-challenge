# Usa la imagen oficial de MySQL como base
FROM mysql:8.0

# Establece variables de entorno para configurar MySQL
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=bdd_challenge

# Copia tu archivo SQL para inicializar la base de datos si es necesario
# (Esto ejecutará los scripts automáticamente al iniciar el contenedor)
COPY ./BaseDatos.sql /docker-entrypoint-initdb.d/

# Expón el puerto 3306 (que es el puerto predeterminado de MySQL)
EXPOSE 3306

# Comando por defecto (es el de la imagen oficial de MySQL)
CMD ["mysqld"]
