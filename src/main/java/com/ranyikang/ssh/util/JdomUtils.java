package com.ranyikang.ssh.util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPath;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * CLASS_NAME: JDomUtils.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: jdom xml 文件处理工具类  <br/>
 * @date: 2023-02-27 <br/>
 */
public class JdomUtils {

    /**
     * xml 文档对象
     */
    private final Document document;
    /**
     * 根节点元素对象
     */
    private final Element root;
    /**
     * xml 文件对象
     */
    private final File xmlFile;

    /**
     * 通过xml文件对象构造一个xml文件处理工具对象
     *
     * @param xmlFile xml 文件对象
     */
    public JdomUtils(File xmlFile) throws IOException, JDOMException {
        this.xmlFile = xmlFile;
        SAXBuilder builder = new SAXBuilder();
        this.document = builder.build(xmlFile);
        root = document.getRootElement();
    }

    /**
     * 通过传入节点路径和节点值设置节点及其对应的值
     *
     * @param nodePath  节点路径
     * @param nodeValue 节点值
     * @throws Exception 抛出异常
     */
    public void setNodeText(String nodePath, String nodeValue) throws Exception {
        setNodeText(nodePath, nodeValue, false, nodePath);
    }

    /**
     * 通过传入节点路径、节点值、是否必须,必须节点路径
     *
     * @param nodePath     节点路径
     * @param nodeValue    节点值
     * @param required     是否必须节点
     * @param requiredName 必须节点名
     * @throws Exception 抛出异常
     */
    @SuppressWarnings("all")
    public void setNodeText(String nodePath, String nodeValue, boolean required, String requiredName) throws Exception {

        if (required && !StringUtils.hasText(nodeValue)) {
            throw new Exception(requiredName + "节点不能为空值!");
        }

        Element element;
        if ("".equalsIgnoreCase(root.getNamespace().getURI())) {
            element = (Element) XPath.selectSingleNode(root, nodePath);
        } else {
            XPath xPath = XPath.newInstance(nodePath);
            xPath.addNamespace(root.getNamespace());
            element = (Element) xPath.selectSingleNode(root);
        }
        if (element == null) {
            throw new Exception("无法设置节点 " + nodePath + " 的节点值,无此节点!");
        } else {
            element.setText(nodeValue == null ? "" : nodeValue);
        }

    }

    /**
     * 获取xml文件内容
     *
     * @return 返回指定xml文件内容字符串
     */
    public String getDocText() {
        return getDocText(true, "");
    }

    /**
     * 获取xml文件内容
     *
     * @param needDeclaration 需要定义是否指定, true: 需要; false: 不需要;
     * @param encode          字符集字符串
     * @return 返回获取到的 xml 文件内容字符串
     */
    public String getDocText(boolean needDeclaration, String encode) {
        XMLOutputter outPutter = new XMLOutputter();
        Format format = outPutter.getFormat();
        format.setExpandEmptyElements(true);
        if (needDeclaration) {
            if (!StringUtils.hasText(encode)) {
                format.setEncoding(getEncode());
            } else {
                format.setEncoding(encode);
            }
            format.setIndent("");
            format.setLineSeparator("");
            outPutter.setFormat(format);
            return outPutter.outputString(document);
        } else {
            String outputString = outPutter.outputString(document);
            if (outputString.startsWith("<?xml")) {
                int pos = outputString.indexOf("?>");
                outputString = outputString.substring(pos + 4);
            }
            return outputString;
        }
    }

    /**
     * 获取系统的文件字符集,默认 GBK
     *
     * @return 返回系统的文件字符集字符串, 默认 GBK
     */
    private String getEncode() {
        return System.getProperty("file.encoding", "GBK");
    }

}
