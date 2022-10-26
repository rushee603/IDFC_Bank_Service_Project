package bank;

public class account
{
customer customer;
private  long accountNo;

private  double balance;
private  String type;
private String date;

 public account() {

 }

 public account(bank.customer customer, long accountNo, double balance, String type, String date) {
  this.customer = customer;
  this.accountNo= accountNo;
  this.balance = balance;
  this.type = type;
  this.date = date;
 }

 public bank.customer getCustomer() {
  return customer;
 }

 public void setCustomer(bank.customer customer) {
  this.customer = customer;
 }

 public long getAccountNo() {return accountNo;}

 public void setAccountNo(long accountNo) {
  this.accountNo= accountNo;
 }

 public double getBalance() {
  return balance;
 }

 public void setBalance(double balance) {
  this.balance = balance;
 }

 public String getType() {
  return type;
 }

 public void setType(String type) {
  this.type = type;
 }

 public String getDate() {
  return date;
 }

 public void setDate(String date) {
  this.date = date;
 }

}

