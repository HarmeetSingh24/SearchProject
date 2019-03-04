package singh.harmeet.com.searchproject1.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

public class Counter {

    private int counter;
    private String query;
    private List<Event> eventList;

    @Inject
    public Counter(){
        this.eventList = new ArrayList<>();
        this.counter = 1;
        this.query = "";
    }

    public Counter(String query){
        this();
        this.query = query;
    }

    public List<Event> getEventList(){
        return eventList;
    }

    public int getListCount(){
       return eventList.size();
    }

    public String getCounter(){
        return Integer.valueOf(counter).toString();
    }

    public String getQuery(){
        return query;
    }

    public void updateList(List<Event> events){
        if(this.eventList!=null && !eventList.isEmpty()){
            this.eventList.addAll(events);
        }else{
            this.eventList = events;
        }
        this.counter++;
    }
}
