CREATE TABLE IF NOT EXISTS customer(
    id uuid default random_uuid() PRIMARY KEY,
    name text,
    email text,
    person_type varchar(2),
    dat_create timestamp not null,
    dat_update timestamp
);