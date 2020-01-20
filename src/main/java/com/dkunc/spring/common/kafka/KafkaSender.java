package com.dkunc.spring.common.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


public class KafkaSender {

	private static final String TOPIC_NAME_TEST_TOPIC = "testTopic";
	
	private static Properties makeProps() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		
//		props.put("group.id", "kafka-consumer-group");
//		props.put("bootstrap.servers", "localhost:9092");
//		props.put("key.serializer", StringSerializer.class);
//		props.put("value.serializer", StringSerializer.class);
		
		return properties;
	}
	
	private static KafkaProducer<String, String> makeProducer() {
		KafkaProducer<String, String> producer =
				new KafkaProducer<>(makeProps());
		
		return producer;
	}
	
	public static void send(String message) {
		KafkaProducer<String, String> producer = makeProducer();
		ProducerRecord<String, String> record = 
				new ProducerRecord<>(TOPIC_NAME_TEST_TOPIC, message);
		
		producer.send(record);
		producer.close();
	}
	
}
