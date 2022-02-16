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



create table flight
(
    id           serial primary key,
    destination  varchar(255),
    fromThis     varchar(255),
    serialNumber varchar(255),
    flightDate   date,
    flightTime   time
);


create table passenger
(
    id       serial primary key,
    username varchar(45),
    password varchar(255),
    email    varchar(255)
);

create table passenger_flight
(
    passenger_id bigint,
    flight_id    bigint,

    CONSTRAINT fk_passenger
        FOREIGN KEY (passenger_id)
            REFERENCES passenger (id),
    CONSTRAINT fk_flight
        FOREIGN KEY (flight_id)
            REFERENCES flight (id)
);

create table passenger_friend
(
    passenger_id bigint,

    friend_id    bigint,
    CONSTRAINT fk_passenger
        FOREIGN KEY (passenger_id)
            REFERENCES passenger (id),
    constraint fk_friend foreign key (friend_id) references friend (id)
);
create table friend
(
    id       serial primary key,
    name     varchar(45),
    lastname varchar(45)
);
insert into passenger(username, password, email)
VALUES ('ulvi', '12345', 'ulvi@ulvi.com');
insert into passenger(username, password, email)
VALUES ('ulvi2', '123456', 'ulvi2@ulvi.com');
insert into passenger(username, password, email)
VALUES ('arif', '123456', 'ulvi2@ulvi.com');

select *
from passenger;
select *
from flight;

insert into flight(destination, fromThis, serialNumber)
VALUES ('Baku', 'Kiev', 123);
insert into flight(destination, fromThis, serialNumber)
VALUES ('Ganja', 'Kiev', 124);



select p.username, p.password, f.destination
from passenger p,
     flight f,
     passenger_flight_friend pff
         inner join friend fr on pff.friend_id = fr.id
where p.id = pff.passenger_id
  and f.id = pff.flight_id;

Alter table flight
    add column passenger_count int;

insert into flight(destination, fromThis, serialNumber, flightDate, passenger_count)
values ('Sumqayit', 'Kiev', 125, '10/02/2022', 35);

insert into flight(destination, fromThis, serialNumber, flightDate, passenger_count)
values ('Sumqayit', 'Kiev', 129, '18/02/2022', 22);

insert into flight(destination, fromThis, serialNumber, flightDate, passenger_count)
values ('Mingecevir', 'Kiev', 126, '10/03/2022', 88);





