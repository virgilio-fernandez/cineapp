INSERT INTO tipos VALUES(1,'español','4k', true,'3d');



INSERT INTO detalles VALUES(1,'Animation','Carlos Gutiérrez Medrano', 'En un pequeño pueblo donde los espíritus regresan una vez al año durante el Día de Muertos, vive Salma, una joven de 16 años, quien es la única en el pueblo que no puede traer a nadie pues no conoce el paradero ni identidad de sus padres. Desde pequeña lo ha investigado sin éxito, hasta que un día Salma y sus amigos, Jorge y Pedro, descubren una pista que los lleva por un camino lleno de aventuras, fantasmas, calaveras y un hombre misterioso hasta el inframundo','https://www.youtube.com/watch?v=VHj3-IteMtM');



INSERT INTO peliculas (id,clasificacion,duracion,estado,fecha_estreno,genero,imagen,titulo,id_detalle) VALUES(1,'A',123,true,'2019-10-31','Infantil','muertos.jpg','Dia de muertos',1);



INSERT INTO tipo_pelicula VALUES(1,1);


INSERT INTO salas VALUES(1,true,100, 'sala uno', 10);
INSERT INTO salas VALUES(2,true,120, 'sala dos', 10);




INSERT INTO usuarios (username, password,estado ,nombre,apellido1,apellido2) VALUES ('andres','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1,'Marcos','Acosta','Avendano');
INSERT INTO usuarios (username, password,estado ,nombre,apellido1,apellido2) VALUES ('admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1,'Javier','Correa','Rodriguez');

INSERT INTO roles (user_id, rol) VALUES (2,'ROLE_ADMINISTRADOR');
INSERT INTO roles (user_id, rol) VALUES (2,'ROLE_CAJERO');
INSERT INTO roles (user_id, rol) VALUES (1,'ROLE_ADMINISTRADOR');


INSERT INTO horarios VALUES(1,100,true,'2019-11-27','12:00',35, 1,1,1);


INSERT INTO descuentos VALUES(1,true,'Ninguno',0);


INSERT INTO clientes (id,nombre,apellido1,apellido2,fecha,nit,estado) VALUES(1,'Andres','Guzman','Molina', '2017-08-01','12345',true);





INSERT INTO informacion_cine VALUES(1,'B/ fatima','cine@gmail.com','aaaa',456785635, 'Cine Chaplin','6659745');

INSERT INTO dosificacion VALUES(1,true,'2019-11-05','1234568',4567835, 100,1);

insert into ventas values(1,30,100,true,'2019-11-26','20:00',70,1,1);


insert into detalle_venta values(1,2,35,70,1,1,1);


insert into facturas values(1,1,'2019-11-26 20:00:00',1,1);


insert into boletos values(1,'2019-11-26','20:00',1,1);
insert into boletos values(2,'2019-11-28','20:00',1,1);








