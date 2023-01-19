-- user
desc user;
select * from user;
-- join
insert into user values(null, '정휘윤', 'gnldbs@gmail.com', password('1234'), 'male', now());


-- login
select no, name from user where email='gnldbs@gmail.com' and password = password('1234');