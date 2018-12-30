# spring-boot-starter-es-security
ES6.X版本配置，支持证书认证

Maven依赖：

```
<dependency>
	<groupId>com.github.yinjihuan</groupId>
	<artifactId>spring-boot-starter-es-security</artifactId>
	<version>1.0.0</version>
</dependency>
    
```

仓库配置：

```
<repositories>
	<repository>
		  <id>jitpack.io</id>
		  <url>https://www.jitpack.io</url>
	</repository>
</repositories>
```

项目配置：

```
spring.data.elasticsearch.cluster-name = fangjia
spring.data.elasticsearch.cluster-nodes = 192.168.0.228:9300
spring.data.elasticsearch.username = 用户名
spring.data.elasticsearch.password = 密码
spring.data.elasticsearch.keystore-path = 证书本地路径
spring.data.elasticsearch.ping_timeout = 5s
```

查询：

```
@Autowired
private ElasticsearchTemplate elasticsearchTemplate;
```
