package dev.mdrobot.SpringPWLFT.model;

import java.util.List;
import java.util.stream.Collectors;

public class SessionStats {

    public double calculateOneRM(List<TrainingSet> setsRecord) {
        List<TrainingSet> sortedList = setsRecord.stream()
                .sorted((s1, s2) -> (int) (s1.getLoad() - s2.getLoad()))
                .collect(Collectors.toList());
        TrainingSet topSet = sortedList.get(sortedList.size() - 1);
        return topSet.getLoad() + (topSet.getRep() * topSet.getLoad() * 0.033);
    }

    public double calculateVolume(List<TrainingSet> setsRecord) {
        Double volume = setsRecord.stream()
                .map(x -> x.getRep() * x.getLoad())
                .reduce(0.0, (a, b) -> a + b);
        return volume;
    }

    public int calculateTotalReps(List<TrainingSet> setsRecord) {
        Integer totalReps = setsRecord.stream()
                .map(x -> (int) x.getRep())
                .reduce(0, Integer::sum);
        return totalReps;
    }

    public double calculateIntensity(double volume, int reps){
        return volume / reps;
    }

    public String getTopSet(List<TrainingSet> setsRecord) {
        List<TrainingSet> sortedList = setsRecord.stream()
                .sorted((s1, s2) -> (int) (s1.getLoad() - s2.getLoad()))
                .collect(Collectors.toList());
        List<String> topSet = sortedList.stream()
                .map(x -> String.valueOf((int) x.getRep()) + " x " + String.valueOf(x.getLoad()))
                .collect(Collectors.toList());
        return topSet.get(topSet.size() - 1);
    }

    public static String recordToString(List<TrainingSet> list) {
        String rs = "";
        for (TrainingSet set : list) {
            rs = rs + (int)set.getRep() + " x " + set.getLoad() + ",  ";
        }
        return rs.substring(0, rs.length() - 3);
    }

    public static void addSessionStats(Exercise ex, List<TrainingSet> sets) {
        ex.setRepetitionMark("x" + ex.getSetsRecord().get(0).getRep());
        ex.setOneRM(ex.calculateOneRM(sets));
        ex.setVolume(ex.calculateVolume(sets));
        ex.setTotalReps(ex.calculateTotalReps(sets));
        ex.setIntensity(ex.calculateIntensity(ex.getVolume(), ex.getTotalReps()));
        ex.setTopSet(ex.getTopSet(sets));
        ex.setSetsRecordAsString(ex.recordToString(sets));
    }

}
