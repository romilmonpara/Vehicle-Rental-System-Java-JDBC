import java.sql.*;
import java.util.*;

class myException extends RuntimeException {
    public myException(String s) {
        super(s);
    }
}

public class App {
    final static String GREEN = "\u001b[32;1m";
    final static String RED = "\033[1;31m";
    final static String YELLOW = "\u001b[33m";
    final static String BLUE = "\033[1;94m";
    final static String PURPLE = "\u001b[35m";
    final static String reset = "\u001b[0m";

    static final String dburl = "jdbc:mysql://localhost:3306/vehiclerent";
    static final String dbuser = "root";
    static final String dbpass = "";
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        System.out.println((con != null) ? "Connection Successfull" : "Failed!!!");
        
        int startingChoice = starting();
        while (true) {
            if (startingChoice == 1 || startingChoice == 2) {
                break;
            }
            System.out.println(RED + "INVALID CHOICE!!!!" + reset);
            startingChoice = starting();
        }
        System.out.println();

        if(startingChoice==1){
            Admin a = new Admin();
            a.adminLogin();
        }else{
            int intial_choice=0;
            while (true) {
                intial_choice = homePage();
                if(intial_choice==1 || intial_choice==2){
                    break;
                }else{
                    intial_choice = homePage();
                }
            }
    
            System.out.println();
            switch (intial_choice) {
                case 1:
                    register();
                    login();
                    menu();
                    break;
                case 2:
                    login();
                    menu();
                    break;
            }
            System.out.println(YELLOW+"\n\nTHANK YOU FOR VISITING!\n"+reset);
        }


    }

    static int starting() {
        Scanner sc = new Scanner(System.in);
        System.out.println(YELLOW+"\n===================================< " + RED + "Welcome Vehicle Rental System" + YELLOW +" >====================================");
        System.out.println(GREEN + "\t\t1 << ADMIN >>\t\t     2 << CUSTOMER >>" + reset);
        System.out.println(YELLOW + "======================================================================================");
        System.out.print("\nEnter Your Choice : "+reset);
        int intial_choice = sc.nextInt();
        return intial_choice;
    } 

    static public int homePage(){
        Scanner sc = new Scanner(System.in);
        System.out.println(YELLOW + "\n===================================WELCOME====================================");
        System.out.println("\t\t1 . << REGISTER >>\t\t2 . << LOGIN >>");
        System.out.println("==============================================================================");
        System.out.print("\nEnter Your Choice : " + reset);
        int intial_choice = sc.nextInt();
         
        return intial_choice;
    }

    
    static void register() throws Exception {
        Scanner sc = new Scanner(System.in);
        String umo = "";

        String uname = "";
        boolean validName = false;
        while (!validName) {
            try {
                System.out.println(PURPLE + "Enter Your Name : " + reset);
                uname = sc.next();
                if (uname.matches(".*\\d.*")) {
                    throw new myException(RED + "INVALID: Name should not contain digits!" + reset);
                }
                validName = true;
            } catch (myException e) {
                System.out.println(e.getMessage());
            }
        }

        int upass = -1;
        while (upass == -1) {
            System.out.println(PURPLE + "Enter Your Password (numeric only): " + reset);
            String passInput = sc.next();

            try {
                upass = Integer.parseInt(passInput);
            } catch (NumberFormatException e) {
                System.out.println(RED + "INVALID: Please enter a numeric value for the password." + reset);
                upass = -1;
            }
        }

        System.out.println(GREEN + "Password accepted: " + upass + reset);

        boolean isValid = false;
        while (!isValid) {
            System.out.println(PURPLE + "Enter Your Mobile No. : " + reset);
            umo = sc.next();
            if (umo.length() != 10) {
                System.out.println(RED + "INVALID LENGTH" + reset);
            } else {
                for (char mobileNumber : umo.toCharArray()) {
                    if (!(Character.isDigit(mobileNumber))) {
                        System.out.println(RED + "INVALID: Please Enter Digits Only!!!" + reset);
                    } else {
                        isValid = true;
                        break;
                    }
                }
            }
        }

        System.out.println(PURPLE + "Enter Your License Number : " + reset);
        String ulic = sc.next();

        String uemail = "";
        boolean isv = false;
        while (!isv) {
            System.out.println(PURPLE + "Enter Your E-mail : " + reset);
            uemail = sc.next();
            if (uemail.matches("^[A-Za-z0-9+_.-]+@gmail\\.com$")) {
                isv = true;
            } else {
                System.out.println(RED + "INVALID: Please Enter a Valid Gmail Address!!!" + reset);
            }
        }

        System.out.println(PURPLE + "Enter Your City : " + reset);
        String ucity = sc.next();

        String driverName = "com.mysql.jdbc.Driver";
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);

        String q = "insert into users(name, pass, licNo, moNo, email, city) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, uname);
        pst.setInt(2, upass);
        pst.setString(3, ulic);
        pst.setString(4, umo);
        pst.setString(5, uemail);
        pst.setString(6, ucity);

        int n = pst.executeUpdate();

        // System.out.println((n > 0) ? GREEN + "Insertion Complete" + reset : RED + "Failed" + reset);
        if (n > 0) {
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newUserId = generatedKeys.getInt(1);
                System.out.println(GREEN + "Insertion Complete. Your User ID is: " + newUserId + reset);
            }
        } else {
            System.out.println(RED + "Failed" + reset);
        }
    }

    //  Login Method
    static void login() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Hashtable<Integer, Integer> ht = new Hashtable<>();

        boolean log = false;
        int z=0;
        int id;
        int pass;

        int rid;
        int rpass;
        boolean b = true;
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        Statement st = con.createStatement();

        String q = "select userId, pass from users";

        ResultSet rs = st.executeQuery(q);

        while (rs.next()) {
            rid = rs.getInt(1);
            rpass = rs.getInt(2);
            ht.put(rid, rpass);
            b = false;
        } 

        boolean isAvailable = true;
        
        while (!log) {
            if (z > 0) {
                System.out.println(RED+"\nINAVLID USER ID OR PASSWORD !!\n"+reset);
            }

            if (isAvailable) {
                System.out.print(YELLOW + "Enter User ID: " + reset);
                id = sc.nextInt();
                if(ht.containsKey(id)) {
                    System.out.println(YELLOW + "Enter Password : " + reset);
                    pass = sc.nextInt();
                    if (ht.containsValue(pass)) {
                        log = true;
                        isAvailable = false;
                    }
                }
                z++;                
            } 
        }
    }

    static void menu() throws SQLException{
        Scanner sc = new Scanner(System.in);
        int c = 0;
        while (c!=5) {
            System.out.println(GREEN+"\n==============================================================================");
            System.out.println("1 . SEARCH CAR  \t 2 . SHOW CARS   \t 3 . RENT A CAR");
            System.out.println("\t\t\t 4 . RETURN A CAR");
            System.out.println("===============================<"+RED+" 5 . EXIT "+GREEN+">===================================");
            System.out.print("\nEnter Your Choice : "+reset);
            c = sc.nextInt();
            System.out.println();
            switch (c) {
                case 1: search();
                break;
                case 2: displayallcars();
                break;
                case 3: rentACar();
                break;
                case 4: returnACar();
                break;
                case 5:
                return;
                default: System.out.println(RED + "Invalid Choice. . ." + reset);
                break;
            }
        }
    }

    static void search() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String driverName = "com.mysql.jdbc.Driver";
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        String q = "{call searchcar(?)}";
        CallableStatement cst = con.prepareCall(q);
        
        System.out.println(PURPLE + "Enter Car Name U Want Search : " + reset);
        String carn = sc.nextLine();

        cst.setString(1, carn);
        ResultSet rs = cst.executeQuery();
        Boolean b = true;

        while (rs.next()) {
            System.out.println(YELLOW+"=============================================================================="+reset);
            System.out.println(GREEN + "Car I'd : " + rs.getInt(1));
            System.out.println(GREEN + "Car Company Name : " + rs.getString(2));
            System.out.println(GREEN + "Car Name : " + rs.getString(3));
            System.out.println(GREEN + "Car Type :" + rs.getString(4));
            System.out.println(GREEN + "Car Rent : " + rs.getInt(5));
            System.out.println(GREEN + "Car Price : " + rs.getInt(6));
            System.out.println(GREEN + "City : " + rs.getString(7));
            System.out.println(YELLOW+"=============================================================================="+reset);
            b = false;
        }

        if(b) System.out.println(RED + "No Cars Found!!!" + reset);
    }

    static void displayallcars() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String driverName = "com.mysql.jdbc.Driver";
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        String q = "{call getavailcars()}";
        CallableStatement cst = con.prepareCall(q);
        ResultSet rs = cst.executeQuery();
        int button = 0;

        while (button != 4) {
            System.out.println(GREEN+"\n==============================================================================");
            System.out.println("\t\t\t 1 . SHOW ALL CARS");
            System.out.println("\t2 . SHOW CARS HIGH TO LOW\t3 . SHOW CARS LOW TO HIGH");
            System.out.println("===============================<"+RED+" 4 . EXIT "+GREEN+">===================================");
            System.out.print("\nEnter Your Choice : "+reset);
            button = sc.nextInt();
            System.out.println();

            switch (button) {
                case 1: while (rs.next()) {
                            System.out.println(YELLOW+"=============================================================================="+reset);
                            System.out.println(GREEN + "Car I'd : " + rs.getInt(1));
                            System.out.println(GREEN + "Car Company Name : " + rs.getString(2));
                            System.out.println(GREEN + "Car Name : " + rs.getString(3));
                            System.out.println(GREEN + "Car Type :" + rs.getString(4));
                            System.out.println(GREEN + "Car Rent : " + rs.getInt(5));
                            System.out.println(GREEN + "Car Price : " + rs.getInt(6));
                            System.out.println(GREEN + "City : " + rs.getString(7));
                            System.out.println(YELLOW+"=============================================================================="+reset);
                        }
                break;

                case 2 : displayAvailCarsHighToLow();
                break;

                case 3 : displayAvailCarsLowToHigh();
                break;

                case 4 :
                break;
                default: System.out.println(RED + "Invalid Choice. . ." + reset);
                break;
            }
        }

    }

    static void displayAvailCarsHighToLow() throws SQLException {
        BinarySearchTree bst1 = new BinarySearchTree();
        fetchInBST(bst1);
        bst1.displayHighToLow();
    }

    static void displayAvailCarsLowToHigh() throws SQLException {
        BinarySearchTree bst = new BinarySearchTree();
        fetchInBST(bst);
        bst.displayLowToHigh();
    }

    static void fetchInBST(BinarySearchTree bst) throws SQLException {
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        String q = "{call getavailcars()}";
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

    static void rentACar() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String driverName = "com.mysql.jdbc.Driver";
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
    
        System.out.println(PURPLE + "Enter Your User ID: " + reset);
        int userId = sc.nextInt();
    
        System.out.println(PURPLE + "Enter Your Mobile Number: " + reset);
        String moNo = sc.next();
    
        String userCheckQuery = "SELECT * FROM users WHERE userId = ? AND moNo = ?";
        PreparedStatement userCheckPst = con.prepareStatement(userCheckQuery);
        userCheckPst.setInt(1, userId);
        userCheckPst.setString(2, moNo);
        ResultSet userRs = userCheckPst.executeQuery();
    
        if (!userRs.next()) {
            System.out.println(RED + "User ID or Mobile Number not found. Rental car operation failed." + reset);
            return;
        }
    
        String userName = userRs.getString("name");
    
        System.out.println(PURPLE + "Enter Car ID You Want to Rent: " + reset);
        int carId = sc.nextInt();
    
        System.out.println(PURPLE + "Enter Number of Days You Want to Rent the Car: " + reset);
        int days = sc.nextInt();
    
        String carQuery = "SELECT * FROM availcars WHERE carId = ?";
        PreparedStatement carPst = con.prepareStatement(carQuery);
        carPst.setInt(1, carId);
        ResultSet carRs = carPst.executeQuery();
    
        if (carRs.next()) {
            String comName = carRs.getString("comName");
            String carName = carRs.getString("carName");
            String type = carRs.getString("type");
            int rent = carRs.getInt("rent");
            int carPrice = carRs.getInt("carPrice");
            String city = carRs.getString("city");
    
            int totalRent = rent * days;
            double advancePayment = totalRent * 0.5;
    
            System.out.println(YELLOW + "--------------------------------------------------------------------------------" + reset);
            System.out.println(BLUE + "\t\tName : " + userName);
            System.out.println(BLUE + "\t\tMobile number : " + moNo);
            System.out.println(BLUE + "\t\tDays : " + days);
            System.out.println(BLUE + "\t\tTotal Price : " + totalRent);
            System.out.println(YELLOW + "--------------------------------------------------------------------------------" + reset);
    
            System.out.println(GREEN + "\nYou Have To Pay 50% Advance");
            System.out.println("Please Pay Rs " + advancePayment + reset);
            
            String cardNumber;
            while (true) {
                System.out.println(PURPLE + "\nEnter Card Number : " + reset);
                cardNumber = sc.next();
                if (cardNumber.length() == 16) {
                    break;
                } else {
                    System.out.println(RED + "Invalid Card Number && Length Must Be 16!!" + reset);
                }
            }
    
            System.out.println(PURPLE + "Enter CVV : " + reset);
            int cvv = sc.nextInt();
    
            System.out.println(RED + advancePayment + " Has Been Debited From Your Account." + reset);
            
            con.setAutoCommit(false);
            
            // Get new bookId
            String bookIdQuery = "SELECT IFNULL(MAX(bookId), 0) + 1 FROM booked";
            Statement bookIdStmt = con.createStatement();
            ResultSet bookIdRs = bookIdStmt.executeQuery(bookIdQuery);
            bookIdRs.next();
            int newBookId = bookIdRs.getInt(1);
    
            String insertBookedQuery = "INSERT INTO booked(bookId, carId, comName, carName, type, rent, carPrice, city, userId, days, moNo, totalAmt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertBookedPst = con.prepareStatement(insertBookedQuery);
            insertBookedPst.setInt(1, newBookId);
            insertBookedPst.setInt(2, carId);
            insertBookedPst.setString(3, comName);
            insertBookedPst.setString(4, carName);
            insertBookedPst.setString(5, type);
            insertBookedPst.setInt(6, rent);
            insertBookedPst.setInt(7, carPrice);
            insertBookedPst.setString(8, city);
            insertBookedPst.setInt(9, userId);
            insertBookedPst.setInt(10, days);
            insertBookedPst.setString(11, moNo);
            insertBookedPst.setInt(12, totalRent);
    
            int n = insertBookedPst.executeUpdate();
    
            if (n > 0) {
                String insertRevenueQuery = "INSERT INTO revenue(bookId, totalAmt) VALUES (?, ?)";
                PreparedStatement insertRevenuePst = con.prepareStatement(insertRevenueQuery);
                insertRevenuePst.setInt(1, newBookId);
                insertRevenuePst.setInt(2, totalRent);
    
                int revenueInserted = insertRevenuePst.executeUpdate();
    
                if (revenueInserted > 0) {
                    String deleteCarQuery = "DELETE FROM availcars WHERE carId = ?";
                    PreparedStatement deleteCarPst = con.prepareStatement(deleteCarQuery);
                    deleteCarPst.setInt(1, carId);
                    int deleted = deleteCarPst.executeUpdate();
    
                    if (deleted > 0) {
                        System.out.println(GREEN + "Car Rented Successfully." + reset);
                        con.commit();
                    } else {
                        System.out.println(RED + "Failed to Delete the Car from Available Cars" + reset);
                        con.rollback();
                    }
                } else {
                    System.out.println(RED + "Failed to Insert Revenue Record" + reset);
                    con.rollback();
                }
            } else {
                System.out.println(RED + "Failed to Rent the Car" + reset);
                con.rollback();
            }
        } else {
            System.out.println(RED + "No Car Available with the Given ID" + reset);
        }
    }
    
    static void returnACar() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String driverName = "com.mysql.jdbc.Driver";
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
    
        System.out.println(PURPLE + "Enter Your User ID: " + reset);
        int userId = sc.nextInt();
    
        System.out.println(PURPLE + "Enter Your Mobile Number: " + reset);
        String moNo = sc.next();
    
        System.out.println(PURPLE + "Enter Car ID You Are Returning: " + reset);
        int carId = sc.nextInt();
    
        con.setAutoCommit(false);
    
        try {
            // Verify the user
            String userCheckQuery = "SELECT * FROM users WHERE userId = ? AND moNo = ?";
            PreparedStatement userCheckPst = con.prepareStatement(userCheckQuery);
            userCheckPst.setInt(1, userId);
            userCheckPst.setString(2, moNo);
            ResultSet userRs = userCheckPst.executeQuery();
    
            if (!userRs.next()) {
                System.out.println(RED + "User ID or Mobile Number not found. Return operation aborted." + reset);
                con.rollback();
                return;
            }
    
            String bookingQuery = "SELECT * FROM booked WHERE userId = ? AND carId = ? AND moNo = ?";
            PreparedStatement bookingPst = con.prepareStatement(bookingQuery);
            bookingPst.setInt(1, userId);
            bookingPst.setInt(2, carId);
            bookingPst.setString(3, moNo);
            ResultSet bookingRs = bookingPst.executeQuery();
    
            if (!bookingRs.next()) {
                System.out.println(RED + "No Booking Found for the Given Details. Return operation aborted." + reset);
                con.rollback();
                return;
            }
    
            int totalAmt = bookingRs.getInt("totalAmt");
    
            double amountToPay = totalAmt * 0.5;
    
            System.out.println(YELLOW + "--------------------------------------------------------------------------------" + reset);
            System.out.println(BLUE + "\t\tAmount to be Paid for Return: Rs " + amountToPay);
            System.out.println(YELLOW + "--------------------------------------------------------------------------------" + reset);
    
            System.out.println(GREEN + "Payment of Rs " + amountToPay + " processed successfully." + reset);
    
            String checkCarQuery = "SELECT * FROM availcars WHERE carId = ?";
            PreparedStatement checkCarPst = con.prepareStatement(checkCarQuery);
            checkCarPst.setInt(1, carId);
            ResultSet carRs = checkCarPst.executeQuery();
    
            if (!carRs.next()) {
                String insertCarQuery = "INSERT INTO availcars (carId, comName, carName, type, rent, carPrice, city) " +
                                        "SELECT carId, comName, carName, type, rent, carPrice, city FROM booked WHERE carId = ?";
                PreparedStatement insertCarPst = con.prepareStatement(insertCarQuery);
                insertCarPst.setInt(1, carId);
                int inserted = insertCarPst.executeUpdate();
    
                if (inserted > 0) {
                    String deleteBookingQuery = "DELETE FROM booked WHERE carId = ? AND userId = ? AND moNo = ?";
                    PreparedStatement deleteBookingPst = con.prepareStatement(deleteBookingQuery);
                    deleteBookingPst.setInt(1, carId);
                    deleteBookingPst.setInt(2, userId);
                    deleteBookingPst.setString(3, moNo);
                    int deleted = deleteBookingPst.executeUpdate();
    
                    if (deleted > 0) {
                        System.out.println(GREEN + "Car Returned Successfully. Thank you!" + reset);
                        con.commit();
                    } else {
                        System.out.println(RED + "Failed to Delete Booking Record" + reset);
                        con.rollback();
                    }
                } else {
                    System.out.println(RED + "Failed to Insert Car into Available Cars" + reset);
                    con.rollback();
                }
            } else {
                System.out.println(GREEN + "Car is already available." + reset);
    
                String deleteBookingQuery = "DELETE FROM booked WHERE carId = ? AND userId = ? AND moNo = ?";
                PreparedStatement deleteBookingPst = con.prepareStatement(deleteBookingQuery);
                deleteBookingPst.setInt(1, carId);
                deleteBookingPst.setInt(2, userId);
                deleteBookingPst.setString(3, moNo);
                int deleted = deleteBookingPst.executeUpdate();
    
                if (deleted > 0) {
                    System.out.println(GREEN + "Car Returned Successfully. Thank you!" + reset);
                    con.commit();
                } else {
                    System.out.println(RED + "Failed to Delete Booking Record" + reset);
                    con.rollback();
                }
            }
        } catch (SQLException e) {
            System.out.println(RED + "An error occurred: " + e.getMessage() + reset);
            con.rollback();
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

}
