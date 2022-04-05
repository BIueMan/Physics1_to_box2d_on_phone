package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;


import sun.security.jca.GetInstance;
import org.jbox2d.testbed.json.Composition;
import org.jbox2d.testbed.json.jsonReader;




public class MyGdxGame extends ApplicationAdapter {

	private boolean DEBUG = false;

	private OrthographicCamera camera;

	private Box2DDebugRenderer b2dr;
	private World world;
	private Body floor, wall_left, wall_right;
	private Body boxs[], box_play;

	public static float PPM = 32;

	private static float BOARD_WIDTH = 50;
	private static float BOARD_HEIGHT;
	private static float SCALE = 1;

	// drag using mouse
	//String json_text = getIntent().getStringExtra("json_input");



	@Override
	public void create () {
		String jsonPath = "/storage/emulated/0/Download/outputExp.json";
		//String jsonPath = "/storage/emulated/0/Download/output_example.json";
		Composition comp = jsonReader.fromJSON(jsonPath);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		BOARD_HEIGHT = BOARD_WIDTH * h / w;
		float scaleFactor = SCALE * BOARD_WIDTH / w;


		camera = new OrthographicCamera();
		camera.setToOrtho(false, w/2, h/2);

		world = new World(new Vector2(0, -9.8f), false);
		b2dr = new Box2DDebugRenderer();

		//todo: chack nall to comp
		jsonReader.CompositionDecoder(comp, world, scaleFactor);
	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());


		//Reader
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		b2dr.render(world, camera.combined.scl(PPM));
	}

	@Override
	public void resize(int width, int height){
		camera.setToOrtho(false, width/2, height/2);
	}

	@Override
	public void dispose () {
		world.dispose();
		b2dr.dispose();
	}



	public void update(float delta){
		world.step(1 / 60f, 6, 2);

		cameraUpdate(delta);
	}

	private void cameraUpdate(float delta) {
		Vector3 position = camera.position;
		//position.x = floor.getPosition().x * PPM;
		//position.y = floor.getPosition().y * PPM;

		position.x = 0;
		position.y = 0;
		camera.position.set(position);

		camera.update();
	}

	public Body createBox(int x, int y, int width, int height, boolean isStatic){
		return createBox( x,  y,  width,  height,  isStatic, true);
	}

	public Body createBox(int x, int y, int width, int height, boolean isStatic, boolean fixedRotation){
		Body pBody;
		BodyDef def = new BodyDef();
		if (isStatic)
			def.type = BodyDef.BodyType.StaticBody;
		else
			def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x/ PPM, y/ PPM);
		def.fixedRotation = fixedRotation;
		pBody = world.createBody(def);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2 / PPM,height/2 / PPM);
		pBody.createFixture(shape, 1.0f);
		shape.dispose();

		return pBody;
	}

}

/*
public class MyGdxGame extends ApplicationAdapter {

	private boolean DEBUG = false;

	private OrthographicCamera camera;

	private Box2DDebugRenderer b2dr;
	private World world;
	private Body floor, wall_left, wall_right;
	private Body boxs[], box_play;

	public static float PPM = 32;

	// drag using mouse
	//String json_text = getIntent().getStringExtra("json_input");



	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w/2, h/2);

		world = new World(new Vector2(0, -9.8f), false);
		b2dr = new Box2DDebugRenderer();

		floor = createBox(0,-128,300,32,true);
		wall_left = createBox(-128-32,-64,32,128,true);
		wall_right = createBox(128+32,-64,32,128,true);

		box_play = createBox(20,400,64,64,false, false);

		boxs = new Body[5];
		int box_size = 32;
		int x=8,y = 10;
		for(int i=0; i<boxs.length;i++){
			boxs[i] = createBox(x,y+box_size*i,box_size,box_size,false, false);
		}
	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());


		//Reader
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		b2dr.render(world, camera.combined.scl(PPM));
	}

	@Override
	public void resize(int width, int height){
		camera.setToOrtho(false, width/2, height/2);
	}

	@Override
	public void dispose () {
		world.dispose();
		b2dr.dispose();
	}



	public void update(float delta){
		world.step(1 / 60f, 6, 2);

		cameraUpdate(delta);
	}

	private void cameraUpdate(float delta) {
		Vector3 position = camera.position;
		//position.x = floor.getPosition().x * PPM;
		//position.y = floor.getPosition().y * PPM;

		position.x = 0;
		position.y = 0;
		camera.position.set(position);

		camera.update();
	}

	public Body createBox(int x, int y, int width, int height, boolean isStatic){
		return createBox( x,  y,  width,  height,  isStatic, true);
	}

	public Body createBox(int x, int y, int width, int height, boolean isStatic, boolean fixedRotation){
		Body pBody;
		BodyDef def = new BodyDef();
		if (isStatic)
			def.type = BodyDef.BodyType.StaticBody;
		else
			def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x/ PPM, y/ PPM);
		def.fixedRotation = fixedRotation;
		pBody = world.createBody(def);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2 / PPM,height/2 / PPM);
		pBody.createFixture(shape, 1.0f);
		shape.dispose();

		return pBody;
	}

}*/
