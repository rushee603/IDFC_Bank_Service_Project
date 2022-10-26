package bank;

public class customer {
    private String emailid;
    private String fulltName;
    private long mobileNo;
    private int age;

    public customer() {
    }

    public customer(String emailid, String fulltName, long mobileNo, int age) {
        this.emailid = emailid;
        this.fulltName = fulltName;
        this.mobileNo = mobileNo;
        this.age = age;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getFulltName() {
        return fulltName;
    }

    public void setFulltName(String fulltName) {
        this.fulltName = fulltName;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "customer{" +
                "emailid='" + emailid + '\'' +
                ", fulltName='" + fulltName + '\'' +
                ", mobileNo=" + mobileNo +
                ", age=" + age +
                '}';
    }
}