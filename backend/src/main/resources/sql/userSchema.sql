# users 테이블
create table users
(
    user_no  bigint auto_increment comment '회원번호(pk)'
        primary key,
    username varchar(50)                      not null comment '유저아이디',
    password varchar(100)                     not null comment '비밀번호',
    role     varchar(100) default 'ROLE_USER' not null comment '회원 role',
    constraint users_pk2
        unique (username)
)
    comment '회원테이블';
