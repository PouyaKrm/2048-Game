package game;

import javafx.beans.property.SimpleStringProperty;

public class Nut {

    private int value;

    private SimpleStringProperty stringValueProperty;
    private SimpleStringProperty colorPoperty;
    private String color = "gray";

    public Nut(int value) {

        this.value = value;
        stringValueProperty = new SimpleStringProperty("" + value);
        colorPoperty = new SimpleStringProperty(color);
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {

        this.value = value;
        stringValueProperty.setValue("" + value);
        setColorByValue(value);
    }


    private void setColorByValue(int value) {
        switch (value) {
            case 0:
                color = "gray";
                break;

            case 2:
                color = "#E0E0E0";
                break;

            case 4:
                color = "#FFF59D";
                break;

            case 8:
                color = "#FF9800";
                break;

            case 16:
                color = "#F57C00";
                break;

            case 32:
                color = "#EF6C00";
                break;

            case 64:
                color = "#E65100";
                break;

            case 128:
                color = "#FFEE58";
                break;

            case 256:
                color = "#FDD835";
                break;

            case 512:
                color = "#F9A825";
                break;

            case 1024:
                color = "#9E9D24";
                break;

            case 2048:
                color = "#1A237E";
                break;

                default:
                    color = "#1A237E";
        }

        colorPoperty.setValue(color);
    }

    public void combine(Nut nut){
        setValue(value + nut.getValue());
        nut.value = 0;
    }

    public String getColor() {
        return color;
    }



    @Override
    public boolean equals(Object obj) {
        Nut n = (Nut)obj;
        return n.getValue()==value;
    }


    @Override
    public String toString() {
        return "game.Nut{" +
                "value=" + value +
                '}';
    }

    public String getValueAsString() {
        return "" + (value == 0 ? "" : value);
    }


    public SimpleStringProperty getStringValueProperty() {
        return stringValueProperty;
    }

    public SimpleStringProperty getColorProperty() {
        return colorPoperty;

    }

}
