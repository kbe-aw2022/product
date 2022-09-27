package kbe.aw.product.importer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent>
{
   private final DataImporter dataImporter;

   private static final Logger LOGGER = LoggerFactory.getLogger(StartupApplicationListener.class);

   public StartupApplicationListener(final DataImporter dataImporter)
   {
      this.dataImporter = dataImporter;
   }

   @Override public void onApplicationEvent(ContextRefreshedEvent event)
   {
      dataImporter.importAllHardwareComponents();
      dataImporter.importAllProducts();
      LOGGER.info("Product Service started: Components and Products loaded in database");
   }

}
