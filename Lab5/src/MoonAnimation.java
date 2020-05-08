import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class MoonAnimation implements ActionListener, KeyListener {
    private TransformGroup ball;
    private TransformGroup stairs;
    private Transform3D transform3Dball = new Transform3D();
    private Transform3D transform3Dstairs = new Transform3D();

    private float height = 0.0f;
    private float sign = 1.0f; // going up or down
    private Timer timer;
    private float xloc=0.0f;
    private float yloc=0.5f;
    private float zloc=0.0f;

    public MoonAnimation(TransformGroup boat, TransformGroup stairs) {
        this.ball = boat;
        this.stairs = stairs;

        this.stairs.getTransform(this.transform3Dstairs);
        this.ball.getTransform(this.transform3Dball);

        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        height += .1 * sign;

        if (Math.abs(height * 2) >= 7)
            sign = -1.0f * sign;
        transform3Dball.setScale(new Vector3d(0.13, 0.13, 0.13));
        transform3Dball.setTranslation(new Vector3f(0.5f*height + xloc,yloc,zloc));
        ball.setTransform(transform3Dball);
        transform3Dstairs.setTranslation(new Vector3f(-1.5f,-0.5f,0.5f));
        stairs.setTransform(transform3Dstairs);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()=='d') {xloc = xloc + .05f;}
        if (e.getKeyChar()=='a') {xloc = xloc - .05f;}
        if (e.getKeyChar()=='w') {yloc = yloc + .05f;}
        if (e.getKeyChar()=='s') {yloc = yloc - .05f;}
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent ev) {
    }
}
