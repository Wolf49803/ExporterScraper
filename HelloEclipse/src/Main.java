import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.text.PDFTextStripper;
import java.lang.IllegalArgumentException;
import java.lang.StringIndexOutOfBoundsException;

public class Main {
	public static void main(String[] args) throws IOException{
		/*
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    JFrame frame = new JFrame("ExporterScraper");

	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(screenSize.width, screenSize.height);
	    JPanel panel = new JPanel(new GridLayout(5, 1));
	    frame.setContentPane(panel);
	    
	  
	
	   
	    
	    JButton button1 = new JButton("Export a vocabulary list as a PDF file");
	    panel.add(button1);
	    button1.setActionCommand("1");
	    JButton button2 = new JButton("Get the expected Mandarin reflexes of a traditional Chinese character");
	    panel.add(button2);
	    JButton button4 = new JButton("Option 3");
	    panel.add(button4);
	    JButton button5 = new JButton("Option 4");
	    panel.add(button5);
	    JButton button3 = new JButton("Quit");
	    panel.add(button3);
	    
	    
	    frame.setVisible(true);
	    */
		Listener listener = new Listener();
		listener.createFrame();

		int i = 0;
		while (i == 0) {
			System.out.print("Enter input (1: export a vocabulary list as a PDF file; 2: get the expected Mandarin reflexes of a traditional Chinese character; 3: quit): ");
			Scanner scanner = new Scanner(System.in);
	
			int choice = scanner.nextInt();
			
			
			if (choice == 1) {
				System.out.print("Enter PDF file: ");
				String f = scanner.next();
				System.out.print("Enter PDF file: ");
				String f2 = scanner.next();
				exportVocabList(f, f2);
				
				
			} 
			if (choice == 2) {
				ReflexScraper reflexScraper = new ReflexScraper();
				System.out.print("Enter traditional character: ");
				String c = scanner.next();
				System.out.println(reflexScraper.getExpectedReflexes("https://en.wiktionary.org/wiki/" + c));
				
			}
			if (choice == 3) {
				i = 1;
				scanner.close();
			}
			if (choice == 4) {
				System.out.print("Enter traditional character: ");
				String c = scanner.next();
				int totalMatching = 0;
				ReflexScraper reflexScraper = new ReflexScraper();
				System.out.println("Pronounciations: " + reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + c));
				System.out.println("Expected reflexes: " + reflexScraper.getExpectedReflexes("https://en.wiktionary.org/wiki/" + c));
				
				
				for (int j = 0; j < reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + c).size(); j++) {
					if (reflexScraper.getExpectedReflexes("https://en.wiktionary.org/wiki/" + c).contains(reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + c).get(j)) == true) {
						totalMatching++;
					}
				}
				System.out.println("Number of pronounciations matching expected reflexes: " + totalMatching);
			}
			if (choice == 5) {
				System.out.print("Enter traditional characters: ");
				String s = scanner.next();
				double totalMatching = 0.0;
				double totalCharacters = 0.0;
				ReflexScraper reflexScraper = new ReflexScraper();
				System.out.println("————————————————————————————————————————————————————————————");
 				for (int j = 0; j < s.length(); j++) {
 					
 					System.out.println(s.charAt(j) + ": ");
 					System.out.println("Pronounciations: " + reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)));
 					System.out.println("Expected reflexes: " + reflexScraper.getExpectedReflexes("https://en.wiktionary.org/wiki/" + s.charAt(j)));
 					
 					for (int q = 0; q < reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)).size(); q++) {
 						if (reflexScraper.getExpectedReflexes("https://en.wiktionary.org/wiki/" + s.charAt(j)).contains(reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)).get(q)) == true) {
 							totalMatching++;
 						}
 						
 					}
 					if (reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)).size() != 0) {
						totalCharacters += reflexScraper.getPronounciations("https://en.wiktionary.org/wiki/" + s.charAt(j)).size();
					}
 					
 					System.out.println("————————————————————————————————————————————————————————————");
					
				}
 				System.out.println("Number of pronounciations matching expected reflexes: " + (int) totalMatching);
 				System.out.println("Number of pronounciations: " + (int) totalCharacters);
 				System.out.println("————————————————————————————————————————————————————————————");
 				System.out.println("Ratio of number of pronounciations matching expected reflexes to number of pronounciations: " + (totalMatching / totalCharacters));
 				System.out.println("————————————————————————————————————————————————————————————");
			}
		}
	}
	public static void exportVocabList(String file , String file2) throws IOException{	 
		PDDocument document = PDDocument.load(new File(file)); 
		PDDocument newdocument = new PDDocument();
		PDFTextStripper pdfStripper = new PDFTextStripper();

		PDResources res = document.getPage(0).getResources();
		Scanner scanner = new Scanner(pdfStripper.getText(document));
		
		
		for (COSName fontName : res.getFontNames()) {
		
		    //PDFont font = res.getFont(fontName);
			File myFont1 = new File("simkai.ttf");
			PDFont font = PDType0Font.load(newdocument, myFont1);
			
			int j = 0;
			while (scanner.hasNextLine() == true) {
				PDPage newpage = new PDPage();
				newdocument.addPage(newpage);
				PDPageContentStream stream = new PDPageContentStream(newdocument, newdocument.getPage(j));
				stream.setFont(font, 11);
				stream.beginText();
				stream.newLineAtOffset(25,750);
				
				for (int a = 0; a < 49; a++) {
		
					try {
						String line = scanner.nextLine();
						System.out.println(line);
						stream.showText(line.substring(0, line.indexOf(" ")));
						stream.newLineAtOffset(0, -15);
						
					} catch(StringIndexOutOfBoundsException sioobe) {
					} catch(NoSuchElementException nsee) {
					}
					
				}
				
				j++;
				
				stream.endText();
				stream.close();  
				
			}
			               
		} 
		
	
		newdocument.save(file2);
		
		document.close();
		newdocument.close();
		scanner.close();
	
	
	}
}
