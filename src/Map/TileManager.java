package Map;

import Main.GamePanel;
import Map.Tilesets.Grass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[256];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/Maps/TestMap.map");
    }

    public void getTileImage() {
        Grass grass = new Grass();
        ArrayList<BufferedImage> grassTiles = grass.getTiles();

        for (int i = 0; i < 64; i++) {
            tile[i] = new Tile();
            tile[i].image = grassTiles.get(i);
        }
    }

    public void loadMap(String mapPath) {
        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                String[] tokens = line.split(" ");

               while(col < gp.maxScreenCol) {
                   String numbers[] = line.split(" ");
                   int num = Integer.parseInt(numbers[col]);

                   mapTileNum[col][row] = num;
                     col++;
               }
               if(col == gp.maxScreenCol) {
                   col = 0;
                   row++;
               }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int col = 0; col < gp.maxScreenCol; col++) {
            for (int row = 0; row < gp.maxScreenRow; row++) {
                int tileNum = mapTileNum[col][row];
                    g2.drawImage(tile[tileNum].image, col * gp.tileSize, row * gp.tileSize, null);
            }
        }
    }
}
