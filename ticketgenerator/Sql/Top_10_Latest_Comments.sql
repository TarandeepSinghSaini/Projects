if object_id('comment') is not null drop table comment
if object_id('post') is not null drop table post
if object_id('author') is not null drop table author
if object_id('users') is not null drop table users

create table author (
id int primary key,
name varchar(100)
)

create table post(
id int primary key,
name varchar(100),
authorid int,--foreign key references id of table author,
createdts datetime,
foreign key (authorid) REFERENCES author(id)

)


create table users(
id int primary key,
name varchar(100))


create table comment (
id int primary key,
content varchar(1000),
postid int,
createdts datetime,
userid int,
foreign key (postid) references post(id),
foreign key (userid) references users(id)
)


insert into author values
(1,'James Bond'),
(2,'Tom Cruise'),
(3,'Jimmy Fallon')

insert into post(id, name, authorid, createdts) values
(1, 'Silence of Ocean', 1, convert(date,'20210315',112)),
(2, 'Unbreakable bridges', 1, convert(date,'20220107',112)),
(3, 'Fire in the Jungle', 3, convert(date,'20220322',112)),
(4, 'Mission I''m possible ', 2, convert(date,'20220404',112))

insert into users(id, name) values
(1, 'Kate Bishop'),
(2, 'Akshay Devgan'),
(3, 'Imran khan'),
(4, 'Tarandeep singh')

insert into comment(id,content,postid,createdts,userid) values
(1,'Awesome post!!', 1, convert(date,'20210315',112),3),
(2,'OP in the chat :)', 1, convert(date,'20210316',112),2),
(3,'Cool post', 2, convert(date,'20220107',112),2),
(4,'Jimmy rocks evertime', 3, convert(date,'20220310',112),4),
(5,'What a rubbish post?', 1, convert(date,'20220321',112),1),
(6,'Good going Sir', 2, convert(date,'20220321',112),4),
(7,'@Kate keep yourself shut', 1, convert(date,'20220321',112),2),
(8,'@Akshay mind your own business', 1, convert(date,'20220321',112),1),
(9,'@Kate this is my business to support my favorite author', 1, convert(date,'20220322',112),2),
(10,'GREAT!', 3, convert(date,'20220322',112),3),
(11,'@Akshay @Kate Calm down guys, just a post!', 1, convert(date,'20220323',112),4),
(12,'Tom is back in action!', 4, convert(date,'20220323',112),3),
(13,'Muah!! Tommy my fav', 4, convert(date,'20220324',112),1),
(14,'Nice work this time!!', 2, convert(date,'20220324',112),1),
(15,'Another great post!', 2, convert(date,'20220321',112),3)

select top 10 p.name as Post, a.name as "Author name", c.content as Comment, u.name as "Comment Poster", convert(date,c.createdts,112) as "Comment date" 
from comment c
inner join post p on p.id = c.postid
inner join users u on u.id = c.userid
inner join author a on a.id = p.authorid
where a.name = 'James Bond'
order by c.createdts desc, c.id desc 