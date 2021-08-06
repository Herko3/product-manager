create table tenders (
    id bigint auto_increment,
    name varchar(255) not null,
    quotation_date date not null,
    primary key (id)
);

create table products_to_tenders (
    id bigint auto_increment,
    product_id  bigint,
    tender_id bigint,
    primary key (id),
    foreign key (product_id) references products(id) on delete cascade,
    foreign key (tender_id) references tenders(id) on delete cascade
)