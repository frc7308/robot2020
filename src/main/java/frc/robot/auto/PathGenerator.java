package frc.robot.auto;

import frc.robot.util.AutoPath;
import frc.robot.util.Constants;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

public class PathGenerator {
    private HashMap<AutoPath, Waypoint[]> pathMap;
    Trajectory.Config config;

    public PathGenerator() {
        pathMap = new HashMap<AutoPath, Waypoint[]>();

        pathMap.put(AutoPath.PYTHON, 
            new Waypoint[] {
                new Waypoint(0, 0, 0),
                new Waypoint(-0.1, 0, 0),
                new Waypoint(0.5, 0, 0)
            }
        );

        this.config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.kLoopInterval / 1000.0, Constants.kMaxDriveVelocity, Constants.kMaxDriveAcceleration, Constants.kMaxDriveJerk);
    }

    public void generate() {
        System.out.println("\n-------------------------------------\n\nStarting Auto Path Generator...");
        Iterator<AutoPath> autoPathIterator = this.pathMap.keySet().iterator();
        while (autoPathIterator.hasNext()) {
            AutoPath pathName = autoPathIterator.next();
            System.out.println("Generating path \"" + pathName + "\"");
            Trajectory trajectory = Pathfinder.generate(this.pathMap.get(pathName), this.config);
            File trajfile = new File("./pathfiles/" + pathName.toString().toLowerCase() + ".traj");
            Pathfinder.writeToFile(trajfile, trajectory);
        }
    }
}