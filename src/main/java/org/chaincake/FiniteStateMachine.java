package org.chaincake;
import java.util.*;

public class FiniteStateMachine{
    private Attr<FiniteStateMachine,String> _state;
    private UniqueList<FiniteStateMachine,String> _states;
    private UniqueList<FiniteStateMachine,String> _events;
    private UniqueList<FiniteStateMachine,Transition> _transitions;
    final Map<String,StateEvent> stateEvents=new HashMap<>();

    public class EventTransition implements StateEvent{
        private String from;
        private String to;
        public EventTransition(String from, String to){
            this.from=from;
            this.to=to;
        }
        public void changeState(){
            if(_state.value() == this.from){
                _state.value(this.to);
                //System.out.println("Changes: "+this.from+" -> "+this.to);
            } else {
                //System.out.println("Cannot change from: "+_state.value()+" , expected: "+this.from);
            }
        }
    } 

    public FiniteStateMachine(String debug_pattern){
        this._state=new Attr<FiniteStateMachine,String>(this,"state",debug_pattern);
        this._events=new UniqueList<FiniteStateMachine,String>(this,"events",debug_pattern);
        this._states=new UniqueList<FiniteStateMachine,String>(this,"states",debug_pattern);
        this._transitions=new UniqueList<FiniteStateMachine,Transition>(this,"transitions",debug_pattern);
    }

    public Set<String> events(){
        return this._events.list();
    }

    public Set<String> states(){
        return this._states.list();
    }

    public String state(){
        return this._state.value();
    }

    public FiniteStateMachine on(String value){
        if(this._events.contains(value)){
            this.stateEvents.get(value).changeState();
        }
        return this;
    }

    public FiniteStateMachine state(String value){
        this._state.value(value);
        return this;
    }

    public FiniteStateMachine transition(Transition t){
        if(!this._transitions.contains(t.event())){
            this._transitions.list(t)
                ._states.list(t.from())
                ._states.list(t.to())
                ._events.list(t.event());
            this.stateEvents.put(t.event(),new EventTransition(t.from(),t.to()));
        }   
        return this;
    }

    public static void main(String args[]){
        DebugArg arg=new DebugArg(args);
        String debug_pattern="";
        if(arg.contains("debug")){
            debug_pattern=arg.get("debug").get(0);
        }
        FiniteStateMachine fsm=new FiniteStateMachine(debug_pattern);
        fsm.transition(new Transition("melt","solid","liquid",debug_pattern))
            .transition(new Transition("freeze","liquid","solid",debug_pattern))
            .transition(new Transition("vaporize","liquid","gas",debug_pattern))
            .transition(new Transition("condense","gas","liquid",debug_pattern))
            .state("liquid");
        System.out.println(fsm.states());
        System.out.println(fsm.events());
        System.out.println(fsm.state());
        fsm.on("melt").on("freeze");

    }
}
