package net.javaguides.springboot.recommenderSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Recommender {
    private static final Map<Personality, String> chart = new HashMap<Personality, String>();

    static {
        chart.put(new Personality(60, 50,40,30,35,40,30,15,15,5), "ROSE");
        chart.put(new Personality(30, 50,50,30,35,40,15,30,15,15), "ORCHID");
        chart.put(new Personality(50, 40,75,50,35,15,50,15,5,25), "POPPY");
        chart.put(new Personality(50, 50,50,30,35,40,30,30,15,15), "PEONY");
        chart.put(new Personality(15, 15,70,15,80,10,10,15,5,90), "CACTUS");
        chart.put(new Personality(35, 70,30,35,35,75,20,15,35,15), "CALLA LILY");
        chart.put(new Personality(35, 30,40,40,30,30,65,15,10,15), "CHRYSANTHEMUM");
        chart.put(new Personality(55, 40,40,50,35,50,50,15,25,10), "TULIP");
        chart.put(new Personality(40, 40,45,35,25,45,55,50,20,20), "IRIS");
        chart.put(new Personality(40, 30,70,30,70,80,30,40,20,50), "HYDRANGEA");
    }

    public static String knn(Personality p, int k){
        Map<Long, String> distances = new HashMap<Long, String>();

        ArrayList<Long> l = new ArrayList<Long>();

        String s = "none";
        long distance = 999999999;
        for(Map.Entry<Personality, String> entry : chart.entrySet()){
            long current = 0;
            current = (long) (p.getAdaptable() - entry.getKey().getAdaptable()) *(p.getAdaptable() - entry.getKey().getAdaptable())+
                    (long) (p.getAdventurous() - entry.getKey().getAdventurous()) *(p.getAdventurous() - entry.getKey().getAdventurous()) +
                    (long) (p.getCalm() - entry.getKey().getCalm()) *(p.getCalm() - entry.getKey().getCalm()) +
                    (long) (p.getCreative() - entry.getKey().getCreative()) *(p.getCreative() - entry.getKey().getCreative()) +
                    (long) (p.getElegant() - entry.getKey().getElegant()) *(p.getElegant() - entry.getKey().getElegant()) +
                    (long) (p.getExtrovert() - entry.getKey().getExtrovert()) *(p.getExtrovert() - entry.getKey().getExtrovert()) +
                    (long) (p.getGentle() - entry.getKey().getGentle()) *(p.getGentle() - entry.getKey().getGentle()) +
                    (long) (p.getOptimistic() - entry.getKey().getOptimistic()) *(p.getOptimistic() - entry.getKey().getOptimistic()) +
                    (long) (p.getSophisticated() - entry.getKey().getSophisticated()) *(p.getSophisticated() - entry.getKey().getSophisticated()) +
                    (long) (p.getWarm() - entry.getKey().getWarm()) *(p.getWarm() - entry.getKey().getWarm());
            if(current < distance){
                distance = current;
                s = entry.getValue();
            }

            distances.put(current, entry.getValue());
            l.add(current);

            System.out.println(entry.getValue() + "  " + current);
        }

        Collections.sort(l);
        long dist = l.get(k-1);
        return distances.get(dist);
    }
}