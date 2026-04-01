import java.util.*;

class BogieComparator implements Comparator<String> {
    public int compare(String b1, String b2) {
        return getPriority(b1) - getPriority(b2);
    }

    private int getPriority(String id) {
        if (id.startsWith("E")) return 1;
        if (id.startsWith("P")) return 2;
        if (id.startsWith("C")) return 3;
        if (id.startsWith("G")) return 4;
        return 5;
    }
}

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        SortedSet<String> bogieSet = new TreeSet<>(new BogieComparator());

        bogieSet.add("P1");
        bogieSet.add("C1");
        bogieSet.add("E1");
        bogieSet.add("G1");
        bogieSet.add("P2");

        System.out.println("Train Formation Order:");
        for(String b : bogieSet) {
            System.out.println(b);
        }
    }
}