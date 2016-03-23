package zookeeper;

/**
 * Created by gj on 16/3/19.
 */
public class ZookeeperConnection {

    private String host;

    private String port;

    private Integer timeout = 3000;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
