SET search_path TO inventory;

CREATE TABLE stock_position
(
    id UUID PRIMARY KEY,

    aisle VARCHAR(10) NOT NULL,

    rack VARCHAR(10) NOT NULL,

    bin VARCHAR(10) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL,

    updated_at TIMESTAMPTZ,

    deleted_at TIMESTAMPTZ,

    CONSTRAINT uq_stock_position_location
        UNIQUE (aisle, rack, bin)
);

CREATE TABLE stock_unit
(
    id UUID PRIMARY KEY,

    product_id UUID NOT NULL UNIQUE,

    stock_position_id UUID NOT NULL,

    total_stock INTEGER NOT NULL
        CHECK (total_stock >= 0),

    reserved_stock INTEGER NOT NULL DEFAULT 0
        CHECK (reserved_stock >= 0),

    inbound_stock INTEGER NOT NULL DEFAULT 0
        CHECK (inbound_stock >= 0),

    created_at TIMESTAMPTZ NOT NULL,

    updated_at TIMESTAMPTZ,

    deleted_at TIMESTAMPTZ,

    version BIGINT NOT NULL DEFAULT 0,

    CONSTRAINT uq_stock_unit_product
        UNIQUE (product_id),

    CONSTRAINT uq_stock_unit_position
        UNIQUE (stock_position_id),

    CONSTRAINT fk_stock_unit_position
        FOREIGN KEY (stock_position_id)
        REFERENCES stock_position(id)
);

CREATE TABLE inbox_event
(
    id UUID PRIMARY KEY,

    event_id UUID NOT NULL UNIQUE,

    event_type VARCHAR(100) NOT NULL,

    payload JSONB NOT NULL,

    processed BOOLEAN NOT NULL DEFAULT FALSE,

    received_at TIMESTAMPTZ NOT NULL,

    processed_at TIMESTAMPTZ
);

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

CREATE INDEX idx_stock_unit_product_id
    ON stock_unit(product_id);

CREATE INDEX idx_stock_position_location
    ON stock_position(aisle, rack, bin);

CREATE INDEX idx_stock_unit_position_id
    ON stock_unit(stock_position_id);

CREATE INDEX idx_outbox_published
    ON outbox_event(published);

CREATE INDEX idx_outbox_created_at
    ON outbox_event(created_at);

CREATE INDEX idx_inbox_processed
    ON inbox_event(processed);