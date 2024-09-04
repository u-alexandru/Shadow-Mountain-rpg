package Map;

import Main.GamePanel;
import Map.Tilesets.Tilesmap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[256];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/Maps/TestMap.map");
    }

    public void getTileImage() {
        Tilesmap tilesmap = new Tilesmap();
        ArrayList<BufferedImage> allTilesmap = tilesmap.getTiles();

        for (int i = 0; i < 64; i++) {
            tile[i] = new Tile();
            tile[i].image = allTilesmap.get(i);
        }
    }

    public void loadMap(String mapPath) {
        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int worldCol = 0;
            int worldRow = 0;

            while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
                String line = br.readLine();
                String[] tokens = line.split(" ");

               while(worldCol < gp.maxWorldCol) {
                   String numbers[] = line.split(" ");
                   int num = Integer.parseInt(numbers[worldCol]);

                   mapTileNum[worldCol][worldRow] = num;
                     worldCol++;
               }
               if(worldCol == gp.maxWorldCol) {
                   worldCol = 0;
                   worldRow++;
               }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.screenWidth / 2;
                int screenY = worldY - gp.player.worldY + gp.screenHeight / 2;
                int tileNum = mapTileNum[col][row];
                if(worldX > gp.player.worldX - gp.screenWidth / 2 - gp.tileSize &&
                        worldX < gp.player.worldX + gp.screenWidth / 2 &&
                        worldY > gp.player.worldY - gp.screenHeight / 2 - gp.tileSize &&
                        worldY < gp.player.worldY + gp.screenHeight / 2) {
                        g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            }
        }
    }
}
