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
    day_of_weeks varchar(255) not null,
    exclude_holiday varchar(1) not null default 'N',
    start_time time not null,
    end_time time not null,
    count bigint not null,

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

CREATE TABLE stretching_auth (
    stretching_auth_id bigint primary key auto_increment,
    member_id bigint not null,
    type varchar(32) not null,
    year int not null,
    month int not null,
    day int not null,
    auth_time time not null,

    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp
)
create index idx_stretchingauth_memberid on stretching_auth(member_id);
create index idx_stretchingauth_year on stretching_auth(year);
create index idx_stretchingauth_month on stretching_auth(month);
create index idx_stretchingauth_day on stretching_auth(day);
create index idx_stretchingauth_createdat on stretching_auth(created_at);