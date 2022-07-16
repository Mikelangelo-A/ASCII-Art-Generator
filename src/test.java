public class test {
    public static void random() {

        int length = 65;
        double range = 255 / (double) length;
        int number1 = 2;
        int number2 = 155;
        int number3 = 255;
        int number4 = 0;
        double index1 = (int) Math.floor(number1 / range);
        double index2 = (int) Math.floor(number2 / range);
        double index3 = (int) Math.floor(number3 / range);
        int index4 = (int) Math.floor(number4 / range);
        System.out.println(index1 + " " + index2 + " " + index3 + " " + index4);
    }
}
