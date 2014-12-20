
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
                                AssetContent assetContent=new AssetContent(assetContentName, repairMultiplier);
                                asset.addAssetContent(assetContent);
                            }
                            assets.addAsset(asset);
                            
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
        
    }
    public static void parsingInitialData(Document doc){
        
    }
    
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Parsing parse=new Parsing();
        Document doc1=parse.parseAssets("src/Assets.xml");
        Document doc2=parse.parseCostumersGroup("src/CustomersGroups.xml");
        Document doc3=parse.parseAssetContentsRepairDetails("src/AssetContentsRepairDetails.xml");
        Document doc4=parse.parseInitialData("src/InitialData.xml");
        Assets assets=parsingAssets(doc1);
        parsingCostumersGroup(doc2);
        parsingAssetContentsRepairDetails(doc3);
        parsingInitialData(doc4);
    }
}
