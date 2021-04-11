package by.it.calc;

import java.util.Arrays;

class Vector extends Var {
    private double[] value;

    public double[] getValue() {
        return value;
    }

    public Vector(double[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    public Vector(Vector vector) {
        double[] newValue = vector.value;
        this.value = Arrays.copyOf(newValue, newValue.length);
    }

    public Vector(String strVector) {
        String line = strVector.replace("{", "");
        line = line.replaceAll("\\s+", "");
        line = line.replace("}", "");

        line = line.trim();                        //убираем пробелы в конце
        String[] strArray = line.split(",");    // переыодмс стороку в массив строк
        double[] array = new double[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            array[i] = Double.parseDouble(strArray[i]);
            this.value = array;
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append('{');
        String delimiter = "";
        for (double element : value) {
            out.append(delimiter).append(element);
            delimiter = ",";
        }
        out.append('}');
        return out.toString();
    }

    @Override
    public Var add(Var other) throws CalcException {
        if (other instanceof Scalar) {
            Scalar otherScalar = (Scalar) other;
            double[] sum = Arrays.copyOf(this.value, this.value.length);
            for (int i = 0; i < this.value.length; i++) {
                sum[i] += otherScalar.getValue();
            }
            Vector result = new Vector(sum);
            return result;
        } else if (other instanceof Vector) {
            Vector otherVector = (Vector) other;
            if (otherVector.value.length != value.length) {
                throw new CalcException();
            }
            double[] sum = Arrays.copyOf(value, value.length);
            for (int i = 0; i < sum.length; i++) {
                sum[i] += otherVector.value[i];
            }
            Vector result = new Vector(sum);
            return result;
        } else
            return super.add(other);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        if (other instanceof Scalar) {
            Scalar otherScalar = (Scalar) other;
            double[] sub = Arrays.copyOf(this.value, this.value.length);
            for (int i = 0; i < this.value.length; i++) {
                sub[i] += -otherScalar.getValue();
            }
            Vector result = new Vector(sub);
            return result;
        } else if (other instanceof Vector) {
            Vector otherVector = (Vector) other;
            if (otherVector.value.length != value.length) {
                throw new CalcException("Operation error ");
            }
            double[] sub = Arrays.copyOf(value, value.length);
            for (int i = 0; i < sub.length; i++) {
                sub[i] += -otherVector.value[i];
            }
            Vector result = new Vector(sub);
            return result;
        } else
            return super.sub(other);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        if (other instanceof Scalar) {
            Scalar otherScalar = (Scalar) other;
            double[] mult = Arrays.copyOf(this.value, this.value.length);
            for (int i = 0; i < mult.length; i++) {
                mult[i] *= otherScalar.getValue();
            }
            Vector result = new Vector(mult);
            return result;
        } else if (other instanceof Vector) {
            Vector otherVector = (Vector) other;
            if (otherVector.value.length != value.length) {
                throw new CalcException("Operation error ");
            }
            double[] mult = Arrays.copyOf(value, value.length);
            double sum = 0;
            for (int i = 0; i < mult.length; i++) {
                mult[i] *= otherVector.value[i];
                sum += mult[i];
            }
            Scalar result = new Scalar(sum);
            return result;
        } else
            return super.mul(other);
    }

    @Override
    public Var div(Var other) throws CalcException {
        if (other instanceof Scalar) {
            Scalar otherScalar = (Scalar) other;
            if (otherScalar.getValue() == 0) {
                throw new CalcException("Operation error ");
            }
            double[] divr = Arrays.copyOf(this.value, this.value.length);
            for (int i = 0; i < divr.length; i++) {
                divr[i] /= otherScalar.getValue();
            }
            Vector result = new Vector(divr);
            return result;
        } else return super.div(other);
    }
}


