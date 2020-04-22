package com.company;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrintingImage extends Application{
    private HeaderBitmapImage image; // приватне поле, яке зберігає об'єкт з інформацією про заголовок зображення
    private int numberOfPixels; // приватне поле для збереження кількості пікселів з чорним кольором
    public PrintingImage()
    {}
    public PrintingImage(HeaderBitmapImage image) // перевизначений стандартний конструктор
    {
        this.image = image;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        ReadingImageFromFile.loadBitmapImage("C:\\Users\\dvovc\\IdeaProjects\\Lab3\\sources\\trajectory.bmp");
        this.image = ReadingImageFromFile.pr.image;
        int width = (int)this.image.getWidth();
        int height = (int)this.image.getHeight();
        int half = (int)image.getHalfOfWidth();
        Group root = new Group();
        Scene scene = new Scene (root, width, height);
        Circle cir;
        int let = 0;
        int let1 = 0;
        int let2 = 0;
        char[][] map = new char[width][height];
// виконуємо зчитування даних про пікселі
        BufferedInputStream reader = new BufferedInputStream (new FileInputStream("pixels.txt"));
        for(int i=0;i<height;i++) // поки не кінець зображення по висоті
        {
            for(int j=0;j<half;j++) // поки не кінець зображення по довжині
            {
                let = reader.read(); // зчитуємо один символ з файлу
                let1=let;
                let2=let;
                let1=let1&(0xf0); // старший байт - перший піксель
                let1=let1>>4; // зсув на 4 розряди
                let2=let2&(0x0f); // молодший байт - другий піксель
                if(j*2<width) // так як 1 символ кодує 2 пікселі нам необхідно пройти до середини ширини зображення
                {
                    cir = new Circle ((j)*2,(height-1-i),1, Color.valueOf((returnPixelColor(let1)))); // за допомогою стандартного
// примітива Коло радіусом в 1 піксель та кольором визначеним за допомогою методу returnPixelColor малюємо піксель
                    //root.getChildren().add(cir); //додаємо об'єкт в сцену
                    if (returnPixelColor(let1) == "BLACK") // якщо колір пікселя чорний, то ставимо в масиві 1
                    {
                        map[j*2][height-1-i] = '1';
                        numberOfPixels++; // збільшуємо кількість чорних пікселів
                    }
                    else
                    {
                        map[j*2][height-1-i] = '0';
                    }
                }
                if(j*2+1<width) // для другого пікселя
                {
                    cir = new Circle ((j)*2+1,(height-1-i),1,Color.valueOf((returnPixelColor(let2))));
                    //root.getChildren().add(cir);
                    if (returnPixelColor(let2) == "BLACK")
                    {
                        map[j*2+1][height-1-i] = '1';
                        numberOfPixels++;
                    }
                    else
                    {
                        map[j*2+1][height-1-i] = '0';
                    }
                }
            }
        }
        primaryStage.setTitle("Lab3");
        primaryStage.setScene(scene); // ініціалізуємо сцену
        primaryStage.show(); // візуалізуємо сцену
        reader.close();

        int[][] black;
        black = new int[numberOfPixels][2];
        int lich = 0;

        BufferedOutputStream writer = new BufferedOutputStream (new FileOutputStream("map.txt")); // записуємо карту для руху по траекторії в файл
        for(int i=0;i<height;i++) // поки не кінець зображення по висоті
        {
            for(int j=0;j<width;j++) // поки не кінець зображення по довжині
            {
                if (map[j][i] == '1')
                {
                    black[lich][0] = j;
                    black[lich][1] = i;
                    lich++;
                }
                writer.write(map[j][i]);
            }
            writer.write(10);
        }
        writer.close();
        System.out.println("number of black color pixels = " + numberOfPixels);

        // далі необхідно зробити рух об'єкту по заданій траеторії
        Path path2 = new Path();
        for (int l=0; l<numberOfPixels-1; l++)
        {
            path2.getElements().addAll(
                    new MoveTo(black[l][0],black[l][1]),
                    new LineTo (black[l+1][0],black[l+1][1])
            );
        }

        //animation
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(5000));
        pathTransition.setPath(path2);

        // main picture

        // dolphin body

        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(135.0f);
        moveTo.setY(80.0f);

        QuadCurveTo quadTo = new QuadCurveTo();
        quadTo.setControlX(200.0f);
        quadTo.setControlY(0.0f);
        quadTo.setX(260.0f);
        quadTo.setY(80.0f);

        LineTo line1 = new LineTo();
        line1.setY(100.0f);
        line1.setX(265.0f);

        QuadCurveTo quadNose = new QuadCurveTo();
        quadNose.setControlX(315.0f);
        quadNose.setControlY(145.0f);
        quadNose.setX(251.0f);
        quadNose.setY(145.0f);

        LineTo line2 = new LineTo();
        line2.setX(245.0f);
        line2.setY(180.0f);

        LineTo line3 = new LineTo();
        line3.setX(225.0f);
        line3.setY(163.0f);

        QuadCurveTo quad3 = new QuadCurveTo();
        quad3.setControlX(210.0f);
        quad3.setControlY(190.0f);
        quad3.setX(225.0f);
        quad3.setY(210.0f);

        LineTo line4 = new LineTo();
        line4.setX(255.0f);
        line4.setY(230.0f);

        QuadCurveTo quad4 = new QuadCurveTo();
        quad4.setControlX(241.0f);
        quad4.setControlY(250.0f);
        quad4.setX(220.0f);
        quad4.setY(240.0f);

        LineTo line5 = new LineTo();
        line5.setX(205.0f);
        line5.setY(266.0f);

        QuadCurveTo quad5 = new QuadCurveTo();
        quad5.setControlX(180.0f);
        quad5.setControlY(250.0f);
        quad5.setX(190.0f);
        quad5.setY(228.0f);

        LineTo line6 = new LineTo();
        line6.setX(190.0f);
        line6.setY(216.0f);

        QuadCurveTo quad6 = new QuadCurveTo();
        quad6.setControlX(145.0f);
        quad6.setControlY(200.0f);
        quad6.setX(129.0f);
        quad6.setY(124.0f);

        LineTo line7 = new LineTo();
        line7.setX(104.0f);
        line7.setY(109.0f);

        QuadCurveTo quad7 = new QuadCurveTo();
        quad7.setControlX(110.0f);
        quad7.setControlY(90.0f);
        quad7.setX(135.0f);
        quad7.setY(80.0f);

        path.getElements().add(moveTo);
        path.getElements().add(quadTo);
        path.getElements().add(line1);
        path.getElements().add(quadNose);
        path.getElements().add(line2);
        path.getElements().add(line3);
        path.getElements().add(quad3);
        path.getElements().add(line4);
        path.getElements().add(quad4);
        path.getElements().add(line5);
        path.getElements().add(quad5);
        path.getElements().add(line6);
        path.getElements().add(quad6);
        path.getElements().add(line7);
        path.getElements().add(quad7);

        path.setFill(Color.rgb(48, 162, 215));
        path.setStrokeWidth(2.0f);

        root.getChildren().add(path);

        Path chest = new Path();
        MoveTo moveTo1 = new MoveTo();
        moveTo1.setX(194.0f);
        moveTo1.setY(130.0f);

        QuadCurveTo quadChest1 = new QuadCurveTo();
        quadChest1.setControlX(210.0f);
        quadChest1.setControlY(178.0f);
        quadChest1.setX(230.0f);
        quadChest1.setY(136.0f);

        QuadCurveTo quadChest2 = new QuadCurveTo();
        quadChest2.setControlX(231.0f);
        quadChest2.setControlY(140.0f);
        quadChest2.setX(225.0f);
        quadChest2.setY(163.0f);

        QuadCurveTo quadChest3 = new QuadCurveTo();
        quadChest3.setControlX(210.0f);
        quadChest3.setControlY(190.0f);
        quadChest3.setX(225.0f);
        quadChest3.setY(210.0f);

        QuadCurveTo quadChest4 = new QuadCurveTo();
        quadChest4.setControlX(195.0f);
        quadChest4.setControlY(190.0f);
        quadChest4.setX(194.0f);
        quadChest4.setY(130.0f);

        chest.getElements().add(moveTo1);
        chest.getElements().add(quadChest1);
        chest.getElements().add(quadChest2);
        chest.getElements().add(quadChest3);
        chest.getElements().add(quadChest4);

        chest.setFill(Color.rgb(232, 147, 150));

        root.getChildren().add(chest);

        Circle circle1 = new Circle();
        circle1.setCenterX(155.0f);
        circle1.setCenterY(100.0f);
        circle1.setRadius(8.0f);
        circle1.setFill(Color.rgb(105,105,178));
        root.getChildren().add(circle1);

        Circle circle2 = new Circle();
        circle2.setCenterX(150.0f);
        circle2.setCenterY(125.0f);
        circle2.setRadius(7.0f);
        circle2.setFill(Color.rgb(105,105,178));
        root.getChildren().add(circle2);

        Circle circle3 = new Circle();
        circle3.setCenterX(150.0f);
        circle3.setCenterY(150.0f);
        circle3.setRadius(6.0f);
        circle3.setFill(Color.rgb(105,105,178));
        root.getChildren().add(circle3);

        Polyline polyhand = new Polyline();
        polyhand.getPoints().addAll(164.0, 160.0,
                183.0, 180.0,
                185.0, 166.0,
                188.0, 160.0,
                189.0, 156.0,
                189.0, 154.0,
                189.0, 153.0,
                188.0, 150.0);
        polyhand.setStrokeWidth(2.0);
        root.getChildren().add(polyhand);

        QuadCurve quadUnder = new QuadCurve();
        quadUnder.setStartX(265.0f);
        quadUnder.setStartY(100.0f);
        quadUnder.setEndX(225.0f);
        quadUnder.setEndY(100.0f);
        quadUnder.setControlX(244.0f);
        quadUnder.setControlY(94.0f);
        quadUnder.setFill(Color.rgb(48, 162, 215));
        quadUnder.setStroke(Color.BLACK);
        quadUnder.setStrokeWidth(1.0);

        root.getChildren().add(quadUnder);

        Circle circleEye1 = new Circle();
        circleEye1.setCenterX(225.0f);
        circleEye1.setCenterY(90.0f);
        circleEye1.setRadius(8.0f);
        circleEye1.setFill(Color.WHITE);
        circleEye1.setStroke(Color.BLACK);
        circleEye1.setStrokeWidth(1.5);
        root.getChildren().add(circleEye1);

        Circle circleInner1 = new Circle();
        circleInner1.setCenterX(226.0f);
        circleInner1.setCenterY(93.0f);
        circleInner1.setRadius(4.0f);
        circleInner1.setFill(Color.BLACK);
        circleInner1.setStroke(Color.BLACK);
        circleInner1.setStrokeWidth(1.0);
        root.getChildren().add(circleInner1);

        Circle circleEye2 = new Circle();
        circleEye2.setCenterX(245.0f);
        circleEye2.setCenterY(87.0f);
        circleEye2.setRadius(8.0f);
        circleEye2.setFill(Color.WHITE);
        circleEye2.setStroke(Color.BLACK);
        circleEye2.setStrokeWidth(1.5);
        root.getChildren().add(circleEye2);

        Circle circleInner2 = new Circle();
        circleInner2.setCenterX(246.0f);
        circleInner2.setCenterY(90.0f);
        circleInner2.setRadius(4.0f);
        circleInner2.setFill(Color.BLACK);
        circleInner2.setStroke(Color.BLACK);
        circleInner2.setStrokeWidth(1.0);
        root.getChildren().add(circleInner2);

        QuadCurve quadMouth = new QuadCurve();
        quadMouth.setStartX(194.0f);
        quadMouth.setStartY(110.0f);
        quadMouth.setEndX(224.0f);
        quadMouth.setEndY(135.0f);
        quadMouth.setControlX(210.0f);
        quadMouth.setControlY(168.0f);
        quadMouth.setFill(Color.rgb(201, 36, 35));
        quadMouth.setStroke(Color.BLACK);
        quadMouth.setStrokeWidth(1.5);
        root.getChildren().add(quadMouth);

        QuadCurve quadBelow = new QuadCurve();
        quadBelow.setStartX(251.0f);
        quadBelow.setStartY(145.0f);
        quadBelow.setEndX(194.0f);
        quadBelow.setEndY(110.0f);
        quadBelow.setControlX(210.0f);
        quadBelow.setControlY(134.0f);
        quadBelow.setFill(Color.rgb(48, 162, 215));
        quadBelow.setStroke(Color.BLACK);
        quadBelow.setStrokeWidth(1.0);
        root.getChildren().add(quadBelow);

        QuadCurve quadRight = new QuadCurve();
        quadRight.setStartX(225.0f);
        quadRight.setStartY(163.0f);
        quadRight.setEndX(230.0f);
        quadRight.setEndY(138.0f);
        quadRight.setControlX(230.0f);
        quadRight.setControlY(140.0f);
        quadRight.setFill(Color.rgb(48, 162, 215));
        quadRight.setStroke(Color.BLACK);
        quadRight.setStrokeWidth(1.0);
        root.getChildren().add(quadRight);

        QuadCurve leftEyebrow = new QuadCurve();
        leftEyebrow.setStartX(218.0f);
        leftEyebrow.setStartY(80.0f);
        leftEyebrow.setEndX(230.0f);
        leftEyebrow.setEndY(80.0f);
        leftEyebrow.setControlX(224.0f);
        leftEyebrow.setControlY(75.0f);
        leftEyebrow.setFill(Color.rgb(48, 162, 215));
        leftEyebrow.setStroke(Color.BLACK);
        leftEyebrow.setStrokeWidth(1.5);
        root.getChildren().add(leftEyebrow);

        QuadCurve rightEyebrow = new QuadCurve();
        rightEyebrow.setStartX(238.0f);
        rightEyebrow.setStartY(78.0f);
        rightEyebrow.setEndX(249.0f);
        rightEyebrow.setEndY(76.0f);
        rightEyebrow.setControlX(242.0f);
        rightEyebrow.setControlY(73.0f);
        rightEyebrow.setFill(Color.rgb(48, 162, 215));
        rightEyebrow.setStroke(Color.BLACK);
        rightEyebrow.setStrokeWidth(1.5);
        root.getChildren().add(rightEyebrow);

        pathTransition.setNode(root);

        // Animation
        int cycleCount = 2; //
        int time = 4000;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setCycleCount(cycleCount);
        scaleTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(time), root);
        scaleTransition2.setToX(0.5);
        scaleTransition2.setToY(0.5);
        scaleTransition2.setCycleCount(cycleCount);
        scaleTransition2.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                pathTransition,
                scaleTransition,
                rotateTransition,
                scaleTransition2
        );

        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();

    }
    private String returnPixelColor (int color) // метод для співставлення кольорів 16-бітного зображення
    {
        String col = "BLACK";
        switch(color)
        {
            case 0: return "BLACK";
            case 1: return "LIGHTCORAL";
            case 2: return "GREEN";
            case 3: return "BROWN";
            case 4: return "BLUE";
            case 5: return "MAGENTA";
            case 6: return "CYAN";
            case 7: return "LIGHTGRAY";
            case 8: return "DARKGRAY";
            case 9: return "RED";
            case 10:return "LIGHTGREEN";
            case 11:return "YELLOW";
            case 12:return "LIGHTBLUE";
            case 13:return "LIGHTPINK";
            case 14:return "LIGHTCYAN";
            case 15:return "WHITE";
        }
        return col;
    }
    public static void main (String args[])
    {
        launch(args);
    }
}
