create table suppliers(
    id bigint auto_increment,
    name varchar(255) not null,
    country varchar(255) not null,
    address varchar(255) not null,
    currency varchar(5) not null,
    primary key (id)
)