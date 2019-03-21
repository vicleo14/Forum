CREATE DATABASE FOROREDES;
USE FOROREDES;

CREATE TABLE usuario(
	nickName varchar(15) not null primary key,
	contrasenia varchar(15));
	
CREATE TABLE publicacion(
	idPublic int not null auto_increment primary key,
	nombre varchar(100),
	info varchar(1000),
	imagen varchar(25),
	fecha date,
	nickName varchar(15),
		foreign key(nickName)
			references usuario(nickName)
				ON UPDATE CASCADE
				ON DELETE CASCADE);

CREATE TABLE comentario(
	idComent int not null auto_increment primary key,
	info varchar(1000),
	imagen varchar(25),
	nickName varchar(15),
	idPublic int,
		foreign key(nickName)
			references usuario(nickName)
				ON UPDATE CASCADE
				ON DELETE CASCADE,
		foreign key(idPublic)
			references publicacion(idPublic)
				ON UPDATE CASCADE
				ON DELETE CASCADE);

-- Inserta un usuario a la base de datos
DELIMITER #
CREATE PROCEDURE insertUser(IN nick varchar(15), IN pass varchar(15))
BEGIN
	INSERT INTO usuario VALUES (nick, pass);
END #
DELIMITER ;

-- Inserta una publicacion a la base de datos
DELIMITER #
CREATE PROCEDURE insertPub(IN name varchar(100), IN inf varchar(1000), IN img varchar(25), IN fech date, IN nick varchar(15))
BEGIN
	INSERT INTO publicacion(nombre, info, imagen, fecha, nickName) VALUES (name, inf, img, fech, nick);
END #
DELIMITER ;

-- Inserta un comentario a la base de datos
DELIMITER #
CREATE PROCEDURE insertCom(IN inf varchar(1000), IN img varchar(25), IN nick varchar(15), IN idP int)
BEGIN
	INSERT INTO comentario(info, imagen, nickName,idPublic) VALUES (inf, img, nick, idP);
END #
DELIMITER ;

-- Regresa los datos de la publicacion idP
DELIMITER # 
CREATE PROCEDURE publication(IN idP int)
BEGIN
	SELECT p.idPublic, p.nombre, p.info, p.imagen, p.fecha,c.idComent,(SELECT p.nickName FROM punlicacion p WHERE p.idPublic=idP) as creador, c.info, c.imagen, c.nickName
		FROM publicacion p, comentario c, usuario u
			WHERE p.nickName=u.nickName AND p.idPublic=c.idPublic AND c.idPublic=idP;
END #
DELIMITER ;

-- Regresa la lista de publicaciones y su fecha
DELIMITER #
CREATE PROCEDURE publications()
BEGIN
	SELECT idPublic, nombre, fecha FROM publicacion;
END #
DELIMITER ;

-- Validar si existe un usuario
DELIMITER #
CREATE PROCEDURE userValid(IN nick varchar(15), IN pass varchar(15))
BEGIN
	SELECT * FROM usuario WHERE nickName = nick AND contrasenia = pass;
END #
DELIMITER ;