INSERT INTO transactions (user_id, amount, status)
SELECT
    (random() * 1000)::int,
    (random() * 10000)::numeric(10,2),
    CASE
        WHEN random() > 0.5 THEN 'SUCCESS'
        ELSE 'FAILED'
        END
FROM generate_series(1, 100000);