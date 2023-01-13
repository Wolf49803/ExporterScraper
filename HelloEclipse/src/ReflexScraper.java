import com.jaunt.*;
import java.util.ArrayList;

public class ReflexScraper {
	public ArrayList<String> getExpectedReflexes(String url) {
		ArrayList<String> reflexes = new ArrayList<String>();
		Elements e;
		UserAgent userAgent = new UserAgent();
	    try {
        	userAgent.visit(url);
        	e = userAgent.doc.findEach("<small|td>");
        	ArrayList<Element> list = new ArrayList<Element>();
        	
        	for (Element element : e) {
        		list.add(element);
        	}		
        	
        	for (Element element : list) {
        		if (element.getChildText().contains("Mandarin") == true) {
        			if (reflexes.contains(list.get(list.indexOf(element) + 1).getChildText().replace("\n", "")) == false) {
        				reflexes.add(list.get(list.indexOf(element) + 1).getChildText().replace("\n", ""));
        			}
        		}
        	}
        } catch (JauntException je) {
        }
	    return reflexes;
	}
	
	public ArrayList<String> getPronounciations(String url) {
		ArrayList<String> pronounciations = new ArrayList<String>();
		Elements e;
		UserAgent userAgent = new UserAgent();
		try {
        	userAgent.visit(url);
        	e = userAgent.doc.findEach("<a>");
        	ArrayList<Element> list = new ArrayList<Element>();
        	
        	for (Element element : e) {
        		list.add(element);
        	}		
        	
        	for (Element element : list) {
        		if (element.getChildText().contains("Pinyin") == true) {
        			if (list.get(list.indexOf(element) + 1).getChildText().charAt(0) >= 'a') {
        				if (pronounciations.contains(list.get(list.indexOf(element) + 1).getChildText().replace("\n", "")) == false && (list.get(list.indexOf(element) + 1).getChildText().replace("\n", "").charAt(list.get(list.indexOf(element) + 1).getChildText().replace("\n", "").length() - 1) != 'r' || list.get(list.indexOf(element) + 1).getChildText().replace("\n", "").length() == 2)) {
        					pronounciations.add(list.get(list.indexOf(element) + 1).getChildText().replace("\n", ""));
        				}
        			}
        		}
        	}
        } catch (JauntException je) {
        }
		return pronounciations;
	}
	
	public ArrayList<String> getExpectedReflex(String url) {
		ArrayList<String> reflex = new ArrayList<String>();
		Elements e;
		UserAgent userAgent = new UserAgent();
	    try {
        	userAgent.visit(url);
        	e = userAgent.doc.findEach("<small|td>");
        	ArrayList<Element> list = new ArrayList<Element>();
        	
        	for (Element element : e) {
        		list.add(element);
        	}		
        	
        	for (Element element : list) {
        		if (element.getChildText().contains("Mandarin") == true) {
        			if (reflex.contains(list.get(list.indexOf(element) + 1).getChildText().replace("\n", "")) == false) {
        				if (reflex.size() == 0) {
        					reflex.add(list.get(list.indexOf(element) + 1).getChildText().replace("\n", ""));
        				}
        			}
        		}
        	}
        } catch (JauntException je) {
        }
	    return reflex;
	}
	
	public ArrayList<String> getPronounciation(String url) {
		ArrayList<String> pronounciation = new ArrayList<String>();
		Elements e;
		UserAgent userAgent = new UserAgent();
		
		try {
        	userAgent.visit(url);
        	e = userAgent.doc.findEach("<a>");
        	ArrayList<Element> list = new ArrayList<Element>();
        	
        	for (Element element : e) {
        		list.add(element);
        	}		
        	
        	for (Element element : list) {
        		if (element.getChildText().contains("Pinyin") == true) {
        			if (list.get(list.indexOf(element) + 1).getChildText().charAt(0) >= 'a') {
        				if (pronounciation.contains(list.get(list.indexOf(element) + 1).getChildText().replace("\n", "")) == false && (list.get(list.indexOf(element) + 1).getChildText().replace("\n", "").charAt(list.get(list.indexOf(element) + 1).getChildText().replace("\n", "").length() - 1) != 'r' || list.get(list.indexOf(element) + 1).getChildText().replace("\n", "").length() == 2)) {
        					if (pronounciation.size() == 0) {
        						pronounciation.add(list.get(list.indexOf(element) + 1).getChildText().replace("\n", ""));
        					}
        				}
        			}
        		}
        	}
        } catch (JauntException je) {
        }
		
		return pronounciation;
	}
	
}