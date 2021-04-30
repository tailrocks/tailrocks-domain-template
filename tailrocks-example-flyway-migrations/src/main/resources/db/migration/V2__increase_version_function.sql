CREATE FUNCTION increase_version() RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.version = NEW.version
    THEN
        NEW.version = OLD.version + 1;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
