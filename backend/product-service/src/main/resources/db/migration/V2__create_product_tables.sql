SET search_path TO product;

CREATE TABLE category
(
    id UUID PRIMARY KEY,

    description VARCHAR(100) NOT NULL UNIQUE,

    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ
);

CREATE TABLE product
(
    id UUID PRIMARY KEY,

    category_id UUID NOT NULL,

    display_name VARCHAR(200) NOT NULL UNIQUE,

    volume_in_ml INTEGER NOT NULL CHECK (volume_in_ml > 0),

    weight_in_gram INTEGER NOT NULL CHECK (weight_in_gram > 0),

    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
        REFERENCES category(id)
);

CREATE INDEX idx_product_category_id
    ON product(category_id);

CREATE TABLE outbox_event
(
    id UUID PRIMARY KEY,

    aggregate_id UUID NOT NULL,

    aggregate_type VARCHAR(100) NOT NULL,

    event_type VARCHAR(100) NOT NULL,

    payload JSONB NOT NULL,

    published BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMPTZ NOT NULL,

    published_at TIMESTAMPTZ
);

CREATE INDEX idx_outbox_published
    ON outbox_event(published);

CREATE INDEX idx_outbox_created_at
    ON outbox_event(created_at);