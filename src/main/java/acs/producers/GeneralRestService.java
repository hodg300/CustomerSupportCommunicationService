package acs.producers;
import acs.utils.ExternalServiceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class GeneralRestService {

    private RestTemplate restTemplate;
    private String url;

    @Value("${shoppingCatalogService.path}")
    private String shoppingCatalogServicePath;
    @Value("${shoppingCatalogService.port}")
    private String shoppingCatalogServicePort;

    @Value("${productReturnAndRefundsService.path}")
    private String productReturnAndRefundsServicePath;
    @Value("${productReturnAndRefundsService.port}")
    private String productReturnAndRefundsServicePort;

    @Value("${trackingService.path}")
    private String trackingServicePath;
    @Value("${trackingService.port}")
    private String trackingServicePort;

    @Value("${blogCommentsService.path}")
    private String blogCommentsServicePath;
    @Value("${blogCommentsService.port}")
    private String blogCommentsServicePort;

//    @Value("${userManagementService.port:8081}")
//    public void setPort(String port) {
//        this.port = Integer.parseInt(port);
//    }

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();

        // TODO !!!! configure this url using service configuration
        this.url = "http://localhost";
    }

    public Object checkIfExists(String id, ExternalServiceType externalServiceType){

        switch (externalServiceType){

            case SHOPPING_CATALOG_SERVICE:
                System.out.println(this.url + ":" + shoppingCatalogServicePort + "/" + shoppingCatalogServicePath + "/" + id);
                return this.restTemplate.getForObject(this.url + ":{port}/{subUrl}/{id}", Object.class, shoppingCatalogServicePort, shoppingCatalogServicePath, id);

            case TRACKING_SERVICE:
                return this.restTemplate.getForObject(this.url + ":{port}/{subUrl}/{id}", Map.class, trackingServicePort, trackingServicePath, id);

            case TRANSACTION_SERVICE:
                return this.restTemplate.getForObject(this.url + ":{port}/{subUrl}/{id}", Map.class, productReturnAndRefundsServicePort, productReturnAndRefundsServicePath, id);

            case BLOG_COMMENTS_SERVICE:
                return this.restTemplate.getForObject(this.url + ":{port}/{subUrl}/{id}", Map.class, blogCommentsServicePort, blogCommentsServicePath, id);

        }

        return null;

    }
}
