CREATE TABLE `user`(
	id bigint(64) not null,
	name varchar(20) not null,
	age TINYINT ,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;