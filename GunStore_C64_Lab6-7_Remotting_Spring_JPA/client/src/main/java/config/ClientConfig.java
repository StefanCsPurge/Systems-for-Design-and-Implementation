package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.*;


@Configuration
@ComponentScan({"ui", "service"})
public class ClientConfig {
    @Qualifier("gunServiceServer")
    @Bean
    RmiProxyFactoryBean rmiGunProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/GunService");
        rmiProxyFactoryBean.setServiceInterface(GunServiceInterface.class);
        return rmiProxyFactoryBean;
    }

    @Qualifier("orderServiceServer")
    @Bean
    RmiProxyFactoryBean rmiOrderProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/OrderService");
        rmiProxyFactoryBean.setServiceInterface(OrderServiceInterface.class);
        return rmiProxyFactoryBean;
    }

    @Qualifier("clientServiceServer")
    @Bean
    RmiProxyFactoryBean rmiClientProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/ClientService");
        rmiProxyFactoryBean.setServiceInterface(ClientServiceInterface.class);
        return rmiProxyFactoryBean;
    }

    @Qualifier("ammoServiceServer")
    @Bean
    RmiProxyFactoryBean rmiAmmoProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/AmmoService");
        rmiProxyFactoryBean.setServiceInterface(AmmoServiceInterface.class);
        return rmiProxyFactoryBean;
    }

    @Qualifier("gunAccessoryServiceServer")
    @Bean
    RmiProxyFactoryBean rmiGunAccessoryProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/GunAccessoryService");
        rmiProxyFactoryBean.setServiceInterface(GunAccessoryServiceInterface.class);
        return rmiProxyFactoryBean;
    }


}
