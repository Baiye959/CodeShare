# 青少年代码交流网站 codeshare

## 1. 网站分区

登录注册页面

404页面

导航栏组件

题库页面-单题页面-算法动画自定义组件

debug页面

网课推荐页面-推荐详情页面

排行榜页面

个人中心页面-代码编写modal



## 2. 文件关系树

codeshare主要文件

```bash
|——（git仓库隐藏文件夹）
|
|——  web（前端项目文件夹）
    |—— dist（前端上线时的打包文件）
    |——  node_modules（前端依赖）
    |——  src（前端代码）
        |——  App.vue
        |——  main.js
        |——  assets（图片文件夹）
        |——  components（自定义组件文件夹）
             |——  ContentField.vue（主编辑区组件）
             |——  NavBar.vue（导航栏组件）
             |——  Queue.vue（队列动画组件）
             |——  Stack.vue（栈动画组件）
        |——  router（前端路径配置文件夹）
        |——  store（全局变量文件夹）
            |——  user.js（当前登录的用户）
        |——  views（前端页面文件夹）
            |——  database
                |——  DatabaseIndex.vue（题库页面）
                |——  TopicIndex.vue（单题页面）
            |——  debug
                |——  DebugIndex.vue（debug区页面）
            |——  error
                |——  NotFound.vue（404页面）
            |——  lesson
                |——  LessonIndex.vue（网课推荐页面）
                |——  LessonIdIndex.vue（推荐详情页面）
            |——  ranklist
                |——  RanklistIndex.vue（排行榜页面）
            |——  user
                |——  account
                    |——  UserAccount.vue（登录注册页面）
                |——  code
                    |——  UserCodeIndex.vue（个人中心页面）
|
|——  backend（后端项目文件夹）
    |——  target
        |——  backend-0.0.1-SNAPSHOT.jar（后端上线时的打包文件）
    |——  pom.xml（后端依赖）
    |——  src（后端代码文件夹）
        |——  main/resources
                |——  application.properties（配置后端端口）
            |——  java/com/codeshare/backend
                |——  BackendApplication.java（后端主文件）
                |——  config（后端配置文件夹）
                    |——  CorsConfig.java（解决跨域问题）
                    |——  SecurityConfig.java（添加后端白名单api：登录和注册的api）
                |——  pojo
                |——  mapper
                |——  service
                    |——  impl
                |—— controller
```

## 3. 数据库创建代码（本项目用的是mysql）

```
create table code
(
    id          int auto_increment
        primary key,
    user_id     int                     not null,
    title       varchar(100)            not null,
    description varchar(300)            null,
    content     varchar(10000)          not null,
    is_passed   varchar(10) default '0' not null,
    createtime  datetime                null,
    modifytime  datetime                null,
    topic_id    varchar(100)            not null,
    constraint code_pk
        unique (id)
);

create table comment
(
    id         int auto_increment
        primary key,
    content    varchar(15000) not null,
    user_name  varchar(100)   not null,
    createtime datetime       not null,
    code_id    int            not null,
    constraint comment_pk
        unique (id)
);

create table lesson
(
    id          int auto_increment
        primary key,
    title       varchar(100) not null,
    description varchar(300) null,
    user_id     int          not null,
    lesson_name varchar(100) not null,
    constraint lesson_pk
        unique (id)
);

create table test
(
    id        int auto_increment
        primary key,
    test_name varchar(100) not null,
    constraint test_pk
        unique (id)
);

create table topic
(
    id            int auto_increment
        primary key,
    test_name     varchar(100)   not null,
    topic_name    varchar(100)   not null,
    topic_content varchar(10000) not null,
    constraint topic_pk
        unique (id)
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(100)  null,
    password varchar(100)  null,
    photo    varchar(1000) null,
    rating   int default 0 not null,
    constraint user_pk
        unique (id)
);


```
























