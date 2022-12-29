# Populate permiso table
INSERT INTO permiso (id_permiso, nombre) VALUES (1, 'conectarse'), (2, 'comentar'), (3, 'ver_foto'), (4, 'crear_album');
# Populate rol table
INSERT INTO rol (id_rol, nombre) VALUES (1, 'user'), (2, 'admin');
# Populate rol_permiso table
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (1, 1), (1, 2), (1, 3), (1,4),
(2,1), (2,2), (2,3), (2,4);
# Populate usuario_rol table
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 2);
