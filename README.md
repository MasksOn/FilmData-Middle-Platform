# movie-recommend-system

|                  |           |                 |
| ---------------- | --------- | --------------- |
| 软件工具             | 版本        | 备注              |
| Apache Flink     | 1.14.0    | 实时、流式处理引擎、数据处理层 |
| Apache Flume     | 1.9.0     | 日志收集服务、数据源层     |
| Apache Zookeeper | 3.5.7     | 分布式系统协调服务       |
| Apache Hadoop    | 3.1.3     | HDFS，Yarn       |
| Apache Hive      | 2.2.3     | 数据存储层           |
| Apache Kafka     | 2.12-2.41 | 发布订阅消息系统        |
| Scala            | 2.12.7    | 开发语言            |
| Java             | 1.8       | 开发语言            |
| MySql            | 8.0.23    | 数据源层、离线数据服务层    |
| Spark            | 3.1.1     | 离线、批处理引擎、数据处理层  |
| ClickHouse       | 21.8.5.7  | 数据服务层、OLAP      |
| Neo4j            | 3.5.35    | 数据服务层           |

## Hadoop高可用部署

![[Pasted image 20260308100159.png]]


本项目在大数据底座的基础上进行实时离线的数仓开发,

离线层，利用 Spark 构建了 **ODS（贴源层）  DWD（明细层） DWS（汇总层）  ADS（应用层）** 四层数仓体系。最终将结果存入Mysql（service layer），完成了海量电影基础信息、用户打分日志与标签数据的清洗。在 DWS 层构建了“电影综合评价”与“用户偏好画像”等宽表；在 ADS 层输出了推荐算法特征库、年代类型演进趋势以及全局高分热度榜单。（离线表）
实时层：通过Python模拟用户前端业务埋点的数据，并通过Flink接收，并通过侧输流进行传输给了ClickHouse,为后面中台的实时大表传输数据。（实时表）

流批一体化，再通过侧输流将实时层的数据存储到hive表的ods_baize_dw.ods_rating_log为后续的推荐系统进行离线存储

配置文件在附录
集群规划:

| 基础框架                            | 角色名称 (组件)          | hadoop102 | hadoop103 | hadoop104 |
| :------------------------------ | :----------------- | :-------: | :-------: | :-------: |
| **Zookeeper**                   | ZK Server          |     ✔     |     ✔     |     ✔     |
| **HDFS**                        | NameNode           |     ✔     |     ✔     |           |
|                                 | DataNode           |     ✔     |     ✔     |     ✔     |
|                                 | JournalNode        |     ✔     |     ✔     |     ✔     |
|                                 | ZKFC               |     ✔     |     ✔     |           |
| **YARN**                        | ResourceManager    |     ✔     |     ✔     |           |
|                                 | NodeManager        |     ✔     |     ✔     |     ✔     |
| **Kafka**                       | Kafka Broker       |     ✔     |     ✔     |     ✔     |
| **Flink**                       | JobManager         |     ✔     |           |           |
|                                 | TaskManager        |     ✔     |     ✔     |     ✔     |
| **调度系统** <br>(DolphinScheduler) | API Server         |     ✔     |           |           |
|                                 | Master             |           |     ✔     |           |
|                                 | Worker             |           |     ✔     |     ✔     |
|                                 | Alert              |           |           |     ✔     |
| **图数据库**                        | Neo4j              |           |           |     ✔     |
| **OLAP 引擎**                     | ClickHouse         |           |           |     ✔     |
| **计算与其他**                       | Master (Spark)     |     ✔     |           |           |
|                                 | HistoryServer      |     ✔     |           |           |
|                                 | Hive / 其他 (RunJar) |     ✔     |           |           |

root@server102:/opt/hadoop-scripts# ./jpsall 
=============== hadoop102 ===============
200003 QuorumPeerMain
515335 TaskManagerRunner
538024 NameNode
200418 JournalNode
514947 StandaloneSessionClusterEntrypoint
511939 HistoryServer
538701 DFSZKFailoverController
537570 RunJar
203120 NodeManager
201973 DataNode
520305 TaskManagerRunner
579664 Jps
505245 Kafka
349663 Master
308671 ApiApplicationServer
202973 ResourceManager
=============== hadoop103 ===============
417030 DFSZKFailoverController
246978 MasterServer
206498 DataNode
335140 Kafka
417310 Jps
247051 WorkerServer
380783 TaskManagerRunner
205689 QuorumPeerMain
206857 ResourceManager
206015 JournalNode
207565 NameNode
206956 NodeManager
=============== hadoop104 ===============
166914 DataNode
59602 QuorumPeerMain
199697 AlertServer
272544 TaskManagerRunner
20452 DFSZKFailoverController
167156 NodeManager
288451 CommunityEntryPoint
256683 Kafka
166552 JournalNode
199624 WorkerServer
291561 Jps

