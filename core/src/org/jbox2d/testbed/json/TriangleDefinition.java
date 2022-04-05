package org.jbox2d.testbed.json;


//import org.jbox2d.testbed.tests.Ball;
import org.jbox2d.testbed.tests.Poly;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class TriangleDefinition {

    public String A;
    public String B;
    public String C;
    public boolean IsStatic;

    public Body Draw(World world, float scale)
    {
        Vector2 a =  jsonReader.toVec2(A);
        a.x =a.x*scale;
        a.y =a.y*scale;
        Vector2 b =  jsonReader.toVec2(B);
        b.x =b.x*scale;
        b.y =b.y*scale;
        Vector2 c =  jsonReader.toVec2(C);
        c.x =c.x*scale;
        c.y =c.y*scale;

        // TODO: set dynamic to !IsStatic
        Poly triangle = new Poly(world, false, a, b, c);
        return triangle.body;
    }

}
