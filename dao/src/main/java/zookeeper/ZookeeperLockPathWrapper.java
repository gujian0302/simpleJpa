package zookeeper;

/**
 * Created by gj on 16/3/19.
 */
public interface ZookeeperLockPathWrapper {


    String wrap(String path,String locknode,LockMode mode);

    String unwrap(String path);

    String wrap(String path,String locknode);

}

