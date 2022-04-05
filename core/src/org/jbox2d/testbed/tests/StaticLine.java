package org.jbox2d.testbed.tests;

//import org.jbox2d.collision.shapes.ChainShape;
//import org.jbox2d.common.Vec2;
//import org.jbox2d.dynamics.Body;
//import org.jbox2d.dynamics.BodyDef;
//import org.jbox2d.dynamics.World;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;

/**
 * @author Georgee Tsintsadze & Yonatan Sackstein
 * @version  1.0
 *
 * Class that contains a body of a static chain line with the provided vertices.
 * The resulting body is only STATIC
 */
public class StaticLine {
    public Body body;

    /**
     * Creates a body and a chain shape (and fixture for them).
     * PAY ATTENTION when providing vertices and if closing loop because chain cannot intersect itself!
     *
     * @param world     The world in which the ball is created
     * @param closeLoop If true, the last vertex will be connected to the first one
     * @param vertices  All the points of the line. Can be provided as an Vec2 array or as
     *                  multiple individual Vec2 arguments
     */
    public StaticLine(World world, boolean closeLoop, float angle, Vector2... vertices) {

        // Creating body
        BodyDef lineBodyDef = new BodyDef();
        body = world.createBody(lineBodyDef);

        // Creating shape
        ChainShape lineShape = new ChainShape();
        if (closeLoop) {
            lineShape.createLoop(vertices);
        }
        else {
            lineShape.createChain(vertices);
        }

        // Creating fixture
        body.createFixture(lineShape, 0);
    }
}
