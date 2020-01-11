package game;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helper {


    public  static GameOperationResult moveToRightAndAddZeros(Nut[] row) {

        List<Nut> filteredNuts = filterZeros(Stream.of(row).collect(Collectors.toList()));

        Object[] originValues = Stream.of(row).map(e -> e.getValue()).toArray();

        for(int i = filteredNuts.size() - 1; i >= 1; i--) {

            Nut current = filteredNuts.get(i);
            Nut previous = filteredNuts.get(i - 1);

            if(current.equals(previous)){
                current.combine(previous);
            }
        }


        Object[] result = addZeroToBeginning(filterZeros(filteredNuts), row.length).toArray();

        Object[] castedArray = Stream.of(result).map(n -> {
            Nut e = (Nut)n;
            return e.getValue();
        }).toArray();

        boolean moveHappen = !Arrays.equals(castedArray, originValues);

        return new GameOperationResult<>(Arrays.copyOf(result, row.length, Nut[].class), moveHappen);
    }

    private  static List<Nut> filterZeros(List<Nut> row) {

        return row.stream().filter(n -> n.getValue() != 0).collect(Collectors.toList());
    }


    private static List<Nut> addZeroToBeginning(List<Nut> row, int finalSize) {
        if (finalSize <= row.size())
            return row;

        while (row.size() < finalSize) {
            row.add(0, new Nut(0));
        }

        return row;
    }


    private static List<Nut> addZeroToEnd(List<Nut> row, int finalSize) {
        if (finalSize <= row.size())
            return row;

        while (row.size() < finalSize) {
            row.add(new Nut(0));
        }

        return row;
    }


    public static GameOperationResult<Nut[]> moveToLeftAndAddZeros(Nut[] row) {


        List<Nut> filteredNuts = filterZeros(Stream.of(row).collect(Collectors.toList()));

        Object[] originValues = Stream.of(row).map(e -> e.getValue()).toArray();

        for(int i = 0; i <= filteredNuts.size() - 2; i++) {

            Nut current = filteredNuts.get(i);
            Nut next = filteredNuts.get(i + 1);

            if(current.equals(next)){
                current.combine(next);
            }
        }


        Object[] result = addZeroToEnd(filterZeros(filteredNuts), row.length).toArray();

        Object[] castedArray = Stream.of(result).map(n -> {
            Nut e = (Nut)n;
            return e.getValue();
        }).toArray();

        boolean moveHappen = !Arrays.equals(castedArray, originValues);

        return new GameOperationResult<>(Arrays.copyOf(result, row.length, Nut[].class), moveHappen);
    }
}
