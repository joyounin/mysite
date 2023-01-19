-- user
desc user;
select no, name, email, password, gender, join_date from user;

-- join
insert into user values(null, '헹헹헹', 'msh372@naver.com', password('1234'), 'male', now());

-- login
select no, name from user where '3723519@naver.com' and password = password('1234');

-- update
update user set no = 1, name = '안눙하세요', gender = "female" where no= 1 and name = '조영인' and gender = 'male';

update book set rent = 'Y' where no = ?;