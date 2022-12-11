CREATE TABLE member (
    member_id bigint primary key auto_increment,
    firebase_token varchar(255) not null,
    nickname varchar(255) not null,

    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp
);
create index idx_member_createdat on member(created_at);

CREATE TABLE alarm (
    alarm_id bigint primary key auto_increment,
    member_id bigint not null,

    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp
);
create index idx_alarm_createdat on alarm(created_at);

CREATE TABLE push (
    push_id bigint primary key auto_increment,
    alarm_id bigint not null,
    firebase_token varchar(255) not null,
    day_of_week varchar(10) not null,
    push_time time not null,
    exclude_holiday_yn varchar(1) not null,

    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp
);
create index idx_push_pushtime on push(push_time);
create index idx_push_dayofweek on push(day_of_week);
create index idx_push_excludeholiday on push(exclude_holiday_yn);
create index idx_push_createdat on push(created_at);