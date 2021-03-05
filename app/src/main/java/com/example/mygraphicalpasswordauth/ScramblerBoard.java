/*
   PLEASE DO NOT MESS WITH THIS FILE (CODE)
*/

package com.example.mygraphicalpasswordauth;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;


public class ScramblerBoard {

    public static int NUM_TILES = 3; // the number of tiles
    private static final int[][] NEIGHBOUR_COORDS = {
            { -1, 0 },
            { 1, 0 },
            { 0, -1 },
            { 0, 1 }
    };
    public ArrayList<ScramblerTile> tiles;
    public Integer steps = 0;
    public ScramblerBoard previousBoard = null;




    ScramblerBoard(Bitmap bitmap, int parentWidth) {
        int changedHeight = (int) (((float) parentWidth / (float) bitmap.getWidth()) * bitmap.getHeight());
        Bitmap square = Bitmap.createScaledBitmap(bitmap, parentWidth, changedHeight, false);
        Integer tileWidth = square.getWidth() / NUM_TILES;
        tiles = new ArrayList<>();
        for (int i = 0; i <= NUM_TILES - 1; i++) {
            for (int j = 0; j <= NUM_TILES - 1; j++) {
                if ((i == (NUM_TILES - 1)) && (j == (NUM_TILES - 1))) {
                    tiles.add(null);
                }
                else {
                    Bitmap tile = Bitmap.createBitmap(square, j * tileWidth, i * tileWidth, tileWidth, tileWidth);
                    ScramblerTile addTile = new ScramblerTile(tile, (NUM_TILES * i) + j);
                    tiles.add(addTile);
                }
            }
        }
    }

    ScramblerBoard(ScramblerBoard otherBoard) {
        tiles = (ArrayList<ScramblerTile>) otherBoard.tiles.clone();
        steps = otherBoard.steps + 1;
        previousBoard = otherBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        return tiles.equals(((ScramblerBoard) o).tiles);
    }

    public void draw(Canvas canvas) {
        if (tiles == null) {
            return;
        }
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {  // this multiplies the tiles, for 3 x 3
            ScramblerTile tile = tiles.get(i);
            if (tile != null) {
                tile.draw(canvas, i % NUM_TILES, i / NUM_TILES);
            }
        }
    }

    public boolean click(float x, float y) {
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {
            ScramblerTile tile = tiles.get(i);
            if (tile != null) {
                if (tile.isClicked(x, y, i % NUM_TILES, i / NUM_TILES)) {
                    return tryMoving(i % NUM_TILES, i / NUM_TILES);
                }
            }
        }
        return false;
    }

    private boolean tryMoving(int tileX, int tileY) { // this enables the movement of the tiles
        for (int[] delta : NEIGHBOUR_COORDS) {
            int nullX = tileX + delta[0];
            int nullY = tileY + delta[1];
            if (nullX >= 0 && nullX < NUM_TILES && nullY >= 0 && nullY < NUM_TILES &&
                    tiles.get(XYtoIndex(nullX, nullY)) == null) {
                swapTiles(XYtoIndex(nullX, nullY), XYtoIndex(tileX, tileY));
                return true;
            }

        }
        return false;
    }

    public boolean resolved() {
        for (int i = 0; i < NUM_TILES * NUM_TILES - 1; i++) {
            ScramblerTile tile = tiles.get(i);
            if (tile == null || tile.getNumber() != i)
                return false;
        }
        return true;
    }

    private int XYtoIndex(int x, int y) {
        return x + y * NUM_TILES;
    }

    protected void swapTiles(int i, int j) { //swapping of tile
        ScramblerTile temp = tiles.get(i);
        tiles.set(i, tiles.get(j));
        tiles.set(j, temp);
        }




    public ArrayList<ScramblerBoard> neighbours() {
        boolean breakOut = false;
        int i,j = 0;
        for (i = 0; i <= NUM_TILES - 1; i++) {
            for (j = 0; j <= NUM_TILES - 1; j++) {
                if (tiles.get((NUM_TILES * i) + j) == null) {
                    breakOut = true;
                    break;
                }
            }
            if (breakOut) {
                break;
            }
        }
        int nullX = j, nullY = i;
        ArrayList<ScramblerBoard> neighbouringBoards = new ArrayList<>();
        for (int k = 0; k <= 3; k++) {
            int x = nullX + NEIGHBOUR_COORDS[k][0];
            int y = nullY + NEIGHBOUR_COORDS[k][1];
            if ((x >= 0) && (x <= NUM_TILES - 1) && (y >= 0) && (y <= NUM_TILES - 1)) {
                ScramblerBoard newBoard = new ScramblerBoard(this);
                newBoard.swapTiles((nullY * NUM_TILES) + nullX, (y * NUM_TILES) + x);
                neighbouringBoards.add(newBoard);
            }
        }
        return neighbouringBoards;
    }

    public int priority() {
        Integer toReturn = steps, position;
        for (int i = 0; i <= NUM_TILES - 1; i++) {
            for (int j = 0; j <= NUM_TILES - 1; j++) {
                if (tiles.get((NUM_TILES * i) + j) == null) {
                    continue;
                }
                position = tiles.get((NUM_TILES * i) + j).getNumber();
                toReturn += Math.abs((position / NUM_TILES) - i) + Math.abs((position % NUM_TILES) - j);
            }
        }
        return toReturn;
    }

}
