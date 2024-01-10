class Car {
    String name;
    int year;

    Car (String c, int y){
        name = c; year = y;
    }

    //@Override
    public boolean equals(Car otherCar){
        if(this.name.equals(otherCar.name) && this.year == otherCar.year){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void main (String[] u) {
        Car c1 = new Car("Volvo", 2015);
        Car c2 = new Car("Volvo", 2015);
        System.out.println(c1==c2); // Första utskriften
        System.out.println(c1.equals(c2)); // Andra utskriften
    }
}