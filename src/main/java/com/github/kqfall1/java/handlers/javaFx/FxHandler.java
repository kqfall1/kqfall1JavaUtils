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
 * of this type directly. Remember to instantiate {@code stage} when implementing the
 * {@code start} method.
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

	/**
 	* Is intended to be a read-only record of the stage passed to the override of
	 * Application.start(). The implementation of the override should save the parameter
	 * to this class.
 	*/
	private Stage stage;
	private String title;

	public FxHandler(String title)
	{
		this();
		this.title = title;
	}

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
	}

	public final void setFooter(HBox footer)
	{
		this.footer = footer;
	}

	public final void setHeader(HBox header)
	{
		this.header = header;
	}

	public final void setLeft(GridPane left)
	{
		this.left = left;
	}

	public final void setRight(GridPane right)
	{
		this.right = right;
	}

	public final void setScene(Scene scene)
	{
		this.scene = scene;
	}

	public final void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public final void setTitle(String title)
	{
		this.title = title;
	}

	public abstract void start(Stage stage);
}