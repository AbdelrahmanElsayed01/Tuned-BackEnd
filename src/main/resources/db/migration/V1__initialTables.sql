create table playlist
(
    id      bigint not null auto_increment,
    user_id bigint,
    primary key (id)
) engine=InnoDB;
create table playlist_song
(
    id           bigint not null auto_increment,
    user         bigint,
    playlists_id bigint,
    songs_id     bigint,
    primary key (id)
) engine=InnoDB;
create table song
(
    id       bigint not null auto_increment,
    artist   varchar(255),
    duration varchar(255),
    image    varchar(255),
    title    varchar(255),
    uri      varchar(255),
    primary key (id)
) engine=InnoDB;
create table users
(
    id           bigint not null auto_increment,
    email        varchar(255),
    name         varchar(100),
    password     varchar(255),
    subscription enum ('free','premium'),
    username     varchar(100),
    primary key (id)
) engine=InnoDB;
alter table playlist
    add constraint UK_d0niaimds3crylblpqf6nha82 unique (user_id);
alter table playlist
    add constraint FKj8q80g2puy49wsp49p3taqllv foreign key (user_id) references users (id);
alter table playlist_song
    add constraint `FK4j9sol2d42wc2tproyjsst93l` foreign key (playlists_id) references playlist (id);
alter table playlist_song
    add constraint FKdrsftmay1io58gdvp8mko9jmh foreign key (songs_id) references song (id);
