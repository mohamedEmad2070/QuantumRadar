package rules;

import model.CarObservation;
import model.Violation;

public class SeatbeltRule implements Rule{
    @Override
    public Violation evaluate(CarObservation observation) {
        if (!observation.isSeatbeltFastened()){
            return new Violation("Seatbelt",
                    " Seatbelt not fastned "
            ,100);
        }
        return null;
    }
}
