package me.amiralles.springbootocpclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class OpenShiftClientController {

    private final OpenShiftClientService openShiftClientService;

    public OpenShiftClientController(OpenShiftClientService openShiftClientService) {
        this.openShiftClientService = openShiftClientService;
    }

    @GetMapping("/pods")
    public List<String> getPodNames(@RequestParam String namespace) {
        return openShiftClientService.getPodNamesInNamespace(namespace);
    }

    @GetMapping("/podCount")
    public int getPodCount(@RequestParam String namespace) {
        return openShiftClientService.getPodCountInNamespace(namespace);
    }

    // Endpoint to get pod names starting with a specific prefix
    @GetMapping("/podsWithPrefix")
    public List<String> getPodsWithPrefix(@RequestParam String namespace, @RequestParam String prefix) {
        return openShiftClientService.getPodsWithPrefix(namespace, prefix);
    }
}