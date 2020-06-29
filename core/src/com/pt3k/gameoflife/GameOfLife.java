package com.pt3k.gameoflife;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class GameOfLife extends ApplicationAdapter {

	private int width = 100;
	private int height = 100;
	private int xOffset = 0;

	private OrthographicCamera camera;

	public Grid game;
	private ShapeRenderer shape;

	@Override
	public void create () {

		shape = new ShapeRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 600 + xOffset,600);

		game = new Grid(width,height, xOffset);
	}

	@Override
	public void render () {
		camera.update();
		shape.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.drawLines(shape);
		game.render(shape);
		game.update();
	}

	@Override
	public void resize(int width, int height) {
		game.resize(width,height);
		camera.setToOrtho(true, width + xOffset,height);
	}

	@Override
	public void dispose () {
	}
}
