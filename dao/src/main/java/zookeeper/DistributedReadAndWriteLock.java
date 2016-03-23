package zookeeper;

/**
 * Created by gj on 16/3/20.
 */
public interface DistributedReadAndWriteLock {

    boolean lock(String path,String node,LockMode mode);

    boolean unlock(String path,String node,LockMode mode);

}
