package pt.unl.itqb.imagej;

import net.imagej.ImageJ;
import org.scijava.command.Command;
import org.scijava.plugin.Plugin;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        button.setText("Click me to show dialog!");
        parent.add(button);
        parent.pack();
        parent.setVisible(true);
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String name = JOptionPane.showInputDialog(parent,
                        "Hello World", null);
            }
        });
    }
}
