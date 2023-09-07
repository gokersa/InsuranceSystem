import java.awt.*;
import java.util.Date;
import java.time.Instant;
import java.util.Scanner;

public class InsuranceManager {
    InsuranceManager(){}

    public Insurance creatInsurance(User user) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Yaptırmak istediğiniz sigortayı seçiniz : ");
        System.out.println("1-) Sağlık \n 2-)Konut \n 3-)Seyahat \n 4-) Taşıt");
        String choice = inp.nextLine();
        System.out.println("Miktarı giriniz: ");
        double price = inp.nextInt();

        if(choice.equals("1"))  return  new HealthInsurance(user, "Sağlık Sigortası", price*1.23, Date.from(Instant.now()));
        if(choice.equals("2")) return  new ResidenceInsurance(user, "Konut Sigortası", price*1.32, Date.from(Instant.now()));
        if(choice.equals("3")) return  new TravelInsurance(user, "Seyahat Sigortası", price*2.4, Date.from(Instant.now()));
        return  new CarInsurance(user, "Taşıt Sigortası", price*1.62, Date.from(Instant.now()));


    }
    class HealthInsurance extends Insurance {

        HealthInsurance(User user, String name, double price, Date date) {
            super(user, name, price, date);
        }

        @Override
        public double calculate() {
            return super.getPrice();
        }
    }
    private class ResidenceInsurance extends Insurance {
        public ResidenceInsurance(User user, String name, double price, Date date) {
            super(user, name, price, date );
        }
        @Override
        public double calculate() {
            return  super.getPrice();
        }
    }

    private class TravelInsurance extends Insurance {
        public TravelInsurance(User user, String name, double price, Date date) {
            super(user, name, price, date);
        }
        @Override
        public double calculate() {
            return  super.getPrice();
        }
    }

    private class CarInsurance extends Insurance {
        public CarInsurance(User user, String name, double price, Date date) {
            super(user, name, price, date);
        }
        @Override
        public double calculate() {
            return  super.getPrice();
        }
    }
}
