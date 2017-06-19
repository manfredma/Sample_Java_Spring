package property.editor;

public class House {
    private int position;
    public String area;
    private double price;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "House{" + "position=" + position + ", area='" + area + '\'' + ", price=" + price + '}';
    }
}