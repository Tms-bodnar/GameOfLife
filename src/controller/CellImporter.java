/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Cell;

/**
 *
 * @author Bodnár Tamás <tms.bodnar@gmail.com> | www.kalandlabor.hu
 * A Cellimporter osztály a beolvasott fájlból készít használható 
 * sejteket illetve ezek listáját, valamint az ezekkel benépesített mezőt
 */
public class CellImporter {

    private boolean[][] field = new boolean[90][180];
    private List<Cell> cellList = new ArrayList<>();

    public CellImporter() {
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }
/**
 * List<Cell> cellLIst getter
 * @return List<Cell> cellList
 */
    public List<Cell> getCellList() {
        return cellList;
    }
/**
 * A cellList metódus a paraméterként kapott SrtingBufferből Cell objaktumokat,
 * majd ezekből listát készít. A sejtek elhelyezésénél figyelembe veszi
 * a #P kóddal leírt eltolásokat.
 * 
 * @param pattern
 * @return List<Cell> cellList
 */
    public List<Cell> cellList(StringBuffer pattern) {
    // két változó az eltolásokhoz
        int xRel = 0;
        int yRel = 0;
    //a mező mérete
        int fieldX = 180;
        int fieldY = 90;
        List<Cell> cl = new ArrayList<>();
    //A kapott StrinBuffer feldarabolása
        String[] cellarray = pattern.toString().split("\\$");
        for (int i = 0; i < cellarray.length; i++) {
    //ha #P kóddal kezdődik a String tömb eleme, akkor megváltoztatjuk az eltolást
            if (cellarray[i].startsWith("#P")) {
                String[] cord = cellarray[i].split(" ");
                yRel = Integer.parseInt(cord[1]);
                xRel = Integer.parseInt(cord[2]);
            }
            for (int j = 0; j < cellarray[i].length(); j++) {
    //a sejtet jelképező * helyének meghatározása, új Cell objektum létrehozása az adatokkal
                if (cellarray[i].charAt(j) == '*') {
                    Cell cell = new Cell(((i + (fieldY / 2) + xRel)), ((j + (fieldX / 2) + yRel)), true);
                    cl.add(cell);
                }
            }
        }
    //cellLista mentése az adattagba
        setCellList(cl);
        return cl;
    }
/**
 * A getCellField metódus a cellList lista alapján kiszámolja a mező népességét,
 * a megfelelő cellákba élő sejteket helyezve
 * @return boolean[][] field
 */
    public boolean[][] getCellField() {
        boolean[][] field = new boolean[90][180];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (Cell c : this.cellList) {
    //ha a Cell objektum koordinátái megegyeznek a mező cellájának koordinátáival,
    //a sejtet elhelyezi a mező cellájában
                    if (i == c.getX() && j == c.getY()) {
                        field[i][j] = true;
                    }
                }
            }
        }
    //elmenti a mezőt az adattagba, és visszaadja    
        setField(field);
        return this.field;
    }
/**
 * boolean[][] field getter
 * @return boolean[][] field
 */
    public boolean[][] getField() {
        return field;
    }

    public void setField(boolean[][] field) {
        this.field = field;
    }

}
