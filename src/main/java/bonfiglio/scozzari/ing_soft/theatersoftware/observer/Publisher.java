package bonfiglio.scozzari.ing_soft.theatersoftware.observer;

import java.util.List;

public interface Publisher {
     static void notifyObservers(List<Observers> observers){
        for (Observers o: observers) {
            o.update();
        }
    }
}
