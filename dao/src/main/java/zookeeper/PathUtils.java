package zookeeper;

/**
 * Created by gj on 16/3/19.
 */
public class PathUtils {

    public static String format(String url){
        if(url == null){
            throw  new  NullPointerException("zookeeper_url can not be null");
        }

        if(url.equals("")){
            return "/";
        }

        return null;
    }


}
