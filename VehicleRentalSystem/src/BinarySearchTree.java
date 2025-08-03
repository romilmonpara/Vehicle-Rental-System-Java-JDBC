public class BinarySearchTree {
        final static String GREEN = "\u001b[32;1m";
        final static String YELLOW = "\u001b[33m";
        final static String RED = "\033[1;31m";
        final static String reset = "\u001b[0m";

    private class Node {
        int rent; // Sorting by rent
        int carId;
        String comName;
        String carName;
        String type;
        int price;
        String city;
        Node left, right;

        Node(int rent, int carId, String comName, String carName, String type, int price, String city) {
            this.rent = rent;
            this.carId = carId;
            this.comName = comName;
            this.carName = carName;
            this.type = type;
            this.price = price;
            this.city = city;
            this.left = this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int rent, int carId, String comName, String carName, String type, int price, String city) {
        root = insertRec(root, rent, carId, comName, carName, type, price, city);
    }

    private Node insertRec(Node root, int rent, int carId, String comName, String carName, String type, int price, String city) {
        if (root == null) {
            return new Node(rent, carId, comName, carName, type, price, city);
        }
        if (rent < root.rent) {
            root.left = insertRec(root.left, rent, carId, comName, carName, type, price, city);
        } else {
            // For rent values equal to or greater than the current node's rent
            root.right = insertRec(root.right, rent, carId, comName, carName, type, price, city);
        }
        return root;
    }

    public void displayHighToLow() {
        if (root == null) {
            System.out.println(RED + "No cars to display." + reset);
        } else {
            displayHighToLowRec(root);
        }
    }

    private void displayHighToLowRec(Node root) {
        if (root != null) {
            displayHighToLowRec(root.right);
            printNode(root);
            displayHighToLowRec(root.left);
        }
    }

    public void displayLowToHigh() {
        if (root == null) {
            System.out.println(RED + "No cars to display." + reset);
        } else {
            displayLowToHighRec(root);
        }
    }

    private void displayLowToHighRec(Node root) {
        if (root != null) {
            displayLowToHighRec(root.left);
            printNode(root);
            displayLowToHighRec(root.right);
        }
    }

    private void printNode(Node node) {
        System.out.println(GREEN + "Car ID: " + node.carId + reset);
        System.out.println(GREEN + "Car Company Name: " + node.comName + reset);
        System.out.println(GREEN + "Car Name: " + node.carName + reset);
        System.out.println(GREEN + "Car Type: " + node.type + reset);
        System.out.println(GREEN + "Car Rent: " + node.rent + reset);
        System.out.println(GREEN + "Car Price: " + node.price + reset);
        System.out.println(GREEN + "City: " + node.city + reset);
        System.out.println(YELLOW + "==============================================================================" + reset);
    }

    public boolean isEmpty() {
        return root == null;
    }
}
