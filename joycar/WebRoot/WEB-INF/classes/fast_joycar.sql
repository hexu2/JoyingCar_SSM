-------------------------------------登录system账号操作--------------------------------


select * from t_admin

delete from t_admin where admin_id  = 41;
commit;
--1.为该项目创建表空间
create tablespace joycar
datafile 'E:\oracle\tablespace\joycar.dbf' size 4M autoextend on next 4M;

--2.创建工程用户名：joycar 密码：joycar
create user joycar identified by joycar
       default tablespace joycar;

--3.给用户赋权限
grant create view, connect,resource to joycar;

-------------------------------------登录joy账号操作--------------------------------
--1.管理员表
drop table t_admin;
create table t_admin(
       admin_Id number(30)  primary key,--管理员id
       admin_account varchar2(30),--管理员账号
       admin_password varchar2(30),--管理员账号密码
       account_status char(1),--账号状态
       create_time date,--创建时间
       modified_time date--最近一次修改时间
);
drop sequence seq_admin;   
create sequence seq_admin;

insert into t_admin(admin_Id,admin_account,admin_password,account_status,create_time,modified_time)
       values(seq_admin.nextval,'admin','admin',0,sysdate,sysdate);
insert into t_admin(admin_Id,admin_account,admin_password,account_status,create_time,modified_time)
       values(seq_admin.nextval,'admin1','admin1',0,sysdate,sysdate);
commit;
--创建视图
drop view v$admin;
create view v$admin
as select
       admin_Id,
       admin_account,
       admin_password,
       account_status,
       account_status_name,
       create_time,
       modified_time,
       rownum as rn
       from(
            select
                   admin_Id,
                   admin_account,
                   admin_password,
                   account_status,
                   create_time,
                   modified_time,
                   account_status_name,
                   rownum as rn
                   from(
                        select 
                               a.admin_Id,
                               a.admin_account,
                               a.admin_password,
                               a.account_status,
                               a.create_time,
                               a.modified_time,
                               c.config_value as account_status_name
                               from t_admin a, t_config c
                               where a.account_status = c.config_key and c.config_type = 'admin_account_status'
                               order by admin_id asc
                   )
       );
--2.用户表
drop table t_user;
create table t_user(
       user_Id number(30)  primary key,--用户id
       user_account varchar2(30),--用户账号
       user_password varchar2(30),--用户密码
       user_picture varchar2(50),--用户头像
       phone_number varchar2(30),--手机号码
       car_number varchar2(30),--车牌号
       account_status char(1),--账号状态
       create_time date,--创建时间
       modified_time date--最近一次修改时间
);
comment on table t_user 
    is 'user table';
comment on column t_user .user_Id
    is 'user ID';
comment on column t_user .user_account
    is 'user account';
comment on column t_user .user_password
    is 'user password';
comment on column t_user .user_picture
    is 'user picture';
comment on column t_user .phone_number
    is 'phone number';
comment on column t_user .car_number
    is 'car number';
comment on column t_user .account_status
    is 'user status zero:yes one:no';
comment on column t_user .create_time
    is 'create time';
comment on column t_user .modified_time
    is 'modified time';

drop sequence seq_user;
create sequence seq_user;
insert into t_user(user_Id,user_account,user_password,user_picture,phone_number,car_number,account_status,create_time,modified_time)
       values(seq_user.nextval,'hexu','hexu','','15755843966','88888888','0',sysdate,add_months(sysdate, 12));
commit;

--创建user视图
drop view v$user;
create view v$user
as select
       user_Id,
       user_account,
       user_password,
       user_picture,
       phone_number,
       car_number,
       account_status,
       account_status_name,
       create_time,
       modified_time,
       phone_user_name,
       id_number,
       phone_user_sex,
       phone_user_sex_name,
       phone_user_age,
       phone_fee,
       phone_status,
       phone_status_name,
       rownum as rn
       from(
            select 
                   a.user_Id,
                   a.user_account,
                   a.user_password,
                   a.user_picture,
                   a.phone_number,
                   a.car_number,
                   a.account_status,
                   c.config_value as account_status_name,
                   a.create_time,
                   a.modified_time,
                   b.phone_user_name,
                   b.id_number,
                   b.phone_user_sex,
                   e.config_value as phone_user_sex_name,
                   b.phone_user_age,
                   b.phone_fee,
                   b.phone_status,
                   d.config_value as phone_status_name
                   from t_user a  
                   inner join t_phone_user b
                   on a.phone_number = b.phone_number
                   inner join t_config c
                   on c.config_type = 'user_account_status' and c.config_key = a.account_status
                   inner join t_config d
                   on d.config_type = 'phone_status' and d.config_key = b.phone_status
                   inner join t_config e
                   on e.config_type = 'user_sex' and e.config_key = b.phone_user_sex
                   order by a.user_Id asc
       );
       

--3.电话用户表
drop table t_phone_user;
create table t_phone_user(
       phone_user_id number(30),--手机用户id
       phone_number varchar2(30) primary key,--手机号码
       phone_user_name varchar2(30),--手机用户名
       phone_user_sex char(1),--手机用户性别
       phone_user_age number,--手机用户年龄
       id_number varchar2(30),--身份证后六位
       phone_fee number,--话费余额
       phone_status char(1),--手机状态
       create_time date,--创建时间
       modified_time date  --最近一次修改时间
);
comment on table t_phone_user
        is 'phone user table';
comment on column t_phone_user.phone_user_id
        is 'phone user id';
comment on column t_phone_user.phone_number
        is 'phone number';
comment on column t_phone_user.phone_user_name
        is 'phone user name';
comment on column t_phone_user.id_number
        is 'phone user id number';
comment on column t_phone_user.phone_user_sex
        is 'phone user sex';
comment on column t_phone_user.phone_user_age
        is 'phone user age';
comment on column t_phone_user.phone_fee
        is 'phone user phone fee';
comment on column t_phone_user.phone_status
        is 'phone user phone status';
comment on column t_phone_user.create_time
        is 'phone user create_time';
comment on column t_phone_user.modified_time
        is 'phone user modified_time';
drop sequence seq_phone_user;
create sequence seq_phone_user;
commit;

--创建视图
drop view v$phoneuser;
create view v$phoneuser
as select
   phone_user_id,
   phone_number,
   phone_user_name,
   id_number,
   phone_user_sex,
   phone_user_sex_name,
   phone_user_age,
   phone_fee,
   phone_status,
   phone_status_name,
   create_time,
   modified_time,
   rownum as rn
   from(
      select 
             b.phone_user_id,
             b.phone_number,
             b.phone_user_name,
             b.id_number,
             b.phone_user_sex,
             d.config_value as phone_user_sex_name,
             b.phone_user_age,
             b.phone_fee,
             b.phone_status,
             c.config_value as phone_status_name,
             b.create_time,
             b.modified_time
             from t_phone_user b
             inner join t_config c
             on c.config_type = 'phone_status' and c.config_key = b.phone_status
             inner join t_config d
             on d.config_type = 'user_sex' and d.config_key = b.phone_user_sex
             order by b.phone_user_id asc
   );

--5.省份表
drop table t_province;
create table t_province(
       province_id number(30) primary key,--省份id
       province_name varchar2(50),
       create_time date,--创建时间
       modified_time date  --最近一次修改时间
);

drop sequence seq_province;
create sequence seq_province;
commit;

insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'京',sysdate,sysdate);
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'津',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'冀',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'晋',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'蒙',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'辽',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'吉',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'黑',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'沪',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'苏',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'浙',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'皖',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'闽',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'赣',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'鲁',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'豫',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'鄂',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'湘',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'粤',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'桂',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'琼',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'渝',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'川',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'贵',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'云',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'藏',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'陕',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'甘',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'青',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'宁',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'新',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'港',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'澳',sysdate,sysdate);      
insert into t_province(province_id,province_name,create_time,modified_time)
       values(seq_province.nextval,'台',sysdate,sysdate);      
commit;

--4.汽车表
drop table t_car;
create table t_car(
       car_id number(30)primary key,--汽车id
       user_account varchar2(30),--用户账号
       province_id number,--省份id
       car_number varchar2(50) ,--车牌号
       car_body_number varchar2(50),--车架号
       car_type varchar2(30),--车辆类型
       check_time date,--年检时间
       next_check_time date,--下次年检时间s
       check_status varchar2(30),--年检状态
       create_time date,--创建时间
       modified_time date--最近一次修改时间
);
comment on table t_car
        is 'car table';
comment on column t_car.car_id
        is 'car id';
comment on column t_car.user_account
        is 'user account';
comment on column t_car.car_number
        is 'car number ';
comment on column t_car.car_body_number
        is 'car body number';
comment on column t_car.car_type
        is 'car type';
comment on column t_car.check_time
        is 'check time';
comment on column t_car.next_check_time
        is 'next check time';
comment on column t_car.check_status
        is 'check status';
comment on column t_car.create_time
        is 'crate time';
comment on column t_car.modified_time
        is 'modified time';


drop sequence seq_car;
create sequence seq_car;
commit;

--创建视图
drop view v$car;
create view v$car
as select
       car_id,
       user_account,
       province_id,
       province_name,
       car_number,
       car_body_number,
       car_type,
       car_type_name,
       check_time,
       next_check_time,
       check_status,
       check_status_name,
       create_time,
       modified_time,
       rownum as rn
       from(
          select 
                 a.car_id,
                 a.user_account,
                 a.province_id,
                 b.province_name,
                 a.car_number,
                 a.car_body_number,
                 a.car_type,
                 d.config_value as car_type_name,
                 a.check_time,
                 a.next_check_time,
                 a.check_status,
                 c.config_value as check_status_name,
                 a.create_time,
                 a.modified_time
                 from t_car a
                 left join t_province b
                 on b.province_id = a.province_id
                 inner join t_config c
                 on c.config_type='car_check_status' and c.config_key = a.check_status
                 inner join t_config d
                 on d.config_type='car_type' and d.config_key = a.car_type
                 order by a.car_id asc
       );

--5,违章记录表
drop table t_record;
create table t_record(
       record_id number(30),--id
       record_number varchar(50),--记录号
       user_account varchar2(30),--用户账号
       car_number varchar2(30),--车牌号
       location_id number(30),--违章地点id
       action_id number(30),--行为id
       record_status varchar(3),--记录状态
       record_detail varchar(100),--记录详情
       record_picture varchar(50),--违章照片
       create_time date,--违章时间
       modified_time date--最近一次修改时间
);
comment on table t_record
        is 'record table';
comment on column t_record.record_id
        is 'record id';
comment on column t_record.record_number
        is 'record number';
comment on column t_record.user_account
        is 'user account';
comment on column t_record.car_number
        is 'car_number';
comment on column t_record.action_id
        is 'action id';
comment on column t_record.location_id
        is 'location id';
comment on column t_record.record_status
        is 'record status';
comment on column t_record.record_detail
        is 'record detail';
comment on column t_record.record_picture
        is 'record picture';
comment on column t_record.create_time
        is 'create time';
comment on column t_record.modified_time
        is 'modified time';

drop sequence seq_record;
create sequence seq_record;
commit;

--创建视图
drop view v$record;
create view v$record
as select
       record_id,
       record_number,
       user_account,
       car_number,
       action_id,
       action_name,
       action_money,
       action_score,
       location_id,
       location_name,
       record_status,
       record_status_name,
       record_detail,
       record_picture,
       create_time,
       modified_time,
       rownum as rn
       from(
          select 
                 a.record_id,
                 a.record_number,
                 a.user_account,
                 a.car_number,
                 a.action_id,
                 d.action_name,
                 d.action_money,
                 d.action_score,
                 a.location_id,
                 c.location_name,
                 a.record_status,
                 b.config_value as record_status_name,
                 a.record_detail,
                 a.record_picture,
                 a.create_time,
                 a.modified_time
                 from t_record a
                 inner join t_config b
                 on b.config_type='car_record_status' and b.config_key = a.record_status
                 inner join t_location c
                 on c.location_id = a.location_id 
                 inner join t_action d
                 on d.action_id = a.action_id
                 order by a.record_id asc
       );

--6.违章地点表
drop table t_location;
create table t_location(
     location_id number(30),--违章地点id
     location_name varchar2(500),--地点名
     create_time date,--创建时间
     modified_time date--最近一次修改时间
);
comment on table t_location
        is 'table for the location';
comment on column t_location.location_id
        is 'location id';
comment on column t_location.location_name
        is 'location name';
comment on column t_location.create_time
        is 'create time';
comment on column t_location.modified_time
        is 'modified time';

drop sequence seq_location;
create sequence seq_location;

insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'清河西路',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'商贸城路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'商厦路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'颍河闸',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'泉河桥路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'百货大楼路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'电业局路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'白金汉宫路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'阜王路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'金种子路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'火车站路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'颍河西路中心大药房门前',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'淮河路与颖上路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'淮河路阜王路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'淮河路沙河路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'淮河路颍州路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'南门路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'拦河坝路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'三里湾路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'二中路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'莲池路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'纸厂路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'电业局路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'百货大楼路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'白金汉宫路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'小隅首路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'东城墙路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'西城墙路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'文峰路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'阜王路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'师范院校路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'市政府路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'阜王路与一道河路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'中青路与一道河路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'颖上路与一道河路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'颍州路与一道河路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'颍河西路于中南大道交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'人民路与中南大道交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'师范学院西区路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'幸福路与向阳路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'金种子路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'火车站路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'商厦路口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'105国道与新阳大道交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'105国道与东方汽贸城交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'中南大道与临泉路路交叉口',sysdate,sysdate);
insert into t_location(location_id,location_name,create_time,modified_time)
       values(seq_location.nextval,'商贸城路口',sysdate,sysdate);
commit;

--7.违章行为表
drop table t_action;
create table t_action(
       action_id number(30),--违章行为id
       action_name varchar2(300),--违章行为名
       action_money number(30),--罚金
       action_score number(30),--扣分
       create_time date,--创建时间
       modified_time date--最近一次修改时间
);
comment on table t_action
        is 'action table';
comment on column t_action.action_id
        is 'action id';
comment on column t_action.action_name
        is 'action name';
comment on column t_action.action_money
        is 'action money';
comment on column t_action.action_score
        is 'action score';
comment on column t_action.create_time
        is 'create time';
comment on column t_action.modified_time
        is 'modified time';

drop sequence seq_action;
create sequence seq_action;

insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'驾驶机动车违反道路交通信号灯通行','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'超速未达到20%','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'驾驶机动车不按照规定避让校车','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'占用应急车道','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'运载特殊物品不挂相应警告标志','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'运输危险品','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'运输危险品','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'机动车载物遗洒、飘散载运物 ','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'机动车载人超过核定人数未达20% ','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'机动车发生故障尚能移动，未移至不妨碍交通地点','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'公交站台违章停车','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'随意并线','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'非法上下乘客','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'非法掉头','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'违反规定进入专用车道内行驶','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'机动车通过无交通信号控制路口未减速让行 ','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'遇有前方车辆排队等候或缓慢行驶时，穿插等候车辆 ','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'运载危险化学品未按规定行驶 ','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'机动车发生故障，未按规定报警','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'不避让执行紧急任务的警车、消防车、救护车、工程救险车','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'机动车号牌不清晰或不完整','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'喷涂放大号不清晰','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'机动车喷涂、粘贴标识或车身广告影响安全驾驶 ','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'实习期内驾车未按规定粘贴悬挂实习标志 ','200',0,sysdate,sysdate);
insert into t_action(action_id,action_name,action_money,action_score,create_time,modified_time)
       values(seq_action.nextval,'在道路上学习驾驶教练车乘坐无关人员','200',0,sysdate,sysdate);
commit;

--8.充值卡表
drop table t_recharge_card;
create table t_recharge_card(
       card_id number(30),--充值卡id
       card_number varchar2(32),--卡号
       card_password varchar2(32),--密码
       card_value number(30),--金额
       start_time date,--开始时间
       end_time date,--结束时间
       card_status varchar(10),--充值卡状态
       create_time date,--创建时间
       modified_time date--修改时间
);
comment on table t_recharge_card
        is 'recharge card table';
comment on column t_recharge_card.card_id
        is 'car id';
comment on column t_recharge_card.card_number
        is 'card number';
comment on column t_recharge_card.card_password
        is 'card password';
comment on column t_recharge_card.card_value
        is 'card value';
comment on column t_recharge_card.start_time
        is 'start time';
comment on column t_recharge_card.end_time
        is 'end time';
comment on column t_recharge_card.card_status
        is 'card status';
comment on column t_recharge_card.create_time
        is 'create time';
comment on column t_recharge_card.modified_time
        is 'modified time';


drop sequence seq_recharge_card;
create sequence seq_recharge_card;
commit;

--创建视图
drop view v$rechargecard;
create view v$rechargecard
as select
       card_id,
       card_number,
       card_password,
       card_value,
       start_time,
       end_time,
       card_status,
       create_time,
       card_status_name,
       modified_time,
       rownum rn
       from(
          select 
                  a.card_id,
                  a.card_number,
                  a.card_password,
                  a.card_value,
                  a.start_time,
                  a.end_time,
                  a.card_status,
                  b.config_value as card_status_name,
                  a.create_time,
                  a.modified_time
                  from t_recharge_card a
                  inner join t_config b
                  on b.config_type='fee_card_status' and b.config_key = a.card_status
                  order by a.card_id asc
       );

--批量添加密保卡
create or replace procedure p_add_recharge_cards
( cardNumbers in number,
  cardValue in number,
  startTime in date,
  endTime in date)
is

begin
    for i in 0..cardNumbers-1 loop
        --插入数据
		insert into t_recharge_card(card_id,card_number,card_password,card_value,start_time,end_time,card_status,create_time,modified_time)
			values(seq_recharge_card.nextval,sys_guid(),substr(sys_guid(),0,13),cardValue,startTime,endTime,0,sysdate,sysdate);
        commit;
    end loop;
end p_add_recharge_cards;
--定时任务（将过期密保卡状态置为‘3’）
create or replace procedure p_check_recharge_card_status
is   cursor c is select card_id,card_status,end_time from t_recharge_card;
    v_recharge_card  t_recharge_card%rowtype;
    v_id  t_recharge_card.card_id% type;
    v_card_status  t_recharge_card.card_status% type;
    v_end_time  t_recharge_card.end_time%type;
begin
    for v_recharge_card in c loop
        v_card_status := v_recharge_card.card_status;
        v_end_time := v_recharge_card.end_time;
        v_id := v_recharge_card.card_id;
        if v_end_time <= sysdate then
            update t_recharge_card set card_status='2' where card_id = v_id and card_status = '0';
            commit;
        end if;
    end loop;
end p_check_recharge_card_status;

--3,设置定时任务
DECLARE 
    JOB1   NUMBER; 
BEGIN 
    SYS.DBMS_JOB.SUBMIT 
        ( job => JOB1   
          ,what => 'p_check_recharge_card_status;' 
          ,next_date => sysdate 
          ,interval => 'sysdate + 1' 
          ,no_parse => TRUE 
        ); 
commit; 
END;


--9.配置表
drop table t_config;
create table t_config(
       config_id number(30),--配置id
       config_type varchar2(100),--配置类型
       config_key varchar2(100),--key
       config_value varchar2(100),--value
       create_time date,--创建时间
       modified_time date--最近一次修改时间
);
comment on table t_config
        is 'config table';
comment on column t_config.config_id
        is 'config id';
comment on column t_config.config_type
        is 'config type';
comment on column t_config.config_key
        is 'config key';
comment on column t_config.config_value
        is 'config value';
comment on column t_config.create_time
        is 'create time';
comment on column t_config.modified_time
        is 'modified time';
drop sequence seq_config;
create sequence seq_config;
--管理员账号状态0：正常，1：已注销
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'admin_account_status','0','正常',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'admin_account_status','1','已注销',sysdate,sysdate);
--用户账号状态0：正常，1：已注销
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'user_account_status','0','正常',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'user_account_status','1','已注销',sysdate,sysdate);
--手机用户性别0：男，1：女
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'user_sex','0','男',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'user_sex','1','女',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'phone_status','0','正常',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'phone_status','1','已注销',sysdate,sysdate);
--汽车表汽车年检状态0：已年检，1：未年检
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'car_check_status','0','已年检',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'car_check_status','1','未年检',sysdate,sysdate);
--汽车类型 0：蓝牌小车 1：黄牌大车
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'car_type','0','蓝牌小车',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'car_type','1','黄牌大车',sysdate,sysdate);

--违章记录状态0：已处理，1：未处理
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'car_record_status','0','已缴费',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'car_record_status','1','未缴费',sysdate,sysdate);
--手机充值卡状态0：正常,1：已使用,2:已过期
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'fee_card_status','0','正常',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'fee_card_status','1','已使用',sysdate,sysdate);
insert into t_config(config_id,config_type,config_key,config_value,create_time,modified_time)
       values(seq_config.nextval,'fee_card_status','2','已过期',sysdate,sysdate);

commit;


