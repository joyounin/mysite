-- user
desc user;

select no, name, email, password, gender, join_date from user;

insert into user values(null, '조영인', '3723519@naver.com', password('1234'), 'male', now());