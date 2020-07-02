package com.blibli.demo.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

//@Configuration
//@EnableKafka
public class KafkaConfiguration {
//
//  @Value(value = "${demo.kafka.host}")
//  private String host;
//
//  @Value(value = "${demo.kafka.group-id}")
//  private String groupId;
//
//  @Value(value = "${demo.kafka.session-timeout-in-ms}")
//  private String sessionTimeoutInMs;
//
//  @Value(value = "${demo.kafka.max-poll-records:50}")
//  private String maxPollRecords;
//
//  @Value(value = "${demo.kafka.max-poll-interval-in-ms:300000}")
//  private String maxPollIntervalInMs;
//
//  @Value(value = "${demo.kafka.auto-offset-reset:latest}")
//  private String autoOffsetReset;
//
//  @Bean
//  public ProducerFactory<String, String> producerFactory() {
//    Map<String, Object> properties = new HashMap<>();
//    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.host);
//    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//    return new DefaultKafkaProducerFactory<>(properties);
//  }
//
//  @Bean
//  public ConsumerFactory<String, String> consumerFactory() {
//    Map<String, Object> properties = new HashMap<>();
//    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.host);
//    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//    properties.put(ConsumerConfig.GROUP_ID_CONFIG, this.groupId);
//    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.autoOffsetReset);
//    properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, this.maxPollRecords);
//    properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, this.maxPollIntervalInMs);
//    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//    properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, this.sessionTimeoutInMs);
//    return new DefaultKafkaConsumerFactory<>(properties);
//  }
//
//  @Bean
//  public KafkaTemplate<String, String> kafkaTemplate(
//      ProducerFactory<String, String> producerFactory) {
//    return new KafkaTemplate<>(producerFactory);
//  }
//
//  @Bean
//  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory(
//      ConsumerFactory<String, String> consumerFactory) {
//    ConcurrentKafkaListenerContainerFactory<String, String>
//        concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
//    concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
//    concurrentKafkaListenerContainerFactory.getContainerProperties()
//        .setAckMode(ContainerProperties.AckMode.RECORD);
//    return concurrentKafkaListenerContainerFactory;
//  }

}
