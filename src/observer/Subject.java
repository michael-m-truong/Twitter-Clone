package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String latestData;
    
    public void attach(Observer observer) {
        System.out.println("attatched!!!!");
        observers.add(observer);
    }

    public List<Observer> getObservers() {
        System.out.println("model observerszzzz: " + observers.size());
        return observers;
    }

    public String getLatestData() {
        return latestData;
    }

    public void setLatestData(String data) {
        latestData = data;
    }

    public void notifyObservers() {
        System.out.println("model observers: " + observers.size());
        for (Observer observer: observers) {
            observer.update(this);
        }
    }
}
