package org.chaincake;
abstract class Debuggable <K>{
    private String debug_pattern="";
    private String name;
    private String parentName;
    private K parent;

    public Debuggable(K parent,String name,String debug_pattern){
        this.name = name;
        this.parent = parent;
        this.parentName=parent.getClass().getName();
        this.debug_pattern=debug_pattern;
    }

    protected String fullName(){
        return this.parentName+":"+this.name();
    }

    protected String name(){
        return this.name;
    }

    protected K parent(){
        return this.parent;
    }

    protected String parentName(){
        return this.parentName;
    }

    protected boolean isDebug(){
        if(debug_pattern=="")
            return false;
        String[] parts = debug_pattern.split(",");
        for (int i=0;i<parts.length;i++){
            if(parts[i].equals(this.fullName()))
                return true;
            if(parts[i].substring(parts[i].length()-1).equals("*") && this.fullName().startsWith(parts[i].substring(0,parts[i].length()-1)))
                return true;
        }
        return false; 
    }
}