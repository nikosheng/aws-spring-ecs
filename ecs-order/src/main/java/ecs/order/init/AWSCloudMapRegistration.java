package ecs.order.init;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.servicediscovery.AWSServiceDiscovery;
import com.amazonaws.services.servicediscovery.AWSServiceDiscoveryClient;
import com.amazonaws.services.servicediscovery.AWSServiceDiscoveryClientBuilder;
import com.amazonaws.services.servicediscovery.model.RegisterInstanceRequest;
import com.amazonaws.services.servicediscovery.model.RegisterInstanceResult;
import ecs.order.util.MapBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.UUID;

@Component
public class AWSCloudMapRegistration implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(AWSCloudMapRegistration.class);

    @Value("${aws.servicediscovery.serviceId:}")
    private String cloudMapServiceId;

    @Autowired
    private AWSStaticCredentialsProvider credentialsProvider;

    @Override
    public void run(String... args) throws Exception {
        AWSServiceDiscovery client = AWSServiceDiscoveryClientBuilder.standard()
                .withRegion("ap-southeast-1")
                .withCredentials(credentialsProvider)
                .build();

        InetAddress ipv4 = InetAddress.getLocalHost();

        RegisterInstanceRequest request = new RegisterInstanceRequest()
                .withServiceId(cloudMapServiceId)
                .withInstanceId(UUID.randomUUID().toString().replaceAll("-", ""))
                .withAttributes(
                        new MapBuilder.Builder<String>()
                                .map("AWS_INSTANCE_IPV4", ipv4.getHostAddress())
                                .build().map()
                );
        RegisterInstanceResult result = client.registerInstance(request);
        log.info("AWS Cloud Map Register Instance Result: " + result.getOperationId());
    }
}
