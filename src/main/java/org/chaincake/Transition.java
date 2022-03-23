package org.chaincake;

public final class Transition{
    private Attr<Transition,String> event;
    private Attr<Transition,String> from;
    private Attr<Transition,String> to;

    public Transition(String event, String from, String to, String debug_pattern){
        this.event=new Attr<Transition,String>(this,"event",debug_pattern);
        this.from=new Attr<Transition,String>(this,"from",debug_pattern);
        this.to=new Attr<Transition,String>(this,"to",debug_pattern);
        this.event.value(event);
        this.from.value(from);
        this.to.value(to);
    }

    public String event(){
        return this.event.value();
    }

    public String from(){
        return this.from.value();
    }

    public String to(){
        return this.to.value();
    }

    public Transition(String event, String from, String to){
        this(event,from,to,"");
    }

    public String toString(){
        return "<"+this.getClass().getName()+"> \""+this.event.value()+"\": \""+this.from.value()+"\" -> \""+this.to.value()+"\"";
    }
    public static void main(String args[]){
        DebugArg arg=new DebugArg(args);
        String debug_pattern="";
        Transition t;
        if(arg.contains("debug")){
            debug_pattern=arg.get("debug").get(0);
        }
        t=new Transition("melt","solid","liquid",debug_pattern);
        System.out.println(t);
    }
}