package Entity;

import Enums.Direction;
import Input.KeyHandler;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;
    short playerTileSize = 160;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyHandler) {

        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - playerTileSize / 2;
        screenY = gp.screenHeight / 2 - playerTileSize / 2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 128;
        worldY = gp.tileSize * 128;
        speed = 4;
        scale = 1.5f;
    }

    public void getPlayerImage() {

        try {

            upIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_idle/sprite_0.png"))));
            upIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_idle/sprite_1.png"))));
            upIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_idle/sprite_2.png"))));
            upIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_idle/sprite_3.png"))));
            upIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_idle/sprite_4.png"))));

            upWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_walk/sprite_0.png"))));
            upWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_walk/sprite_1.png"))));
            upWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_walk/sprite_2.png"))));
            upWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_walk/sprite_3.png"))));
            upWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_walk/sprite_4.png"))));
            upWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/up_walk/sprite_5.png"))));

            sideIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_idle/sprite_0.png"))));
            sideIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_idle/sprite_1.png"))));
            sideIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_idle/sprite_2.png"))));
            sideIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_idle/sprite_3.png"))));
            sideIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_idle/sprite_4.png"))));

            sideWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_walk/sprite_0.png"))));
            sideWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_walk/sprite_1.png"))));
            sideWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_walk/sprite_2.png"))));
            sideWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_walk/sprite_3.png"))));
            sideWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_walk/sprite_4.png"))));
            sideWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/side_walk/sprite_5.png"))));

            downIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_idle/sprite_0.png"))));
            downIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_idle/sprite_1.png"))));
            downIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_idle/sprite_2.png"))));
            downIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_idle/sprite_3.png"))));
            downIdle.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_idle/sprite_4.png"))));

            downWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_walk/sprite_0.png"))));
            downWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_walk/sprite_1.png"))));
            downWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_walk/sprite_2.png"))));
            downWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_walk/sprite_3.png"))));
            downWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_walk/sprite_4.png"))));
            downWalk.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Player/down_walk/sprite_5.png"))));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyHandler.upPressed) {
            direction = Direction.UP;
            worldY -= speed;
            isMoving = true;
        } else if(keyHandler.downPressed) {
            direction = Direction.DOWN;
            worldY += speed;
            isMoving = true;
        } else if (keyHandler.leftPressed) {
            direction = Direction.LEFT;
            worldX -= speed;
            isMoving = true;
        } else if(keyHandler.rightPressed) {
            direction = Direction.RIGHT;
            worldX += speed;
            isMoving = true;
        }

        if(direction == Direction.UP && !keyHandler.upPressed) {
            isMoving = false;
        } else if(direction == Direction.DOWN && !keyHandler.downPressed) {
            isMoving = false;
        } else if(direction == Direction.LEFT && !keyHandler.leftPressed) {
            isMoving = false;
        } else if(direction == Direction.RIGHT && !keyHandler.rightPressed) {
            isMoving = false;
        }

        updateSprite();
    }


    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if (isMoving) {
            switch (direction) {
                case UP:
                    if (spriteNum >= upWalk.size()) {
                        spriteNum = 0;
                    }
                    image = upWalk.get(spriteNum);
                    break;
                case DOWN:
                    if (spriteNum >= downWalk.size()) {
                        spriteNum = 0;
                    }
                    image = downWalk.get(spriteNum);
                    break;
                case LEFT:
                    if (spriteNum >= sideWalk.size()) {
                        spriteNum = 0;
                    }
                    image = sideWalk.get(spriteNum);
                    break;
                case RIGHT:
                    if (spriteNum >= sideWalk.size()) {
                        spriteNum = 0;
                    }
                    image = flipImageHorizontally(sideWalk.get(spriteNum));
                    break;
            }
        } else {
            switch (direction) {
                case UP:
                    if (spriteNum >= upIdle.size()) {
                        spriteNum = 0;
                    }
                    image = upIdle.get(spriteNum);
                    break;
                case DOWN:
                    if (spriteNum >= downIdle.size()) {
                        spriteNum = 0;
                    }
                    image = downIdle.get(spriteNum);
                    break;
                case LEFT:
                    if (spriteNum >= sideIdle.size()) {
                        spriteNum = 0;
                    }
                    image = sideIdle.get(spriteNum);
                    break;
                case RIGHT:
                    if (spriteNum >= sideIdle.size()) {
                        spriteNum = 0;
                    }
                    image = flipImageHorizontally(sideIdle.get(spriteNum));
                    break;
            }
        }
        g2.drawImage(image, screenX, screenY, playerTileSize, playerTileSize, null);
    }
}
