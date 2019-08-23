package kafka.produce;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author bo.yang
 */
public class ProducerFastStart {

    public static final String brokerList = "10.118.80.53:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        properties.put("bootstrap.servers", brokerList);

        // 配置生产者客户端参数并创建kafkaProducer实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        // 构建所需要发送的消息
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello,kafka!");

        // 发送消息
        try {
            producer.send(record);
        }catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭生产者客户端示例
        producer.close();


    }
}
