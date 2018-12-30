package com.cxytiandi.es;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EsProperties {
	/**
	 * ES集群名称
	 */
	@Value("${spring.data.elasticsearch.cluster-name:fangjia}")
	private String clusterName;
	
	/**
	 * ES用户名
	 */
	@Value("${spring.data.elasticsearch.username:yinjihuan}")
	private String username;
	
	/**
	 * ES密码
	 */
	@Value("${spring.data.elasticsearch.password:123456}")
	private String password;
	
	/**
	 * ES证书地址
	 */
	@Value("${spring.data.elasticsearch.keystore-path:C:\\opt\\settings\\elastic-certificates.p12}")
	private String keystorePath;
	
	/**
	 * ES集群节点
	 */
	@Value("${spring.data.elasticsearch.cluster-nodes:127.0.0.1:9300}")
	private String clusterNodes;
	
	/**
	 * ES心跳间隔
	 */
	@Value("${spring.data.elasticsearch.ping_timeout:5s}")
	private String pingTimeout;
}
