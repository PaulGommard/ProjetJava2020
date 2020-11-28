package src.models;

public class Login
{
    private long loginID;
    private String email;
    private String password;
    private String customer;

    public Login() {

    }

    public long getLoginID() {
        return loginID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getCustomer() {
        return customer;
    }

    public void setLoginID(long loginID) {
        this.loginID = loginID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginID=" + loginID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", customer='" + customer + '\'' +
                '}';
    }

}
