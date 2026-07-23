package rules;

import model.CarObservation;
import model.CarType;
import model.Violation;

public class TruckSpeedRule implements Rule{
    @Override
    public Violation evaluate(CarObservation observation) {
        if(observation.getCarType() == CarType.TRUCK && observation.getSpeed() >60){
            return new Violation("Truck Speed Limit" ,
                    "speed of " + observation.getSpeed() + " exceeded allowed speed 60 ",300);
        }
        return null;
    }
}
