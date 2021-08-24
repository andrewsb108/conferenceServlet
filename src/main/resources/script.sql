create table if not exists users
(
    id         int primary key                       not null auto_increment,
    first_name varchar(30)                           not null,
    last_name  varchar(30)                           not null,
    email      varchar(50)                           not null,
    password   varchar(100)                          not null,
    enabled    boolean                default 1      not null,
    role       enum ('USER', 'MODERATOR, SPEAKER') default 'USER' not null,
    unique (email)
);