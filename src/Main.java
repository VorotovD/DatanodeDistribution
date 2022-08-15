import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // quantity of DN
        int DN = 20;
        // quantity of NN
        int NN = 1;
        Map<Integer, StringBuilder> resultOfDistribution = new HashMap<>();
        // Таблица
        System.out.format("|%s|%d",DN,NN);
        /*for (int i = 1; i <= DN; i++) {
            System.out.println(distribution(resultOfDistribution, i, NN));
        }*/
    }

    //Метод распределения DN и NN по VM
    public static Map<Integer, StringBuilder> distribution(Map<Integer, StringBuilder> result, final int DN, final int NN) {
        //Мета-данные по заполнению и распределению NN и DN
        ArrayList<Integer> metaData = new ArrayList<>(4);
        metaData.add(0, 0); // 1 VM
        metaData.add(1, 0); // 2 VM
        metaData.add(2, 0); // 3 VM
        metaData.add(3, 0); // номер VM для распределения
        // Список распределенных DN для каждой VM
        StringBuilder regex1 = new StringBuilder("NN");
        StringBuilder regex2 = new StringBuilder();
        StringBuilder regex3 = new StringBuilder();
        // Определение места NN
        result.put(1, regex1);
        metaData.set(0, 1);

        //Распределение DN по VM
        for (int i = 1; i <= DN; i++) {
            switch (choiceOfVM(metaData).get(3)) {
                case (1) -> result.put(1, regex1.append(",DN").append(i));
                case (2) -> result.put(2, regex2.append("DN").append(i).append(","));
                case (3) -> result.put(3, regex3.append("DN").append(i).append(","));
            }
        }
    // Форматирование списка распределенных DN
        for (Integer key : result.keySet()) {
            String regex = ",";
            StringBuilder value = result.get(key);
            int index = value.lastIndexOf(",");
            int length = value.length() - 1;
            if (index == length) {
                value.deleteCharAt(length);
            }
        }
        return result;
    }

    // Выбор №VM для распределения
    public static ArrayList<Integer> choiceOfVM(ArrayList<Integer> metaData) {

        if (metaData.get(1) <= metaData.get(0)) {
            if (metaData.get(1) >= metaData.get(2)) {
                int quantity = metaData.get(2);
                metaData.set(2, ++quantity);
                metaData.set(3, 3);
                return metaData;
            }
            int quantity = metaData.get(1);
            metaData.set(1, ++quantity);
            metaData.set(3, 2);
            return metaData;
        } else {
            int quantity = metaData.get(2);
            metaData.set(0, ++quantity);
            metaData.set(3, 1);
            return metaData;
        }
    }
}
