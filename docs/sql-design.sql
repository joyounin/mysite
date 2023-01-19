-- user
desc user;
select no, name, email, password, gender, join_date from user;

-- join
insert into user values(null, '조영인', '3723519@naver.com', password('1234'), 'male', now());

-- login
select no, name from user where '3723519@naver.com' and password = password('1234');