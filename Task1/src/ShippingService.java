public class ShippingService {
    private static ShippingService shippingService = null;
    private ShippingService(){}

    public static ShippingService getInstance() {
        if(shippingService == null){
            shippingService = new ShippingService();
        }
        return shippingService;
    }

    public double ship(Product obj, String address){
        System.out.println("paperBook is shipped to: " + address);
        return 0;
    }
}
