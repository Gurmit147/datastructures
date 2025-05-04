package calories;

public class CaloriesCart {
    
    public String foodname;
    public int foodcalories;
    
    // Make the cart array and index static for a global cart
    private static CaloriesCart[] calories = new CaloriesCart[100];
    private static int caloriesIndex = 0;

    public CaloriesCart(String foodname, int foodcalories) {
        this.foodname = foodname;
        this.foodcalories = foodcalories;
    }


    public static boolean Add(String foodname, int foodcalories) {
        if (caloriesIndex < calories.length) {
            calories[caloriesIndex] = new CaloriesCart(foodname, foodcalories);
            caloriesIndex++; 
            return true;
        } else {
            return false; 
        }
    }
    
    public static CaloriesCart[] getCartItems() {
    return calories;
}

    public static int getCartSize() {
        return caloriesIndex;
    }
    
    public static void deleteItem(int index) {
    if (index >= 0 && index < caloriesIndex) {
        // Shift remaining items to the left
        for (int i = index; i < caloriesIndex - 1; i++) {
            calories[i] = calories[i + 1];
        }
        caloriesIndex--;
        calories[caloriesIndex] = null; // clear the last element
    }
}


    
}
