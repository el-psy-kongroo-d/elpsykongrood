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

ALTER TABLE articles ENABLE ROW LEVEL SECURITY;

COMMIT;
