package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gj on 16/3/30.
 */
@Configuration
public class CuratorConfiguration {

    @Autowired
    private CuratorFactory curatorFactory;

    @Bean
    public CuratorFramework curator(){

        return curatorFactory.getClient();
    }
}
