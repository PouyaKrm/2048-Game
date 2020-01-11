//import com.sun.rowset.internal.Row;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class GameOperations {
//
//    private List<game.Nut> originRows;
//
//
//    private static List<game.Nut> filterZeros(List<game.Nut> row) {
//
//        return row.stream().filter(n -> n.getValue() != 0).collect(Collectors.toList());
//    }
//
//    public static List<game.Nut> addZeroToBeginning(List<game.Nut> row, int finalSize) {
//        if (finalSize <= row.size())
//            return row;
//
//        while (row.size() < finalSize) {
//            row.add(0, new game.Nut(0));
//        }
//
//        return row;
//    }
//
//    public static List<game.Nut> addZeroToEnd(List<game.Nut> row, int finalSize) {
//        if (finalSize <= row.size())
//            return row;
//
//        while (row.size() < finalSize) {
//            row.add(new game.Nut(0));
//        }
//
//        return row;
//    }
//
//    private static List<game.Nut> combineNut(List<game.Nut> nuts, int hostIndex, int guestIndex) {
//        game.Nut host = nuts.get(hostIndex);
//        game.Nut guest = nuts.get(guestIndex);
//
//        host.addToValue(guest);
//
//        nuts.remove(guest);
//
//        return nuts;
//    }
//
//     public static game.GameOperationResult moveToRightAndAddZeros(game.Nut[] row) {
//
//
//        List<game.Nut> filteredNuts = filterZeros(Stream.of(row).collect(Collectors.toList()));
//
//        boolean movementHappened = filteredNuts.size() < row.length;
//
//        for(int i = filteredNuts.size() - 1; i >= 1; i--) {
//
//            game.Nut current = filteredNuts.get(i);
//            game.Nut previous = filteredNuts.get(i - 1);
//
//            if(current.equals(previous)){
//                current.combine(previous);
//                movementHappened = true;
//            }
//        }
//
//        Object[] result = addZeroToBeginning(filterZeros(filteredNuts), row.length).toArray();
//
//        return new game.GameOperationResult<game.Nut[]>(Arrays.copyOf(result, result.length, game.Nut[].class), movementHappened);
//     }
//
//    public static game.GameOperationResult moveToLeftAndAddZeros(game.Nut[] row) {
//
//        List<game.Nut> filteredNuts = filterZeros(Stream.of(row).collect(Collectors.toList()));
//
//        boolean movementHappened = filteredNuts.size() < row.length;
//
//        for(int i = 0; i <= filteredNuts.size() - 2; i++) {
//
//            game.Nut current = filteredNuts.get(i);
//            game.Nut next = filteredNuts.get(i + 1);
//
//            if(current.equals(next)){
//                current.combine(next);
//                movementHappened = true;
//            }
//        }
//
//
//        Object[] result = addZeroToEnd(filterZeros(filteredNuts), row.length).toArray();
//
//        return new game.GameOperationResult(Arrays.copyOf(result, row.length, game.Nut[].class), movementHappened);
//    }
//
//    public static game.GameOperationResult<game.Nut[][]> moveColumnUp(game.Nut[][] nuts, int columnIndex) {
//        List<game.Nut> selectedCol = Stream.of(nuts).map(e -> e[columnIndex]).collect(Collectors.toList());
//        game.GameOperationResult<game.Nut[]> result = moveToLeftAndAddZeros(selectedCol.toArray(new game.Nut[nuts.length]));
//
//        for (int i = 0; i < nuts.length; i++) {
//            nuts[i][columnIndex] = result.getResult()[i];
//        }
//
//        return new game.GameOperationResult<>(nuts, result.isMovementHappened());
//
//    }
//
//    public static game.GameOperationResult<game.Nut[][]> moveColumnDown(game.Nut[][] nuts, int columnIndex) {
//        List<game.Nut> selectedCol = Stream.of(nuts).map(e -> e[columnIndex]).collect(Collectors.toList());
//        game.GameOperationResult<game.Nut[]> result = moveToRightAndAddZeros(selectedCol.toArray(new game.Nut[nuts.length]));
//
//        for (int i = 0; i < nuts.length; i++) {
//            nuts[i][columnIndex] = result.getResult()[i];
//        }
//
//        return new game.GameOperationResult<>(nuts, result.isMovementHappened());
//    }
//
//}
