package com.ranyikang.ssh.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * CLASS_NAME: CrawlerTestTwo.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 爬虫测试二  <br/>
 * @date: 2023-01-30 <br/>
 */
public class CrawlerTestTwo {

    public static void main(String[] args) {
        try {
            //1.需要建立一个连接
            //https://pvp.qq.com/web201605/herolist.shtml
            Connection connection = Jsoup.connect("https://pvp.qq.com/web201605/herolist.shtml");
            //2.通过连接获取一个Document对象
            Document document = connection.get();
            //3.通过Document对象，寻找里面的一个UL对象，的class属性值herolist clearfix
            Element elementUL = document.selectFirst("[class=herolist clearfix]");
            //4.通过UL标签对象，寻找里面的每一个LI对象
            Elements elementLis = elementUL.select("li");
            //5.遍历上面这个elementLis集合
            for(Element elementLi : elementLis){    //  <li></li>
                //想要得到li中的那个<a href="路径">鬼谷子</a>连接元素    名字 路径
                Element elementA = elementLi.selectFirst("a");
                //得到<a href='值'>xxx</a>中间的那个文字----英雄名字
                String innerText = elementA.text();//英雄名字(可以替换number当作图片名称)
                //得到href中的属性值
                //https://pvp.qq.com/web201605/herolist.shtml
                //herodetail/189.shtml
                String hrefURL = elementA.attr("href");//主要目的，找到路径，通过路径，产生新连接，找大图去
                //发现得到的hrefURL是一个相对的路径，拼接绝对的路径，才能访问
                String path = new StringBuilder("https://pvp.qq.com/web201605/").append(hrefURL).toString();
                //根据这个path路径，去创建一个新的连接(相当于，点击了以下)
                Connection newConnection = Jsoup.connect(path);
                //--------------------------
                //根据新的连接，获取新的document对象
                Document newDocument = newConnection.get();
                //通过新的document获取一个div
                Element elementDiv = newDocument.selectFirst("[class=zk-con1 zk-con]");
                //获取这个div中的style属性值
                //  background:url('//game.gtimg.cn/images/yxzj/img201606/skin/hero-info/190/190-bigskin-1.jpg') center 0
                String backgroundURL = elementDiv.attr("style");
                //找寻 两个单引号的位置
                int left = backgroundURL.indexOf("'");
                int right = backgroundURL.lastIndexOf("'");
                String newBG = backgroundURL.substring(left+1,right);//不可变的特性
                //      //game.gtimg.cn/images/yxzj/img201606/skin/hero-info/190/190-bigskin-1.jpg
                URL url = new URL("https:"+newBG);
                //====================================================
                //====================================================
                //  以上的部分就是将网站上的一些标签做了一个分析，分析最终得到一个url
                System.out.println("下载"+innerText);
                //  输入流，读取刚才的url路径对应的图片
                InputStream is = url.openStream();
                //  输出流，写到我们本地的硬盘上
                FileOutputStream fos = new FileOutputStream(Objects.requireNonNull(CrawlerTestOne.class.getClassLoader().getResource("")).getPath()+"img//"+innerText+".jpg");
                //  下载图片
                byte[] b = new byte[1024];
                int count = is.read(b);
                while(count!=-1){
                    fos.write(b,0,count);
                    fos.flush();
                    count = is.read(b);
                }
                is.close();
                fos.close();
            }
            System.out.println("文件爬取完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
