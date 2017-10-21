CREATE SCHEMA insurance_mantra;

USE insurance_mantra;

CREATE TABLE policy_category (
  category_id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_name varchar(50) NOT NULL,
  creation_date date,
  category_desc varchar(1000)
);

CREATE TABLE policy_brand (
  brand_id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  brand_name varchar(50) NOT NULL,
  creation_date date
);

alter table policy_brand 
add brand_email varchar(100),
add password varchar(2000) not null,
add brand_address varchar(200),
add brand_city varchar(50),
add brand_state varchar(20),
add brand_country varchar(20),
add brand_phone int(10),
add brand_status varchar(15);

CREATE TABLE policy_sub_category (
  sub_category_id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_id int unsigned not null,
  sub_category_name varchar(50) NOT NULL,
  creation_date date,  
  sub_category_desc varchar(1000),
  sub_category_features varchar(1000),
  sub_category_benefits varchar(1000),
  KEY FK_policy_category_category_id (category_id),
  CONSTRAINT FK_policy_category_category_id FOREIGN KEY (category_id)
	REFERENCES policy_category(category_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE policy_user (
  user_id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  brand_id int unsigned not null,
  user_name varchar(50) NOT NULL,
  password varchar(2000) not null,
  user_email varchar(100),
  user_role varchar(15),
  user_dob date,
  user_address varchar(200),
  user_city varchar(50),
  user_state varchar(20),
  user_country varchar(20),
  user_phone int(10),
  KEY FK_policy_brand_brand_id (brand_id),
  CONSTRAINT FK_policy_brand_brand_id FOREIGN KEY (brand_id)
  REFERENCES policy_brand(brand_id) ON DELETE CASCADE ON UPDATE CASCADE
);
alter table policy_user drop foreign key FK_policy_brand_brand_id;
alter table policy_user drop column brand_id;
alter table policy_user add creation_date date;
alter table policy_user add name varchar(50) NOT NULL;

CREATE TABLE policy_plan (
  plan_id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  sub_category_id int unsigned not null,
  brand_id int unsigned not null,
  plan_name varchar(50) NOT NULL,
  min_entry_age char(2) not null,
  max_entry_age char(2) not null,
  plan_maturity char(2),
  premium_amount varchar(20),
  sum_assured varchar(20),
  plan_desc varchar(1000),
  plan_features varchar(1000), 
  KEY FK_policy_brand_onpolicyplan_brand_id (brand_id),    
  CONSTRAINT FK_policy_brand_onpolicyplan_brand_id FOREIGN KEY (brand_id)
  REFERENCES policy_brand(brand_id) ON DELETE CASCADE ON UPDATE CASCADE,  
  KEY FK_policy_sub_category_sub_category_id (sub_category_id),  
  CONSTRAINT FK_policy_sub_category_sub_category_id FOREIGN KEY (sub_category_id)
  REFERENCES policy_sub_category(sub_category_id) ON DELETE CASCADE ON UPDATE CASCADE
);

alter table policy_plan add creation_date date;

CREATE TABLE policy_order_history (
  order_id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  plan_id int unsigned not null,
  user_id int unsigned not null,
  brand_id int unsigned not null,
  requester_name varchar(50) NOT NULL,
  requester_email varchar(100),
  requester_phone int(10) NOT NULL,
  requester_dob date,
  requester_income varchar(20),
  order_status varchar(10),
  order_creation_date date,
  order_completion_date date,   
  
  KEY FK_policy_plan_plan_id (plan_id),    
  CONSTRAINT FK_policy_plan_plan_id FOREIGN KEY (plan_id)
  REFERENCES policy_plan(plan_id) ON DELETE CASCADE ON UPDATE CASCADE, 
  
  KEY FK_policy_user_user_id (user_id),    
  CONSTRAINT FK_policy_user_user_id FOREIGN KEY (user_id)
  REFERENCES policy_user(user_id) ON DELETE CASCADE ON UPDATE CASCADE, 
  
  KEY FK_policy_brand_onhistory_brand_id (brand_id),  
  CONSTRAINT FK_policy_brand_onhistory_brand_id FOREIGN KEY (brand_id)
  REFERENCES policy_brand(brand_id) ON DELETE CASCADE ON UPDATE CASCADE
);
