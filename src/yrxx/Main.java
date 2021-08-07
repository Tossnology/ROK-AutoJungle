package yrxx;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 脚本启动入口
 */
public class Main {
    static Robot robot;
    static double ZOOM_RATIO;
    static int MAP_BTN_X;
    static int MAP_BTN_Y;
    static int SCOUT_BTN_X;
    static int SCOUT_BTN_Y;
    static int SEARCH_BTN_X;
    static int SEARCH_BTN_Y;
    static int ENEMY_X;
    static int ENEMY_Y;
    static int ATTACK_BTN_X;
    static int ATTACK_BTN_Y;
    static int CREATE_BTN_X;
    static int CREATE_BTN_Y;
    static int START_BTN_X;
    static int START_BTN_Y;
    static int REDCROSS_X;
    static int REDCROSS_Y;
    static int HEAL_BTN_X;
    static int HEAL_BTN_Y;
    static int HELP_BTN_X;
    static int HELP_BTN_Y;
    static int COLLECT_BTN_X;
    static int COLLECT_BTN_Y;
    static int HEALING_ICON_X;
    static int HEALING_ICON_Y;
    static int HEALING_ICON_R;
    static int HEALING_ICON_G;
    static int HEALING_ICON_B;
    static int BLUE_BTN_X;
    static int BLUE_BTN_Y;
    static int BLUE_BTN_R;
    static int BLUE_BTN_G;
    static int BLUE_BTN_B;
    static int BACK_ICON_X;
    static int BACK_ICON_Y;
    static int BACK_ICON_R;
    static int BACK_ICON_G;
    static int BACK_ICON_B;

    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/yrxx/positions.property"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ZOOM_RATIO = Double.valueOf(prop.getProperty("zoom_ratio"));
        MAP_BTN_X = Integer.valueOf(prop.getProperty("map_btn_X"));
        MAP_BTN_Y = Integer.valueOf(prop.getProperty("map_btn_Y"));
        SCOUT_BTN_X = Integer.valueOf(prop.getProperty("scout_btn_X"));
        SCOUT_BTN_Y = Integer.valueOf(prop.getProperty("scout_btn_Y"));
        SEARCH_BTN_X = Integer.valueOf(prop.getProperty("search_btn_X"));
        SEARCH_BTN_Y = Integer.valueOf(prop.getProperty("search_btn_Y"));
        ENEMY_X = Integer.valueOf(prop.getProperty("enemy_X"));
        ENEMY_Y = Integer.valueOf(prop.getProperty("enemy_Y"));
        ATTACK_BTN_X = Integer.valueOf(prop.getProperty("attack_btn_X"));
        ATTACK_BTN_Y = Integer.valueOf(prop.getProperty("attack_btn_Y"));
        CREATE_BTN_X = Integer.valueOf(prop.getProperty("create_btn_X"));
        CREATE_BTN_Y = Integer.valueOf(prop.getProperty("create_btn_Y"));
        START_BTN_X = Integer.valueOf(prop.getProperty("start_btn_X"));
        START_BTN_Y = Integer.valueOf(prop.getProperty("start_btn_Y"));
        REDCROSS_X = Integer.valueOf(prop.getProperty("redcross_X"));
        REDCROSS_Y = Integer.valueOf(prop.getProperty("redcross_Y"));
        HEAL_BTN_X = Integer.valueOf(prop.getProperty("heal_btn_X"));
        HEAL_BTN_Y = Integer.valueOf(prop.getProperty("heal_btn_Y"));
        HELP_BTN_X = Integer.valueOf(prop.getProperty("help_X"));
        HELP_BTN_Y = Integer.valueOf(prop.getProperty("help_Y"));
        COLLECT_BTN_X = Integer.valueOf(prop.getProperty("collect_X"));
        COLLECT_BTN_Y = Integer.valueOf(prop.getProperty("collect_Y"));
        HEALING_ICON_X = Integer.valueOf(prop.getProperty("healing_icon_X"));
        HEALING_ICON_Y = Integer.valueOf(prop.getProperty("healing_icon_Y"));
        HEALING_ICON_R = Integer.valueOf(prop.getProperty("healing_icon_R"));
        HEALING_ICON_G = Integer.valueOf(prop.getProperty("healing_icon_G"));
        HEALING_ICON_B = Integer.valueOf(prop.getProperty("healing_icon_B"));
        BLUE_BTN_X = Integer.valueOf(prop.getProperty("blue_btn_X"));
        BLUE_BTN_Y = Integer.valueOf(prop.getProperty("blue_btn_Y"));
        BLUE_BTN_R = Integer.valueOf(prop.getProperty("blue_btn_R"));
        BLUE_BTN_G = Integer.valueOf(prop.getProperty("blue_btn_G"));
        BLUE_BTN_B = Integer.valueOf(prop.getProperty("blue_btn_B"));
        BACK_ICON_X = Integer.valueOf(prop.getProperty("back_icon_X"));
        BACK_ICON_Y = Integer.valueOf(prop.getProperty("back_icon_Y"));
        BACK_ICON_R = Integer.valueOf(prop.getProperty("back_icon_R"));
        BACK_ICON_G = Integer.valueOf(prop.getProperty("back_icon_G"));
        BACK_ICON_B = Integer.valueOf(prop.getProperty("back_icon_B"));
    }

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws AWTException, InterruptedException {
	// write your code here
        while (true) {
            clickMap();
            clickScout();
            clickSearch();
            clickEnemy();
            clickAttack();
            clickCreateArmy();
            clickStartAttack();
            clickMap();
            while (true) {
                if (isBacking()) {
                    break;
                }
            }
            clickRedCross();
            clickHealing();
            clickHelp();
            while (true) {
                if (isCure()) {
                    break;
                }
            }
            clickCollectArmy();
            while (true) {
                if (isHome()) {
                    break;
                }
            }

            //if (isTired()) break;
        }
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);

    }

//    private static boolean isTired() {
//        Color decisionRGB = robot.getPixelColor(135, 203);
//        if (Math.abs(decisionRGB.getRed() - 142) < 5 && Math.abs(decisionRGB.getGreen() - 255) < 5 && Math.abs(decisionRGB.getBlue()) < 5) {
//            return true;
//        }
//        return false;
//    }

    private static void clickCollectArmy() {
        move(COLLECT_BTN_X, COLLECT_BTN_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean isCure() {
        Color decisionRGB = robot.getPixelColor(HEALING_ICON_X, HEALING_ICON_Y);
        if (Math.abs(decisionRGB.getRed() - HEALING_ICON_R) < 5 && Math.abs(decisionRGB.getGreen() - HEALING_ICON_G) < 5 && Math.abs(decisionRGB.getBlue() - HEALING_ICON_B) < 5) {
            return false;
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static boolean isHome() {
        //1487,374
        Color decisionRGB = robot.getPixelColor(BLUE_BTN_X, BLUE_BTN_Y);
        if (Math.abs(decisionRGB.getRed() - BLUE_BTN_R) < 5 && Math.abs(decisionRGB.getGreen() - BLUE_BTN_G) < 5 && Math.abs(decisionRGB.getBlue() - BLUE_BTN_B) < 5) {
            return false;
        }
        return true;
    }

    private static boolean isBacking() {
        //1487,374
        Color decisionRGB = robot.getPixelColor(BACK_ICON_X, BACK_ICON_Y);
        if (Math.abs(decisionRGB.getRed() - BACK_ICON_R) < 5 && Math.abs(decisionRGB.getGreen() - BACK_ICON_G) < 5 && Math.abs(decisionRGB.getBlue() - BACK_ICON_B) < 5) {
            return true;
        }
        return false;
    }

    public static void clickMap() {
        move(MAP_BTN_X, MAP_BTN_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void clickScout() {
        move(SCOUT_BTN_X, SCOUT_BTN_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickSearch() {
        move(SEARCH_BTN_X, SEARCH_BTN_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickEnemy() {
        move(ENEMY_X, ENEMY_Y);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickAttack() {
        move(ATTACK_BTN_X, ATTACK_BTN_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickCreateArmy() {
        move(CREATE_BTN_X, CREATE_BTN_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickStartAttack() {
        move(START_BTN_X, START_BTN_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickRedCross() {
        move(REDCROSS_X, REDCROSS_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickHealing() {
        move(HEAL_BTN_X, HEAL_BTN_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickHelp() {
        move(HELP_BTN_X, HELP_BTN_Y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void move(int x, int y) {
        robot.mouseMove(-1, -1);
        robot.mouseMove((int) (x/ZOOM_RATIO), (int) (y/ZOOM_RATIO));
    }
}
