import java.util.Scanner;

public class AddressManager {

    public static void addAddress (User user, Address address){
        user.getAddressList().add(address);
    }

    public static void removeAddress(User user, Address address) {
        user.getAddressList().remove(address);
    }

    public static Address creatAddress() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter address : ");
        String adr = inp.nextLine();
        return new HomeAddress(adr);
    }
}

class HomeAddress implements Address {
    private String address;

    public HomeAddress (String address) {
        this.address = address;
    }


    @Override
    public String getAdress() {
        return address;
    }

    @Override
    public void setAdress(String address) {
        this.address = address;
    }
}

class BusinessAddress implements Address {

    @Override
    public String getAdress() {
        return null;
    }

    @Override
    public void setAdress(String address) {

    }
}
