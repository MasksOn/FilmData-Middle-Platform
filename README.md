# Film Data Middle Platform

#  Data Platform (电影推荐与数据治理中台)


![Java](https://img.shields.io/badge/Java-Spring_Boot_3-green.svg)
![BigData](https://img.shields.io/badge/BigData-Hadoop_|_Spark_|_Flink-orange.svg)
![Vue](https://img.shields.io/badge/Frontend-Vue_3-brightgreen.svg)

## 📖 项目简介

本项目是一个基于大数据生态体系构建的综合性电影推荐与数据治理中台21]。系统打通了**“数据采集—批流计算—数据治理—智能应用”**的全生命周期链路，旨在解决企业级海量数据处理中的高并发痛点与“数据孤岛”问题

平台不仅支撑了底层 2700万+ 规模数据的规范化四层数仓建设与秒级实时大屏，还创新性地引入了 **Neo4j 图数据库**进行血缘链路追踪22]，并深度整合 **大语言模型（LLM）** 实现了智能化的元数据治理与可解释性 AI 推荐

##  核心特性

* ** 批流一体的大数据底座**
  * **高可用架构**：底层基于 Zookeeper 协调的 Hadoop 3 (HDFS/YARN) HA 架构，保障全局容灾
  * **规范化离线数仓**：基于 Spark SQL 构建 ODS -> DWD -> DWS -> ADS 标准四层数仓模型，有效降低跨表关联成本
  * **秒级实时流处理**：采用 Flume + Kafka + Flink 架构，通过 Flink 侧输出流（Side Output）机制将日志精准路由至 ClickHouse 与 Hive，实现“一路输入，多路落盘”的流批一体化

*  全链路数据治理中心
  * **数据质量监控 (DQC)**：结合 Hive Metastore 与定时调度，实现表级/字段级的空值率、注释缺漏率等健康度大盘监控
  * **全景血缘拓扑**：利用 ANTLR4 深度解析 SQL 语法树提取表级血缘，并持久化至 Neo4j，通过有向无环图 (DAG) 解决数据溯源难题

*  LLM 智能化赋能
  * **Schema-Aware 智能补全**：借助大模型自动推断并批量回填缺失的 Hive 字段业务注释，极大降低人工维护成本
  * **NL2SQL 探查助手**：将业务人员的自然语言（如“近十年各题材电影数量”）自动转化为可执行的 SQL 并动态渲染报表

*  微服务化智能推荐引擎
  * **多策略协同过滤**：基于 Python FastAPI 独立构建，支持 Item-CF、User-CF、混合策略及新用户冷启动降级兜底
  * **可解释性 AI 推荐**：结合 LLM 画像引擎，为协同过滤结果动态生成带情感偏好的“自然语言推荐理由”，打破算法黑盒

*  多维实时大屏与交互管控
  * 整合 Vue.js 与 ECharts，实现 GMV 实时滚屏、CDN 全国健康度态势感知地图、受众画像词云与电影特征雷达图展示

##  技术栈架构

### 1. 基础设施 & 离线计算
* **Hadoop Ecosystem**: HDFS (HA), YARN (HA), Zookeeper
* **Offline Computing**: Apache Spark, Hive
* **Task Scheduling**: Quartz / Apache DolphinScheduler

### 2. 实时流计算
* **Data Ingestion**: Python (Mock Scripts), Flume, Apache Kafka
* **Stream Processing**: Apache Flink
* **OLAP Storage**: ClickHouse

### 3. 数据治理 & 业务后端
* **Graph Database**: Neo4j (Lineage tracking
* **Relational Database**: MySQL
* **Backend Framework**: Spring Boot 3, MyBatis, JWT Auth

### 4. AI 推荐 & 前端展示
* **Algorithm Microservice**: Python, FastAPI, Scikit-learn, Pandas
* **LLM Integration**: GPT / 自定义大模型接口
* **Frontend**: Vue 3, Vue Router, Element-UI, Apache ECharts

##  系统截图

- [实时营收与大屏监控]
  
- [全链路数据血缘拓扑]
- [Hive元数据智能字典]
- [AI 可解释个性化推荐]

<img width="1614" height="1268" alt="Pasted image 20260308100159" src="https://github.com/user-attachments/assets/11094a60-cc92-47fc-a2ce-4689421d489f" />
