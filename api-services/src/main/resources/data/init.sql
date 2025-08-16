CREATE TABLE products
(
    product_id     VARCHAR(255) NOT NULL PRIMARY KEY,
    name           VARCHAR(1000) NOT NULL,
    description    TEXT,
    price          JSONB,
    category       VARCHAR(200),
    brand          VARCHAR(100),
    stock          JSONB,
    sku            VARCHAR(100),
    tags           JSONB,
    images         JSONB,
    specifications JSONB,
    created_at     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    product_index  VARCHAR(100),
    store_id       VARCHAR(100) NOT NULL
);