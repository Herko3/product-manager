create table products(
    id bigint auto_increment,
    supplier_id bigint not null,
    article_number varchar(20) not null,
    name varchar(255) not null,
    description varchar(255),
    type varchar(25) not null,
    net_price bigint not null,
    gross_price bigint not null,
    primary key (id)
)