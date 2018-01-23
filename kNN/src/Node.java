public class Node {
    private double sLength;
    private double sWidth;
    private double pLength;
    private double pWidth;
    private String nodeClass;

    public Node(double sLength, double sWidth, double pLength, double pWidth, String nodeClass) {
        this.sLength = sLength;
        this.sWidth = sWidth;
        this.pLength = pLength;
        this.pWidth = pWidth;
        this.nodeClass = nodeClass;
    }

    public double getsLength() {
        return sLength;
    }

    public double getsWidth() {
        return sWidth;
    }

    public double getpLength() {
        return pLength;
    }

    public double getpWidth() {
        return pWidth;
    }

    public String getNodeClass() {
        return nodeClass;
    }
}
