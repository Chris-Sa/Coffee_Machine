import java.util.ArrayList;
import java.util.Scanner;

class ManufacturingController {
    // here you may declare a field
    private static ArrayList<String> productRequests = new ArrayList();

    public static String requestProduct(String product) {
        // write your code here
        int requestNumber = getNumberOfProducts() + 1;
        String request = requestNumber + ". Requested " + product;
        productRequests.add(request);
        return request;
    }

    public static int getNumberOfProducts() {
        // write your code here
        return productRequests.size();
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String product = scanner.nextLine();
            System.out.println(ManufacturingController.requestProduct(product));
            System.out.println(ManufacturingController.getNumberOfProducts());
        }
    }
}