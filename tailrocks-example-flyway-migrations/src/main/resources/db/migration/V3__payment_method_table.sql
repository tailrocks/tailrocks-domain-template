CREATE TYPE "payment_method_card_brand" AS ENUM ('VISA', 'MASTERCARD');

CREATE TABLE payment_method (
    id                   CHAR(24)                                  NOT NULL,
    created_date         TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    last_modified_date   TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    version              BIGINT                      DEFAULT 0     NOT NULL,

    account_id           CHAR(24)                                  NOT NULL,
    card_brand           PAYMENT_METHOD_CARD_BRAND,
    card_expiration_date DATE,
    card_number          CHARACTER VARYING(255),
    card_holder_name     CHARACTER VARYING(255),

    CONSTRAINT uk_payment_method_account_id_card_number UNIQUE (account_id, card_number),
    PRIMARY KEY (id)
);

CREATE TRIGGER update_last_modified_date
    BEFORE UPDATE
    ON payment_method
    FOR EACH ROW
EXECUTE PROCEDURE update_last_modified_date();

CREATE TRIGGER increase_version
    BEFORE UPDATE
    ON payment_method
    FOR EACH ROW
EXECUTE PROCEDURE increase_version();
