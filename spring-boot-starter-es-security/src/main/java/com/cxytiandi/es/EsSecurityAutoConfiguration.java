package com.cxytiandi.es;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 启用ES信息自动配置
 * 
 * @author yinjihuan
 * 
 * @about http://cxytiandi.com/about
 *
 **/
@Configuration
@Component
@EnableAutoConfiguration(exclude={ElasticsearchAutoConfiguration.class})
public class EsSecurityAutoConfiguration {
	
	private Logger logger = LoggerFactory.getLogger(EsSecurityAutoConfiguration.class);
	
	@Autowired
	private EsProperties esProperties;
	
	@SuppressWarnings({ "resource", "unchecked" })
	@Bean
	public Client client() {
		try {
			Settings.Builder builder = Settings.builder()
					.put("client.transport.ping_timeout", esProperties.getPingTimeout())
					.put("cluster.name", esProperties.getClusterName())
					.put("xpack.security.user", esProperties.getUsername() + ":" + esProperties.getPassword())
					.put("xpack.security.transport.ssl.enabled", "true")
					.put("xpack.security.transport.ssl.truststore.path", esProperties.getKeystorePath())
					.put("xpack.security.transport.ssl.keystore.path", esProperties.getKeystorePath())
					.put("xpack.security.transport.ssl.verification_mode", "certificate");
			Settings settings = builder.build();
			String[] nodes = esProperties.getClusterNodes().split(",");
			TransportAddress[] addressArray = new TransportAddress[nodes.length];
			for (int i = 0; i < nodes.length; i++) {
				String[] nodeArray = nodes[i].split(":");
				addressArray[i] = new TransportAddress(InetAddress.getByName(nodeArray[0]), Integer.parseInt(nodeArray[1]));
			}
			return new PreBuiltXPackTransportClient(settings).addTransportAddresses(addressArray);
		} catch (Exception e) {
			logger.error("初始化ESClient异常", e);
		}
		return null;
	}
	
}
