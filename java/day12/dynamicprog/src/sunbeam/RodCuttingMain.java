package sunbeam;

public class RodCuttingMain {
	private static final int[] price = {0, 1, 5, 8, 9, 10, 14, 17, 20, 24, 30};
	/*
	public static int recRodMaxPrice(int len) {
		if(len == 0)
			return 0;
		int maxPrice = 0;
		for(int i=1; i<=len; i++) {
			int leftPrice = price[i];
			int rightPrice = recRodMaxPrice(len-i);
			int lenPrice = leftPrice + rightPrice;
			if(lenPrice > maxPrice)
				maxPrice = lenPrice;
		}
		return maxPrice;
	}
	*/
	
	private static int count = 0;
	public static int recRodMaxPrice(int len) {
		count++;
		if(len == 0)
			return 0;
		int maxPrice = 0;
		for(int i=1; i<=len; i++)
			maxPrice = Math.max(maxPrice, price[i] + recRodMaxPrice(len-i));
		return maxPrice;
	}
	
	public static int memRodMaxPrice(int len, int[] maxPrices) {
		count++;
		if(len == 0)
			return maxPrices[len] = 0;
		if(maxPrices[len] != 0)
			return maxPrices[len];
		int maxPrice = 0;
		for(int i=1; i<=len; i++)
			maxPrice = Math.max(maxPrice, price[i] + memRodMaxPrice(len-i, maxPrices));
		maxPrices[len] = maxPrice;
		return maxPrices[len];
	}
	public static int memRodMaxPrice(int len) {
		int[] maxPrices = new int[len+1];
		return memRodMaxPrice(len, maxPrices);
	}
	
	public static int dpRodMaxPrice(int length) {
		count++;
		int[] maxPrices = new int[length+1];
		maxPrices[0] = 0;
		for(int len=1; len<=length; len++) {
			maxPrices[len] = 0;
			for(int i=1; i<=len; i++)
				maxPrices[len] = Math.max(maxPrices[len], price[i] + maxPrices[len-i]);
		}
		return maxPrices[length];
	}
	public static void main(String[] args) {
		int maxPrice, rodLength = 8;
		
		count = 0;
		maxPrice = recRodMaxPrice(rodLength);
		System.out.println("Max price for length " + rodLength + " = " + maxPrice + " with fn calls: " + count);

		count = 0;
		maxPrice = memRodMaxPrice(rodLength);
		System.out.println("Max price for length " + rodLength + " = " + maxPrice + " with fn calls: " + count);

		count = 0;
		maxPrice = dpRodMaxPrice(rodLength);
		System.out.println("Max price for length " + rodLength + " = " + maxPrice + " with fn calls: " + count);
	}
}

