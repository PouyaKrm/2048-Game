package game;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Environment {

    private int rows, cols;

    private Nut[][] nuts;

    private boolean movementHappened = true;

    public Environment(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        nuts = new Nut[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                nuts[i][j] = new Nut(0);
            }
        }

        createRandomNutIfMovementHappened();
        createRandomNutIfMovementHappened();
        movementHappened = false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
         for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                builder.append(nuts[i][j].getValue() + " ");
            }
            builder.append("\n");
        }

         return builder.toString();
    }

    public void show(){
        System.out.println(this);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Nut[][] getNuts() {
        return nuts;
    }

    private void createRandomNutIfMovementHappened() {
        if (!movementHappened) {
            return;
        }

        Random random = new Random();
        int x = random.nextInt(rows);
        int y = random.nextInt(cols);

        while(nuts[x][y].getValue() != 0) {
            x = random.nextInt(rows);
            y = random.nextInt(cols);
        }
        double randValue = Math.random();
        nuts[x][y].setValue(randValue < 0.9 ? 2 : 4);
    }


    public int getMaxValue() {

        int max = 0;

        for (Nut[] e : nuts) {
            for (Nut n : e) {
                max = n.getValue() > max ? n.getValue() : max;
            }
        }

        return max;
    }

    private boolean moveRowToRight(int rowIndex) {
        Nut[] row = nuts[rowIndex];

            GameOperationResult<Nut[]>  result = Helper.moveToRightAndAddZeros(row);

        nuts[rowIndex] = result.getResult();

        return result.isMovementHappened();
    }


    private boolean moveRowToLeft(int rowIndex) {

        Nut[] row = nuts[rowIndex];

        GameOperationResult<Nut[]> result = Helper.moveToLeftAndAddZeros(row);

        nuts[rowIndex] = result.getResult();

        return result.isMovementHappened();
    }

    private boolean moveColumnUp(int columnIndex) {

        List<Nut> selectedCol = Stream.of(nuts).map(e -> e[columnIndex]).collect(Collectors.toList());
        GameOperationResult<Nut[]> result = Helper.moveToLeftAndAddZeros(selectedCol.toArray(new Nut[nuts.length]));

        for (int i = 0; i < nuts.length; i++) {
            nuts[i][columnIndex] = result.getResult()[i];
        }

        return result.isMovementHappened();
    }

    private  boolean moveColumnDown(int columnIndex) {
        List<Nut> selectedCol = Stream.of(nuts).map(e -> e[columnIndex]).collect(Collectors.toList());
        GameOperationResult<Nut[]> result = Helper.moveToRightAndAddZeros(selectedCol.toArray(new Nut[nuts.length]));

        for (int i = 0; i < nuts.length; i++) {
            nuts[i][columnIndex] = result.getResult()[i];
        }

        return result.isMovementHappened();
    }



    public void moveRight() {

        boolean moveResult;
        movementHappened = false;
        for (int i = 0; i < rows; i++) {
            moveResult = moveRowToRight(i);
            this.movementHappened = movementHappened || moveResult;
        }

        createRandomNutIfMovementHappened();
    }


    public void moveLeft() {
        boolean moveResult;
        movementHappened = false;
        for (int i = 0; i < rows; i++) {
            moveResult = moveRowToLeft(i);
            this.movementHappened = moveResult  || movementHappened;
        }

        createRandomNutIfMovementHappened();
    }

    public void moveUp() {

        boolean moveResult;
        movementHappened = false;

        for (int i = 0; i < cols; i++) {
            moveResult = moveColumnUp(i);
            movementHappened = movementHappened || moveResult ;
        }

        createRandomNutIfMovementHappened();
    }

    public void moveDown() {

        boolean moveResult = false;
        movementHappened = false;

        for (int i = 0; i < cols; i++) {
            moveResult = moveColumnDown(i);
            movementHappened = movementHappened || moveResult;
        }

        createRandomNutIfMovementHappened();
    }


    public boolean hasWon(){
        return getMaxValue() == 2048;
    }

    public boolean hasLost() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                if(nuts[i][j].getValue() == 0){
                    return false;
                }
            }
        }

        for (int  i = 0; i< rows; i++) {
            for (int j = 0; j < cols - 1; j++) {
                if (nuts[i][j].equals(nuts[i][j+1])){
                    return false;
                }

            }
        }

        for (int i = 0; i < rows - 1; i++){
            for (int j = 0; j < cols; j++) {

                if(nuts[i][j].equals(nuts[i+1][j])){
                    return false;
                }
            }
        }

        return true;
    }



}
