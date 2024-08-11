import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Anu123#");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println(con.isClosed()?"close":"open");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("<------------------ Welcome to St. Teresa's Academy ------------------>");
        while(true){
            System.out.println("Enter 1 to add the student");
            System.out.println("Enter 2 remove the student");
            System.out.println("Enter 3 update the student");
            System.out.println("Enter 4 display all the student");
            System.out.println("Enter 5 to exit");
           // Scanner scan = new Scanner(System.in);
            int a = scan.nextInt();
            if(a==5){
                break;
            }else if (a==1){
                System.out.println("Enter the Roll Number of the student and must be unique");
                int id = scan.nextInt();
                System.out.println("Enter the name of the student");
                String name = scan.next();
                scan.nextLine();
                System.out.println("Enter the password of the student");
                String pass = scan.next();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into jdbc.student Values(?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.setInt(1,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.setString(2,name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.setString(3,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("<----------------------- Value entered successfully ----------------------->");
            }else if (a==2){
                System.out.println("Enter the roll number  of the deleted student");
                int roll_number = scan.nextInt();
                PreparedStatement stmt = null;
                try {
                    stmt = con.prepareStatement("delete from student where id = ?");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    stmt.setInt(1,roll_number);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    stmt.execute();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("<------------ Deleted -------------->");
            }else if(a==3){
                System.out.println("Enter the roll number of the student:");
                int roll= scan.nextInt();
                System.out.println("Enter the name of the updated:");
                String str = scan.next();
                PreparedStatement stmt = null;
                try {
                    stmt = con.prepareStatement("update Student set name= ? where id= ?");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    stmt.setString(1,str);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    stmt.setInt(2,roll);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    stmt.execute();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("<-------------- Name updated ---------------->");
            }else if(a==4){

                try {
                    PreparedStatement stmt = con.prepareStatement("select * from student");
                    ResultSet set = stmt.executeQuery();
                    while(set.next()){
                        int roll = set.getInt(1);
                        String name = set.getString(2);
                        String password = set.getString(3);
                        System.out.println("Roll No -> " +roll+" and name = "+name+" and password -> "+password);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("<-------------- Name updated ---------------->");
            }

        }

    }
}
