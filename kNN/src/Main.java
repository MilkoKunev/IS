import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

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
                trainingArr.add(new Node(sLength, sWidth, pLength, pWidth, nodeClass));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 20; i++) {
            int index = random.nextInt(150 - i);
            testArr.add(trainingArr.get(index));
            trainingArr.remove(index);

        }
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Milko Kunev\\IdeaProjects\\IS\\kNN\\src\\data.txt");

        readData(file);
        int rightPredictions = 0;
        for(int i = 0; i < 20; i++) {
            String nodeClass = measureDistances(testArr.get(i), 5);

            if(nodeClass.equals(testArr.get(i).getNodeClass())) {

                rightPredictions++;
            }

        }
        System.out.println("--------Right predictions---------");
        System.out.println(rightPredictions);
        System.out.println("--------Accuracy------------------");
        System.out.println((double)rightPredictions/20.0 * 100);



    }

    public static String measureDistances(Node test, int k) {
        HashMap<Node, Double> map = new HashMap<>();
        for(int i = 0; i < trainingArr.size(); i++) {
            double distance = getEuclideanDistance(test, trainingArr.get(i));
            map.put(trainingArr.get(i), distance);
        }

        LinkedList<Map.Entry<Node, Double>> list = getKNN(map);

        return findClass(list, k);
    }

    public static String findClass(LinkedList<Map.Entry<Node, Double>> list, int k) {
        HashMap<String, Integer> classNumbers = new HashMap<>();
        int i = 0;
        for (Map.Entry<Node, Double> entry : list) {
            if(i == k) {
                break;
            }
            if(classNumbers.containsKey(entry.getKey().getNodeClass())) {
                classNumbers.put(entry.getKey().getNodeClass(), classNumbers.get(entry.getKey().getNodeClass()) + 1);
            }
            else{
                classNumbers.put(entry.getKey().getNodeClass(), 1);
            }
            i++;
        }

        int max = 0;
        String nodeClass = "";
        for(Map.Entry<String, Integer> entry: classNumbers.entrySet()) {
            int tempMax = entry.getValue();
            if(max < tempMax) {
                max = tempMax;
                nodeClass = entry.getKey();
            }
        }

        return nodeClass;
    }

    public static LinkedList<Map.Entry<Node, Double>> getKNN(HashMap<Node, Double> map) {
        Set<Map.Entry<Node, Double>> mapEntries = map.entrySet();
        LinkedList<Map.Entry<Node, Double>> aList = new LinkedList<>(mapEntries);
        Collections.sort(aList, new Comparator<Map.Entry<Node, Double>>() {
            @Override
            public int compare(Map.Entry<Node, Double> o1, Map.Entry<Node, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return aList;
    }

    public static double getEuclideanDistance(Node e, Node v) {
         return Math.sqrt(Math.pow((e.getpLength() - v.getpLength()), 2) +
                Math.pow((e.getpWidth() - v.getpWidth()), 2) +
                Math.pow((e.getsLength() - v.getsLength()), 2) +
                Math.pow((e.getpWidth() - v.getpWidth()), 2));
    }
}
