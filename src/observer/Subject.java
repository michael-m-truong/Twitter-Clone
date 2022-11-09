package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();
    private List<String> latestData = new ArrayList<>();
    
    public void attach(Observer observer) {
        observers.add(observer);
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public List<String> getLatestData() {
        return latestData;
    }

    public void setLatestData(String data, int index) {
        latestData.add(index, data);
    }

    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update(this);
        }
    }
}
