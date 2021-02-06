package acs.externalServices;

import acs.exceptions.BadRequestException;
import acs.utils.SubjectType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class GeneralRestService {

    private RestTemplate restTemplate;

    @Value("${shoppingCatalogService.url}")
    private String shoppingCatalogServiceUrl;
    @Value("${shoppingCatalogService.path}")
    private String shoppingCatalogServicePath;
    @Value("${shoppingCatalogService.port}")
    private String shoppingCatalogServicePort;


    @Value("${productReturnAndRefundsService.url}")
    private String productReturnAndRefundsServiceUrl;
    @Value("${productReturnAndRefundsService.path}")
    private String productReturnAndRefundsServicePath;
    @Value("${productReturnAndRefundsService.port}")
    private String productReturnAndRefundsServicePort;


    @Value("${trackingService.url}")
    private String trackingServiceUrl;
    @Value("${trackingService.path}")
    private String trackingServicePath;
    @Value("${trackingService.port}")
    private String trackingServicePort;


    @Value("${blogCommentsService.url}")
    private String blogCommentsServiceUrl;
    @Value("${blogCommentsService.path}")
    private String blogCommentsServicePath;
    @Value("${blogCommentsService.port}")
    private String blogCommentsServicePort;


    @Value("${shoppingCartService.url}")
    private String shoppingCartServiceUrl;
    @Value("${shoppingCartService.path}")
    private String shoppingCartServicePath;
    @Value("${shoppingCartService.port}")
    private String shoppingCartServicePort;

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> checkIfExists(String id, SubjectType subjectType){

        Map<String, Object> map;

        switch (subjectType) {
            case SHOPPING_CATALOG_SERVICE:
                map = this.restTemplate.getForObject(shoppingCatalogServiceUrl + ":" + shoppingCatalogServicePort + "/" + shoppingCatalogServicePath + "/" + id, Map.class);
                break;
            case PRODUCT_RETURN_AND_REFUND:
                map = this.restTemplate.getForObject(productReturnAndRefundsServiceUrl + ":" + productReturnAndRefundsServicePort + "/" + productReturnAndRefundsServicePath + "/" + id, Map.class);
                break;
            case TRACKING_SERVICE:
                map =  this.restTemplate.getForObject(trackingServiceUrl + ":" + trackingServicePort + "/" + trackingServicePath + "/" + id, Map.class);
                break;
            case BLOG_COMMENTS_SERVICE:
                map = this.restTemplate.getForObject(blogCommentsServiceUrl + ":" + blogCommentsServicePort + "/" + blogCommentsServicePath + "/" + id, Map.class);
                break;
            case CUSTOMER_SHOPPING_CART_SERVICE:
                map = this.restTemplate.getForObject(shoppingCartServiceUrl + ":" + shoppingCartServicePort + "/" + shoppingCartServicePath + "/" + id, Map.class);
                break;
            default:
                throw new BadRequestException("Unexpected value: " + subjectType + ", please choose one of the defined ExternalServiceTypes from the documentation");
        }

        // in case the external services didn't throw an exception when not found, but return null or empty map
        if (map == null || map.keySet().size() == 0) {
            throw new BadRequestException("The externalId: " + id + " was not found in " + subjectType.toString());
        }

        return map;
    }

}
