package org.jbox2d.testbed.json;

//import org.jbox2d.common.Vec2;
import com.badlogic.gdx.math.Vector2;
//import org.jbox2d.dynamics.Body;
import com.badlogic.gdx.physics.box2d.Body;
//import org.jbox2d.dynamics.World;
import com.badlogic.gdx.physics.box2d.World;
//import org.jbox2d.testbed.tests.Ball;
//import org.jbox2d.testbed.tests.Rect;
import org.jbox2d.testbed.tests.StaticLine;

import static java.lang.Math.cos;
import static java.lang.StrictMath.acos;
import static java.lang.StrictMath.sin;

public class WallDefinition {

    public String A;
    public String B;
    public float Width;

    public Body Draw(World world, float scale)
    {
        Vector2 a = jsonReader.toVec2(A);
        a.x = a.x * scale;
        a.y = a.y * scale;
        Vector2 b = jsonReader.toVec2(B);
        b.x = b.x * scale;
        b.y = b.y * scale;

        StaticLine line = new StaticLine(world, false, 0, a, b);
        return line.body;

//        float Length = b.sub(a).length();
//        double angle = Math.acos(Vec2.dot(b.sub(a), new Vec2(1,0))/Length);
//
//        double hypotenuse = Math.pow( Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2),0.5);
//        double cx = a.x + Width/2 * Math.sin(angle)
//                + 0.5*Math.cos(angle)*hypotenuse;
//        double cy = a.y - Width/2 * Math.cos(angle)
//                + 0.5*Math.sin(angle)*hypotenuse;
//
//        Vec2 center = jsonReader.proportionate(new Vec2((float)cx, (float)cy), proportionX, proportionY);
//        float width = newWidth(Width, angle, proportionX, proportionY);
//        float length = newLength(Length, angle, proportionX, proportionY);
//
//        new Rect(world, center, new Vec2(width/2, length/2), false, -(float)(Math.PI/2-angle));
    }

}
