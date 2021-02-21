import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        ArrayList<Pair<Character, Double>> a = new ArrayList<>();
        a.add(new Pair<>('0', 0.0));
        a.add(new Pair<>('a', 0.0));
        a.add(new Pair<>('5', 0.0));
        a.add(new Pair<>('x', 0.0));
        CreateFiles files = new CreateFiles(a);

        Shannon s = new Shannon();
    }
}
