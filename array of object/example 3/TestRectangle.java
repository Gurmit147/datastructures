import java.util.*;
public class TestRectangle {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Rectangle rect[] = new Rectangle[2];

        for(int i=0; i<rect.length; i++){
            System.out.print("Length: ");
            int length = sc.nextInt();

            System.out.print("Width: ");
            int width = sc.nextInt();

            rect[i] = new Rectangle(length,width);
            rect[i].calculateArea();

            System.out.println("Area of Rectangle " + (i+1) + " = " + rect[i].getArea());
            
        }

        System.out.println("Data stored in array of object");
        for(int i=0; i<rect.length; i++){
            System.out.println("Area of Rectangle " + (i+1) + " = " + rect[i].getArea());
        }
    }
}
