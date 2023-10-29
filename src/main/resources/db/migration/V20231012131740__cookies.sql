CREATE TABLE cookie (
    id bigserial PRIMARY KEY,
    description text NOT NULL,
    creation_date timestamp NOT NULL
);

CREATE INDEX cookie_id_idx ON cookie (id);
CREATE INDEX cookie_description_idx ON cookie (description);