CREATE TABLE supplier(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    comment varchar(100),
    cnpj varchar(20) not null unique,
    primary key (id)
);