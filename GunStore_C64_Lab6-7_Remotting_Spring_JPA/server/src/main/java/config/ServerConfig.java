package config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.remoting.rmi.RmiServiceExporter;
import service.*;

@Configuration
@ComponentScan({"repository", "service", "domain"})
public class ServerConfig implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext theContext) throws BeansException {
        context = theContext;
    }

    @Bean
    RmiServiceExporter rmiGunServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(GunServiceInterface.class);
        rmiServiceExporter.setService(context.getBean(GunService.class));
        rmiServiceExporter.setServiceName("GunService");
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter rmiGunAccessoryExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(GunAccessoryServiceInterface.class);
        rmiServiceExporter.setService(context.getBean(GunAccessoryService.class));
        rmiServiceExporter.setServiceName("GunAccessoryService");
        return rmiServiceExporter;
    }


    @Bean
    RmiServiceExporter rmiClientServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(ClientServiceInterface.class);
        rmiServiceExporter.setService(context.getBean(ClientService.class));
        rmiServiceExporter.setServiceName("ClientService");
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter rmiOrderServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(OrderServiceInterface.class);
        rmiServiceExporter.setService(context.getBean(OrderService.class));
        rmiServiceExporter.setServiceName("OrderService");
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter rmiAmmoServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(AmmoServiceInterface.class);
        rmiServiceExporter.setService(context.getBean(AmmoService.class));
        rmiServiceExporter.setServiceName("AmmoService");
        return rmiServiceExporter;
    }


}
