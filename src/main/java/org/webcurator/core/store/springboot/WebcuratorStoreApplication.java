package org.webcurator.core.store.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.webcurator.core.harvester.coordinator.HarvestAgentListenerService;
import org.webcurator.core.harvester.coordinator.HarvestCoordinatorImpl;
import org.webcurator.core.networkmap.service.NetworkMapRemoteController;
import org.webcurator.core.store.RunnableIndex;
import org.webcurator.core.store.WCTIndexer;
import org.webcurator.core.util.WebServiceEndPoint;
import org.webcurator.domain.model.core.HarvestResultDTO;

import java.io.File;
import java.util.Arrays;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"org.webcurator.store",
        "org.webcurator.core.harvester",
        "org.webcurator.core.rest",
        "org.webcurator.core.reader",
        "org.webcurator.core.store.arc",
        "org.webcurator.core.networkmap.service"},
// HarvestAgentListenerService should be running on webcurator-webapp.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {HarvestAgentListenerService.class, HarvestCoordinatorImpl.class, NetworkMapRemoteController.class})
)
public class WebcuratorStoreApplication {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private WebServiceEndPoint wsEndPoint;

    public static void main(String[] args) {
        try {
            SpringApplication.run(WebcuratorStoreApplication.class, args);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            // Note that this is just here for debugging purposes. It can be deleted at any time.
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

//            HarvestResultDTO result = new HarvestResultDTO();
//            result.setOid((long) 22);
//            result.setHarvestNumber(1);
//            result.setTargetInstanceOid((long) 53);
//            File directory = new File("/usr/local/wct/store/53/1");
//            WCTIndexer wctIndexer = new WCTIndexer(restTemplateBuilder);
//            wctIndexer.initialise(result, directory);
//            wctIndexer.setWsEndPoint(wsEndPoint);
//
//            wctIndexer.setMode(RunnableIndex.Mode.REMOVE);
//            Thread processorRemove = new Thread(wctIndexer);
//            processorRemove.start();
//            processorRemove.join();
//            System.out.println("Remove index finished");
//
//            wctIndexer.setMode(RunnableIndex.Mode.INDEX);
//            Thread processorIndex = new Thread(wctIndexer);
//            processorIndex.start();
//            processorIndex.join();
            System.out.println("Indexing finished");
        };
    }
}
