package com.provigil.calculator;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Unit test for SurveillanceCostCal App.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest()
public class SurveillanceCostCalTest {
	
	SurveillanceCostCal testObj;

	@Test
	public void testApp() throws ParserConfigurationException, SAXException, IOException {
		testObj = new SurveillanceCostCal();
		NodeList mockList = EasyMock.createMock(NodeList.class);
		Node mockNode = EasyMock.createMock(Node.class);
		File file = new File("");
		Element mockEle = EasyMock.createMock(Element.class);
		Document mockdoc = EasyMock.createMock(Document.class);
		DocumentBuilderFactory mockFactory = EasyMock.createMock(DocumentBuilderFactory.class);
		DocumentBuilder mockdb = EasyMock.createMock(DocumentBuilder.class);
		EasyMock.expect(mockFactory.newDocumentBuilder()).andReturn(mockdb).anyTimes();
		EasyMock.expect(mockdb.parse(file)).andReturn(mockdoc).anyTimes();
		EasyMock.expect(mockdoc.getDocumentElement()).andReturn(mockEle).anyTimes();
		mockEle.normalize();
		EasyMock.expectLastCall();
		EasyMock.expect(mockdoc.getElementsByTagName("subscriptions")).andReturn(mockList).anyTimes();
		EasyMock.expect(mockList.getLength()).andReturn(4).anyTimes();
		
		EasyMock.replay(mockList,mockNode,mockEle,mockdoc,mockFactory,mockdb);
		List<SubscriptionBean> output =testObj.calculateTotalAmount();
		
		assertNotNull(output);
	}
}
