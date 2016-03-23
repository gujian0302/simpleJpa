package zookeeper;

import java.util.Comparator;

/**
 * Created by gj on 16/3/20.
 */
public class LockNodeSerialNumberComparator  implements Comparator<ZookeeperLockNode>{
    public int compare(ZookeeperLockNode o1, ZookeeperLockNode o2) {
        return o1.getSerialNumber().compareTo(o2.getSerialNumber());
    }

    private static class LockNodeSerialNumberComparatorHolder{
        private static final LockNodeSerialNumberComparator instance = new LockNodeSerialNumberComparator();
    }


    public static final  LockNodeSerialNumberComparator getInstance(){
        return LockNodeSerialNumberComparatorHolder.instance;
    }
}
