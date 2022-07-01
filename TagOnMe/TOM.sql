Create table TOM_MEMBER_INFO (
    userid varchar2(50) not null primary key,
    userpw varchar2(100) not null,
    nickname varchar2(100) not null,
    regdate date default sysdate not null, 
    enabled char(1) default '1',
    email varchar2(100) not null,
    introduce varchar2(100)
);

Create table TOM_TAG_USER(
    trbno number not null primary key,
    userid varchar2(50) not null,
    usertag varchar2(100) not null,
    leader char(1) default '0',
    CONSTRAINT FK_TAG_USER_leader FOREIGN KEY(userid) REFERENCES TOM_MEMBER_INFO(userid)
);

CREATE SEQUENCE seq_TOM_TAG_USER INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE ;

Create table TOM_BOARD(
    bno number not null primary key,
    hitcnt number default '0' not null,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate, 
    replycnt number default '0' not null,
    boardup number default '0' not null,
    boarddown number default '0' not null,
    replyenabled char(1) default '1',
    deletedyn char(1) default 'N',
    
    CONSTRAINT FK_BOARD_writer FOREIGN KEY(writer) REFERENCES TOM_MEMBER_INFO(userid)
);

Create table TOM_BOARD_attach(
    bno number not null,
    uuid varchar2(100) not null primary key,
    uploadpath varchar2(200) not null,
    filename varchar2(100) not null,
    
    CONSTRAINT FK_BOARD_attach_bno FOREIGN KEY(bno) REFERENCES TOM_BOARD(bno)
);

create table TOM_REPLY (
	rno number not null primary key,
	bno number not null,
	reply varchar2(1000) not null,
	replyer varchar2(50) not null,
	replyDate date default sysdate,
    replyup number default '0' not null,
    replydown number default '0' not null,
    
    CONSTRAINT FK_REPLY_bno FOREIGN KEY(bno) REFERENCES TOM_BOARD(bno),
    CONSTRAINT FK_REPLY_replyer FOREIGN KEY(replyer) REFERENCES TOM_MEMBER_INFO(userid)
);

CREATE SEQUENCE seq_TOM_reply INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE ;
-- insert into TOM_REPLY(rno) values(seq_TOM_reply.nextval);


-- create table TOM_CHAT_INFO (
--    userid varchar2(50) not null,
--    other varchar2(50) not null,
--    threedays_chat brob
-- );

create table tom_tag_board (
    tbno number not null primary key,
    bno number(10) not null,
    boardtag varchar2(100) not null,
    constraint fk_tag_board_bno foreign key(bno) references tom_board(bno)
);

CREATE SEQUENCE seq_tom_tag_board INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE ;

create table tom_siren(
    sno number not null primary key,
    kicker varchar2(100) not null,
    kicked varchar2(100) not null,
    reason varchar2(1000) not null,
    kickloc varchar2(100) not null, 
    CONSTRAINT FK_USERID_kickker FOREIGN KEY(kicker) REFERENCES TOM_MEMBER_INFO(userid),
    CONSTRAINT FK_USERID_kicked FOREIGN KEY(kicked) REFERENCES TOM_MEMBER_INFO(userid)
   
);


CREATE SEQUENCE seq_TOM_siren INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCYCLE;
-- insert into tom_siren(sno) values(seq_TOM_siren.nextval);

SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_TOM_SIREN';

drop table tom_board;
drop table tom_board_attach;
drop table tom_member_info;
drop table tom_reply;
drop table tom_siren;
drop table tom_tag_board;
drop table tom_tag_user;

commit;
