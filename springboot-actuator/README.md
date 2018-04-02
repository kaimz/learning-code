# Spring Boot 2.0 中使用 Actuator
### 前言

主要是完成微服务的监控，完成监控治理。可以查看微服务间的数据处理和调用，当它们之间出现了异常，就可以快速定位到出现问题的地方。

* springboot - veriosn: 2.0


<!--more-->

### 正文

#### 依赖
在 `pom.xml` 文件中加入 actuator 的依赖：
```xml
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>
```
使用 Gradle 构建：
```
dependencies {
	compile("org.springframework.boot:spring-boot-starter-actuator")
}
```
#### 配置
需要注意的是 Spring Boot 2.0 相对于上个版本， Actuator 发生很多变化，
##### keys 的配置改变

| 旧的属性                  | 新的属性                                 |
| ------------------------- | ---------------------------------------- |
| `endpoints.<id>.*`        | `management.endpoint.<id>.*`             |
| `endpoints.cors.*`        | `management.endpoints.web.cors.*`        |
| `endpoints.jmx.*`         | `management.endpoints.jmx.*`             |
| `management.address`      | `management.server.address`              |
| `management.context-path` | `management.server.servlet.context-path` |
| `management.ssl.*`        | `management.server.ssl.*`                |
| `management.port`         | `management.server.port`                 |

##### 基本路径

1. 所有 endpoints 默认情况下都已移至 `/actuator`。就是多了跟路径 `actuator` ；

2. 上个版本中的 `management/context-path:`  和 `management/port:` 改为 :

   ```yaml
   management:
     server:
       port: 8004
       servlet:
         context-path: /xxx # 只有在设置了 management.server.port 时才有效 
   ```

   另外，您还可以使用新的单独属性 `management.endpoints.web.base-path` 为管理端点设置基本路径。

   例如，如果你设置`management.server.servlet.context-path=/management`和`management.endpoints.web.base-path=/application`，你就可以在下面的路径到达终点健康：`/management/application/health`。

   如果你想恢复 1.x 的行为（即具有`/health`代替`/actuator/health`），设置以下属性：`management.endpoints.web.base-path=/`



##### ENDPOINTS

1.X 的时候属性：

| HTTP 方法 | 路径            | 描述                                                         |
| --------- | --------------- | ------------------------------------------------------------ |
| GET       | /autoconfig     | 提供了一份自动配置报告，记录哪些自动配置条件通过了，哪些没通过 |
| GET       | /configprops    | 描述配置属性(包含默认值)如何注入Bean                         |
| GET       | /beans          | 描述应用程序上下文里全部的Bean，以及它们的关系               |
| GET       | /dump           | 获取线程活动的快照                                           |
| GET       | /env            | 获取全部环境属性                                             |
| GET       | /env/{name}     | 根据名称获取特定的环境属性值                                 |
| GET       | /health         | 报告应用程序的健康指标，这些值由HealthIndicator的实现类提供  |
| GET       | /info           | 获取应用程序的定制信息，这些信息由info打头的属性提供         |
| GET       | /mappings       | 描述全部的URI路径，以及它们和控制器(包含Actuator端点)的映射关系 |
| GET       | /metrics        | 报告各种应用程序度量信息，比如内存用量和HTTP请求计数         |
| GET       | /metrics/{name} | 报告指定名称的应用程序度量值                                 |
| POST      | /shutdown       | 关闭应用程序，要求endpoints.shutdown.enabled设置为true       |
| GET       | /trace          | 提供基本的HTTP请求跟踪信息(时间戳、HTTP头等)                 |

2.0 部分更改：

| 1.x 端点       | 2.0 端点（改变）                                             |
| -------------- | ------------------------------------------------------------ |
| `/actuator`    | 不再可用。 但是，在 `management.endpoints.web.base-path` 的根目录中有一个映射，它提供了到所有暴露端点的链接。 |
| `/auditevents` | 该`after`参数不再需要                                        |
| `/autoconfig`  | 重命名为 `/conditions`                                       |
| `/docs`        | 不再可用                                                     |
| `/health`      | 现在有一个 `management.endpoint.health.show-details` 选项 `never`, `always`, `when-authenticated`，而不是依靠 `sensitive` 标志来确定 `health` 端点是否必须显示全部细节。 默认情况下，`/actuator/health`公开并且不显示细节。 |
| `/trace`       | 重命名为 `/httptrace`                                        |

默认端点 path 前面多了一级 `/actuator` 。

**同时注意只有端点`/health`和`/info`端点是暴露的。**

| Property                                    | Default        |
| ------------------------------------------- | -------------- |
| `management.endpoints.jmx.exposure.exclude` |                |
| `management.endpoints.jmx.exposure.include` | `*`            |
| `management.endpoints.web.exposure.exclude` |                |
| `management.endpoints.web.exposure.include` | `info, health` |

1. 您可以按如下方式公开所有端点：`management.endpoints.web.exposure.include=*`
2. 您可以通过以下方式显式启用`/shutdown`端点：`management.endpoint.shutdown.enabled=true`
3. 要公开所有（已启用）网络端点除`env`端点之外：

```properties
management.endpoints.web.exposure.include=*
management.endpoints.web.exposure.exclude=env
```

例如：

我现在开启所有的端点：

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*" # * 在yaml 文件属于关键字
```

执行 `localhost:${port}/actuator`，可以看到所有可以执行查看的端点监控的 Url，然后我们尝试执行关闭应用进程的指令：`shutdown`：

![image](https://zqnight.gitee.io/kaimz.github.io/image/hexo/springboot-actuator/shotdown.png)

##### 端点格式

1. `/actuator/mappings` 端点大改变
   JSON 格式已经更改为现在正确地包含有关上下文层次结构，多个`DispatcherServlets，`部署的 Servlet 和 Servlet 过滤器的信息。详情请参阅[＃9979](https://github.com/spring-projects/spring-boot/issues/9979#issuecomment-357930821)。
   Actuator API 文档的[相关部分](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/actuator-api/html/#mappings)提供了一个示例文档。


2. `/actuator/httptrace` 端点大改变
   响应的结构已经过改进，以反映端点关注跟踪 HTTP 请求 - 响应交换的情况。

### 总结

主要是 Spring Boot 2.0 版本升级在 Actuator 上面有许多改动，需要记录下。

### 参考文章
* [Part V. Spring Boot Actuator: Production-ready features](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-endpoints)
* [Spring Boot 2.0系列文章(一)：Spring Boot 2.0 迁移指南](http://www.54tianzhisheng.cn/2018/03/06/SpringBoot2-Migration-Guide/)