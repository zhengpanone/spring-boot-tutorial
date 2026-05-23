import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * ConsumerSubscribeEmail
 *
 * @author zhengpanone
 * @since 2022-09-16
 */
public class ConsumerSubscribeEmail {
    //队列名称
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String EXCHANGE_FANOUT_INFORM = "inform_exchange_fanout";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.18.215");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            connection = factory.newConnection();
            channel = connection.createChannel();
            //声明交换机 String exchange, BuiltinExchangeType type
            /**
             * 参数明细
             * 1、交换机名称
             * 2、交换机类型，fanout、topic、direct、headers
             * */
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
            //声明队列
            /***
             * 参数明细：
             * 1、队列名称
             * 2、是否持久化
             * 3、是否独占此队列
             * 4、队列不用是否自动删除
             * 5、参数
             * */
            channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
            // 交换机和队列绑定 String queue, String exchange, String routingKey
            /**
             * 参数明细
             * 1、队列名称
             * 2、交换机名称
             * 3、路由key
             * */
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_FANOUT_INFORM, "");

            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    long deliveryTag = envelope.getDeliveryTag();
                    String exchange = envelope.getExchange();
                    //消息内容
                    String message = new String(body, "utf-8");
                    System.out.println(message);
                }
            };
            channel.basicConsume(QUEUE_INFORM_EMAIL, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
