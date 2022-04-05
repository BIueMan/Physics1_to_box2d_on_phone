package org.jbox2d.testbed.tests;

//import org.jbox2d.collision.shapes.PolygonShape;
//import org.jbox2d.common.Vec2;
//import org.jbox2d.dynamics.*;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Georgee Tsintsadze & Yonatan Sackstein
 * @version  1.0
 *
 * Class that creates a STATIC or DYNAMIC polygon from given vertices
 */
public class Poly {
    public Body body;

    /**
     * Creates a polygon body with specified vertices
     *
     * @param world     The world in which the ball is created
     * @param dynamic    If true, the body will be DYNAMIC, else it will be STATIC
     * @param vertices  All the points of the line. Can be provided as an Vec2 array or as
     *                  multiple individual Vec2 arguments
     */
    public Poly(World world, boolean dynamic, Vector2... vertices) {

        // Creating body
        BodyDef polyBodyDef = new BodyDef();
        if (dynamic) {
            polyBodyDef.type = BodyDef.BodyType.DynamicBody;
        }
        else {
            polyBodyDef.type = BodyDef.BodyType.StaticBody;
        }

        float originX = 0;
        float originY = 0;
        for (Vector2 vert : vertices) {
            originX += vert.x;
            originY += vert.y;
        }
        originX = originX / vertices.length;
        originY = originY / vertices.length;

        for (int i=0; i< vertices.length; i++) {
            vertices[i] = vertices[i].sub(new Vector2(originX, originY));
        }

        polyBodyDef.position.set(originX, originY);

        body = world.createBody(polyBodyDef);

        // Creating shape
        PolygonShape polyShape = new PolygonShape();
        polyShape.set(vertices);
        // Creating fixture
        body.createFixture(polyShape, 1);
    }
}
