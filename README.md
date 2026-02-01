# MFG-Service
manufacturing service example for MCP service

Create Tables for this service to work 
```
create table plant(id varchar(10) primary key, name varchar(20));
create table production_line(id varchar(10) primary key, plant_id varchar(10), name varchar(20), status varchar(10), foreign key(plant_id) references plant(id));
create table machine(id varchar(10) primary key, line_id varchar(10), name varchar(30), type varchar(10), foreign key(line_id) references production_line(id));
create table shift(id varchar(10) primary key,  start_time datetime, end_time datetime);
create table product(id varchar(10) primary key, name varchar(20), grade varchar(10), standard varchar(10));
create table production_run(id varchar(10) primary key, line_id varchar(10), product_id varchar(10), shift_id varchar(10), start_time datetime, end_time datetime, planned_qty int, actual_qty int,
foreign key(line_id) references production_line(id), foreign key(product_id) references product(id), foreign key(shift_id) references shift(id));
create table downtime_event(id varchar(10) primary key,run_id varchar(10),line_id	varchar(10),machine_id	varchar(10),reason varchar(20),
category varchar(10),start_time	datetime,end_time datetime,duration int, foreign key(run_id) references production_run(id), 
foreign key(line_id) references production_line(id), foreign key(machine_id) references machine(id));
create table quality_event(id varchar(10) primary key,run_id varchar(10),defect_type varchar(10),quantity int,machine_id varchar(10),
 foreign key(run_id) references production_run(id),foreign key(machine_id) references machine(id));
```
Create Dummy data
```

```
