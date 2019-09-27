package learning.kafkaDemo.kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author bo.yang
 */
public class ConsumerFastStart {
    /**
     * 注意：1，centos安装Kafka记得关闭防火墙
     *      2，kafka配置文件listeners和advertised.listeners记得写ip地址不要用127.0.0.1和localhost
     *     例如：listeners=PLAINTEXT://10.118.80.53:9092
     *          advertised.listeners=PLAINTEXT://10.118.80.53:9092
     */

    public static final String brokerList = "10.118.80.53:9092";
    public static final String topic = "register";
//    public static final String groupId = "group-demo2";
    public static final String groupId = "allgroup";

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        properties.put("bootstrap.servers", brokerList);
        // 设置消费者组的名称
        properties.put("group.id", groupId);

        //创建一个消费者客户端实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 订阅主题
        consumer.subscribe(Collections.singletonList(topic));

        // 循环消费消息
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
            Thread.sleep(2000);
        }
    }


}
