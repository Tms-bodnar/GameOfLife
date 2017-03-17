/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Bodnár Tamás <tms.bodnar@gmail.com> | www.kalandlabor.hu A
 * FieldManipulator osztályban történik a kolónia népességének újraszámolása
 */
public class FieldManipulator {

    private boolean[][] field;
    private boolean[][] previousField = new boolean[90][180];

    public FieldManipulator() {

    }

    public FieldManipulator(boolean[][] field) {
        this.field = field;
    }

    /**
     * A newField metódus paraméterként egy 2d boolean tömbből kiszámítja a mező
     * következő generációjának népességét. A metódus az új mező 2d boolean
     * tömbjét adja vissza
     *
     * @param oldField
     * @return boolean[][] newField
     */
    public boolean[][] newField(boolean[][] oldField) {
        //két tömb létrehozása az új mező kiszámításához,
        boolean[][] newField = new boolean[90][180];
        boolean[][] temp = new boolean[90][180];
        for (int i = 0; i < oldField.length; i++) {
            for (int j = 0; j < oldField[i].length; j++) {
                //az új tömbök feltöltése a paraméterként kapott tömb adataival
                previousField[i][j] = oldField[i][j];
                newField[i][j] = oldField[i][j];
                temp[i][j] = oldField[i][j];
                //az élő sejtek körüli mező tömbjének létrehozása, tekintettel az ArrayIndexOutOfBoundsException-ra
                if (oldField[i][j]) {
                    boolean[][] nbh = new boolean[3][3];
                    if (i == 0 && j == 0) {
                        nbh[0][0] = false;
                        nbh[0][1] = false;;
                        nbh[0][2] = false;;
                        nbh[1][0] = false;;
                        nbh[1][2] = oldField[i][j + 1];
                        nbh[2][0] = false;;
                        nbh[2][1] = oldField[i + 1][j];
                        nbh[2][2] = oldField[i + 1][j + 1];
                    } else if (i == 0 && j > 0 && i < oldField.length - 1 && j < oldField[i].length - 1) {
                        nbh[0][0] = false;
                        nbh[0][1] = false;
                        nbh[0][2] = false;
                        nbh[1][0] = oldField[i][j - 1];
                        nbh[1][2] = oldField[i][j + 1];
                        nbh[2][0] = oldField[i + 1][j - 1];
                        nbh[2][1] = oldField[i + 1][j];
                        nbh[2][2] = oldField[i + 1][j + 1];
                    } else if (j == 0 && i > 0 && i < oldField.length - 1 && j < oldField[i].length - 1) {
                        nbh[0][0] = false;
                        nbh[0][1] = oldField[i - 1][j];
                        nbh[0][2] = oldField[i - 1][j + 1];
                        nbh[1][0] = false;
                        nbh[1][2] = oldField[i][j + 1];
                        nbh[2][0] = false;
                        nbh[2][1] = oldField[i + 1][j];
                        nbh[2][2] = oldField[i + 1][j + 1];
                    } else if (i > 0 && j > 0 && i < oldField.length - 1 && j < oldField[i].length - 1) {
                        nbh[0][0] = oldField[i - 1][j - 1];
                        nbh[0][1] = oldField[i - 1][j];
                        nbh[0][2] = oldField[i - 1][j + 1];
                        nbh[1][0] = oldField[i][j - 1];
                        nbh[1][1] = false;
                        nbh[1][2] = oldField[i][j + 1];
                        nbh[2][0] = oldField[i + 1][j - 1];
                        nbh[2][1] = oldField[i + 1][j];
                        nbh[2][2] = oldField[i + 1][j + 1];
                    } else if (i == oldField.length - 1 && j < oldField[i].length - 1 && i > 0 && j > 0) {
                        nbh[0][0] = oldField[i - 1][j - 1];
                        nbh[0][1] = oldField[i - 1][j];
                        nbh[0][2] = oldField[i - 1][j + 1];
                        nbh[1][0] = oldField[i][j - 1];
                        nbh[1][2] = oldField[i][j + 1];
                        nbh[2][0] = false;
                        nbh[2][1] = false;
                        nbh[2][2] = false;
                    } else if (j == oldField[i].length - 1 && i < oldField.length - 1 && i > 0 && j > 0) {
                        nbh[0][0] = oldField[i - 1][j - 1];
                        nbh[0][1] = oldField[i - 1][j];
                        nbh[0][2] = false;
                        nbh[1][0] = oldField[i][j - 1];
                        nbh[1][2] = false;
                        nbh[2][0] = oldField[i + 1][j - 1];
                        nbh[2][1] = oldField[i + 1][j];
                        nbh[2][2] = false;
                    } else if (i == oldField.length - 1 && j == oldField[i].length - 1) {
                        nbh[0][0] = oldField[i - 1][j - 1];
                        nbh[0][1] = oldField[i - 1][j];
                        nbh[0][2] = false;
                        nbh[1][0] = oldField[i][j - 1];
                        nbh[1][2] = false;
                        nbh[2][0] = false;
                        nbh[2][1] = false;
                        nbh[2][2] = false;
                    }
                    // a szomszédok megszámlálása
                    int neighbour = 0;
                    for (int k = 0; k < nbh.length; k++) {
                        for (int l = 0; l < nbh[k].length; l++) {
                            if (nbh[k][l]) {
                                neighbour += 1;
                            }
                        }
                    }
                    //ha kettőnél kevesebb, vagy háromnál több szomszéd van, akkor a sejt meghal
                    if (neighbour < 2 || neighbour > 3) {
                        temp[i][j] = false;
                    }
                }
                //ha a sejt állapota megváltozott, frissítjük az új mezőben
                if (newField[i][j] != temp[i][j]) {
                    newField[i][j] = temp[i][j];
                }
            }
        }
        // a régi mező elmentése
        setPreviousField(previousField);
        for (int i = 0; i < oldField.length; i++) {
            for (int j = 0; j < oldField[i].length; j++) {
                if (!oldField[i][j]) {
                    // az üres cella körüli sejtek tömbjének létrehozása, tekintettel az ArrayIndexOutOfBoundsException-ra
                    boolean[][] deadNbh = new boolean[3][3];
                    if (i == 0 && j == 0) {
                        deadNbh[0][0] = false;
                        deadNbh[0][1] = false;;
                        deadNbh[0][2] = false;;
                        deadNbh[1][0] = false;;
                        deadNbh[1][2] = oldField[i][j + 1];
                        deadNbh[2][0] = false;;
                        deadNbh[2][1] = oldField[i + 1][j];
                        deadNbh[2][2] = oldField[i + 1][j + 1];
                    } else if (i == 0 && j > 0 && i < oldField.length - 1 && j < oldField[i].length - 1) {
                        deadNbh[0][0] = false;
                        deadNbh[0][1] = false;
                        deadNbh[0][2] = false;
                        deadNbh[1][0] = oldField[i][j - 1];
                        deadNbh[1][2] = oldField[i][j + 1];
                        deadNbh[2][0] = oldField[i + 1][j - 1];
                        deadNbh[2][1] = oldField[i + 1][j];
                        deadNbh[2][2] = oldField[i + 1][j + 1];
                    } else if (j == 0 && i > 0 && i < oldField.length - 1 && j < oldField[i].length - 1) {
                        deadNbh[0][0] = false;
                        deadNbh[0][1] = oldField[i - 1][j];
                        deadNbh[0][2] = oldField[i - 1][j + 1];
                        deadNbh[1][0] = false;
                        deadNbh[1][2] = oldField[i][j + 1];
                        deadNbh[2][0] = false;
                        deadNbh[2][1] = oldField[i + 1][j];
                        deadNbh[2][2] = oldField[i + 1][j + 1];
                    } else if (i > 0 && j > 0 && i < oldField.length - 1 && j < oldField[i].length - 1) {
                        deadNbh[0][0] = oldField[i - 1][j - 1];
                        deadNbh[0][1] = oldField[i - 1][j];
                        deadNbh[0][2] = oldField[i - 1][j + 1];
                        deadNbh[1][0] = oldField[i][j - 1];
                        deadNbh[1][2] = oldField[i][j + 1];
                        deadNbh[2][0] = oldField[i + 1][j - 1];
                        deadNbh[2][1] = oldField[i + 1][j];
                        deadNbh[2][2] = oldField[i + 1][j + 1];
                    } else if (i == oldField.length - 1 && j < oldField[i].length - 1 && i > 0 && j > 0) {
                        deadNbh[0][0] = oldField[i - 1][j - 1];
                        deadNbh[0][1] = oldField[i - 1][j];
                        deadNbh[0][2] = oldField[i - 1][j + 1];
                        deadNbh[1][0] = oldField[i][j - 1];
                        deadNbh[1][2] = oldField[i][j + 1];
                        deadNbh[2][0] = false;
                        deadNbh[2][1] = false;
                        deadNbh[2][2] = false;
                    } else if (j == oldField[i].length - 1 && i < oldField.length - 1 && i > 0 && j > 0) {
                        deadNbh[0][0] = oldField[i - 1][j - 1];
                        deadNbh[0][1] = oldField[i - 1][j];
                        deadNbh[0][2] = false;
                        deadNbh[1][0] = oldField[i][j - 1];
                        deadNbh[1][2] = false;
                        deadNbh[2][0] = oldField[i + 1][j - 1];
                        deadNbh[2][1] = oldField[i + 1][j];
                        deadNbh[2][2] = false;
                    } else if (i == oldField.length - 1 && j == oldField[i].length - 1) {
                        deadNbh[0][0] = oldField[i - 1][j - 1];
                        deadNbh[0][1] = oldField[i - 1][j];
                        deadNbh[0][2] = false;
                        deadNbh[1][0] = oldField[i][j - 1];
                        deadNbh[1][2] = false;
                        deadNbh[2][0] = false;
                        deadNbh[2][1] = false;
                        deadNbh[2][2] = false;
                    }
                    int neighbourOfDead = 0;
                    for (int k = 0; k < deadNbh.length; k++) {
                        for (int l = 0; l < deadNbh[k].length; l++) {
                            // az üres mező szomszédainak megszámlálása        
                            if (deadNbh[k][l]) {
                                neighbourOfDead += 1;
                            }
                        }
                    }
                    //ha az élő szomszédok száma 3, a cellában új sejt születik
                    if (neighbourOfDead == 3) {
                        temp[i][j] = true;
                    }
                }
                //ha a cella állapota megváltozott, elmentjük az új mezőben
                if (newField[i][j] != temp[i][j]) {
                    newField[i][j] = temp[i][j];
                }
            }
        }
        //visszaadjuk az új mezőt
        return newField;
    }

    public boolean[][] getField() {
        return field;
    }

    public void setField(boolean[][] field) {
        this.field = field;
    }

    public boolean[][] getPreviousField() {
        return previousField;
    }

    public void setPreviousField(boolean[][] previousField) {
        this.previousField = previousField;
    }

}
