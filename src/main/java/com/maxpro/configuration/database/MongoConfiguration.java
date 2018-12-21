package com.maxpro.configuration.database;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
public class MongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${jms.server.url}")
    private String jmsServerURL;

    @Value("${jms.server.port}")
    private String jmsServerPort;

    @Value("${jms.queue.address}")
    private String jmsMessageQueueName;

    @Value("${jms.message.property.name}")
    private String jmsMessagePropertyName;


    public String getJMSServerURL() {
        return jmsServerURL;
    }

    public String getJMSServerPort() {
        return jmsServerPort;
    }

    public String getJMSMessageQueueName() {
        return jmsMessageQueueName;
    }

    public String getJMSMessagePropertyName() {
        return jmsMessagePropertyName;
    }
    
    @Bean
    public MongoClient mongoClient() throws IOException {
    //    MongoCredential credential = MongoCredential.createCredential(userName, database, getSecurePassword().toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port));

        return mongoClient;
    }

    private String getSecurePassword() throws IOException {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(System.getProperty("auth.key"));
        Properties props = new EncryptableProperties(encryptor);
        props.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        return props.getProperty("spring.data.mongodb.password");
    }

}
