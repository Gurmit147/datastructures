public class Rectangle {
    private int length, width, area;

    public Rectangle(int l, int w){
        this.length = l;
        this.width = w;
    }

    public void calculateArea(){
        area = length * width;
    }

    public int getArea(){
        return area;
    }
}
