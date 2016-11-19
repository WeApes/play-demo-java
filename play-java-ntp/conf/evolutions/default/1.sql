# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book_messages (
  id                            integer auto_increment not null,
  book_name                     varchar(255),
  book_url                      varchar(255),
  author                        varchar(255),
  book_price                    varchar(255),
  constraint pk_book_messages primary key (id)
);

create table cf_books (
  id                            integer auto_increment not null,
  book_name                     varchar(255),
  book_url                      varchar(255),
  book_type                     varchar(255),
  book_price                    varchar(255),
  constraint pk_cf_books primary key (id)
);


# --- !Downs

drop table if exists book_messages;

drop table if exists cf_books;

