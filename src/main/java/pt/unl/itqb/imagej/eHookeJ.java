package pt.unl.itqb.imagej;

import net.imagej.ImageJ;
import org.scijava.command.Command;
import org.scijava.plugin.Plugin;

@Plugin(type = Command.class, menuPath = "Plugins>eHookeJ")
public class eHookeJ implements Command {


    public static void main(String[] args) throws Exception {
        final ImageJ ij = new ImageJ();
        ij.launch(args);

        // Launch our "Hello World" command right away.
        ij.command().run(eHookeJ.class, true);
    }

    @Override
    public void run() {
        Gui.run();
    }
}
