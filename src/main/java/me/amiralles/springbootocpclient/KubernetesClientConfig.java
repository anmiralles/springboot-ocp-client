package me.amiralles.springbootocpclient;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KubernetesClientConfig {

    @Bean
    public KubernetesClient kubernetesClient() {
        // Use the default configuration for KubernetesClient (automatic discovery of config)
        return new DefaultKubernetesClient();
    }
}