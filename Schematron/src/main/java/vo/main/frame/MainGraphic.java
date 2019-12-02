package vo.main.frame;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.List;

import vo.main.LogWriter;
import vo.main.ValidateSchema;

public class MainGraphic extends Frame implements ActionListener {

	private static final int WIDTH = 350;
	private static final int HEIGHT = 120;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label xmlInput; // Declare input Label
	private Label schemaOutput; // Declare output Label
	private TextField xmlfInput; // Declare input TextField
	private TextField schemafOutput; // Declare output TextField
	private Button validateButton;

	public MainGraphic() {
		setLayout(new FlowLayout());

		// Labels.
		xmlInput = new Label("Xml file: ");
		add(xmlInput);

		xmlfInput = new TextField(10);
		add(xmlfInput);

		schemaOutput = new Label("Schema file: ");
		add(schemaOutput);

		schemafOutput = new TextField(10);
		add(schemafOutput);

		validateButton = new Button("Validate");
		validateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (xmlfInput.getText().length() > 0 && schemafOutput.getText().length() > 0) {

					File schematron = new File(schemafOutput.getText());
					File xml = new File(xmlfInput.getText());

					try {
						if (ValidateSchema.validateAgaistSchema(schematron, xml)) {
							System.out.println("I WORK <3");
							buildMessageFrame("The document is valid!");

						} else {
							System.out.println("We have error on the xml");
							buildMessageFrame("The document is not valid please check the log!");
							List<String> messages = ValidateSchema.validateMessage(schematron, xml);
							LogWriter.writeLog(messages);

						}
					} catch (Exception ex) {
						ex.printStackTrace();
						buildMessageFrame("Error: Check your input files!");
					}
				} else {
					System.out.println("Nice try! Fill with text...");
					buildMessageFrame("Nice try! Fill the paths...");
				}
			}
		});
		add(validateButton);

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.print("I am closed... Bye bye!");
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		setSize(new Dimension(WIDTH, HEIGHT));
		setTitle("Schematron");
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainGraphic();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	/**
	 * Create a frame with a log message.
	 * 
	 * @param message A simple String message.
	 * @return Frame with the message.
	 */
	private Frame buildMessageFrame(String message) {
		final Frame messageFrame = new Frame();

		messageFrame.add(new Label(message));
		messageFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("I am hidden");

				messageFrame.setVisible(false);
				messageFrame.setEnabled(false);
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		messageFrame.setTitle("Info");
		messageFrame.setSize(new Dimension(WIDTH, HEIGHT));
		messageFrame.setVisible(true);
		messageFrame.setEnabled(true);

		return messageFrame;
	}

	private Frame buildMessageFrame() {
		return buildMessageFrame("Hey i am just a test...");
	}

}
