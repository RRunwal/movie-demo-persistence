create table movies(
	movie_id bigint(20) not null auto_increment primary key,
	imdbid varchar(255),
	actors varchar(255),
	director varchar(255),
	title varchar(255),
	year varchar(4), 
	released date
)