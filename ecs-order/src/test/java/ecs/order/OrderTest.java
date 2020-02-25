package ecs.order;

import com.alibaba.fastjson.JSONObject;
import ecs.order.model.Order;
import ecs.order.service.OrderService;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@ActiveProfiles("test")
public class OrderTest {
    public static Logger logger = LoggerFactory.getLogger(OrderTest.class);

//    @ClassRule
//    public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, false, "order");

//    @Autowired
//    private KafkaListenerBean kafkaListenerBean;

//    @Autowired
//    private OrderService orderService;

//    @BeforeClass
//    public static void setUpBeforeClass() {
//        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getEmbeddedKafka().getBrokersAsString());
//    }

    @Test
    public void testFastJson() {
//        String str = "{\"createTimestamp\":1582446455439,\"customerId\":2,\"itemId\":1,\"orderId\":3,\"shipmentAddress\":\"GangNam Street, Seoul, Korea\",\"updateTimestamp\":1582446455439}";
        Order order = Order.OrderBuilder.newBuilder().emptyOrder();
        String str = JSONObject.toJSONString(order);
        Order parseorder = JSONObject.parseObject(str, Order.class);
        System.out.println("");
//        System.out.println(JSONObject.isValid(str));
    }

//    @Test
//    public void orderCreatedSendsKafkaMassage() throws Exception {
//        int receivedBefore = kafkaListenerBean.getReceived();
//        orderService.createOrder(new Order(1L, 1L, 1L, 1L));
//        int i = 0;
//        while (kafkaListenerBean.getReceived() == receivedBefore && i < 5) {
//            Thread.sleep(1000);
//            i++;
//        }
//    }
}
