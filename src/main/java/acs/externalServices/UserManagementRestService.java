package acs.externalServices;

import acs.logic.utils.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;

@Service
public class UserManagementRestService {
    private RestTemplate restTemplate;

    @Value("${userManagementService.url}")
    private String userManagementServiceUrl;

    @Value("${userManagementService.path}")
    private String shoppingCatalogServicePath;
    @Value("${userManagementService.port}")
    private String shoppingCatalogServicePort;

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();
    }

    public User getUser(String email){
        return this.restTemplate.getForObject(userManagementServiceUrl + ":" + shoppingCatalogServicePort + "/" + shoppingCatalogServicePath + "/" + email, User.class);
    }


}
