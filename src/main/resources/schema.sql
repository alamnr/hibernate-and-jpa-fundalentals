
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







# for appending to console
apenders= console, file
appender.console.type = Console
appender.console.name = STDOUT
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n

# for appending to appender.file.name = LOGFILE
appender.file.type = File
appender.file.name = File
appender.file.fileName=logs/log4j.log
appender.file.layout.type=PatternLayout
appender.file.layout.pattern=[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n
appender.file.filter.threshold.type = ThresholdFilter
appender.file.filter.threshold.level = info

rootLogger.level = info
rootLogger.appenderRefs = stdout
rootLogger.appenderRef.stdout.ref = STDOUT
rootLogger.appenderRef.file.ref = File

#log level OFEWIDTA

# log everything   
logger.org.hibernate = ALL

# show sql 
logger.org.hibernate.SQL = ALL

# show the bind parameter values
logger.org.hibernate.Type.descriptor.sql.BasicBinder = TRACE




################################################################33

name=PropertiesConfig
property.filename = logs
appenders = console, file

#logging on the console
appender.console.type = Console
appender.console.name = STDOUT
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n
#logging on the file
appender.file.type = File
appender.file.name = LOGFILE
#appender.file.fileName=${filename}/Mylogs.log
appender.file.fileName=logs/Mylogs.log
appender.file.layout.type=PatternLayout
appender.file.layout.pattern=[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n
appender.file.append=true
loggers=file
logger.file.name= com
logger.file.level = debug
logger.file.appenderRefs = file
logger.file.appenderRef.file.ref = LOGFILE
rootLogger.level = debug
rootLogger.appenderRefs = stdout
rootLogger.appenderRef.stdout.ref = STDOUT
