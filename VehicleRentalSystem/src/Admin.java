import java.sql.*;
import java.util.*;

public class Admin {
    final static String GREEN = "\u001b[32;1m";
    final static String RED = "\033[1;31m";
    final static String YELLOW = "\u001b[33m";
    final static String BLUE = "\033[1;94m";
    final static String PURPLE = "\u001b[35m";
    final static String reset = "\u001b[0m";

    Scanner sc = new Scanner(System.in);

    static final String dburl = "jdbc:mysql://localhost:3306/vehiclerent";
    static final String dbuser = "root";
    static final String dbpass = "";

    static String adminName = "romil";
    static String password = "r1234";

    void adminLogin() throws SQLException{
        String un = "";
        String pass = "";
        int x = 0;
        while (!(adminName.equals(un) && password.equals(pass))) {
            if(x > 0){
                System.out.println(RED+"\nINVALID CREDENTIALS"+reset);
            }
            System.out.print(BLUE+"ENTER USER NAME : "+reset);
            un = sc.next();
            System.out.print(BLUE+"ENTER PASSWORD : "+reset);
            pass = sc.next();
            x++;
        }

        makeChanges();
    }

    void makeChanges() throws SQLException {
        int choice = 0;
        while (choice!=8) {
            System.out.println(YELLOW +"\nWHAT WOULD YOU LIKE TO DO ?");
            System.out.println(BLUE + "\n==============================================================================");
            System.out.println("\t1 . SHOW CARS\t2 . EDIT PRICE\t3 . ADD CAR");
            System.out.println("\t4 . DELETE CAR\t5 . Show Total Revenue");
            System.out.println("\t6 . SHOW CARS HIGH TO LOW\t7 . SHOW CARS LOW TO HIGH");
            System.out.println("===============================<"+RED+" 8 . EXIT "+BLUE+">===================================");
            System.out.println("ENTER CHOICE : " + reset);
            choice = sc.nextInt();
            System.out.println(YELLOW+"=============================================================================="+reset);

            switch (choice) {
                case 1: displayallcars();
                break;
                case 2: rentChange();
                break;
                case 3: addCar();
                break;
                case 4: deleteCar();
                break;
                case 5 : totalrevenue();
                break;
                case 6 : displayCarsHighToLow();
                break;
                case 7 : displayCarsLowToHigh();
                break;
                case 8 : System.out.println(YELLOW+"\n\nTHANK YOU FOR VISITING!\n"+reset);
                break; 
                default:
                break;
            }
        }
    }

    void displayCarsHighToLow() throws SQLException {
        BinarySearchTree bst = new BinarySearchTree();
        fetchAndPopulateBST(bst);
        bst.displayHighToLow();
    }

    void displayCarsLowToHigh() throws SQLException {
        BinarySearchTree bst = new BinarySearchTree();
        fetchAndPopulateBST(bst);
        bst.displayLowToHigh();
    }

    void fetchAndPopulateBST(BinarySearchTree bst) throws SQLException {
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        String q = "{call getallcars()}";
        CallableStatement cst = con.prepareCall(q);
        ResultSet rs = cst.executeQuery();

        while (rs.next()) {
            int carId = rs.getInt(1);
            String comName = rs.getString(2);
            String carName = rs.getString(3);
            String type = rs.getString(4);
            int rent = rs.getInt(5);
            int price = rs.getInt(6);
            String city = rs.getString(7);
            bst.insert(price, carId, comName, carName, type, rent, city);
        }
    }

    static void displayallcars() throws SQLException {
        // String driverName = "com.mysql.jdbc.Driver";
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        String q = "{call getallcars()}";
        CallableStatement cst = con.prepareCall(q);
        ResultSet rs = cst.executeQuery();

        while (rs.next()) {
            System.out.println(GREEN + "Car I'd : " + rs.getInt(1));
            System.out.println(GREEN + "Car Company Name : " + rs.getString(2));
            System.out.println(GREEN + "Car Name : " + rs.getString(3));
            System.out.println(GREEN + "Car Type :" + rs.getString(4));
            System.out.println(GREEN + "Car Rent : " + rs.getInt(5));
            System.out.println(GREEN + "Car Price : " + rs.getInt(6));
            System.out.println(GREEN + "City : " + rs.getString(7));
            System.out.println(YELLOW+"=============================================================================="+reset);
        }

    }

    static void rentChange() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);

        String q = "{call rentchange(?, ?)}";
        // 1. carid   2. new rent
        CallableStatement cst = con.prepareCall(q);
        
        System.out.println(PURPLE + "Enter Car I'd U Want to Change Rent : " + reset);
        int ci = sc.nextInt();
        System.out.println(PURPLE + "Enter New Rent U Want to Change : " + reset);
        int nr = sc.nextInt();

        cst.setInt(1, ci);
        cst.setInt(2, nr);
        int n = cst.executeUpdate();

        if (n > 0) {
            System.out.println(GREEN + nr + " Rent is Successfully Changed." + reset);
        } else {
            System.out.println(RED + "No Cars Found!!!" + reset);
        }
    }

    static void addCar() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);

        String q = "insert into cars(comName, carName, type, rent, carPrice, city) values(?, ?, ?, ?, ?, ?)";
        String q1 = "insert into availcars(carId, comName, carName, type, rent, carPrice, city) values(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement pst1 = con.prepareStatement(q1);

        System.out.println(PURPLE + "Enter Car Company Name : ");
        String cc = sc.next();
        sc.nextLine();
        System.out.println(PURPLE + "Enter Car Name : ");
        String cn = sc.nextLine();
        System.out.println(PURPLE + "Enter Car Type : "); 
        String ct = sc.next();
        System.out.println(PURPLE + "Enter Car Rent : ");
        int cr = sc.nextInt();
        System.out.println(PURPLE + "Enter Car Price : ");  
        int cp = sc.nextInt();
        System.out.println(PURPLE + "Enter Car City(Location): ");
        String ccity = sc.next();

        pst.setString(1, cc);
        pst.setString(2, cn);
        pst.setString(3, ct);
        pst.setInt(4, cr);
        pst.setInt(5, cp);
        pst.setString(6, ccity);

        pst1.setString(2, cc);
        pst1.setString(3, cn);
        pst1.setString(4, ct);
        pst1.setInt(5, cr);
        pst1.setInt(6, cp);
        pst1.setString(7, ccity);

        int n = pst.executeUpdate();

        if (n > 0) {
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newCarId = generatedKeys.getInt(1);
                System.out.println(GREEN + "Insertion Complete. Your Car(" + cn + ")I'd is: " + newCarId + reset);
                pst1.setInt(1, newCarId);
                pst1.executeUpdate();
            }
        } else {
            System.out.println(RED + "Insertion Failed!!!" + reset);
        }

    }

    static void deleteCar() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);

        String q = "{call deletecar(?)}";
        System.out.println(PURPLE + "Enter Car I'd U Want to Delete a Car : ");
        int dci = sc.nextInt();

        CallableStatement cst = con.prepareCall(q);
        cst.setInt(1, dci);

        int n = cst.executeUpdate();

        if (n > 0) {
            System.out.println(GREEN + "Deletion Complete!!!" + reset);
        } else {
            System.out.println(RED + "Deletion Failed!!!" + reset);
        }
    }

    
    static void totalrevenue() throws SQLException {
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        
        String sql = "select totalAmt from revenue";
        Statement st1 = con.createStatement();
        ResultSet rs1 = st1.executeQuery(sql);

        int totalr = 0;
        while (rs1.next()) {
            totalr = totalr + rs1.getInt(1);
        }

        System.out.println(PURPLE + "Total Revenue is : " + GREEN + totalr + reset);
    }

}