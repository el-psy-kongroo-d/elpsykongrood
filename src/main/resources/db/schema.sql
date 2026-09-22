BEGIN;

CREATE TABLE IF NOT EXISTS articles (
    slug text PRIMARY KEY,
    title text NOT NULL,
    content text NOT NULL,
    date timestamptz NOT NULL,
    updated_at timestamptz NOT NULL DEFAULT now(),
    views bigint NOT NULL DEFAULT 0,
    excerpt text
);

CREATE TABLE IF NOT EXISTS comments (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    article_slug text NOT NULL REFERENCES articles(slug),
    nickname text NOT NULL CHECK (char_length(nickname) BETWEEN 1 AND 24),
    body text NOT NULL CHECK (char_length(body) BETWEEN 1 AND 1000),
    created_at timestamptz NOT NULL DEFAULT now()
);

ALTER TABLE comments DROP COLUMN IF EXISTS password_hash;

CREATE INDEX IF NOT EXISTS comments_article_created_id_idx
    ON comments (article_slug, created_at, id);

ALTER TABLE articles ENABLE ROW LEVEL SECURITY;
ALTER TABLE comments ENABLE ROW LEVEL SECURITY;

COMMIT;
