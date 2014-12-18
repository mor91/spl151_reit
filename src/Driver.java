
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
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
    
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Parsing parse=new Parsing();
        parse.parseAssets("src/Assets.xml");
        parse.parseCostumersGroup("src/CustomersGroups.xml");
        parse.parseAssetContentsRepairDetails("src/AssetContentsRepairDetails.xml");
    }
}
