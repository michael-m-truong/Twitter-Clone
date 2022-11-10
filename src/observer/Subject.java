package observer;

import java.util.ArrayList;
import java.util.List;

import composite.INotification;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();
    private INotification latestData;
    
    public void attach(Observer observer) {
        System.out.println("attatched!!!!");
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public List<Observer> getObservers() {
        System.out.println("model observerszzzz: " + observers.size());
        return observers;
    }

    public INotification getLatestData() {
        return latestData;
    }

    public void setLatestData(INotification data) {
        latestData = data;
    }

    public void notifyObservers() {
        System.out.println("model observers: " + observers.size());
        for (Observer observer: observers) {
            observer.update(this);
        }
    }
}
