drop table if exists words.entry;
create table entry (
    id bigint not null auto_increment,
    original varchar(35),
    translation varchar(35),
    primary key (id)
);
