INSERT INTO counters (name, value)
VALUES ('page_views', 0)
ON CONFLICT (name) DO NOTHING;
