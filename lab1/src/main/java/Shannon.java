import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Shannon {
    private FileReader F1, F2;
    ArrayList<Pair<String, Double>> P1 = new ArrayList<>();

    Shannon(){
        try {
            this.F1 = new FileReader("F1.txt");
            this.F2 = new FileReader("F2.txt");

            System.out.println("From first file:");
            this.H(1);
            System.out.println("From second file:");
            this.H(2);

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void H(int flag) throws IOException {
        String buff = "";
        if(flag == 1){
            Scanner scan = new Scanner(this.F1);
            buff = scan.nextLine();
            F1.close();
        }else if(flag == 2){
            Scanner scan = new Scanner(this.F2);
            buff = scan.nextLine();
            F2.close();
        }
        int j, k;
        for(int d = 0; d < 2; ++d){
            StringBuilder s = new StringBuilder();
            for(k = 0; k < d; ++k){
                s.append(buff.charAt(k));
            }
            for(int i = k; i < buff.length(); ++i){
                s.append(buff.charAt(i));
                for(j = 0; j < this.P1.size(); j++){
                    if(this.P1.get(j).getFirst().equals(s.toString())){
                        Double tmp = this.P1.get(j).getSecond();
                        this.P1.get(j).setSecond(++tmp);
                        break;
                    }
                }
                if(j == this.P1.size()){
                    this.P1.add(new Pair<>(s.toString(), 1d));
                }
                s.delete(0, 1);
            }

            Double sum = 0d;
            for(var i : P1){
                sum += i.getSecond();
            }
            for (Pair<String, Double> stringDoublePair : P1) {
                stringDoublePair.setSecond(stringDoublePair.getSecond() / sum);
//                System.out.println("P(" + stringDoublePair.getFirst() + ") = " + stringDoublePair.getSecond() + " ");
            }

            this.plogp(d + 1);
            this.P1.clear();
        }
    }

    private void plogp(int length) {
        double H = 0d;

        for (var a : this.P1)
            H += (-1) * (a.getSecond() * Math.log10(a.getSecond()) / Math.log10(2));

        H /= length;

        System.out.println("H " + length + " = " + H);
    }
}
