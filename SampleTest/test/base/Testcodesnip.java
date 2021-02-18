package base;

public class Testcodesnip {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//priceExtract("$261.00 each");
		System.out.println("Priceval                " +priceExtract("$476.00"));
	}
	public static float priceExtract(String price) {
		price = price.replaceAll("[^\\d.]", "");
		
		price = price.substring(0);
		//ThreadManager.logger.get().info("Extracted price is :- "+price);
		return Float.parseFloat(price);
	}
}
