    create table codigo (
        id integer not null auto_increment,
        codigo varchar(100), 
        telefone varchar(100),       
        primary key (id)
    ) engine=InnoDB default charset=utf8MB4;