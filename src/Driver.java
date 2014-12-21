
import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
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
public class Driver {
    public  static Assets parsingAssets(Document doc){
        NodeList nList = doc.getElementsByTagName("Asset");
        Assets assets=new Assets();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            String assetName= eElement.getElementsByTagName("Name").item(0).getTextContent();
                            String type= eElement.getElementsByTagName("Type").item(0).getTextContent();
                            int size= Integer.parseInt(eElement.getElementsByTagName("Size").item(0).getTextContent());
                            Node locationNode=eElement.getElementsByTagName("Location").item(0);
                            Location location=new Location(Integer.parseInt(locationNode.getAttributes().getNamedItem("x").getNodeValue()),Integer.parseInt(locationNode.getAttributes().getNamedItem("y").getNodeValue()));
                            int costPerNight=Integer.parseInt(eElement.getElementsByTagName("CostPerNight").item(0).getTextContent());
                            Asset asset=new Asset(assetName, type, location, costPerNight, size);
                            NodeList assetContentsList=eElement.getElementsByTagName("AssetContent");
                            for(int i=0;i<assetContentsList.getLength();i++){
                                Node contentsNode=assetContentsList.item(i);
                                Element element=(Element) contentsNode;
                                String assetContentName=element.getElementsByTagName("Name").item(0).getTextContent();
                                double repairMultiplier=Double.parseDouble(element.getElementsByTagName("RepairMultiplier").item(0).getTextContent());
                                // check on asset content collection first 
                                AssetContent assetContent=new AssetContent(assetContentName, repairMultiplier);
                                asset.addAssetContent(assetContent);
                            }
                            assets.addAsset(asset);//semaphore
                            
                    }
            }
            
            return assets;
            
    }
    public static void parsingCostumersGroup(Document doc){
        NodeList nList = doc.getElementsByTagName("CustomerGroupDetails");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            String groupManagerName = eElement.getElementsByTagName("GroupManagerName").item(0).getTextContent();
                            CostumerGroupDetails costumerGroupDetails=new CostumerGroupDetails(groupManagerName);
                            NodeList customersList=eElement.getElementsByTagName("Customer");
                            for(int i=0;i<customersList.getLength();i++){
                                Node costumerNode=customersList.item(i);
                                Element element=(Element) costumerNode;
                                String customerName= element.getElementsByTagName("Name").item(0).getTextContent();
                                String vandalism = element.getElementsByTagName("Vandalism").item(0).getTextContent();
                                int minimumDamage  = Integer.parseInt(element.getElementsByTagName("MinimumDamage").item(0).getTextContent());
                                int maximumDamage  = Integer.parseInt(element.getElementsByTagName("MaximumDamage").item(0).getTextContent());
                                Costumer costumer=new Costumer(customerName, vandalism, minimumDamage, maximumDamage);
                                costumerGroupDetails.addCostumer();
                            }
                            NodeList rentalRequestList=eElement.getElementsByTagName("Request");
                            for(int i=0;i<rentalRequestList.getLength();i++){
                                Node requestNode=rentalRequestList.item(i);
                                Element element=(Element) requestNode;
                                String id=element.getAttribute("id");
                                String type = element.getElementsByTagName("Type").item(0).getTextContent();
                                int size = Integer.parseInt(element.getElementsByTagName("Size").item(0).getTextContent());
                                int duration = Integer.parseInt(element.getElementsByTagName("Duration").item(0).getTextContent());
                                RentalRequest rentalRequest=new RentalRequest(id, type, size, duration);
                                costumerGroupDetails.addRentalRequest();
                            }
                            
                    }
            }
        
    }
    public static void parsingAssetContentsRepairDetails(Document doc){
        NodeList nList = doc.getElementsByTagName("AssetContent");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            //find asset content in the collection
                            String assetContentName=eElement.getElementsByTagName("Name").item(0).getTextContent();
                            NodeList toolsList=eElement.getElementsByTagName("Tool");
                            RepairToolInformation repairToolInformation=new RepairToolInformation();
                            //link RepairToolInormation to the content
                            for(int i=0;i<toolsList.getLength();i++){
                                Node toolNode=toolsList.item(i);
                                Element element=(Element) toolNode;
                                String toolName = element.getElementsByTagName("Name").item(0).getTextContent();
                                int quantity= Integer.parseInt(element.getElementsByTagName("Quantity").item(0).getTextContent());
                                //fint tool on tool collection
                                //RepairTool repairTool=new RepairTool(toolName, quantity);
                                repairToolInformation.addRepairTool(toolName , quantity);
                            }
                            RepairMaterialInformation repairMaterialInformation=new RepairMaterialInformation();
                            //link RepairMaterialInformation to the content
                            NodeList materialsList=eElement.getElementsByTagName("Material");
                            for(int i=0;i<materialsList.getLength();i++){
                                Node materialNode=materialsList.item(i);
                                Element element=(Element) materialNode;
                                String materialName =element.getElementsByTagName("Name").item(0).getTextContent();
                                int quantity= Integer.parseInt(element.getElementsByTagName("Quantity").item(0).getTextContent());
                                //fint Material on material collection
                                //RepairMaterial repairMaterial=new RepairMaterial(materialName, quantity);
                                repairMaterialInformation.addRepairMaterial(materialName, quantity);
                            }
                    }
            }
        
    }
    public static void parsingInitialData(Document doc){
        Warehouse warehouse=new Warehouse();
        NodeList node1=doc.getElementsByTagName("Warehouse");
            Element eElement1 = (Element) node1.item(0);
                NodeList toolsList=eElement1.getElementsByTagName("Tool");
                for(int i=0;i<toolsList.getLength();i++){
                    Node toolNode=toolsList.item(i);
                    Element element=(Element) toolNode;
                    String toolName = element.getElementsByTagName("name").item(0).getTextContent();
                    int quantity =Integer.parseInt( element.getElementsByTagName("quantity").item(0).getTextContent());
                    RepairTool repairTool=new RepairTool(toolName, quantity);
                    //initial tool collection 
                }
                NodeList materialList=eElement1.getElementsByTagName("Material");
                for(int i=0;i<materialList.getLength();i++){
                    Node materialNode=materialList.item(i);
                    Element element=(Element) materialNode;
                    String materialName = element.getElementsByTagName("name").item(0).getTextContent();
                    int quantity = Integer.parseInt(element.getElementsByTagName("quantity").item(0).getTextContent());
                    RepairMaterial repairMaterial=new RepairMaterial(materialName, quantity);
                    //initial Material collection
                }
                
            
            NodeList node2=doc.getElementsByTagName("Staff");
            Element eElement2 = (Element) node2.item(0);
                NodeList clerkList=eElement2.getElementsByTagName("Clerk");
                for(int i=0;i<clerkList.getLength();i++){
                    Node clerkNode=clerkList.item(i);
                    Element element=(Element) clerkNode;
                    String clerkName = element.getElementsByTagName("name").item(0).getTextContent();
                    Node locationNode=eElement2.getElementsByTagName("Location").item(0);
                    Location clerkLocation=new Location(Integer.parseInt(locationNode.getAttributes().getNamedItem("x").getNodeValue()),Integer.parseInt(locationNode.getAttributes().getNamedItem("y").getNodeValue()));
                    ClerkDetails clerkDetails=new ClerkDetails(clerkName, clerkLocation);
                }
                int numberOfMaintenancePersons=Integer.parseInt(eElement2.getElementsByTagName("NumberOfMaintenancePersons").item(0).getTextContent());             
                int totalNumberOfRentalRequests=Integer.parseInt(eElement2.getElementsByTagName("TotalNumberOfRentalRequests").item(0).getTextContent());
            
    }
    
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Parsing parse=new Parsing();
        Document doc1=parse.parseAssets("src/Assets.xml");
        Document doc2=parse.parseCostumersGroup("src/CustomersGroups.xml");
        Document doc3=parse.parseAssetContentsRepairDetails("src/AssetContentsRepairDetails.xml");
        Document doc4=parse.parseInitialData("src/InitialData.xml");
        parsingInitialData(doc4);
        Assets assets=parsingAssets(doc1);
        parsingCostumersGroup(doc2);
        parsingAssetContentsRepairDetails(doc3);
        
    }
}
