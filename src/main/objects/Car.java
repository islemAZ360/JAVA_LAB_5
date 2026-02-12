package main.objects;

// V1.0.1: Use IllegalArgumentException instead Exception, because always goes with throws
public class Car {
    private Boolean cool; //Поле не может быть null

    public Car(Boolean cool) {
        this.setCar(cool);
    }

    public boolean isCool() {
        return this.cool;
    }

    public void setCar(Boolean cool) {
        if (cool == null) throw new IllegalArgumentException("Cool can not be null!");
        this.cool = cool;
    }
}

// V1.0.0: Use Exception
//public class Car {
//    private Boolean cool; //Поле не может быть null
//
//    public Car(Boolean cool) throws Exception {
//        this.setCar(cool);
//    }
//
//    public void setCar(Boolean cool) throws Exception {
//        if (cool == null) throw new Exception("Cool can not be null!");
//        this.cool = cool;
//    }
//}
