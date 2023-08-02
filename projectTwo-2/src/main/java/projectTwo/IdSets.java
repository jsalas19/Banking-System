package projectTwo;

import java.util.*;

public class IdSets {
    protected static Set<Integer> Id_set = new HashSet<>();
    protected static Set<Integer> Acc_Id_set = new HashSet<>();
    protected static List<Integer> Id_list = new ArrayList<>();
    protected static List<Integer> Acc_Id_list = new ArrayList<>();

    public static void create_sets(Map<String, List<String>> data){
        for (int k = 0; k < data.get("Identification Number").size(); k++) {
            Id_list.add(Integer.parseInt(data.get("Identification Number").get(k)));
            Acc_Id_list.add(Integer.parseInt(data.get("Checking Account Number").get(k)) & 1000);
            Id_set.add(Integer.parseInt(data.get("Identification Number").get(k)));
            Acc_Id_set.add(Integer.parseInt(data.get("Checking Account Number").get(k)) & 1000);
        }
        Collections.sort(Acc_Id_list);
        Collections.sort(Id_list);
    }
}
