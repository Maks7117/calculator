package by.it.calc;

import java.util.Objects;

public class CreateVar {

    private CreateVar() {

    }

    static Var createVar(String strVar) throws CalcException {
        if (strVar.matches(Patterns.SCALAR)) {
            return new Scalar(strVar);
        } else if (strVar.matches(Patterns.VECTOR)) {
            return new Vector(strVar);
        } else if (strVar.matches(Patterns.MATRIX)) {
            return new Matrix(strVar);
        } else {
            Var var = Var.getVarMap().get(strVar);
            if (Objects.isNull(var)) {
                throw new CalcException("Operation error ");
            }
            return var;
        }
    }
}
