package com.wurao;

/**
 * @author ：c_peizhi
 * @version ：1.0.0
 * @user ：c_PC
 * @date ：Created in 2019/6/14 9:46
 * @description：
 * @modified By：
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * @说明：jsonp解析标签扩展类
 * @author: 勿扰
 * @CreateTime:2019年1月10日
 * @ModifyTime:2019年1月10日
 */
public class HtmlManage {

    public Document manage(String html){
        Document doc = Jsoup.parse(html);
        return doc;
    }

    /**
     * 管理链接
     * @param url
     * @return
     * @throws IOException
     */
    public Document manageDirect(String url) throws IOException{
        Document doc = Jsoup.connect( url ).get();
        return doc;
    }

    /**
     * 管理查找当前元素的标签
     * @param doc
     * @param tag
     * @return
     */
    public List<String> manageHtmlTag(Document doc,String tag ){
        List<String> list = new ArrayList<String>();

        Elements elements = doc.getElementsByTag(tag);
        for(int i = 0; i < elements.size() ; i++){
            String str = elements.get(i).html();
            list.add(str);
        }
        return list;
    }

    /**
     * 管理元素节点，或节点下面是否有class
     * @param doc
     * @param clas
     * @return
     */
    public List<String> manageHtmlClass(Document doc,String clas ){
        List<String> list = new ArrayList<String>();

        Elements elements = doc.getElementsByClass(clas);
        for(int i = 0; i < elements.size() ; i++){
            String str = elements.get(i).html();
            list.add(str);
        }
        return list;
    }

    /**
     * 管理属性和属性值, 获取所有元素
     * @param doc
     * @param key
     * @param value
     * @return
     */
    public List<String> manageHtmlKey(Document doc,String key,String value ){
        List<String> list = new ArrayList<String>();

        Elements elements = doc.getElementsByAttributeValue(key, value);
        for(int i = 0; i < elements.size() ; i++){
            String str = elements.get(i).html();
            list.add(str);
        }
        return list;
    }

    private static Log log = LogFactory.getLog(HtmlManage.class);
}
