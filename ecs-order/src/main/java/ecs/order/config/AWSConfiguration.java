package ecs.order.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AWSConfiguration {

    @Value("${aws.awsAccessKeyId:}")
    private String awsAccessKeyId;

    @Value("${aws.awsSecretAccessKey:}")
    private String awsSecretAccessKey;

    @Bean
    public AWSStaticCredentialsProvider amazonAWSCredentialsProviderDevelopment() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                awsAccessKeyId, awsSecretAccessKey));
    }
}
