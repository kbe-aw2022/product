package kbe.aw.product.importer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import kbe.aw.product.model.HardwareComponent;
import kbe.aw.product.model.Product;
import kbe.aw.product.repository.HardwareComponentRepository;
import kbe.aw.product.repository.ProductRepository;

@Service
public class DataImporter
{
   @Autowired
   private HardwareComponentRepository hardwareComponentRepository;
   @Autowired
   private ProductRepository productRepository;

   public void importAllHardwareComponents()
   {
      String uri = "http://warehouse:8080/hardwarecomponents";
      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<List<HardwareComponent>> responseEntity =
            restTemplate.exchange(
                  uri,
                  HttpMethod.GET,
                  null,
                  new ParameterizedTypeReference<List<HardwareComponent>>()
                  {
                  }
            );

      List<HardwareComponent> hardwareComponents = responseEntity.getBody();

      hardwareComponentRepository.saveAll(hardwareComponents);
   }

   public void importAllProducts()
   {
      String uri = "http://warehouse:8080/products";
      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<List<Product>> responseEntity =
            restTemplate.exchange(
                  uri,
                  HttpMethod.GET,
                  null,
                  new ParameterizedTypeReference<List<Product>>()
                  {
                  }
            );

      List<Product> products = responseEntity.getBody();

      productRepository.saveAll(products);


   }
}
