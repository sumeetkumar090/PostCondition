package com.examplepostconditions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadLogsXML {

	public static Map<String, ProcessLogDto> processMap;
	public static Map<String, ProcessEventStateTransitionDto> processESTMap;

	public static void main(String[] args) {
		String filePath = "C:/uploads/ProcessLog.xml";
		String filePath1 = "C:/uploads/EventLog.xml";
		String filePath2 = "C:/uploads/ProcessEvent-StateTransitionEvent.xml";
		File processFile = new File(filePath);
		File eventFile = new File(filePath1);
		File processESTFile = new File(filePath2);
		processLog(eventFile, processFile, processESTFile);

		System.exit(0);

	}

	/**
	 * 
	 * @param xmlFile
	 */
	public static void processLog(File eventFile, File processFile, File processESTFile) {
		DocumentBuilder dBuilder;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(eventFile);
			Document doc1 = dBuilder.parse(processFile);
			Document doc2 = dBuilder.parse(processESTFile);
			doc.getDocumentElement().normalize();
			doc1.getDocumentElement().normalize();
			doc2.getDocumentElement().normalize();
			NodeList eventNodeList = doc.getElementsByTagName("EventLog");
			NodeList processNodeList = doc1.getElementsByTagName("ProcessLog");
			NodeList processESTNodeList = doc2.getElementsByTagName("TaskEffect");
			setProcessLog(processNodeList);
			setProcessESTLog(processESTNodeList);
			printResult(eventNodeList);

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
	}

	private static void setProcessESTLog(NodeList processESTNodeList) throws FileNotFoundException {
		processESTMap = new HashMap<>();
		for (int i = 0; i < processESTNodeList.getLength(); i++) {
			ProcessEventStateTransitionDto processEventStateTransitionDto = getProcessEventStateTransitionDto(
					processESTNodeList.item(i));
			processESTMap.put(processEventStateTransitionDto.getTaskId(), processEventStateTransitionDto);
		}
	}

	private static void setProcessLog(NodeList processNodeList) throws FileNotFoundException {
		processMap = new HashMap<>();
		for (int i = 0; i < processNodeList.getLength(); i++) {
			ProcessLogDto processLogDto = getProcessLog(processNodeList.item(i));
			processMap.put(processLogDto.getEventID(), processLogDto);
		}
	}

	private static void printResult(NodeList eventNodeList) {
		for (int i = 0; i < eventNodeList.getLength(); i++) {
			EventLogDto eventLogDto = getEventLog(eventNodeList.item(i));
			ProcessLogDto processLogDto = processMap.get(eventLogDto.getEventId());
			if (processLogDto != null) {
				ProcessEventStateTransitionDto processEventStateTransitionDto = processESTMap
						.get(processLogDto.getTaskID());
				if (processEventStateTransitionDto != null) {
					if (processLogDto.getPostConditions().equals(processEventStateTransitionDto.getPostEffect())) {
						System.out.println("Event Log - Event ID" + "\t" + eventLogDto.getEventId() + "\n");
						System.out.println("Process Log - Task ID" + "\t" + processLogDto.getTaskID() + "\n");
						System.out.println(
								"Process Log - Post Condition" + "\t" + processLogDto.getPostConditions() + "\n");
						System.out.println("ProcessEventStateTransition Log - After Effect" + "\t"
								+ processEventStateTransitionDto.getPostEffect() + "\n");
						System.out.println("YES - Post Condition and After Effect are equal");

					}
				}
				System.out.println("-----------------------------------------------------------------");

				System.out.println("----------------------------------------------------------------");
			}

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

//			System.out.println("-----------------------------------------------------------------");
//			if (eventLog.getEventId().equals(processLog.getEventID())) {
//				System.out.println("Process Log - Task ID" + "\t" + processLog.getTaskID() + "\n");
//				System.out.println("Post-Condition =   " + processLog.getPostConditions());
//			}
//			System.out.println("----------------------------------------------------------------");

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
	 * @param node
	 * @return
	 */
	public static ProcessEventStateTransitionDto getProcessEventStateTransitionDto(Node node) {
		// XMLReaderDOM domReader = new XMLReaderDOM();
		ProcessEventStateTransitionDto processEventStateTransitionDto = new ProcessEventStateTransitionDto();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			processEventStateTransitionDto.setTaskId(getTagValue("TaskID", element));
			processEventStateTransitionDto.setPostEffect(getTagValue("PostEffect", element));
			processEventStateTransitionDto.setPriorEffect(getTagValue("PriorEffect", element));
		}

		return processEventStateTransitionDto;
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