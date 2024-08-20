drop table if exists users;
create table users(
    id bigserial primary key,
    userName varchar not null,
    password varchar not null
);
drop table if exists messages;
create table messages(
    id bigserial primary key,
    sender bigint not null,
    text text not null,
    time timestamp not null
);