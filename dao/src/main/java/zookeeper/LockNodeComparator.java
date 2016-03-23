package zookeeper;

import java.util.Comparator;

/**
 * Created by gj on 16/3/20.
 */
public class LockNodeComparator implements Comparator<ZookeeperLockNode> {
    public int compare(ZookeeperLockNode o1, ZookeeperLockNode o2) {
        int modeCompare = o1.getMode().compareTo(o2.getMode());
        if(modeCompare==0) {
            return o1.getSerialNumber().compareTo(o2.getSerialNumber());
        }else{
            return modeCompare;
        }
    }


    public static  final   LockNodeComparator getInstance(){
        return LockNodeComparatorHolder.instance;
    }

    private static class LockNodeComparatorHolder {
        private static final  LockNodeComparator instance = new LockNodeComparator();
    }
}
