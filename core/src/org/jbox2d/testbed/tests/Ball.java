package org.jbox2d.testbed.tests;

//import org.jbox2d.collision.shapes.CircleShape;
//import org.jbox2d.common.Vec2;
//import org.jbox2d.dynamics.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Georgee Tsintsadze & Yonatan Sackstein
 * @version  1.0
 *
 * Class that contains a body (STATIC or DYNAMIC), circle shape and their fixture the a specified world.
 */
public class Ball {

    public Body body;

    /**
     * Creates a circle body in the given world. The ball can be STATIC or DYNAMIC
     *
     * @param world     The world in which the ball is created
     * @param x         X position
     * @param y         Y position
     * @param radius    Radius of the ball
     * @param dynamic   Optional - Ball is STATIC or DYNAMIC
     */
    Ball(World world, float x, float y, float radius, boolean... dynamic) {
        createBall(world, new Vector2(x, y), radius, dynamic);
    }

    /**
     * Creates a circle body in the given world. The ball can be STATIC or DYNAMIC
     *
     * @param world     The world in which the ball is created
     * @param position  Center Position
     * @param radius    Radius of the ball
     * @param dynamic   Optional - Ball is STATIC or DYNAMIC
     */
    public Ball(World world, Vector2 position, float radius, boolean... dynamic) {
        createBall(world, position, radius, dynamic);
    }

    private void createBall(World world, Vector2 position, float radius, boolean... ballIsDynamic) {

        // If ballIsDynamic is not given, or if given and its true, dynamic will be true
        boolean dynamic =  (ballIsDynamic.length == 0) || (ballIsDynamic[0]);

        // Creating body
        BodyDef ballBodyDef = new BodyDef();
        ballBodyDef.position.set(position);
        if (dynamic) {
            ballBodyDef.type = BodyDef.BodyType.DynamicBody;
        }
        else {
            ballBodyDef.type = BodyDef.BodyType.StaticBody;
            //TODO
            //Rect.DrawStaticX(world, position, 0, radius, radius);
        }
        body = world.createBody(ballBodyDef);

        // Creating shape
        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(radius);

        // Creating fixture
        body.createFixture(ballShape, 1);
    }

}
