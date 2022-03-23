package org.chaincake;

public final class Attr<K,V> extends Debuggable<K>{
    private V value;
    
    public Attr(K parent,String name){
        super(parent,name,"");
    }

    public Attr(K parent, String name, String debug_pattern){
        super(parent,name,debug_pattern);
    }

    public String toString(){
        return "<"+this.parentName()+"> "+this.name()+" = \""+this.value()+"\"";
    }

    public V value(){
        return this.value;
    }

    public K value(V value){
        this.value=value;
        if( this.isDebug() ) 
            System.out.println(this);
        return this.parent();
    }
}