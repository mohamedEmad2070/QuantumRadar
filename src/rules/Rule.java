package rules;

import model.CarObservation;
import model.Violation;

public interface Rule {
    Violation evaluate(CarObservation observation);
}

