import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static ArrayList<Node> trainingArr = new ArrayList<>();
    public static ArrayList<Node> testArr = new ArrayList<>();
    public static Random random = new Random();

    public static void readData(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(",");
                double sLength = Double.parseDouble(temp[0]);
                double sWidth = Double.parseDouble(temp[1]);
                double pLength = Double.parseDouble(temp[2]);
                double pWidth = Double.parseDouble(temp[3]);
                String nodeClass = temp[4];
                testArr.add(new Node(sLength, sWidth, pLength, pWidth, nodeClass));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 20; i++) {
            testArr
        }
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Milko Kunev\\IdeaProjects\\kNN\\src\\data.txt");

        readData(file);
        System.out.println(trainingArr.size());
        for (Node object: trainingArr) {
            System.out.println(object.getNodeClass());
        }
    }
}
