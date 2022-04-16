CREATE TABLE IF NOT EXISTS "departments"
(
    id  BIGSERIAL,
    email          CHARACTER VARYING(256) NOT NULL,
    password       CHARACTER VARYING(256) NOT NULL,
    first_name     CHARACTER VARYING(256) NOT NULL,
    last_name      CHARACTER VARYING(256) NOT NULL,
    role           CHARACTER VARYING(50)  DEFAULT 'ROLE_USER',
    PRIMARY KEY(id)
);

INSERT INTO "departments"(email, password, first_name, last_name, role)
VALUES ('admin@mail.com','$2a$12$75H4ERkrNDhEvaNrjA/G6O5Acl2mJSgM1ulDIJuC54M6CkRt6IZTa', 'Ivan', 'Petrov', 'ROLE_ADMIN'),
       ('user@mail.com','$2a$12$3GoUqcnTpyI9APdRr5GWr.1Sm6JcvwdslU77kVx2BXi1erlqueEwK','Oleg', 'Karpov', 'ROLE_USER');


-- BCrypt(12)
-- admin = $2a$12$75H4ERkrNDhEvaNrjA/G6O5Acl2mJSgM1ulDIJuC54M6CkRt6IZTa
-- user = $2a$12$3GoUqcnTpyI9APdRr5GWr.1Sm6JcvwdslU77kVx2BXi1erlqueEwK