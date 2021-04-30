CREATE FUNCTION update_last_modified_date() RETURNS TRIGGER AS
$$
BEGIN
    NEW.last_modified_date := NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
