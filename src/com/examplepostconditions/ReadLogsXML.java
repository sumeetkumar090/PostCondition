package com.examplepostconditions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadLogsXML {

	public static void main(String[] args) {
		String filePath = "C:/uploads/ProcessLog.xml";
		File xmlFile = new File(filePath);
		processLog(xmlFile);

	}

	/**
	 * 
	 * @param xmlFile
	 */
	public static void processLog(File xmlFile) {
		DocumentBuilder dBuilder;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			// System.out.println("Root element :" +
			// doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("ProcessLog");
			// NodeList nodeList1 = doc.getElementsByTagName("EventLog");
			// now XML is loaded as Document in memory, lets convert it to
			// Object List
			List<ProcessLogDto> processList = new ArrayList<ProcessLogDto>();
			List<EventLogDto> eventList = new ArrayList<EventLogDto>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				processList.add(getProcessLog(nodeList.item(i)));
				eventList.add(getEventLog(nodeList.item(i)));
			}
//			// lets print Event Log list information
//			for (ProcessLogDto processLog : processList) {
//				System.out.println(processLog.getTimeStamp());
//				System.out.println(processLog.getEventID());
//				System.out.println(processLog.getCaseID());
//				System.out.println(processLog.taskID);
//				System.out.println(processLog.postConditions);
//				System.out.println(processLog.toString());
//				System.out.println("----------------------------------------------------------------");
//			}
//			for (EventLogDto event : eventList) {
//				System.out.println(event.timeStamp);
//				System.out.println(event.eventId);
//				System.out.println(event.toString());
////			}

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 
	 * @param node
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static ProcessLogDto getProcessLog(Node node) throws FileNotFoundException {
		// XMLReaderDOM domReader = new XMLReaderDOM();
		ProcessLogDto processLog = new ProcessLogDto();
		EventLogDto eventLog = new EventLogDto();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;

			// Process Log
			processLog.setTimeStamp(getTagValue("TimeStamp", element));
			processLog.setEventID(getTagValue("EventID", element));
			processLog.setCaseID(getTagValue("CaseID", element));
			processLog.setTaskID(getTagValue("TaskID", element));
			processLog.setPostConditions(getTagValue("PostCondition", element));

			// Event Log
			eventLog.setTimeStamp(getTagValue("TimeStamp", element));
			eventLog.setEventId(getTagValue("EventID", element));

			System.out.println("-----------------------------------------------------------------");
			if (eventLog.getEventId().equals(processLog.getEventID())) {
				System.out.println("Event Log - Event ID" + "\t" + eventLog.getEventId() + "\n");
				System.out.println("Process Log - Event ID" + "\t" + processLog.getEventID() + "\n");
				System.out.println("Post-Condition =   " + processLog.getPostConditions());					
			}
			System.out.println("----------------------------------------------------------------");
			
			
		}

		return processLog;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static EventLogDto getEventLog(Node node) {
		// XMLReaderDOM domReader = new XMLReaderDOM();
		EventLogDto eventLog = new EventLogDto();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			eventLog.setTimeStamp(getTagValue("TimeStamp", element));
			eventLog.setEventId(getTagValue("EventID", element));
		}

		return eventLog;
	}

	/**
	 * 
	 * @param tag
	 * @param element
	 * @return
	 */
	public static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
}
