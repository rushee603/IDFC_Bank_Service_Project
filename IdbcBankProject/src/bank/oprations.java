package bank;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
public class oprations {
    public void idbcbank() {

        Scanner Obj = new Scanner(System.in);
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "root123");
            System.out.println("----------------------------------------------------");
            System.out.println("         $$  WELCOME  TO  IDBC  BANK  $$      ");
            System.out.println("----------------------------------------------------");
            while (true) {

                System.out.println(" \n1.Create New Account \n2.Existing Account Details \n3. Deposit Amount \n4.withdraw Amount \n5.Fund Transfer \n6. Statment \n.7 Exit");
                int choice = Obj.nextInt();
                Obj.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Enter Your Account Type (Saving/Pay): ");
                        String type = Obj.nextLine();

                        Random random = new Random();
                        long Accountno = Math.round(random.nextFloat() * Math.pow(10, 12));
                        System.out.println(" Your New Account Number Is : " + Accountno);
                        System.out.println("-----------------------------------------------");

                        System.out.println("Enter Your Emailid :");
                        String emailid = Obj.next();
                        Obj.nextLine();
                        System.out.println("Enter Your FullName :");
                        String fullName = Obj.nextLine();
                        System.out.println("Enter Your Mobile :");
                        long mobileNo = Obj.nextLong();
                        Obj.nextLine();
                        System.out.println("Enter Your Age :");
                        int age = Obj.nextInt();
                        Obj.nextLine();

                        if (age <= 18) {
                            System.out.println("Sorry You Are Not Eligible For New Bank Account , Age Must be +18 ");
                        } else {
                            while (true) {
                                System.out.println("Set Your Pin ");
                                int pin1 = Obj.nextInt();
                                System.out.println("Confirm Your Pin ");
                                int pin2 = Obj.nextInt();

                                if (pin1 != pin2) {
                                    System.out.println("plz check again entered pin");
                                } else {
                                    break;
                                }
                            }
                            System.out.println(" Congratulation " + fullName+"!!");
                            System.out.println();
                            System.out.println(" !! ACCOUNT CREATE SUCCESSFULLY !! ");
                            System.out.println();
                            System.out.println("Your New Account Number is :  " + Accountno);
                            System.out.println("------------------------------------------------------");
                            System.out.println(" Deposit  Some Amount in Your account ");
                            System.out.println("enter your  AccountNno : ");
                            long account1 = Obj.nextLong();
                            System.out.println("Enter Your Balance :");
                            int bal = Obj.nextInt();
                            System.out.println("Your Current Account Balance Is : "+bal);
                            System.out.println("-----------------------------------------------------");

                            String query = "insert into Customer values(?,?,?,?,?)";
                            PreparedStatement pst = con.prepareStatement(query);
                            pst.setString(1, emailid.toString());
                            pst.setString(2, fullName);
                            pst.setLong(3, mobileNo);
                            pst.setInt(4, age);
                            pst.setLong(5, account1);


                            String query3 = "insert into Accounts values(?,?,?)";
                            PreparedStatement pst11 = con.prepareStatement(query3);
                            pst11.setLong(1, Accountno);
                            pst11.setInt(2, bal);
                            pst11.setString(3, type.toString());
                            int rows1 = pst11.executeUpdate();
                            int rows = pst.executeUpdate();
                            break;
                        }

                        //---------------------------------------------- existing customer --------------------------

                        case 2:
                             System.out.println("-------------Welcome To IDBC Bank ------------------ ");

                             System.out.println("Enter Your Emailed :");
                            String emailid1 = Obj.next();
                            System.out.println("Enter your account Number");
                            long acc = Obj.nextLong();
                            System.out.println("enter your pin ");
                            int pinn = Obj.nextInt();


                            String quray1 = "select * from  Customer where emailid=?";
                            PreparedStatement pst1 = con.prepareStatement(quray1);
                            pst1.setString(1, emailid1);
                            ResultSet rs = pst1.executeQuery();
                            while (rs.next()) {
                                System.out.println("---------------------------------------");
                                System.out.println(" CONFIRM YOUR PERSONAL DETAILS ");
                                System.out.println("---------------------------------------");
                                System.out.println(" Emailid:  " + rs.getString(1) + "\n FullName: " + rs.getString(2) + "\n Mobile Number: " + rs.getLong(3) + "\n Age: " + rs.getInt(4));
                            }

                            String quray3 = "select * from  Accounts where accountNo=?";
                            PreparedStatement pst11 = con.prepareStatement(quray3);
                            pst11.setLong(1, acc);
                            ResultSet rss = pst11.executeQuery();
                            while (rss.next()) {
                                System.out.println("---------------------------------------");
                                System.out.println(" CONFIRM YOUR ACCOUNT DETAILS ");
                                System.out.println("---------------------------------------");
                                System.out.println(" Account:  " + rss.getLong(1) + "\n Balance:    " + rss.getInt(2) + "\n AccountType: " + rss.getString(3));
                                System.out.println();
                            }
                            break;

                        //------------------------------------ Deposit Amount ----------------------------------------------------

                    case 3: {
                        System.out.println(" Deposit  Amount $ ");
                        System.out.println("------------------------------------------");
                        System.out.println("Enter Your AccountNo : ");
                        long acc1 = Obj.nextLong();
                        System.out.println("Enter Your Amount $ :");
                        long Amm = Obj.nextLong();

                        String quray4 = " update  Accounts  set balance=(balance +" + Amm + ") where accountNo=" + acc1;
                        Statement st = con.createStatement();
                        int rows1 = st.executeUpdate(quray4);
                        String tp2 = "Deposit";

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String Opration1 = "insert into Opration values(" + acc1 + ",'" + timestamp + "', '" + tp2 + "')";
                        Statement t = con.createStatement();
                        int rows11 = t.executeUpdate(Opration1);
                        System.out.println("Deposit Amount Successfully..!! ");
                        System.out.println("-------------------------------------------");
                        break;
                    }
                    //--------------------------------- Withdrawal Amount --------------------------------------------
                    case 4: {
                        System.out.println(" Withdraw Your Amount $ ");
                        System.out.println("----------------------------------");
                        System.out.println("Enter Your A/C No : ");
                        long acc1 = Obj.nextLong();
                        System.out.println("enter your pin ");
                        int pinn3 = Obj.nextInt();
                        System.out.println("Enter Your Amount $ :");
                        long Amm = Obj.nextLong();
                        String tp = "Withdraw";

                        String balancee="select balance from Accounts where AccountNo="+acc1;
                        Statement sat = con.createStatement();
                        ResultSet rr = sat.executeQuery(balancee);

                        while(rr.next()) {
                            int bal1 = rr.getInt("balance");
                            System.out.println("Your Balance is "+bal1);

                           // '405430674553'
                            if (bal1 < Amm) {
                                System.out.println("Insufficient Balance");

                            } else {


                                String quray5 = " update  Accounts  set balance=(balance -" + Amm + ") where accountNo=" + acc1;
                                Statement sst = con.createStatement();
                                int rows1 = sst.executeUpdate(quray5);

                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                String Opration1 = "insert into Opration values(" + acc1 + ",'" + timestamp + "','" + tp + "')";
                                Statement s = con.createStatement();
                                int rows12 = s.executeUpdate(Opration1);
                                System.out.println(" Withdrawal Amount Successfully..!! ");
                                System.out.println("------------------------------------------------");
                                break;
                            }
                        }

                    }
                    //------------------------------- Fund Transfer ------------------------------------------------

                    case 5: {
                        System.out.println(" Fund Transfer ");
                        System.out.println("--------------------------------");
                        System.out.println("Enter Your A/C No : ");
                        long acc1 = Obj.nextLong();
                        System.out.println("Enter Your Amount :");
                        long Amm = Obj.nextLong();
                        System.out.println("Enter A/C No : ");
                        long acc2 = Obj.nextLong();
                        String type1 = "FundTransfer";

                        String quray6 = " update  Accounts  set balance=(balance -" + Amm + ") where accountNo=" + acc1;
                        Statement sst9 = con.createStatement();
                        int rows12 = sst9.executeUpdate(quray6);

                        String quray7 = " update  Accounts  set balance=(balance +" + Amm + ") where accountNo=" + acc2;
                        Statement st0 = con.createStatement();
                        int rows13 = st0.executeUpdate(quray7);

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String Opration1 = "insert into Opration values(" + acc1 + ",'" + timestamp + "','" + type1 + "')";
                        Statement stt = con.createStatement();
                        int rows1 = stt.executeUpdate(Opration1);
                        System.out.println("Fund Transferred Successfully ");
                        System.out.println("----------------------------------------");
                        break;
                    }
                    case 6:

                        System.out.println("Enter Your a/c no :");
                        long acc2 = Obj.nextLong();
                        String Query = "select * from opration where accountNo="+acc2;
                        Statement stt = con.createStatement();
                        ResultSet rs1 = stt.executeQuery(Query);
                        System.out.println("AccountNo"+"     "+"time"+"    "+"type"   );
                        while(rs1.next())
                        {
                            String accountNo=rs1.getString(1);
                            String time=rs1.getString(2);
                            String type1=rs1.getString(3);
                            System.out.println(accountNo+" "+time+""+"  "+type1 );
                        }
                        break;
                        case 7:
                            System.out.println("|| Thank You For Visiting  ||");
                        break;

                        default:
                        System.out.println("Sorry !! Please Check Your Inputs Again ");
                }
            }
        }
                        catch (Exception a) {

                        a.printStackTrace();
        }
    }
}








