

public class Pair<T, V> {
    private T first;
    private V second;


    Pair(T a, V b){
        this.first = a;
        this.second = b;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
