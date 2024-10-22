package me.amiralles.springbootocpclient;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenShiftClientService {

    private final KubernetesClient kubernetesClient;

    // Inject the KubernetesClient via constructor
    public OpenShiftClientService(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    public List<String> getPodNamesInNamespace(String namespace) {
        try {
            // Get the list of pods in the namespace
            PodList podList = kubernetesClient.pods().inNamespace(namespace).list();

            // Extract the pod names
            List<String> podNames = podList.getItems()
                    .stream()
                    .map(pod -> pod.getMetadata().getName())
                    .collect(Collectors.toList());

            return podNames;

        } catch (KubernetesClientException e) {
            throw new RuntimeException("Error fetching pods: " + e.getMessage(), e);
        }
    }

    public int getPodCountInNamespace(String namespace) {
        try {
            // Get the list of pods in the namespace
            PodList podList = kubernetesClient.pods().inNamespace(namespace).list();

            // Return the pod count
            return podList.getItems().size();

        } catch (KubernetesClientException e) {
            throw new RuntimeException("Error fetching pods: " + e.getMessage(), e);
        }
    }

    // Method to get pod names starting with a specific prefix
    public List<String> getPodsWithPrefix(String namespace, String prefix) {
        try {
            // Fetch the list of pods in the specified namespace
            PodList podList = kubernetesClient.pods().inNamespace(namespace).list();

            // Filter pod names by the prefix and collect them into a list
            List<String> podNames = podList.getItems().stream()
                    .map(pod -> pod.getMetadata().getName())
                    .filter(podName -> podName.startsWith(prefix)) // Filter by prefix
                    .collect(Collectors.toList());

            return podNames;

        } catch (KubernetesClientException e) {
            throw new RuntimeException("Error fetching pods: " + e.getMessage(), e);
        }
    }
}
