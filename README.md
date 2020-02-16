# Custom Shuffleboard 2020 Widgets

This repository contains custom FRC 2020 Shuffleboard widgets. Currently only a temperature gauge was been developed.

# HOW-TO: Use these Widgets
When designing a widget, make sure you set dependencies in `gradle.build` to to better fit the needs of your widget. Currently, the Han Solo gauge library is being used to display info using a gauge.

# HOW-TO: Build, Debug, and Deploy Your Widget(s)

## Building

You still use the FRC 2020 VSCode IDE and build the code using the *WPILib Command Palette*.  Steps:

1. Click the *WPILib Command Palette* button in the top right corner or press CTRL-SHIFT-P.
2. Type *WPILib: Build Robot Code*

Why would we be building Robot code?  We aren't - this command simply invokes the `build` task set up by Gradle.


You may alternately use the *WPILib: Run a command in Gradle* command:

1. Click the *WPILib Command Palette* button in the top right corner or press CTRL-SHIFT-P.
2. Type `WPILib: Run a command in Gradle`
3. Type `build` to execute the build task

Building only builds the project.  It does not deploy the resulting jar where it can be picked up by Shuffleboard automatically.

## Deploying

Deploying will copy the jar to the `%USERPROFILE%/Shuffleboard/plugins` (Windows) or `~/Shuffleboard/plugins` (Linux/Mac) folder on the local machine.  Shuffleboard will automatically make widgets in your jar available for use when it starts. Deploying is similar to building:

1. Click the *WPILib Command Palette* button in the top right corner or press CTRL-SHIFT-P.
2. Type `WPILib: Run a command in Gradle`
3. Type `deployWidget` to execute the deployment

Running the `deployWidget` task will automatically invoke the `build` task.

## Debugging

Once the `deployWidget` Gradle task copies your jar for automatic loading by Shuffleboard, you can perform some debugging.

### Debugging Messages
Using Java API methods to output text to [STDOUT](https://en.wikipedia.org/wiki/Standard_streams "Learn about standard streams") (i.e. `System.out.println()` or similar) will be captured in output log files.  Every time Shuffleboard runs, 
a log file may be found at `%USERPROFILE%/Shuffleboard` (Windows) or `~/Shuffleboard` (Linux/Mac).  

# Errors and Issues
Some of the widget properties cause the Shuffleboard to have an error when saving the layout. It appears to be related to the way the properties are persisted when saving the Shuffleboard layout. Providing the class explicitly to the API when setting up exported properties makes things work better.

Example of BAD code:
```
propertyList.add(Group.of("Tick Lable Display"
		, Setting.of("Tick Label Orientation", _theGauge.tickLabelOrientationProperty())
		, Setting.of("Tick Label Color", _theGauge.tickLabelColorProperty(), Color.class)
		, Setting.of("Tick Lavel Location", _theGauge.tickLabelLocationProperty())
));

```

Example of GOOD code:
```
propertyList.add(Group.of("Tick Lable Display"
		, Setting.of("Tick Label Orientation", _theGauge.tickLabelOrientationProperty(), TickLabelOrientation.class)
		, Setting.of("Tick Label Color", _theGauge.tickLabelColorProperty(), Color.class)
		, Setting.of("Tick Lavel Location", _theGauge.tickLabelLocationProperty(), TickLabelLocation.class)
));

```