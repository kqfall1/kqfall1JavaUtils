package com.github.kqfall1.java.handlers.javaFx;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Handles user IO operations by extending {@code javafx.application.Application}
 * and providing a basic GUI for the end-user to interact with.
 *
 * <p>
 * Encapsulate {@code FxHandler} into {@code InputValidator} rather than using objects
 * of this type directly. Instantiate {@code stage} when implementing the {@code start}
 * method.
 * </p>
 */
public abstract class FxHandler extends Application
{
	private GridPane center;
	private static final String DEFAULT_TITLE = "FxHandler";
	private HBox footer;
	private HBox header;
	private GridPane left;
	private GridPane right;
	private final BorderPane root;
	private Scene scene;
	private Stage stage;
	private String title;

	public FxHandler()
	{
		center = new GridPane();
		root = new BorderPane();
		footer = new HBox();
		header = new HBox();
		left = new GridPane();
		right = new GridPane();
		scene = new Scene(root);
		title = DEFAULT_TITLE;

		root.setTop(header);
		root.setLeft(left);
		root.setCenter(center);
		root.setRight(right);
		root.setBottom(footer);
	}

	public final GridPane getCenter()
	{
		return center;
	}

	public final HBox getFooter()
	{
		return footer;
	}

	public final HBox getHeader()
	{
		return header;
	}

	public final GridPane getLeft()
	{
		return left;
	}

	public final GridPane getRight()
	{
		return right;
	}

	public final BorderPane getRoot()
	{
		return root;
	}

	public final Scene getScene()
	{
		return scene;
	}

	public final Stage getStage()
	{
		return stage;
	}

	public final String getTitle()
	{
		return title;
	}

	public final void setCenter(GridPane center)
	{
		this.center = center;
		root.setCenter(center);
	}

	public final void setFooter(HBox footer)
	{
		this.footer = footer;
		root.setBottom(footer);
	}

	public final void setHeader(HBox header)
	{
		this.header = header;
		root.setTop(header);
	}

	public final void setLeft(GridPane left)
	{
		this.left = left;
		root.setLeft(left);
	}

	public final void setRight(GridPane right)
	{
		this.right = right;
		root.setRight(right);
	}

	public final void setScene(Scene scene)
	{
		this.scene = scene;
		scene.setRoot(root);
	}

	public final void setStage(Stage stage)
	{
		this.stage = stage;
		stage.setScene(scene);
	}

	public final void setTitle(String title)
	{
		if (stage != null)
		{
			this.title = title;
			stage.setTitle(title);
		}
	}

	public abstract void start(Stage stage);

	@Override
	public String toString()
	{
		return String.format(
			"%s[center=%s,footer=%s,header=%s,left=%s,right=%s,root=%s,scene=%s,stage=%s,title=%s]",
			getClass(),
			getCenter(),
			getFooter(),
			getHeader(),
			getLeft(),
			getRight(),
			getRoot(),
			getScene(),
			getStage(),
			getTitle()
		);
	}
}