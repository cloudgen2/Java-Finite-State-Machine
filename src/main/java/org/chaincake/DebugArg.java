package org.chaincake;
import java.util.*;

public class DebugArg{
    final Map<String, List<String>> params = new HashMap<>();
    List<String> options = null;

    public boolean contains(String key){
        return params.containsKey(key);
    }

    public List<String> get(String key){
        return params.get(key);
    }

    public DebugArg(String args[]){
        for (int i = 0; i < args.length; i++) {
            final String a = args[i];
            if (a.charAt(0) == '-' && a.charAt(1) == '-' ) {
                if (a.length() < 3) {
                    System.err.println("Error at argument " + a);
                    return;
                }
                options = new ArrayList<>();
                params.put(a.substring(2), options);
            } else if (options != null) {
                options.add(a);
            } else {
                System.err.println("Illegal parameter usage");
                return;
            }
        }

    }
}