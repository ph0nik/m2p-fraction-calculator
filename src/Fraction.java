public class Fraction {
    private int numerator;
    private int denumerator;

    public Fraction(int num, int denum) {
        if (denum == 0) throw new IllegalArgumentException();
        else if (denum < 0){
            this.numerator = -num;
            this.denumerator = -denum;
        } else {
            this.numerator = num;
            this.denumerator = denum;
        }
    }

    public Fraction(int num) {
        this(num, 1);
    }

    public Fraction() {
        this(0, 1);
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenumerator() {
        return this.denumerator;
    }

    public double toDouble() {
        return numerator / denumerator;
    }

    public Fraction add(Fraction other) {
        int x = (this.numerator * other.denumerator) + (this.denumerator * other.numerator);
        int y = this.denumerator * other.denumerator;
        int gcd = gcd(x, y);
        return new Fraction(x / gcd, y / gcd);
    }

    public Fraction subtract(Fraction other) {
        return add(new Fraction(-other.getNumerator(), other.getDenumerator()));
    }

    public Fraction multiply(Fraction other) {
        int x = this.numerator * other.numerator;
        int y = this.denumerator * other.denumerator;
        int gcd = gcd(x, y);
        return new Fraction(x / gcd, y / gcd);
    }

    public Fraction divide(Fraction other) throws IllegalArgumentException {

        if (other.getDenumerator() == 0) throw new IllegalArgumentException();
        return this.multiply(new Fraction(other.getDenumerator(), other.getNumerator()));
    }

    private static int gcd(int num, int den) {
        int restDiv;
        while (den != 0) {
            restDiv = num % den;
            num = den;
            den = restDiv;
        }
        return Math.abs(num);
    }

    private void toLowestTerms() {
        int gcd = gcd(this.numerator, this.denumerator);
        this.numerator /= gcd;
        this.denumerator /= gcd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;
        this.toLowestTerms();
        fraction.toLowestTerms();

        if (numerator != fraction.numerator) return false;
        return denumerator == fraction.denumerator;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denumerator;
        return result;
    }

    @Override
    public String toString() {
        return numerator + "/" + denumerator;
    }
}
