package kbe.aw.product.rabbitmq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kbe.aw.product.configuration.RabbitConfiguration;
import kbe.aw.product.model.Product;
import kbe.aw.product.repository.ProductRepository;

@Component
public class ProductRequestHandler
{
   @Autowired
   private ObjectMapper objectMapper;
   @Autowired
   private ProductRepository productRepository;

   @RabbitListener(queues = RabbitConfiguration.REQUEST_PRODUCT_QUE)
   public String handleComponentRequest(CustomMessage receivedMessage)
   {
      List<Product> products = new ArrayList<>();

      if(receivedMessage.getMessage().contains("getAllProducts"))
      {
         products.addAll((Collection<? extends Product>) productRepository.findAll());
      }
      else
      {
         int productId = Integer.parseInt(receivedMessage.getMessage());
         Optional<Product> foundProduct = productRepository.findById(productId);

         products.add(foundProduct.get());
      }

      try
      {
         return objectMapper.writeValueAsString(products);
      }
      catch (JsonProcessingException e)
      {
         throw new RuntimeException(e);
      }
   }
}
