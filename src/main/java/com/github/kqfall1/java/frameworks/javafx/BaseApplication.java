package com.github.kqfall1.java.frameworks.javafx;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Extends {@code Application} and provides a GUI skeleton to be built upon.
 *
 * <p>Instantiate {@code stage} when implementing the {@code start} method.</p>
 *
 * @author Quinn Keenan
 * @since 01/12/2025
 */
public abstract class BaseApplication extends Application
{
	private GridPane center;
	private static final String DEFAULT_TITLE = "BaseApplication";
	private HBox footer;
	private HBox header;
	private GridPane left;
	private GridPane right;
	private final BorderPane root;
	private Scene scene;
	private Stage stage;
	private String title;

	public BaseApplication()
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

	/**
	 * The main entry point for all JavaFX applications.
	 *
	 * <p>The {@code start} method is called after the {@code Application.init} method has returned, and after the
	 * system is ready for the application to begin running.</p>
	 * @param primaryStage the primary {@code Stage} for this application, onto which the application {@code Scene} can be set.
	 * The primary {@code Stage} will be embedded in the browser if the application was launched as an applet. Applications may
	 * create other {@code Stage} objects, if needed, but they will not be a primary {@code Stage} and will not be embedded in the browser.
	 */
	public abstract void start(Stage primaryStage);

	@Override
	public String toString()
	{
		return String.format(
			"%s[center=%s,footer=%s,header=%s,left=%s,right=%s,root=%s,scene=%s,stage=%s,title=%s]",
			getClass().getName(),
			getCenter() != null ? getCenter() : "null",
			getFooter() != null ? getFooter() : "null",
			getHeader() != null ? getHeader() : "null",
			getLeft() != null ? getLeft() : "null",
			getRight() != null ? getRight() : "null",
			getRoot() != null ? getRoot() : "null",
			getScene() != null ? getScene() : "null",
			getStage() != null ? getStage() : "null",
			getTitle() != null ? getTitle() : "null"
		);
	}
}