-- 테이블 삭제
drop table TOM_TAGS;
drop table TOM_BOARD_ATTACH;
drop table TOM_REPLY;
drop table TOM_SIREN;
drop table TOM_TAG_BOARD;
drop table TOM_TAG_USER;
drop table TOM_BOARD;
drop table TOM_MEMBER_INFO;
-- 시퀀스 삭제
DROP SEQUENCE seq_TOM_TAGS;
DROP SEQUENCE seq_TOM_MEMBER_INFO;
DROP SEQUENCE seq_TOM_TAG_USER;
DROP SEQUENCE seq_TOM_BOARD;
DROP SEQUENCE seq_TOM_BOARD_attach;
DROP SEQUENCE seq_TOM_reply;
DROP SEQUENCE seq_tom_tag_board;
DROP SEQUENCE seq_TOM_siren;

-- 테이블 생성
create table tom_profile_attach (
    uno number not null primary key,
    uuid varchar2(100) not null,
    filepath varchar2(100) not null,
    filename varchar2(100) not null
);

Create table TOM_TAGS(
    tno number not null primary key,
    tags varchar2(100) not null
--    CONSTRAINT FK_TAG_USER_UNO FOREIGN KEY(UNO) REFERENCES TOM_MEMBER_INFO(UNO)
);
CREATE SEQUENCE seq_TOM_TAGS INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE nocache;

Create table TOM_MEMBER_INFO (
    uno number not null primary key,
    userid varchar2(50) not null,
    userpw varchar2(100) not null,
    nickname varchar2(100) not null,
    regdate date default sysdate not null, 
    enabled char(1) default '1',
    email varchar2(100) not null,
    introduce varchar2(100)
);
CREATE SEQUENCE seq_TOM_MEMBER_INFO INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE nocache ;

Create table TOM_TAG_USER(
    tuno number not null primary key,
    UNO number not null,
    tno number not null,
    leader char(1) default '0' not null
--    CONSTRAINT FK_TAG_USER_UNO FOREIGN KEY(UNO) REFERENCES TOM_MEMBER_INFO(UNO)
);
CREATE SEQUENCE seq_TOM_TAG_USER INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE nocache;

Create table TOM_BOARD(
    bno number not null primary key,
    hitcnt number default '0' not null,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer number not null,
    regdate date default sysdate, 
    replycnt number default '0' not null,
    boardup number default '0' not null,
    boarddown number default '0' not null,
    replyenabled char(1) default '1',
    deletedyn char(1) default 'N' not null
--    CONSTRAINT FK_BOARD_writer FOREIGN KEY(writer) REFERENCES TOM_MEMBER_INFO(uno)
);
CREATE SEQUENCE seq_TOM_BOARD INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE nocache;

Create table TOM_BOARD_attach(
    bano number not null primary key,
    bno number not null,
    originalname varchar2(300) not null,
    savename varchar2(100) not null,
    filesize NUMBER not null,
    deletedyn char(1) default 'N' not null,
    regdate date default sysdate not null
--    CONSTRAINT FK_BOARD_attach_bno FOREIGN KEY(bno) REFERENCES TOM_BOARD(bno)
);
CREATE SEQUENCE seq_TOM_BOARD_attach INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE nocache;

create table TOM_REPLY (
	rno number not null primary key,
	bno number not null,
	reply varchar2(1000) not null,
	replyer number not null,
	regdate date default sysdate,
    replyup number default '0' not null,
    replydown number default '0' not null,
	deletedyn char(1) default 'N' not null
--    CONSTRAINT FK_REPLY_bno FOREIGN KEY(bno) REFERENCES TOM_BOARD(bno),
--    CONSTRAINT FK_REPLY_replyer FOREIGN KEY(replyer) REFERENCES TOM_MEMBER_INFO(uno)
);
CREATE SEQUENCE seq_TOM_reply INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE nocache ;
-- insert into TOM_REPLY(rno) values(seq_TOM_reply.nextval);

-- create table TOM_CHAT_INFO (
--    UNO varchar2(50) not null,
--    other varchar2(50) not null,
--    threedays_chat brob
-- );

create table tom_tag_board (
    tbno number not null primary key,
    bno number not null,
    tno number not null
--    constraint fk_tag_board_bno foreign key(bno) references tom_board(bno)
);
CREATE SEQUENCE seq_tom_tag_board INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE nocache ;

create table tom_siren(
    sno number not null primary key,
    kicker number not null,
    kicked number not null,
    reason varchar2(1000) not null,
    kickloc varchar2(100) not null
--    CONSTRAINT FK_UNO_kickker FOREIGN KEY(kicker) REFERENCES TOM_MEMBER_INFO(uno),
--    CONSTRAINT FK_UNO_kicked FOREIGN KEY(kicked) REFERENCES TOM_MEMBER_INFO(UNO)
);
CREATE SEQUENCE seq_TOM_siren INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE nocache;
-- insert into tom_siren(sno) values(seq_TOM_siren.nextval);

-- 시퀀스 마지막 번호 조회
SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_TOM_SIREN';

commit;

-- 더미데이터
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '필통');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '운동화');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '안경');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '수첩');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '책');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '텀블러');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '포크');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '샐러드');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '로션');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '아이폰');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '숟가락');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '모니터');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '나이키');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '수첩');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '쓰레기');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '커피');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '가죽필통');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '저렴이');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '아디다스');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '광고');
insert into tom_tags
values (SEQ_TOM_TAGS.NEXTVAL, '나이키광고');

insert into tom_member_info (UNO, USERID, USERPW, NICKNAME, EMAIL, INTRODUCE)
values (SEQ_tom_member_info.NEXTVAL, 'user1', '0001', '유저1', 'user1@gmail.com', '안녕하세요, 유저1입니다.');
insert into tom_member_info (UNO, USERID, USERPW, NICKNAME, EMAIL)
values (SEQ_tom_member_info.NEXTVAL, 'user2', '0002', '유저2', 'user2@gmail.com');
insert into tom_member_info (UNO, USERID, USERPW, NICKNAME, EMAIL, INTRODUCE)
values (SEQ_tom_member_info.NEXTVAL, 'user3', '0003', '유저3', 'user3@gmail.com', '안녕하세요, 유저3입니다.');
insert into tom_member_info (UNO, USERID, USERPW, NICKNAME, EMAIL)
values (SEQ_tom_member_info.NEXTVAL, 'user4', '0004', '유저4', 'user4@gmail.com');
insert into tom_member_info (UNO, USERID, USERPW, NICKNAME, EMAIL, INTRODUCE)
values (SEQ_tom_member_info.NEXTVAL, 'user5', '0005', '유저5', 'user5@gmail.com', '안녕하세요, 유저5입니다.');

insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 1, 1, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 1, 2, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 1, 3, DEFAULT);
--
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 2, 4, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 2, 1, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 2, 5, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 2, 6, DEFAULT);
--
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 3, 1, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 3, 2, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 3, 7, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 3, 8, DEFAULT);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 3, 9, DEFAULT);
--
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 4, 8, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 4, 6, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 4, 10, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 4, 11, DEFAULT);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 4, 12, DEFAULT);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 4, 13, DEFAULT);
--
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 5, 10, 1);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 5, 14, DEFAULT);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 5, 3, DEFAULT);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 5, 8, DEFAULT);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 5, 12, DEFAULT);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 5, 15, DEFAULT);
insert into tom_tag_user
values (SEQ_tom_tag_user.NEXTVAL, 5, 16, DEFAULT);

insert into tom_siren
values (SEQ_TOM_SIREN.nextval, 1, 5, '욕설', '댓글');
insert into tom_siren
values (SEQ_TOM_SIREN.nextval, 2, 5, '불쾌감을 주는 컨텐츠', '게시글');

insert into tom_board (bno, title, CONTENT, writer)
values (1, '가죽 필통 추천해줘', '나 가죽 필통 사려고 하는데 추천 좀. 너무 비싼 거 말고.', 1);
insert into tom_board (bno, title, CONTENT, writer)
values (2, '나이키 이번 광고 잘 뽑은듯', '역시 나이키가 광고 하나는 잘 뽑음. 아디다스 광고는 대체로 ㅂㄹ..', 4);
insert into tom_board (bno, title, CONTENT, writer)
values (3, '여기 다 잠수냐', 'ㅉㅉ뭐하냐', 5);

insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 1, 1);
insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 1, 17);
insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 1, 18);
--
insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 2, 13);
insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 2, 19);
insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 2, 20);
insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 2, 21);
--
insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 3, 18);
insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 3, 15);
insert into tom_tag_board
values (SEQ_tom_tag_board.nextval, 3, 20);

insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, 1, '가죽필통 말고 가죽 운동화는 추천 가능', 3);
insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, 1, '걍 다이소에서 사', 4);
--
insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, 2, '나이키에서 텀블러는 안 만드냐', 2);
--
insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, 3, '어쩌라고', 1);
insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, 3, 'ㅗㅗㅗㅗㅗㅗ', 5);

create or replace NONEDITIONABLE function set_file_no
return number is
file_no tom_board_attach.bano%type;
begin 
    select seq_tom_board_attach.nextval
    into file_no
    from dual;
    return file_no;
end;

commit;