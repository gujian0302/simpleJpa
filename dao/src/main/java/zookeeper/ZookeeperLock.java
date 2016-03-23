package zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by gj on 16/3/20.
 */
public class ZookeeperLock implements  DistributedReadAndWriteLock{
    private ZooKeeper zooKeeper;

    private ZookeeperLockPathWrapper lockPathWrapper;

    private static Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);

    public boolean lock(String path, String node, LockMode mode) {

        String createdPath = createLock(path,node,mode);

        String createdSerialNumber =  createdPath.substring(createdPath.lastIndexOf("-") + 1);
        List<String> children = getChildren(path,node);
        SortedSet<ZookeeperLockNode> set;

        if(mode == LockMode.READ) {
            set = new TreeSet<ZookeeperLockNode>(LockNodeComparator.getInstance());
        }else{
            set = new TreeSet<ZookeeperLockNode>(LockNodeSerialNumberComparator.getInstance());
        }
        for(String child : children){
            ZookeeperLockNode item= new ZookeeperLockNode(child);
            set.add(item);
        }

        ZookeeperLockNode first = set.first();
        if(first.getMode().equals(LockMode.WRITE) && first.getSerialNumber().compareTo(createdSerialNumber) < 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean unlock(String path, String node, LockMode mode) {
        return false;
    }

    public String createLock(String path,String node, LockMode mode){

        try {
            String actualPath =  zooKeeper.create(lockPathWrapper.wrap(path, node, mode), null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            logger.debug("created path is:{}",actualPath);

            return actualPath;
        }catch(KeeperException e){

            e.printStackTrace();

        }catch(InterruptedException e) {

            e.printStackTrace();
        }

        return null;
    }


    public List<String> getChildren(String path,String node){

        try {
            List<String> children = zooKeeper.getChildren(lockPathWrapper.wrap(path, node), false);
            logger.debug("list children:{}",children);
            return children;
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (KeeperException e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean exist(String completePath) throws KeeperException,InterruptedException{
        Stat stat = zooKeeper.exists(completePath, true);
        return stat != null;
    }




}
