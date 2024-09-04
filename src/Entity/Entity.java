package Entity;

import Enums.Direction;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {

    public int worldX, worldY;
    public int speed;
    public float scale = 1;

    public ArrayList<BufferedImage> upIdle;
    public ArrayList<BufferedImage> upWalk;

    public ArrayList<BufferedImage> sideIdle;
    public ArrayList<BufferedImage> sideWalk;

    public ArrayList<BufferedImage> downIdle;
    public ArrayList<BufferedImage> downWalk;

    public Direction direction = Direction.DOWN;

    public boolean isMoving = false;

    public Entity() {
        upIdle = new ArrayList<>();
        upWalk = new ArrayList<>();
        sideIdle = new ArrayList<>();
        sideWalk = new ArrayList<>();
        downIdle = new ArrayList<>();
        downWalk = new ArrayList<>();
    }

    public BufferedImage flipImageHorizontally(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flippedImage = new BufferedImage(width, height, image.getType());
        Graphics2D g2 = flippedImage.createGraphics();
        g2.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        g2.dispose();
        return flippedImage;
    }

    public BufferedImage scaleImage(BufferedImage image, float scale) {
        int width = (int) (image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);
        BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        scaledImage = scaleOp.filter(image, scaledImage);
        return scaledImage;
    }

    public int spriteCounter = 0;
    public int spriteNum = 0;

    public void updateSprite() {
        spriteCounter++;
        if(spriteCounter >= 10) {
            spriteCounter = 0;
            spriteNum++;
        }
    }
}
