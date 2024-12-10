INSERT INTO users (username,email,password,created_at) VALUES ('admin','admin@aulab.it','admin','20240912');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN'),('ROLE_REVISOR'),('ROLE_WRITER'),('ROLE_USER');

INSERT INTO users_roles (user_id,role_id) VALUES (1,1);

INSERT INTO categories (name) VALUES ('politica'),('economia'),('food&drink'),('sport'),('intrattenimento'),('tech');