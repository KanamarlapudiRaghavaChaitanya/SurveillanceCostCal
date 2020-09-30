package com.provigil.calculator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Hello world!
 * 
 */
public class SurveillanceCostCal {
	
	static List<SubscriptionBean> subBeanList;
	static SubscriptionBean subBean;
	
	public  void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		calculateTotalAmount();
		
	}
	
	public  List<SubscriptionBean> calculateTotalAmount() throws ParserConfigurationException, SAXException, IOException {
		subBeanList = new ArrayList<>();
		parseingXml(subBeanList);
		for(SubscriptionBean bean: subBeanList) {
			
			if(bean.getLocation().equalsIgnoreCase("Indoor")) {
			
				subBean =innerLocCase(bean);
	
			}else {
				subBean = outerLocCase(bean);
			}
		}
		
		return subBeanList;
	}
	
	public  SubscriptionBean innerLocCase(SubscriptionBean bean) {
		double cost =0;
		double modifiedArea = 0;
		Map<Integer,Double > map=new HashMap<>();
		//Slab 1
		if(bean.getArea()>0 ){	
			if(bean.getPlanType().equalsIgnoreCase("MONTHLY")) {
			cost = 2*2500;
			modifiedArea = (bean.getArea()-2500);
			
				//Slab2
				if(modifiedArea>0) {
					cost = cost+ (1.5*2500);
					modifiedArea = modifiedArea-2500;
					
					//Slab3
					if(modifiedArea>0) {
						cost = cost+ (1*modifiedArea);
						modifiedArea = modifiedArea - 45000;
						
						//Slab4
						if(modifiedArea>0) {
							cost = cost+ 0.8* modifiedArea;
							
						}
					}
						
				}
			
			}else {
			cost =1.5*2500;
			modifiedArea = (bean.getArea()-2500);
			
				//Slab2
				if(modifiedArea>0) {
					cost = cost+ (1* 2500);
					modifiedArea = modifiedArea-2500;
					
					//Slab3
					if(modifiedArea>0) {
						cost = cost+ (0.6*modifiedArea);
						modifiedArea = modifiedArea - 45000;
						
						//Slab4
						if(modifiedArea>0) {
							cost = cost+ 0.5* modifiedArea;
						}
					}
				}
			}
		}
		
		map.put(bean.area,new Double(cost));
		bean.mapList.add(map);	
		return bean;
	}
	public  SubscriptionBean outerLocCase(SubscriptionBean bean) {
		double cost =0;
		double modifiedArea=0;
		Map<Integer,Double > map=new HashMap<>();

		
		//Slab 1
				if(bean.getArea()>0 ){	
					if(bean.getPlanType().equalsIgnoreCase("MONTHLY")) {
					cost = 2.5*2500;
					modifiedArea = (bean.getArea()-2500);
					
						//Slab2
						if(modifiedArea>0) {
							cost = cost+ (1.5*2500);
							modifiedArea = modifiedArea-2500;
							
							//Slab3
							if(modifiedArea>0) {
								cost = cost+ (1.2*modifiedArea);
								modifiedArea = modifiedArea - 45000;
								
								//Slab4
								if(modifiedArea>0) {
									cost = cost+ 0.8* modifiedArea;
									
								}
							}
								
						}
					
					}else {
					cost =2*2500;
					modifiedArea = (bean.getArea()-2500);
					
						//Slab2
						if(modifiedArea>0) {
							cost = cost+ (1* 2500);
							modifiedArea = modifiedArea-2500;
							
							//Slab3
							if(modifiedArea>0) {
								cost = cost+ (0.8*modifiedArea);
								modifiedArea = modifiedArea - 45000;
								
								//Slab4
								if(modifiedArea>0) {
									cost = cost+ 0.5* modifiedArea;
								}
							}
						}
					}
				}
			
		map.put(bean.area ,new Double(cost));
		bean.mapList.add(map);
		return bean;
	}
	
	public  void parseingXml(List<SubscriptionBean> list) throws ParserConfigurationException, SAXException, IOException  {
		
		
		File file = new File("C:\\Users\\sunny chaitanya\\Downloads\\surveillance-cost-cal\\surveillance-cost-cal\\src\\main\\resources\\sample01.xml");  
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);  
		doc.getDocumentElement().normalize();  
		NodeList nodeList = doc.getElementsByTagName("subscription"); 
		
		for (int itr = 0; itr < nodeList.getLength(); itr++) {  
			Node node = nodeList.item(itr);  
		if (node.getNodeType() == Node.ELEMENT_NODE) {  
			Element eElement = (Element) node;  
			subBean = new SubscriptionBean();
			subBean.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
			subBean.setArea(Integer.parseInt(eElement.getElementsByTagName("area").item(0).getTextContent()));
			subBean.setPlanType(eElement.getElementsByTagName("plan").item(0).getTextContent());
			subBean.setLocation(eElement.getElementsByTagName("location").item(0).getTextContent());

			list.add(subBean);
				}  
			}  
		}
	}
