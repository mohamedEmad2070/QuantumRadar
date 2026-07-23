package rules;

import model.CarObservation;
import model.CarType;
import model.Violation;

public class PrivateSpeedRule implements Rule {
    @Override
    public Violation evaluate(CarObservation observation) {
        if (observation.getCarType() == CarType.PRIVATE && observation.getSpeed() > 80){
            return new Violation("Private Speed Limit",
                    "speed of " + observation.getSpeed() + " exceeded allowed speed 80 ",300 );
        }
        return null;
    }
}
