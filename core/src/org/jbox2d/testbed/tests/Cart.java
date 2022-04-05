package org.jbox2d.testbed.tests;

//import org.jbox2d.collision.shapes.CircleShape;
//import org.jbox2d.collision.shapes.PolygonShape;
//import org.jbox2d.common.Vec2;
//import org.jbox2d.dynamics.*;
//import org.jbox2d.dynamics.joints.RevoluteJointDef;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 * @author Georgee Tsintsadze & Yonatan Sackstein
 * @version  1.0
 *
 * Class that contains a rectangular body, and two circle bodies connected to it using revolve (revolute) joint
 */
public class Cart {

    private static short cartGroupIndex = -1; // bodies in the same group never collide

    public Body body;

    /**
     * Creates a cart body (main rectangle and two circles for wheels)
     * The relations that define the wheels position and scale are defined using the static Cart class variables:
     * wheelRadiusScale and wheelsRelativePos.
     *
     * @param world         The world in which the cart is created
     * @param rectVert      Vec2[] of each of the cart body vertices
     * @param wheelCenters  Vec2[] of each of the cart wheels centers
     * @param wheelRadius   The radius of both wheels (only one because they are same)
     */
    public Cart(World world, Vector2[] rectVert, Vector2[] wheelCenters, float wheelRadius) {

        // Increment group index to differ between each cart created.
        // (all bodies created with same group index will not collide,
        // here those are the rectangle and two wheels)
        cartGroupIndex += 1;

        float cartCenterX = 0;
        float cartCenterY = 0;
        for (Vector2 vert : rectVert) {
            cartCenterX += vert.x;
            cartCenterY += vert.y;
        }
        cartCenterX = cartCenterX / rectVert.length;
        cartCenterY = cartCenterY / rectVert.length;

        for (int i=0; i< rectVert.length; i++) {
            rectVert[i] = rectVert[i].sub(new Vector2(cartCenterX, cartCenterY));
        }

        // ------------------------------------------------------------------------
        //      Creating cart body (main body and two wheelHolders, no wheels)
        // ------------------------------------------------------------------------

        // Creating main cart body body
        BodyDef cartBodyDef = new BodyDef();
        cartBodyDef.position.set(cartCenterX, cartCenterY);
        cartBodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(cartBodyDef);

        // Creating main cart body shape
        PolygonShape cartBodyBox = new PolygonShape();
        cartBodyBox.set(rectVert);

        // Creating main cart body fixture
        FixtureDef cartBodyFixtureDef = new FixtureDef();
        cartBodyFixtureDef.shape = cartBodyBox;
        cartBodyFixtureDef.density = 1;
        cartBodyFixtureDef.filter.groupIndex = cartGroupIndex;
        body.createFixture(cartBodyFixtureDef);

        // ------------------------------------------------------------------------
        //                            Creating wheels
        // ------------------------------------------------------------------------

        // Creating shape (to be used in both wheels)
        CircleShape wheelShape = new CircleShape();
        wheelShape.setRadius(wheelRadius);

        // ---------- Left wheel ----------

        // Creating body
        BodyDef leftWheelBodyDef = new BodyDef();
        leftWheelBodyDef.position.set(wheelCenters[0]);
        leftWheelBodyDef.type = BodyDef.BodyType.DynamicBody;
        Body leftWheel = world.createBody(leftWheelBodyDef);

        // Creating fixture
        FixtureDef leftWheelFixtureDef = new FixtureDef();
        leftWheelFixtureDef.shape = wheelShape;
        leftWheelFixtureDef.density = 1.0f;
        leftWheelFixtureDef.filter.groupIndex = cartGroupIndex;
        leftWheel.createFixture(leftWheelFixtureDef);

        // Connecting left wheel to cart
        RevoluteJointDef leftWheelJointDef = new RevoluteJointDef();
        leftWheelJointDef.initialize(body, leftWheel, wheelCenters[0]);
        world.createJoint(leftWheelJointDef);

        // ---------- Right wheel ----------

        // Creating body
        BodyDef rightWheelBodyDef = new BodyDef();
        rightWheelBodyDef.position.set(wheelCenters[1]);
        rightWheelBodyDef.type = BodyDef.BodyType.DynamicBody;
        Body rightWheel = world.createBody(rightWheelBodyDef);

        // Creating fixture
        FixtureDef rightWheelFixtureDef = new FixtureDef();
        rightWheelFixtureDef.shape = wheelShape;
        rightWheelFixtureDef.density = 1.0f;
        rightWheelFixtureDef.filter.groupIndex = cartGroupIndex;
        rightWheel.createFixture(rightWheelFixtureDef);

        // Connecting right wheel to cart
        RevoluteJointDef rightWheelJointDef = new RevoluteJointDef();
        rightWheelJointDef.initialize(body, rightWheel, wheelCenters[1]);
        world.createJoint(rightWheelJointDef);
    }
}
