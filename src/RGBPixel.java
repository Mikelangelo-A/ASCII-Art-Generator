public class RGBPixel {
    private int red;
    private int green;
    private int blue;

    public RGBPixel(int red, int green, int blue){
        if(red >= 0 && red <= 255){
            this.red = red;
        }
        if(green >= 0 && green <= 255){
            this.green = green;
        }
        if(blue >= 0 && blue <= 255){
            this.blue = blue;
        }
    }
    public int[] getRGB(){
        int[] rgb = {red, green, blue};
        return rgb;
    }
    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
