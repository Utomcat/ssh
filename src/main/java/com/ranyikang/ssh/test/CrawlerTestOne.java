package com.ranyikang.ssh.test;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * CLASS_NAME: CrawlerTestOne.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 爬虫测试一  <br/>
 * @date: 2023-01-30 <br/>
 */
@Slf4j
public class CrawlerTestOne {

    private static final String HTTPS = "https:";
    private static final String ROOT_PATH = HTTPS + "//pvp.qq.com/web201605/";

    public static void main(String[] args) {
        String urlStr = ROOT_PATH + "herolist.shtml";
        Map<String,String> imageUrlMap = new HashMap<>(16);
        try {
            Document document = Jsoup.connect(urlStr).get();
            Element element = document.selectFirst("[class=herolist clearfix]");
            assert element != null;
            Elements lis = element.getElementsByTag("li");
            lis.forEach(item -> {
                Element el = item.getElementsByTag("a").get(0);
                String href = el.attributes().get("href");
                String name = el.text();
                imageUrlMap.put(name,ROOT_PATH + href);
            });
            /*imageUrlMap.forEach((key,value) -> {
                try {
                    Document imgDocument = Jsoup.connect(value).get();
                    String imgCssStyle = imgDocument.getElementsByClass("zk-con1 zk-con").get(0).attr("style");
                    String imgUrl = imgCssStyle.substring(imgCssStyle.indexOf("'")+1, imgCssStyle.lastIndexOf("'"));
                    URL img = new URL(HTTPS + imgUrl);
                    InputStream inputStream = img.openStream();
                    String path = Objects.requireNonNull(CrawlerTestOne.class.getClassLoader().getResource("")).getPath()+"img/";
                    File file = new File(path);
                    if (!file.exists()) {
                        if (file.mkdir()) {
                            log.info("创建目标目录成功!");
                        }
                    }
                    String imgPath = path+key+".jpg";
                    FileOutputStream fileOutputStream = new FileOutputStream(imgPath);
                    byte[] bytes = new byte[1024];
                    int count = inputStream.read(bytes);
                    while (count != -1){
                        fileOutputStream.write(bytes,0,count);
                        fileOutputStream.flush();
                        count = inputStream.read(bytes);
                    }
                    inputStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
