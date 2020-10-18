package interfaces;

import abstractClasses.Graph;

import java.util.Scanner;

public interface IWeighted<T> {

    void addEdge(T fromV, T toV, double weight);

    double getWeightFromEdge(T fromV, T toV);

    void setWeightToEdge(T fromV, T toV, double weight);

    default void changeWeight(T fromV, T toV, Double weight){
        Scanner sc = new Scanner(System.in);
        System.out.print("Would you like to change weight: Y(yes) ");
        String s = sc.nextLine();
        if ("Y".equals(s)) {
            setWeightToEdge(fromV, toV, weight);
        }
    }

}
