-- user
desc user;
select no, name, email, password, gender, join_date from user;
select no, name, password, gender from user where no = 2;

-- join
insert into user values(null, '헹헹헹', 'msh372@naver.com', password('1234'), 'male', now());

-- login
select no, name from user where '3723519@naver.com' and password = password('1234');

-- update
update user set no = 1, name = '안눙하세요', gender = "female" where no= 1;

update book set rent = 'Y' where no = ?;

delete from user;

select count(*) from board;

desc board;
delete from board where user_no=9 and no=178;
select no, title, contents, user_no as userno, g_no, o_no, depth, hit 
			  from board 
		 	 where no = 178
		 	   and user_no = 9;
desc user;
select * from site;
insert into user values(null, "관리자", "admin@mysite.com", password('1234'), 'male', "ADMIN", now());
alter table user add column role enum("ADMIN", "USER") default "USER" after gender;

 select no, name, password, message
    			   from guestbook
			   order by no desc
  			      limit 0, 5;

select no, name, password, message
	    				from guestbook 
	   				   where no < 30
					order by no desc
	   				   limit 0, 5;
