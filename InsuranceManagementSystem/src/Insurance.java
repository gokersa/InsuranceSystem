import java.util.Date;

public abstract class Insurance {
    private final String name;
    private final double price;
    private final Date date;
    private final User user;

    Insurance (User user, String name, double price, Date date) {
        this.date = date;
        this.user = user;
        this.name = name;
        this.price = price;
    }

    public double calculate () {
        return price;
    }

    public double getPrice(){return price;}
    public String getName() {return name;}
    public String getCustomerName() {return user.getName();}
}
