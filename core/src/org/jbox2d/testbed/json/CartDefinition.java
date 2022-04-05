package org.jbox2d.testbed.json;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import org.jbox2d.testbed.tests.Cart;
//import org.jbox2d.testbed.tests.Cart;

public class CartDefinition {

    public String A;
    public String B;
    public String C;
    public String D;
    public String wheel1;
    public String wheel2;
    public float radius;

    public Body Draw(World world, float scale)
    {
        // TODO: another proportionate function with string as vector parameter
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

        Vector2 wheelCenter1 = jsonReader.toVec2(wheel1);
        wheelCenter1.x = wheelCenter1.x*scale;
        wheelCenter1.y = wheelCenter1.y*scale;
        Vector2 wheelCenter2 = jsonReader.toVec2(wheel2);
        wheelCenter2.x = wheelCenter2.x*scale;
        wheelCenter2.y = wheelCenter2.y*scale;

        radius = radius * scale;

        // TODO: Figure out how the proportionate function should affect the wheels radius

        Vector2[] wheelCenters = new Vector2[2];
        wheelCenters[0] = wheelCenter1;
        wheelCenters[1] = wheelCenter2;

        Vector2[] vertices = new Vector2[4];
        vertices[0] = vert1;
        vertices[1] = vert2;
        vertices[2] = vert3;
        vertices[3] = vert4;

        Cart cart = new Cart(world, vertices, wheelCenters, radius);
        return cart.body;
    }

}