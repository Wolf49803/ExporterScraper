import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Listener implements ActionListener {
	JFrame frame = new JFrame("ExporterScraper");
	public void createFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(screenSize.width, screenSize.height);
	    JPanel panel = new JPanel(new GridLayout(5, 1));
	    frame.setContentPane(panel);
	    
	    JButton button1 = new JButton("Export a vocabulary list as a PDF file");
	    panel.add(button1);
	    //JButton button2 = new JButton("Get the expected Mandarin reflexes of a traditional Chinese character");
	    //panel.add(button2);
	    //JButton button3 = new JButton("Get the expected Mandarin reflexes and pronounciations of a traditional Chinese character");
	    //panel.add(button3);
	    JButton button4 = new JButton("Get the expected Mandarin reflexes and pronounciations of a string of traditional Chinese characters");
	    panel.add(button4);
	    
	    JButton reflexesButton2 = new JButton("Get the expected Mandarin reflexes and pronounciations of a string of traditional Chinese characters (one pronounciation and reflex per character)");
	    panel.add(reflexesButton2);
	    
	    
	    JButton button5 = new JButton("Quit");
	    panel.add(button5);
	    
	    frame.setVisible(true);
	    
	    button1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae){
	    		JPanel panel2 = new JPanel(new GridLayout(6, 1));
	    		frame.setContentPane(panel2);
				
	    		TextArea ta = new TextArea();
				ta.setText("Enter file path and name of PDF file (ie. \"/Users/username/Downloads/filename.pdf\"): ");
				ta.setEditable(false);
				panel2.add(ta);
				
				TextField tf = new TextField();
				tf.setBackground(Color.LIGHT_GRAY);
				panel2.add(tf);
				
				TextArea ta2 = new TextArea();
				ta2.setText("Enter save file path and name of new PDF file (ie. \"/Users/username/Downloads/filename.pdf\"): ");
				ta2.setEditable(false);
				panel2.add(ta2);
				
				TextField tf2 = new TextField();
				tf2.setBackground(Color.LIGHT_GRAY);
				panel2.add(tf2);
				
				JButton button7 = new JButton("Export");
				panel2.add(button7);
				
				JButton button9 = new JButton("Back");
				panel2.add(button9);
				
				frame.setVisible(true);
				
				button9.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						frame.setContentPane(panel);
						frame.setVisible(true);
					}
				});
				
				button7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						try {
							frame.setContentPane(panel);
							frame.setVisible(true);
							Main.exportVocabList(tf.getText(), tf2.getText());
						} catch (IOException e) {
						}
					}
				});
	    	}    	
	    });
	
	
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				JPanel panel2 = new JPanel(new GridLayout(5, 1));
	    		frame.setContentPane(panel2);
	    		
	    		TextArea ta = new TextArea();
				ta.setText("Enter string of traditional characters (ie. \"自古有中華有夷狄乃天地自然之限也\"): ");
				ta.setEditable(false);
				panel2.add(ta);
				
				TextField tf = new TextField();
				tf.setBackground(Color.LIGHT_GRAY);
				panel2.add(tf);
				
				JButton button7 = new JButton("Results");
				panel2.add(button7);
				
				TextArea ta3 = new TextArea();
				ta3.setEditable(false);
				panel2.add(ta3);
				
				JButton button9 = new JButton("Back");
				panel2.add(button9);
				
				frame.setVisible(true);
				
				button9.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						frame.setContentPane(panel);
						frame.setVisible(true);
					}
				});
				
				
				button7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						String s = tf.getText();
						double totalMatching = 0.0;
						double totalCharacters = 0.0;
						ReflexScraper reflexScraper = new ReflexScraper();
			
						ta3.append("\n");
						for (int j = 0; j < s.length(); j++) {
							
							
							ta3.append(s.charAt(j) + ": " + "\n");
							ta3.append("Pronounciations: " + reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)) + "\n");
							ta3.append("Expected reflexes: " + reflexScraper.getExpectedReflexes("https://en.wiktionary.org/wiki/" + s.charAt(j)) + "\n");
							
							for (int q = 0; q < reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)).size(); q++) {
								if (reflexScraper.getExpectedReflexes("https://en.wiktionary.org/wiki/" + s.charAt(j)).contains(reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)).get(q)) == true) {
									totalMatching++;
								}
								
							}
							if (reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)).size() != 0) {
								totalCharacters += reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)).size();
							}
							
							ta3.append("\n");
							
						}
						ta3.append("Number of pronounciations matching expected reflexes: " + (int) totalMatching + "\n");
						ta3.append("Number of pronounciations: " + (int) totalCharacters + "\n");
						ta3.append("\n");
						ta3.append("Ratio of number of pronounciations matching expected reflexes to number of pronounciations: " + (totalMatching / totalCharacters) + "\n");
						ta3.append("\n");
					}
				});
			}
		});
		
		
		reflexesButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				JPanel panel2 = new JPanel(new GridLayout(5, 1));
	    		frame.setContentPane(panel2);
	    		
	    		TextArea ta = new TextArea();
				ta.setText("Enter string of traditional characters (ie. \"自古有中華有夷狄乃天地自然之限也\"): ");
				ta.setEditable(false);
				panel2.add(ta);
				
				TextField tf = new TextField();
				tf.setBackground(Color.LIGHT_GRAY);
				panel2.add(tf);
				
				JButton button7 = new JButton("Results");
				panel2.add(button7);
				
				TextArea ta3 = new TextArea();
				ta3.setEditable(false);
				panel2.add(ta3);
				
				JButton button9 = new JButton("Back");
				panel2.add(button9);
				
				frame.setVisible(true);
				
				button9.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						frame.setContentPane(panel);
						frame.setVisible(true);
					}
				});
				
				
				button7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						String s = tf.getText();
						double totalMatching = 0.0;
						double totalCharacters = 0.0;
						ReflexScraper reflexScraper = new ReflexScraper();
			
						ta3.append("\n");
						for (int j = 0; j < s.length(); j++) {
							
							
							ta3.append(s.charAt(j) + ": " + "\n");
							ta3.append("Pronounciation: " + reflexScraper.getPronounciation("https://en.wiktionary.org/wiki/" + s.charAt(j)) + "\n");
							ta3.append("Expected reflex: " + reflexScraper.getExpectedReflex("https://en.wiktionary.org/wiki/" + s.charAt(j)) + "\n");
							
							for (int q = 0; q < reflexScraper.getPronounciation("https://en.wiktionary.org/wiki/" + s.charAt(j)).size(); q++) {
								if (reflexScraper.getExpectedReflex("https://en.wiktionary.org/wiki/" + s.charAt(j)).contains(reflexScraper.getPronounciation("https://en.wiktionary.org/wiki/" + s.charAt(j)).get(q)) == true) {
									totalMatching++;
								}
								
							}
							
							if (reflexScraper.getPronounciation("https://en.wiktionary.org/wiki/" + s.charAt(j)).size() != 0) {
								totalCharacters += reflexScraper.getPronounciation("https://en.wiktionary.org/wiki/" + s.charAt(j)).size();
							}
							
							ta3.append("\n");
							
						}
						ta3.append("Number of pronounciations matching expected reflexes: " + (int) totalMatching + "\n");
						ta3.append("Number of pronounciations: " + (int) totalCharacters + "\n");
						ta3.append("\n");
						ta3.append("Ratio of number of pronounciations matching expected reflexes to number of pronounciations: " + (totalMatching / totalCharacters) + "\n");
						ta3.append("\n");
					}
				});
			}
		});
		
		
		
		
	}
    
	public void actionPerformed(ActionEvent e){
		
	}
}
