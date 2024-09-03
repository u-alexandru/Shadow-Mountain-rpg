package Map.Tilesets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Grass {
    ArrayList<BufferedImage> grassTiles = new ArrayList<>();

    public Grass() {
        loadTiles();
    }

    private void loadTiles() {
        try {
            for (int i = 0; i < 64; i++) {
                String tileName = String.format("/Tilesets/Grass/grass_%02d.png", i);
                grassTiles.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(tileName))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BufferedImage> getTiles() {
        return grassTiles;
    }
}
