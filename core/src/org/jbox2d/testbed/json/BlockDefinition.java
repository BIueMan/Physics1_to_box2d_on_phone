package org.jbox2d.testbed.json;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import org.jbox2d.testbed.tests.Poly;
//import org.jbox2d.testbed.tests.Poly;
//import org.jbox2d.testbed.tests.Rect;

public class BlockDefinition {

    public String A;
    public String B;
    public String C;
    public String D;
    public boolean IsStatic;

    public Body Draw(World world, float scale)
    {
        Vector2 vert1 = jsonReader.toVec2(A);
        vert1.x = vert1.x*scale;
        vert1.y = vert1.y*scale;
        Vector2 vert2 = jsonReader.toVec2(B);
        vert2.x = vert2.x*scale;
        vert2.y = vert2.y*scale;
        Vector2 vert3 = jsonReader.toVec2(C);
        vert3.x = vert3.x*scale;
        vert3.y = vert3.y*scale;
        Vector2 vert4 = jsonReader.toVec2(D);
        vert4.x = vert4.x*scale;
        vert4.y = vert4.y*scale;

        Poly rect = new Poly(world, !IsStatic, vert1, vert2, vert3, vert4);
        return rect.body;
    }
}
