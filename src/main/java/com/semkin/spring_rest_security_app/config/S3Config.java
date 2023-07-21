package com.semkin.spring_rest_security_app.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.amazonaws.auth.profile.internal.ProfileKeyConstants.AWS_ACCESS_KEY_ID;
import static com.amazonaws.auth.profile.internal.ProfileKeyConstants.AWS_SECRET_ACCESS_KEY;

@Configuration
@RequiredArgsConstructor
public class S3Config {
    @Bean
    public AmazonS3 s3client() {
        System.out.println(System.getenv(AWS_ACCESS_KEY_ID));
        System.out.println(System.getenv(AWS_SECRET_ACCESS_KEY));

        AWSCredentials credentials = new BasicAWSCredentials(
                System.getenv(AWS_ACCESS_KEY_ID),
                System.getenv(AWS_SECRET_ACCESS_KEY)
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
