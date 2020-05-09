INSERT INTO usuarios (username, password, enabled, nombre, apellidos, correo, acceso_actual, ultimo_acceso, fecha_nacimiento) VALUES ('admin', '$2a$10$yFjdiWrU5sYh/dIu3oo0wORtMU4ab8YQibIdNt3yb8mJA3nSv9sTK', true, 'Admin', 'Admin', 'admin@admin.com', NOW(), NOW(), '1994-05-09');
INSERT INTO usuarios (username, password, enabled, nombre, apellidos, correo, acceso_actual, ultimo_acceso, fecha_nacimiento) VALUES ('adrian', '$2a$10$CtFYKSwnVrrRgzIUTV5u4OWCgz/eAUMEZoxpjPg2Ush9V7r653V2e', true, 'Adrian', 'Navarro Gabino', 'adrian@navarro.com', NOW(), NOW(), '1994-05-09');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 2);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 1);