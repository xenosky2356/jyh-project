insert into tom_member_info (USERID, USERPW, NICKNAME, EMAIL, INTRODUCE)
values ('user1', '0001', '유저1', 'user1@gmail.com', '안녕하세요, 유저1입니다.');
insert into tom_member_info (USERID, USERPW, NICKNAME, EMAIL)
values ('user2', '0002', '유저2', 'user2@gmail.com');
insert into tom_member_info (USERID, USERPW, NICKNAME, EMAIL, INTRODUCE)
values ('user3', '0003', '유저3', 'user3@gmail.com', '안녕하세요, 유저3입니다.');
insert into tom_member_info (USERID, USERPW, NICKNAME, EMAIL)
values ('user4', '0004', '유저4', 'user4@gmail.com');
insert into tom_member_info (USERID, USERPW, NICKNAME, EMAIL, INTRODUCE)
values ('user5', '0005', '유저5', 'user5@gmail.com', '안녕하세요, 유저5입니다.');

insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user1', '필통', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user1', '운동화', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user1', '안경', DEFAULT);
--
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user2', '수첩', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user2', '필통', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user2', '책', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user2', '텀블러', DEFAULT);
--
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user3', '필통', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user3', '운동화', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user3', '포크', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user3', '샐러드', DEFAULT);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user3', '로션', DEFAULT);
--
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user4', '샐러드', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user4', '텀블러', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user4', '아이폰', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user4', '숟가락', DEFAULT);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user4', '모니터', DEFAULT);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user4', '나이키', DEFAULT);
--
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user5', '아이폰', 1);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user5', '수첩', DEFAULT);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user5', '안경', DEFAULT);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user5', '샐러드', DEFAULT);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user5', '모니터', DEFAULT);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user5', '쓰레기', DEFAULT);
insert into tom_tag_user
values (SEQ_TOM_TAG_USER.NEXTVAL, 'user5', '커피', DEFAULT);

insert into tom_siren
values (SEQ_TOM_SIREN.nextval,'user1', 'user5', '욕설', '댓글');
insert into tom_siren
values (SEQ_TOM_SIREN.nextval,'user2', 'user5', '불쾌감을 주는 컨텐츠', '게시글');

insert into tom_board (bno, title, CONTENT, writer)
values (1, '가죽 필통 추천해줘', '나 가죽 필통 사려고 하는데 추천 좀. 너무 비싼 거 말고.', 'user1' );
insert into tom_board (bno, title, CONTENT, writer)
values (2, '나이키 이번 광고 잘 뽑은듯', '역시 나이키가 광고 하나는 잘 뽑음. 아디다스 광고는 대체로 ㅂㄹ..', 'user4' );
insert into tom_board (bno, title, CONTENT, writer)
values (3, '여기 다 잠수냐', 'ㅉㅉ뭐하냐', 'user5' );

insert into tom_tag_board
values (SEQ_TOM_TAG_BOARD.nextval, 1, '필통');
insert into tom_tag_board
values (SEQ_TOM_TAG_BOARD.nextval, 1, '가죽 필통');
insert into tom_tag_board
values (SEQ_TOM_TAG_BOARD.nextval, 1, '저렴이');
--
insert into tom_tag_board
values (SEQ_TOM_TAG_BOARD.nextval, 2, '나이키');
insert into tom_tag_board
values (SEQ_TOM_TAG_BOARD.nextval, 2, '아디다스');
insert into tom_tag_board
values (SEQ_TOM_TAG_BOARD.nextval, 2, '광고');
insert into tom_tag_board
values (SEQ_TOM_TAG_BOARD.nextval, 2, '나이키광고');

insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, '1', '가죽필통 말고 가죽 운동화는 추천 가능', 'user3');
insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, '1', '걍 다이소에서 사', 'user4');
--
insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, '2', '나이키에서 텀블러는 안 만드냐', 'user2');
--
insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, '3', '어쩌라고; 너나 잘해', 'user1');
insert into tom_reply (rno, bno, reply, replyer)
values (SEQ_TOM_REPLY.nextval, '3', '어쪠례궤~~~눼나 쨜훼~~~~~~ㅋㅋㅋㅋㅋㅋㅋ', 'user5');





