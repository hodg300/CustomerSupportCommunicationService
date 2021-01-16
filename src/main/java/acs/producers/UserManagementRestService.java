package acs.producers;

import acs.boundary.TicketBoundary;
import acs.logic.utils.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class UserManagementRestService {
    private RestTemplate restTemplate;
    private String url;
    private int port;

    @Value("${userManagementService.port:8081}")
    public void setPort(String port) {
        this.port = Integer.parseInt(port);
    }

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();

        // TODO !!!! configure this url using service configuration
        this.url = "http://localhost:" + port + "/users";
    }

    public User getUser(String email){
        return this.restTemplate.getForObject(this.url + "/{email}", User.class, email);
    }


}
