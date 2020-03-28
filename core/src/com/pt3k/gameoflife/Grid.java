package com.pt3k.gameoflife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Grid {

    public int[][] map;
    public int width;
    public int height;
    public int screenWidth;
    public int screenHeight;
    public float cellHeight;
    public float cellWidth;
    public int xOffset;

    public Grid(int width, int height, int xOffset) {

        map = newMap(width, height);
        this.width = width;
        this.height = height;
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        cellWidth = screenWidth/(float)width;
        cellHeight = screenHeight/(float)height;
        this.xOffset = xOffset;
    }

    public void update() {
        int[][] newMap = newMap(width, height);
        int neighbours;

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {

                neighbours = countNeighbours(i,j);
                if(map[i][j] == 0 & neighbours == 3) {
                    newMap[i][j] = 1;
                } else if(map[i][j] == 1 && (neighbours < 2 || neighbours > 3)) {
                    newMap[i][j] = 0;
                } else {
                    newMap[i][j] = map[i][j];
                }
            }
        }

        map = newMap;
    }

    public void resize(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        cellWidth = screenWidth/(float)width;
        cellHeight = screenHeight/(float)height;
    }

    public void render(ShapeRenderer shape) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(0,0,0,0);
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(map[i][j] == 1) {
                    shape.rect(i*cellWidth + xOffset,j*cellHeight, cellWidth, cellHeight);
                }
            }
        }
        shape.end();
    }

    public void drawLines(ShapeRenderer shape) {
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(0,0,0,0);
        
        for(int i = 0; i <= width; i++) {
            shape.line(i*cellWidth+xOffset,0,i*cellWidth + xOffset,cellHeight*height);
        }

        for(int i = 0; i <= height; i++) {
            shape.line(xOffset,i*cellHeight,screenWidth + xOffset,i*cellHeight);
        }

        shape.end();
    }

    public int countNeighbours(int row, int column) {
        int sum = 0;

        for(int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                if (map[(row + i + width)%width][(column + j + height)%height] == 1)
                    sum++;
            }
        }
        sum -= map[row][column];
        return sum;
    }

    public int[][] newMap (int width, int height) {
        int[][] map = new int[width][height];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                int element = MathUtils.floor(MathUtils.random(1));
                map[i][j] = element;
            }
        }

        return map;
    }
}
