package edu.tbd4421.ShuffleboardPlugin;

import edu.wpi.first.shuffleboard.api.data.types.NumberType;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import eu.hansolo.medusa.Gauge;
//import eu.hansolo.medusa.TickLabelLocation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;
import java.util.LinkedList;


@Description(dataTypes = { NumberType.class}, name = "Temperature Gauge")
@ParametrizedController(value = "TemperatureGauge.fxml")
public class TemperatureGauge extends SimpleAnnotatedWidget<Number> {

	@FXML
	private AnchorPane _thePane;

	@FXML
	Gauge _theGauge;

	@FXML
	Label title;

	@Override
	public Pane getView() {

		_theGauge.valueProperty().bind(dataProperty());

		return _thePane;
	}

	@Override
	public java.util.List<edu.wpi.first.shuffleboard.api.prefs.Group> getSettings() {

		LinkedList<Group> propertyList = new LinkedList<Group>();
		propertyList.add(Group.of("Display Limits"
		, Setting.of("Min Value", _theGauge.minValueProperty(), Double.class)
		, Setting.of("Max Value", _theGauge.maxValueProperty(), Double.class)
		, Setting.of("Angle Range", _theGauge.angleRangeProperty(), Double.class)
		, Setting.of("Threshold", _theGauge.tresholdProperty(), Double.class)
		, Setting.of("Threshold Color", _theGauge.thresholdColorProperty(), Color.class)
		, Setting.of("Color", _theGauge.barColorProperty(), Color.class)
		, Setting.of("Title", title.accessibleTextProperty(), String.class)
		));
		return propertyList;
	  }

}
