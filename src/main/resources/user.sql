create table users
(
    id      serial primary key,
    name    varchar(45),
    surname varchar(45),
    email   varchar(45)
);

create table flights
(
    id          serial primary key,
    fromCity    varchar(255),
    destination varchar(255),
    flightDate  date,
    flightTime  time,
    seats       int
);