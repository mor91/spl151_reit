
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class Parsing {

    public Parsing() {
    }
    
    public static Document parseAssets(String fileName) throws SAXException {
        try{
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Asset");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    System.out.println("\nCurrent Element :" + nNode.getNodeName());
                    
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            System.out.println("Name : " + eElement.getElementsByTagName("Name").item(0).getTextContent());
                            System.out.println("Type : " + eElement.getElementsByTagName("Type").item(0).getTextContent());
                            System.out.println("Size : " + eElement.getElementsByTagName("Size").item(0).getTextContent());
                            Node locationNode=eElement.getElementsByTagName("Location").item(0);
                            System.out.println("Location : " + locationNode.getAttributes().getNamedItem("x").getNodeValue()+locationNode.getAttributes().getNamedItem("y").getNodeValue());
                            System.out.println("CostPerNight : " + eElement.getElementsByTagName("CostPerNight").item(0).getTextContent());
                           
                            NodeList assetContentsList=eElement.getElementsByTagName("AssetContent");
                            for(int i=0;i<assetContentsList.getLength();i++){
                                Node contentsNode=assetContentsList.item(i);
                                Element element=(Element) contentsNode;
                                System.out.println("Name : " + element.getElementsByTagName("Name").item(0).getTextContent());
                                System.out.println("RepairMultiplier : " + element.getElementsByTagName("RepairMultiplier").item(0).getTextContent());
                            }
                    }
            }
            return doc;

        }catch (Exception e) {
	e.printStackTrace();
        }
        
        return null;
    }
    public static Document parseCostumersGroup(String fileName){
        try{
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("CustomerGroupDetails");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    System.out.println("\nCurrent Element :" + nNode.getNodeName());
                    
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            System.out.println("GroupManagerName : " + eElement.getElementsByTagName("GroupManagerName").item(0).getTextContent());
                            System.out.println("Customers :");
                            NodeList customersList=eElement.getElementsByTagName("Customer");
                            for(int i=0;i<customersList.getLength();i++){
                                Node costumerNode=customersList.item(i);
                                Element element=(Element) costumerNode;
                                System.out.println("Name : " + element.getElementsByTagName("Name").item(0).getTextContent());
                                System.out.println("Vandalism : " + element.getElementsByTagName("Vandalism").item(0).getTextContent());
                                System.out.println("MinimumDamage : " + element.getElementsByTagName("MinimumDamage").item(0).getTextContent());
                                System.out.println("MaximumDamage : " + element.getElementsByTagName("MaximumDamage").item(0).getTextContent());
                            }
                            System.out.println("RentalRequests :");
                            NodeList rentalRequestList=eElement.getElementsByTagName("Request");
                            for(int i=0;i<rentalRequestList.getLength();i++){
                                Node requestNode=rentalRequestList.item(i);
                                Element element=(Element) requestNode;
                                System.out.println("id : " + element.getAttribute("id"));
                                System.out.println("Type : " + element.getElementsByTagName("Type").item(0).getTextContent());
                                System.out.println("Size : " + element.getElementsByTagName("Size").item(0).getTextContent());
                                System.out.println("Duration : " + element.getElementsByTagName("Duration").item(0).getTextContent());
                            }
                    }
            }
            return doc;

        }catch (Exception e) {
	e.printStackTrace();
        }
        return null;
    }
    public static Document parseAssetContentsRepairDetails(String fileName){
        try{
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("AssetContent");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    System.out.println("\nCurrent Element :" + nNode.getNodeName());
                    
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            System.out.println("Name : " + eElement.getElementsByTagName("Name").item(0).getTextContent());
                            System.out.println("Tools :");
                            NodeList toolsList=eElement.getElementsByTagName("Tool");
                            for(int i=0;i<toolsList.getLength();i++){
                                Node toolNode=toolsList.item(i);
                                Element element=(Element) toolNode;
                                System.out.println("Name : " + element.getElementsByTagName("Name").item(0).getTextContent());
                                System.out.println("Quantity : " + element.getElementsByTagName("Quantity").item(0).getTextContent());
                             }
                            System.out.println("Materials :");
                            NodeList materialsList=eElement.getElementsByTagName("Material");
                            for(int i=0;i<materialsList.getLength();i++){
                                Node materialNode=materialsList.item(i);
                                Element element=(Element) materialNode;
                                System.out.println("Name : " + element.getElementsByTagName("Name").item(0).getTextContent());
                                System.out.println("Quantity : " + element.getElementsByTagName("Quantity").item(0).getTextContent());
                            }
                    }
            }
            return doc;

        }catch (Exception e) {
	e.printStackTrace();
        }
        return null;
    }
    public static Document parseInitialData(String fileName){
        try{
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList node1=doc.getElementsByTagName("Warehouse");
            Element eElement1 = (Element) node1.item(0);
                System.out.println("Tools :");
                NodeList toolsList=eElement1.getElementsByTagName("Tool");
                for(int i=0;i<toolsList.getLength();i++){
                    Node toolNode=toolsList.item(i);
                    Element element=(Element) toolNode;
                    System.out.println("name : " + element.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("quantity : " + element.getElementsByTagName("quantity").item(0).getTextContent());
                }
                NodeList materialList=eElement1.getElementsByTagName("Material");
                for(int i=0;i<materialList.getLength();i++){
                    Node materialNode=materialList.item(i);
                    Element element=(Element) materialNode;
                    System.out.println("name : " + element.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("quantity : " + element.getElementsByTagName("quantity").item(0).getTextContent());
                }
                
            
            NodeList node2=doc.getElementsByTagName("Staff");
            Element eElement2 = (Element) node2.item(0);
                System.out.println("clerks :");
                NodeList clerkList=eElement2.getElementsByTagName("Clerk");
                for(int i=0;i<clerkList.getLength();i++){
                    Node clerkNode=clerkList.item(i);
                    Element element=(Element) clerkNode;
                    System.out.println("name : " + element.getElementsByTagName("name").item(0).getTextContent());
                    Node locationNode=eElement2.getElementsByTagName("Location").item(0);
                    System.out.println("Location : " + locationNode.getAttributes().getNamedItem("x")+locationNode.getAttributes().getNamedItem("y"));
                }
                System.out.println("NumberOfMaintenancePersons: "+eElement2.getElementsByTagName("NumberOfMaintenancePersons").item(0).getTextContent());             
                System.out.println("TotalNumberOfRentalRequests: "+eElement2.getElementsByTagName("TotalNumberOfRentalRequests").item(0).getTextContent());
            
                return doc;
             }catch (Exception e) {
	e.printStackTrace();
        }
        return null;
    }
    
}