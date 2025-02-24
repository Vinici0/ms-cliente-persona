package org.borja.springcloud.msvc.clients.msclients;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.borja.springcloud.msvc.clients.msclients.models.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@EnableR2dbcRepositories
@SpringBootApplication
public class MsClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsClientsApplication.class, args);
    }


    //Para que Kafka pueda deserializar el objeto que recibe
    @Bean
    public ConsumerFactory<String, Client> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Client.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }
    //Se instancia el listener para que pueda escuchar los mensajes que llegan a Kafka
    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Client>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Client> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
