package acs.producers;

import acs.exceptions.BadRequestException;
import acs.utils.ExternalServiceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class GeneralRestService {

    private RestTemplate restTemplate;

    @Value("${baseUrl}")
    private String baseUrl;

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

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> checkIfExists(String id, ExternalServiceType externalServiceType){

        Map<String, Object> map;

        switch (externalServiceType) {
            case SHOPPING_CATALOG_SERVICE:
                map = this.restTemplate.getForObject(baseUrl + ":" + shoppingCatalogServicePort + "/" + shoppingCatalogServicePath + "/" + id, Map.class);
                break;
            case TRACKING_SERVICE:
                map =  this.restTemplate.getForObject(baseUrl + ":" + trackingServicePort + "/" + trackingServicePath + "/" + id, Map.class);
                break;
            case PRODUCT_RETURN_AND_REFUND:
                map = this.restTemplate.getForObject(baseUrl + ":" + productReturnAndRefundsServicePort + "/" + productReturnAndRefundsServicePath + "/" + id, Map.class);
                break;
            case BLOG_COMMENTS_SERVICE:
                map = this.restTemplate.getForObject(baseUrl + ":" + blogCommentsServicePort + "/" + blogCommentsServicePath + "/" + id, Map.class);
                break;
            default:
                throw new BadRequestException("Unexpected value: " + externalServiceType + ", please choose one of the defined ExternalServiceTypes from the documentation");
        }

        // in case the external services didn't throw an exception when not found, but return null or empty map
        if (map == null || map.keySet().size() == 0) {
            throw new BadRequestException("The externalId: " + id + " was not found in " + externalServiceType.toString());
        }

        return map;
    }

}
