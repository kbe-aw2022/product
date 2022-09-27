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
import kbe.aw.product.model.HardwareComponent;
import kbe.aw.product.repository.HardwareComponentRepository;

@Component
public class HardwareComponentRequestHandler
{
   @Autowired
   private HardwareComponentRepository hardwareComponentRepository;
   @Autowired
   private ObjectMapper objectMapper;

   @RabbitListener(queues = RabbitConfiguration.REQUEST_COMPONENT_QUE)
   public String handleComponentRequest(CustomMessage receivedMessage)
   {
      List<HardwareComponent> hardwareComponentList = new ArrayList<>();

      if(receivedMessage.getMessage().contains("getAllHardwareComponents"))
      {
         hardwareComponentList.addAll((Collection<? extends HardwareComponent>) hardwareComponentRepository.findAll());
      }
      else
      {
         int hardwareComponentId = Integer.parseInt(receivedMessage.getMessage());
         Optional<HardwareComponent> foundHardwareComponent = hardwareComponentRepository.findById(hardwareComponentId);

         hardwareComponentList.add(foundHardwareComponent.get());
      }

      try
      {
         return objectMapper.writeValueAsString(hardwareComponentList);
      }
      catch (JsonProcessingException e)
      {
         throw new RuntimeException(e);
      }
   }
}
