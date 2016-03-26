package zookeeper;

import org.apache.zookeeper.KeeperException;

/**
 * Created by gj on 16/3/20.
 */
public interface DistributedReadAndWriteLock {

    String lock(String path,String node,LockMode mode);

    void unlock(String path,String node,LockMode mode);

    void unlock(String path, String node, String actualPath);

    void unlock(String actualPath) throws KeeperException,  InterruptedException;
}
