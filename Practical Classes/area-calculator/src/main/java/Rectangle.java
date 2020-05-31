public class Rectangle implements AreaShape{
    private double widht;
    private double height;

    public Rectangle(double widht, double height) {
        this.widht = widht;
        this.height = height;
    }

    public double getWidht() {
        return widht;
    }

    public void setWidht(double widht) {
        this.widht = widht;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return getWidht()*getHeight();
    }

    public void draw() {
        System.out.println("Rectangle");
    }
}
