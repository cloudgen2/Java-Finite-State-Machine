package org.chaincake;
import java.util.*;

public class UniqueList<K,V> extends Debuggable<K>{
    final Map<V,Boolean> _value = new HashMap<>();

    public UniqueList(K parent,String name){
        super(parent,name,"");
    }

    public UniqueList(K parent,String name, String debug_pattern){
        super(parent,name,debug_pattern);
    }

    public boolean contains(String key){
        return this._value.containsKey(key);
    }

    public K list(V key){
        this._value.put(key,true);
        if(this.isDebug()){
            System.out.println(this);
        }
        return this.parent();
    }

    public Set<V> list(){
        return this._value.keySet();
    }
    public String toString(){
        return "<"+this.parentName()+"> "+this.name()+" = \""+this.list()+"\"";
    }
}