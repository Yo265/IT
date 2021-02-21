import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;


public class CreateFiles {
    private ArrayList<Pair<Character, Double>> alphabet;
    private final ArrayList<Double> P1 = new ArrayList<>();
    private final ArrayList<Double> P2 = new ArrayList<>();



    public CreateFiles(ArrayList<Pair<Character, Double>> A){
        try {
            FileWriter f1 = new FileWriter("F1.txt", false);
            FileWriter f2 = new FileWriter("F2.txt", false);
            this.alphabet = A;

            System.out.println("F1:");
            this.GenerateF1();
            this.Print();
            this.Write(f1);
            f1.close();
            this.alphabet.remove(this.alphabet.size() - 1);

            System.out.println("Theory");
            this.plogp(1, this.P1);
            ArrayList<Double> H1 = this.PaPb(this.P1, this.P1);
            this.plogp(2, H1);
            ArrayList<Double> H2 = this.PaPb(H1, this.P1);
            this.plogp(3, H2);

            System.out.println("F2:");
            this.GenerateF2();
            this.Print();
            this.Write(f2);

            System.out.println("Theory");
            this.plogp(1, this.P2);
            H1 = this.PaPb(this.P2, this.P2);
            this.plogp(2, H1);
            H2 = this.PaPb(H1, this.P2);
            this.plogp(3, H2);

//            for(Pair<Character, Double> a : this.alphabet){
//                System.out.println(a.getFirst() + " " + a.getSecond());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void Write(FileWriter File) {
        for(int i = 0; i < 11000; ++i){
            double rand = Math.random();
            for(int j = 1; j < this.alphabet.size(); ++j){
                if(rand >= this.alphabet.get(j - 1).getSecond() && rand < this.alphabet.get(j).getSecond()){
                    try {
                        File.write(this.alphabet.get(j - 1).getFirst());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            File.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Print() {
    }

    void GenerateF1() {
        double p = 0.0;
        for(int i = 0; i < this.alphabet.size(); ++i){
            this.alphabet.get(i).setSecond(p);
            this.P1.add(((double) (1) / this.alphabet.size()));
            p += (double) (1) / this.alphabet.size();
        }
        this.alphabet.add(new Pair<>(' ', 1d));
    }


    void GenerateF2() {
        ArrayList<Integer> num = new ArrayList<>();
        int sum = 0, tmp;
        for(int i = 0; i < this.alphabet.size(); ++i){
            tmp = (int) (Math.random() * 100);
            num.add(tmp);
            sum += tmp;
        }
        double p = 0.0;
        for(int i = 0; i < this.alphabet.size(); ++i){
            this.alphabet.get(i).setSecond(p);
            this.P2.add((double) (num.get(i)) / sum);
            p += P2.get(i);

        }
        this.alphabet.add(new Pair<>(' ', 1d));
        this.alphabet.sort(Comparator.comparing(Pair::getSecond));
    }
    void plogp(int length, ArrayList<Double> P) {
        double H = 0;
        for (Double a : P) {
            H += (-1) * (a * Math.log10(a) / Math.log10(2));

        }

        H /= length;

        System.out.println("H" + length + " = " + H);
    }
    ArrayList<Double> PaPb(ArrayList<Double> P, ArrayList<Double> P1){
        ArrayList<Double> H = new ArrayList<>();

        for(Double i : P)
            for(Double j : P1)
                H.add(i * j);
        return H;
    }

}
