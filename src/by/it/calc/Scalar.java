package by.it.calc;

class Scalar extends Var {
    private double value;
    public double getValue() {
        return value;
    }

    public Scalar(double value){
        this.value = value;
    }


    public Scalar(Scalar newscalar){
        this.value = newscalar.value;

    }


    public Scalar(String strScalar){
        this.value = Double.parseDouble(strScalar);

    }

    public String toString() {
        return Double.toString(value);
    }

    @Override
    public Var add(Var other)throws CalcException {
        if (other instanceof Scalar){
            Scalar otherScalar = (Scalar) other;
            double sum = this.value + otherScalar.value;
            Scalar result = new Scalar(sum);
            return result;
        } else return other.add(this);

    }

    @Override
    public Var sub(Var other) throws CalcException {
        if (other instanceof Scalar) {
            Scalar otherScalar = (Scalar) other;
            double sub = this.value - otherScalar.value;
            Scalar result = new Scalar(sub);
            return result;
        } else return other.sub(this).mul(new Scalar(-1));
    }

    @Override
    public Var mul(Var other) throws CalcException{
        if (other instanceof Scalar) {
            Scalar otherScalar = (Scalar) other;
            double mul = this.value * otherScalar.value;
            Scalar result = new Scalar(mul);
            return result;
        } else return other.mul(this);
    }

    @Override
    public Var div(Var other) throws CalcException {
        if (other instanceof Scalar) {
            Scalar otherScalar = (Scalar) other;
            if(otherScalar.value == 0) {
                throw new CalcException(" division by zero");
            }
            double div = this.value / otherScalar.value;
            Scalar result = new Scalar(div);
            return result;

        } else return super.div(other);
    }
}
