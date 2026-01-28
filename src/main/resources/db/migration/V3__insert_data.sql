INSERT INTO roles(name)
VALUES ('ROLE_ADMIN')
ON CONFLICT DO NOTHING;
INSERT INTO roles(name)
VALUES ('ROLE_USER')
ON CONFLICT DO NOTHING;

INSERT INTO products(title, price)
VALUES ('Tomato', 100),
       ('Carrot', 50),
       ('Potato', 80)
ON CONFLICT DO NOTHING;
