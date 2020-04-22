package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Star extends JPanel implements ActionListener {
    // Масштабування відбувається відносно центру координат,
// тому малювати фігуру бажано також симетрично центру
    double points[][] = {
            { 20, 10 }, { 20, 130 }, { 280, 130 }, { 280, 10 }, { 20, 10 }
    };
    Timer timer;

    // Для анімації масштабування
    private double scale = 1;
    private double delta = 0.05;

    // Для анімації руху
    private double dx = -1;
    private double tx = 0;
    private double dy = 0;
    private double ty = 0;
    private double animation = 0;
    private static int maxWidth;
    private static int maxHeight;

    public Star() {
        timer = new Timer(20, this);
        timer.start();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setBackground(Color.GREEN);
        g2d.clearRect(0, 0, maxWidth+1, maxHeight+1);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(Color.BLACK);
        BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        g2d.drawRect(10, 10, 460, 440);
        g2d.translate(90, 90);
        g2d.scale(scale, 0.99);

        g2d.translate(tx, ty);
        GradientPaint gp = new GradientPaint(5, 25,
                Color.RED, 20, 2, Color.BLUE, true);
        g2d.setPaint(gp);

        GeneralPath star = new GeneralPath();
        star.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            star.lineTo(points[k][0], points[k][1]);
        star.closePath();
        g2d.fill(star);
        g2d.setPaint(gp);

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(148, 10, 4, 40);
        g2d.fillRect(20, 48, 260, 4);
        g2d.fillRect(20, 88, 260, 4);
        g2d.fillRect(148, 90, 4, 40);
        g2d.fillRect(78, 50, 4, 40);
        g2d.fillRect(218, 50, 4, 40);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Приклад анімації");
        frame.add(new Star());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        maxWidth = 480;
        maxHeight = 480;
    }

    public void actionPerformed(ActionEvent e) {

        if ( animation == 0){
            if ( scale < 0.01 ) {
                delta = -delta;

            } else if (scale > 0.99) {
                delta = -delta;

            }
            animation = 1;
            scale += delta;
            repaint();
        } else {
            if (tx < -50) {
                System.out.println("1");
                tx = -50;
                ty = 0;
                dx = 0;
                dy = 1;
                animation = 0;
            } else if (tx > 50) {
                System.out.println("2");
                ty = 50;
                tx = 50;
                dx = 0;
                dy = -1;
                animation = 0;
            }

            if (ty < 0) {
                System.out.println("3");
                ty = 0;
                tx = 50;
                dy = 0;
                dx = -1;
                animation = 0;
            } else if (ty > 50) {
                System.out.println("4");
                ty = 50;
                tx = -50;
                dy = 0;
                dx = 1;
                animation = 0;
            }
            tx += dx;
            ty += dy;

            repaint();
        }
    }
}
