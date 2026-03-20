# Film Data Middle Platform

#  Data Platform (电影推荐与数据治理中台)

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-Spring_Boot_3-green.svg)
![BigData](https://img.shields.io/badge/BigData-Hadoop_|_Spark_|_Flink-orange.svg)
![Vue](https://img.shields.io/badge/Frontend-Vue_3-brightgreen.svg)

## 📖 项目简介

[cite_start]本项目是一个基于大数据生态体系构建的综合性电影推荐与数据治理中台 [cite: 21][cite_start]。系统打通了**“数据采集—批流计算—数据治理—智能应用”**的全生命周期链路，旨在解决企业级海量数据处理中的高并发痛点与“数据孤岛”问题 [cite: 76, 78]。

[cite_start]平台不仅支撑了底层 2700万+ 规模数据的规范化四层数仓建设与秒级实时大屏 [cite: 21, 91, 381][cite_start]，还创新性地引入了 **Neo4j 图数据库**进行血缘链路追踪 [cite: 22][cite_start]，并深度整合 **大语言模型（LLM）** 实现了智能化的元数据治理与可解释性 AI 推荐 [cite: 37, 342]。

##  核心特性

* ** 批流一体的大数据底座**
  * [cite_start]**高可用架构**：底层基于 Zookeeper 协调的 Hadoop 3 (HDFS/YARN) HA 架构，保障全局容灾 [cite: 96, 97]。
  * [cite_start]**规范化离线数仓**：基于 Spark SQL 构建 ODS -> DWD -> DWS -> ADS 标准四层数仓模型，有效降低跨表关联成本 [cite: 90]。
  * [cite_start]**秒级实时流处理**：采用 Flume + Kafka + Flink 架构，通过 Flink 侧输出流（Side Output）机制将日志精准路由至 ClickHouse 与 Hive，实现“一路输入，多路落盘”的流批一体化 [cite: 154, 156, 172]。

* ** 全链路数据治理中心**
  * [cite_start]**数据质量监控 (DQC)**：结合 Hive Metastore 与定时调度，实现表级/字段级的空值率、注释缺漏率等健康度大盘监控 [cite: 125, 162]。
  * [cite_start]**全景血缘拓扑**：利用 ANTLR4 深度解析 SQL 语法树提取表级血缘，并持久化至 Neo4j，通过有向无环图 (DAG) 解决数据溯源难题 [cite: 113, 163, 293]。

* ** LLM 智能化赋能**
  * [cite_start]**Schema-Aware 智能补全**：借助大模型自动推断并批量回填缺失的 Hive 字段业务注释，极大降低人工维护成本 [cite: 127, 289]。
  * [cite_start]**NL2SQL 探查助手**：将业务人员的自然语言（如“近十年各题材电影数量”）自动转化为可执行的 SQL 并动态渲染报表 [cite: 127, 324]。

* ** 微服务化智能推荐引擎**
  * [cite_start]**多策略协同过滤**：基于 Python FastAPI 独立构建，支持 Item-CF、User-CF、混合策略及新用户冷启动降级兜底 [cite: 92]。
  * [cite_start]**可解释性 AI 推荐**：结合 LLM 画像引擎，为协同过滤结果动态生成带情感偏好的“自然语言推荐理由”，打破算法黑盒 [cite: 342]。

* ** 多维实时大屏与交互管控**
  * [cite_start]整合 Vue.js 与 ECharts，实现 GMV 实时滚屏、CDN 全国健康度态势感知地图、受众画像词云与电影特征雷达图展示 [cite: 91, 109, 122]。

##  技术栈架构

### 1. 基础设施 & 离线计算
* [cite_start]**Hadoop Ecosystem**: HDFS (HA), YARN (HA), Zookeeper [cite: 89, 96, 97]
* [cite_start]**Offline Computing**: Apache Spark, Hive [cite: 21, 90, 101]
* [cite_start]**Task Scheduling**: Quartz / Apache DolphinScheduler [cite: 89, 124]

### 2. 实时流计算
* [cite_start]**Data Ingestion**: Python (Mock Scripts), Flume, Apache Kafka [cite: 88, 105, 155]
* [cite_start]**Stream Processing**: Apache Flink [cite: 21, 104]
* [cite_start]**OLAP Storage**: ClickHouse [cite: 21, 247]

### 3. 数据治理 & 业务后端
* [cite_start]**Graph Database**: Neo4j (Lineage tracking) [cite: 22, 163]
* [cite_start]**Relational Database**: MySQL [cite: 181]
* [cite_start]**Backend Framework**: Spring Boot 3, MyBatis, JWT Auth [cite: 50, 107]

### 4. AI 推荐 & 前端展示
* [cite_start]**Algorithm Microservice**: Python, FastAPI, Scikit-learn, Pandas [cite: 92, 113]
* [cite_start]**LLM Integration**: GPT / 自定义大模型接口 [cite: 289, 324]
* [cite_start]**Frontend**: Vue 3, Vue Router, Element-UI, Apache ECharts [cite: 22, 108, 109]

##  系统截图

*(建议上传截图至仓库的 `images` 文件夹并替换以下链接)*

- [实时营收与大屏监控](./images/realtime_dashboard.png)
- [全链路数据血缘拓扑](./images/data_lineage.png)
- [Hive元数据智能字典](./images/metadata_dictionary.png)
- [AI 可解释个性化推荐](./images/ai_recommendation.png)

<img width="1614" height="1268" alt="Pasted image 20260308100159" src="https://github.com/user-attachments/assets/11094a60-cc92-47fc-a2ce-4689421d489f" />
