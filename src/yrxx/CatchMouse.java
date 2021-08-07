package yrxx;

import java.awt.*;

/**
 * 鼠标位置捕捉工具，能捕捉坐标及其RGB
 */
public class CatchMouse {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(-1, -1);
            robot.mouseMove((int) (1487/1.25), (int) (374/1.25));
            int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
            int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
            System.out.println(robot.getPixelColor(x, y).getRed() + " " + robot.getPixelColor(x, y).getGreen() + " " +robot.getPixelColor(x, y).getBlue());
            while (true) {
                x = (int) MouseInfo.getPointerInfo().getLocation().getX();
                y = (int) MouseInfo.getPointerInfo().getLocation().getY();
                Color color = robot.getPixelColor(x, y);
                String s = "(" + x + "," + y + ")" + " R:" + color.getRed() + " G:" + color.getGreen() + " B:" + color.getBlue();
                System.out.print(s);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < s.length(); i++) {
                    System.out.print("\b");
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }


    }
}
