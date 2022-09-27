package kbe.aw.product.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration
{
   public static final String REQUEST_COMPONENT_QUE = "request_show_hardware_component_que";
   public static final String REQUEST_HARDWARE_COMPONENT_EXCHANGE = "request_hardware_component_exchange";

   //Product
   public static final String REQUEST_PRODUCT_QUE = "request_show_product_que";
   public static final String REQUEST_PRODUCT_EXCHANGE = "request_product_exchange";

   public static final String MESSAGE_ROUTING_KEY = "message_routingKey";

   @Bean
   public MessageConverter messageConverter()
   {
      return new Jackson2JsonMessageConverter();
   }

   @Bean
   public AmqpTemplate template(ConnectionFactory connectionFactory)
   {
      RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
      rabbitTemplate.setMessageConverter(messageConverter());
      return rabbitTemplate;
   }

   private static class RequestHardwareComponentConfiguration
   {
      @Bean
      @Qualifier(REQUEST_COMPONENT_QUE)
      public Queue request_show_hardware_component_que()
      {
         return new Queue(REQUEST_COMPONENT_QUE);
      }

      @Bean
      @Qualifier(REQUEST_HARDWARE_COMPONENT_EXCHANGE)
      public TopicExchange request_hardware_component_exchange()
      {
         return new TopicExchange(REQUEST_HARDWARE_COMPONENT_EXCHANGE);
      }

      @Bean
      public Binding binding_show_component_with_component_exchange(@Qualifier(REQUEST_COMPONENT_QUE) Queue queue,
            @Qualifier(REQUEST_HARDWARE_COMPONENT_EXCHANGE) TopicExchange exchange)
      {
         return BindingBuilder
               .bind(queue)
               .to(exchange)
               .with(MESSAGE_ROUTING_KEY);
      }
   }

   private static class RequestProductConfiguration
   {
      @Bean
      @Qualifier(REQUEST_PRODUCT_QUE)
      public Queue request_show_product_que()
      {
         return new Queue(REQUEST_PRODUCT_QUE);
      }


      @Bean
      @Qualifier(REQUEST_PRODUCT_EXCHANGE)
      public TopicExchange request_product_exchange()
      {
         return new TopicExchange(REQUEST_PRODUCT_EXCHANGE);
      }

      @Bean
      public Binding binding_show_product_with_exchange(@Qualifier(REQUEST_PRODUCT_QUE) Queue queue,
            @Qualifier(REQUEST_PRODUCT_EXCHANGE) TopicExchange exchange)
      {
         return BindingBuilder
               .bind(queue)
               .to(exchange)
               .with(MESSAGE_ROUTING_KEY);
      }
   }
}
