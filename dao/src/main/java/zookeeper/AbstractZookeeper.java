package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * Created by gj on 16/3/20.
 */
public abstract class AbstractZookeeper {

    private ZooKeeper zookeeper;

    protected AbstractZookeeper(ZooKeeper zookeeper) {
        this.zookeeper = zookeeper;
    }

    public List<String> getChildren(String path, boolean watch) throws KeeperException, InterruptedException {
        return zookeeper.getChildren(path, watch);
    }
}
