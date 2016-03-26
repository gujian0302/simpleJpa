package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by gj on 16/3/20.
 */
public class ZookeeperLock implements DistributedReadAndWriteLock {

    private ZooKeeper zooKeeper;

    private ZookeeperLockPathWrapper lockPathWrapper;

    private static Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);

    public String lock(String path, String node, LockMode mode) {

        String createdPath = createLock(path, node, mode);

        SortedSet<ZookeeperLockNode> set = getChildrenNodes(path, node, mode);

        while (!tryGetLock(createdPath, path, node, mode)) {

            String watchPath = lockPathWrapper.wrap(path, node) + set.first().getLockNode();
            try {
                if (exist(watchPath)) {
                    break;
                }

                set = getChildrenNodes(path, node, mode);
            } catch (KeeperException e) {

                logger.error("keepException while exist");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public String tryGetLock(String createdPath, String path, String node, LockMode mode)throws  KeeperException,InterruptedException{
        SortedSet<ZookeeperLockNode> set = getChildrenNodes(path, node, mode);
        if (tryGetLock(createdPath, set, mode)){

            return createdPath;
        }else{
            String watchPath = lockPathWrapper.wrap(path, node) + set.first().getLockNode();
            if(exist(watchPath)){

            }
        }


    }

    public void unlock(String actualPath) throws KeeperException, InterruptedException {
        zooKeeper.delete(actualPath, -1);
    }

    public boolean tryGetLock(String createdPath, SortedSet<ZookeeperLockNode> set, LockMode mode) {

        String createdSerialNumber = createdPath.substring(createdPath.lastIndexOf("-") + 1);

        ZookeeperLockNode first = set.first();
        switch (mode) {
            case READ:

                if (first.getMode().equals(LockMode.WRITE) && first.getSerialNumber().compareTo(createdSerialNumber) < 0) {
                    return false;
                } else {
                    return true;
                }
            case WRITE:

                if (first.getSerialNumber().compareTo(createdSerialNumber) < 0) {
                    return false;
                } else {
                    return true;
                }
            default:
                throw new IllegalArgumentException("lock mode type can not be found");

        }

    }

    public SortedSet<ZookeeperLockNode> getChildrenNodes(String path, String node, LockMode mode) {
        List<String> childrenPath = getChildren(path, node);
        Comparator comparator;
        switch (mode) {
            case READ:
                comparator = LockNodeComparator.getInstance();
                break;


            case WRITE:
                comparator = LockNodeSerialNumberComparator.getInstance();
                break;

            default:
                throw new IllegalArgumentException("lock mode can not be found");
        }
        SortedSet<ZookeeperLockNode> set = new TreeSet<ZookeeperLockNode>(comparator);

        for (String child : childrenPath) {
            ZookeeperLockNode item = new ZookeeperLockNode(child);
            set.add(item);
        }
        return set;
    }

    public String createLock(String path, String node, LockMode mode) {

        try {
            String actualPath = zooKeeper.create(lockPathWrapper.wrap(path, node, mode), null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            logger.debug("created path is:{}", actualPath);

            return actualPath;
        } catch (KeeperException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        return null;
    }


    public List<String> getChildren(String path, String node) {
        try {
            List<String> children = zooKeeper.getChildren(lockPathWrapper.wrap(path, node), false);
            logger.debug("list children:{}", children);
            return children;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean exist(final String completePath) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(completePath, new Watcher() {
            public void process(WatchedEvent event) {
                if (event.getType().equals(Event.EventType.NodeDeleted)) {

                    return  tryGetLock(completePath,completePath,completePath,LockMode.READ);

                }


            }
        });


        return stat != null;
    }

    public void unlock(String path, String node, LockMode mode) {

    }

    public void unlock(String path, String node, String actualPath) {

    }

}
