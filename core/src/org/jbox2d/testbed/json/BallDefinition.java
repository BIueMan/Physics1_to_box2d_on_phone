package org.jbox2d.testbed.json;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import org.jbox2d.testbed.tests.Ball;
//import org.jbox2d.testbed.tests.Ball;

public class BallDefinition {

    public String Center;
    public float Radius;
    public Boolean IsStatic;

    public Body Draw(World world, float scale)
    {
        Vector2 center = jsonReader.toVec2(Center);
        center.x = center.x*scale;
        center.y = center.y*scale;

        Radius = Radius * scale;

        Ball ball = new Ball(world, center, Radius, !IsStatic);
        return ball.body;
    }

}
