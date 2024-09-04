package Map.Tilesets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Tilesmap {
    ArrayList<BufferedImage> Tiles = new ArrayList<>();

    public Tilesmap() {
        loadTiles();
    }

    private void loadTiles() {
        try {
            Path tilesetDir = Paths.get(Objects.requireNonNull(getClass().getResource("/Tilesets")).toURI());
            Files.list(tilesetDir)
                    .filter(path -> path.toString().endsWith(".png"))
                    .forEach(path -> {
                        try {
                            Tiles.add(ImageIO.read(path.toFile()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BufferedImage> getTiles() {
        return Tiles;
    }
}
