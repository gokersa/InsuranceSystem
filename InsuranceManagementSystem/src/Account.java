import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Account implements Comparable<Account> {

   enum AuthenticationStatus {SUCCESS,FAIL};

   User user;
   ArrayList<Insurance> insuranceList;
   AuthenticationStatus status;
   private int type;

   final void showUserInfo(){
       System.out.println("Hesap Bilgileri : ");
       System.out.println("Ad : " + user.getName());
       System.out.println("Soyad : " + user.getSurname());
       System.out.println("E-mail : " + user.getEmail());
       System.out.println("İş : " + user.getJob());
       System.out.println("Yaş : " + user.getAge());
       System.out.println("SOn Giriş Tarihi : " + user.getLastLogin());
   }
    public void login (String email, String password) throws InvalidAuthenticationException{
       if (! (user.getEmail().equals(email) && user.getPassword().equals(password))) {
           status = AuthenticationStatus.FAIL;
           throw new InvalidAuthenticationException ("Invalid Password or Email");
       }
       else {
           status = AuthenticationStatus.SUCCESS;
           user.setLastLogin();
       }
    }

    public abstract void addAddress(Address adress);
    public abstract void removeAddress (Address address);

    public boolean isLogin() {
        return status == AuthenticationStatus.SUCCESS;
    }

    public AuthenticationStatus getAuthenticationStatus() {
        return status;
    }

    public void setAuthenticationStatus(AuthenticationStatus authenticationStatus) {
        this.status = authenticationStatus;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public void setInsuranceList (ArrayList<Insurance> InsuranceList) {
        this.insuranceList = insuranceList;
    }

    public abstract void addInsurance(Insurance i);


    public User getUser() {
        return user;
    }

    public void setType(int a) {type = a;}

    public int getType(){return type;}

    @Override
    public int compareTo(Account o) {
        return this.hashCode() - o.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return Objects.equals(user,account.user);
    }

    static class Enterprise extends Account {

        public Enterprise() {
            this.setAuthenticationStatus(AuthenticationStatus.FAIL);
            this.setInsuranceList(new ArrayList<>());
            setType(1);
        }
        public Enterprise(User user) {
            this();
            this.setUser(user);
        }

        @Override
        public void addAddress (Address address) {
            user.getAddressList().add(address);
        }

        @Override
        public void removeAddress (Address address) {
            user.getAddressList().remove(address);
        }

        @Override
        public void addInsurance (Insurance i) {
            user.getInsuranceList().add(i);
        }

    }

    static class Individual extends Account {

        public Individual() {
            this.setAuthenticationStatus(AuthenticationStatus.FAIL);
            this.setInsuranceList(new ArrayList<>());
            setType(0);

        }

        public Individual(User user) {
            this();
            this.setUser(user);
        }

        @Override
        public void addAddress(Address address) {
            user.getAddressList().add(address);
        }

        @Override
        public void removeAddress(Address address) {
            user.getAddressList().remove(address);
        }

        @Override
        public void addInsurance(Insurance i) {
            User.getInsuranceList().add(i);
        }
    }}




