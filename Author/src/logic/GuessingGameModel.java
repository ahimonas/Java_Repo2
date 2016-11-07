package logic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GuessingGameModel {

	YesNoTree myT;

	public GuessingGameModel() {
		buildFromFile();
	}

	private void buildFromFile() {
		this.myT.getCurrentNode().getInfo().toString();

	}

	public static YesNoTree readXMLAnimalsFile(String file) {
		return readXMLAnimalsFile(new File(file));
	}

	public static YesNoTree readXMLAnimalsFile(File file) {
		DocumentBuilderFactory myF = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = myF.newDocumentBuilder();
			Document document = builder.parse(file);

			return parseTree(document);

		} catch (SAXException sxe) {
			Exception myEx = sxe;
			if (sxe.getException() != null)
				myEx = sxe.getException();
			myEx.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			// I/O error
			ioe.printStackTrace();
		}

		return null;
	}

	private static YesNoTree parseTree(Document document) {
		YesNoTree tree = new YesNoTree();

		// parse root
		Element root = (Element) document.getDocumentElement();

		tree = new YesNoTree(parseExprNode(root));

		return tree;
	}

	private static TreeNode parseExprNode(Element element) {

		if (element.hasChildNodes()) {
			NodeList myC = element.getChildNodes();
			String myTxt = "";
			TreeNode norYnode = null;
			TreeNode deffNotNode = null;
			Element myCurr;

			for (int i = 0; i < myC.getLength(); i++) {
				if (myC.item(i) instanceof Element) {
					myCurr = (Element) myC.item(i);
					if (myCurr.getTagName().equals("question")) {
						myTxt = myCurr.getAttribute("value");
					} else if (myCurr.getTagName().equals("answer")) {
						if (myCurr.getAttribute("value").equals("yes")) {
							norYnode = parseExprNode(myCurr);
						}
						if (myCurr.getAttribute("value").equals("no"))
							;
						{
							deffNotNode = parseExprNode(myCurr);
						}
					} else if (myCurr.getTagName().equals("node")) {
						return parseExprNode(myCurr);
					} else if (myCurr.getTagName().equals("statement")) {
						return new TreeNode(myCurr.getAttribute("value"));
					}

				}
			}

			TreeNode exprNode = new TreeNode(myTxt);
			exprNode.setNoNode(deffNotNode);
			exprNode.setYesNode(norYnode);
			return exprNode;
		}
		return new TreeNode();
	}

	private YesNoTree buildFromScratch() {
		TreeNode thisCurrRoot;
		thisCurrRoot = new TreeNode();
		thisCurrRoot.setInfo("Does it fly?");
		thisCurrRoot.setYesNode(new TreeNode("Duck"));
		thisCurrRoot.setNoNode(new TreeNode("Does it live in Water?",
				new TreeNode("This is a whale"),
				new TreeNode("This is a moose")));
		return new YesNoTree(thisCurrRoot);
	}

	// reinitializes game when user presses restart
	public void reInitialize() {

		this.myT = buildFromScratch();

	}

	public void yesSelection() {
		this.myT.moveYesNode();
	}

	public void noSelection() {
		this.myT.moveNoNode();
	}

	public boolean isQuestion() {
		return !this.myT.getCurrentNode().isLeaf();
	}

	public String updateInfo() {
		return this.myT.getCurrentNode().getInfo();
	}

}
