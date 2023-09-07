import java.util.ArrayList;
import java.util.Scanner;


public class AccountManager {

    ArrayList<Account> accounts;
    User currentUser;
    Account account;
    AccountManager(){
        accounts = new ArrayList<>();
        run();
    }

    public void run(){
        Scanner inp = new Scanner(System.in);
        String choise = "GO";
        while(!choise.equals("3")) {
            System.out.println("1-) Yeni bir hesap yarat");
            System.out.println("2-) Hesaba giriş yap");
            System.out.println("3-) Çıkış yap");
            System.out.println("4-) Admin girişi");
            System.out.println("Seçiminiz : ");
            choise = inp.nextLine();

            if (choise.equals("1")) {
                creatAccount();
            } else if ( choise.equals("2")) {
                System.out.println("E-mail adresinizi giriniz : ");
                String email = inp.nextLine();
                System.out.println("Parola giriniz : ");
                String password = inp.nextLine();

                login(email,password);
            } else if (choise.equals("4")) {
                System.out.println("Sistemdeki tüm hesaplar listeleniyor...");
                for (Account a : getAccounts()) {
                    System.out.println("E-mail : " + a.getUser().getEmail() + "Parola : " + a.getUser().getPassword());
                }
                System.out.println("Sistemdeki Sigortalı kullanıcılar listeneliyor...");
                for(Insurance a : User.getInsuranceList()) {
                    System.out.println("Kullanıcı ismi : " + a.getName() +  "Toplam Sigorta miktarı : " + a.getPrice()) ;
                }
            }

        }
    }

    public void creatAccount() {
        Scanner inp = new Scanner(System.in);

        String name, surname, email, password, job, age;
        System.out.println("İsminizi giriniz : " ); name = inp.nextLine();
        System.out.println("Soyisminizi giriniz : " ); surname = inp.nextLine();
        System.out.println("E-mail adresinizi giriniz : " ); email = inp.nextLine();
        System.out.println("Parolanızı giriniz : " ); password = inp.nextLine();
        System.out.println("Mesleğinizi giriniz : " ); job = inp.nextLine();
        System.out.println("Yaşınızı giriniz : " ); age = inp.nextLine();

        User user = new User(name, surname,email,password,job, age);

        System.out.println("Açmak istediğiniz hesap kişisel kullanım için ise 1'e kurumsal kullanım için ise 2'ye basınız : ");
        name = inp.nextLine();
        Account prop = null;
        if(name.equals("1")) {
            prop = new Account.Individual(user);
        }
        else if (name.equals("2")) {
            prop = new Account.Enterprise(user);
        }

        accounts.add(prop);
    }

    public void login (String email, String password) {
        for (Account account : accounts) {
            try {
                account.login(email,password);
                if(account.isLogin()) {
                    currentUser = account.getUser();
                    this.account = account;
                    if(account.getType() == 1 ) {
                        individualUserProcess();
                    }
                    else {
                        individualUserProcess();
                    }
                    break;
                }

            } catch (InvalidAuthenticationException e) {

            }
        }
    }

    public void individualUserProcess() {
        System.out.println("Merhaba : " + currentUser.getName());
        String choice = "1";
        while (!choice.equals("q")) {
            Scanner inp = new Scanner(System.in);

            System.out.println("1-) Bilgilerimi göster");
            System.out.println("2-) Sigorta satın al");
            System.out.println("3-) Sigorta Listemi göster");
            System.out.println("4-) Adres ekle");
            System.out.println("5-) Bütün adresleri göster");
            choice = inp.nextLine();

            if(choice.equals("1")) {
                account.showUserInfo();
            } else if (choice.equals("2")) {
                InsuranceManager insuranceManager = new InsuranceManager();
                account.addInsurance(insuranceManager.creatInsurance(currentUser));
            } else if (choice.equals("3")) {
                for (Insurance insurance : account.getUser().getInsuranceList()) {
                    System.out.println("İsim : " + insurance.getName());
                    System.out.println("Ücret : " + insurance.getPrice());
                }

            } else if (choice.equals("4")) {
                account.addAddress(AddressManager.creatAddress());
            } else if (choice.equals("5")) {
                for (Address adr : currentUser.getAddressList()) {
                    System.out.println(adr.getAdress());
                }
            }
        }

    }

    public void enterpriseUserProcess(){

    }
    public ArrayList<Account> getAccounts(){
        return accounts;
    }


}
