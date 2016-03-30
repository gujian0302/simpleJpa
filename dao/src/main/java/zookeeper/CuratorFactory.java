package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PreDestroy;

/**
 * Created by gj on 16/3/30.
 */

public class CuratorFactory implements InitializingBean {

    private String connectString;

    private String namespace;

    private Integer sleepBetweenRetires;

    private Integer timeout;

    private CuratorFramework client;

    public void afterPropertiesSet() throws Exception {

        init();

    }


    @PreDestroy
    public void destory() {
        client.close();

    }

    public void init() {
        client = CuratorFrameworkFactory.builder().connectString(connectString).namespace(namespace).retryPolicy(new RetryNTimes(Integer.MAX_VALUE, sleepBetweenRetires)).connectionTimeoutMs(timeout).build();
        client.start();
    }


    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Integer getSleepBetweenRetires() {
        return sleepBetweenRetires;
    }

    public void setSleepBetweenRetires(Integer sleepBetweenRetires) {
        this.sleepBetweenRetires = sleepBetweenRetires;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
