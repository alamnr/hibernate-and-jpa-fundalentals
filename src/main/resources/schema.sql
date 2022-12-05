
create database bookstore;
use bookstore;

create table publisher(
	code varchar(4) not null,
    publisher_name varchar(100) not null,
    primary key (code)
);

create table book(
	isbn varchar(50) not null,
    book_name varchar(100) not null,
    publisher_code varchar(4) not null,
    primary key(isbn),
    foreign key (publisher_code) references publisher (code)
);

create table chapter(
	book_isbn varchar(50) not null,
    chapter_num int not null,
    title varchar(100) not null,
    primary key (book_isbn,chapter_num),
    foreign key (book_isbn) references book (isbn)

);


create database hello-world;
use hello-world;
create table message (
    id bigint(20) not null auto_increment,
    text varchar(255) null default null,
    primary key(id)
);


