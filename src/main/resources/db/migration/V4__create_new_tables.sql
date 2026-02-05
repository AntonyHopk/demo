CREATE TABLE addresses (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE,
    country VARCHAR(50),
    city VARCHAR(50),
    street VARCHAR(100),
    CONSTRAINT fk_address_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);
CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_comments_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_comments_user_id ON comments(user_id);
CREATE TABLE IF NOT EXISTS groups (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS group_members (
  group_id BIGINT NOT NULL,
  user_id  BIGINT NOT NULL,
  PRIMARY KEY (group_id, user_id),
  CONSTRAINT fk_group_members_group FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE,
  CONSTRAINT fk_group_members_user  FOREIGN KEY (user_id)  REFERENCES users(id)  ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_group_members_user_id ON group_members(user_id);


