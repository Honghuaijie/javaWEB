package come.hhj.myssm.ioc;

import come.hhj.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ClassPathXMLApplicationContext
 * Package: com.hhj.io
 * Description:
 *      bean工厂接口的实现类
 * @Author honghuaijie
 * @Create 2023/11/3 16:22
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class ClassPathXMLApplicationContext implements BeanFactory {
    private Map<String,Object> beanMap = new HashMap<>();
    private String path = "applicationContext.xml";

    public ClassPathXMLApplicationContext(){
        this("applicationContext.xml");
    }


    public ClassPathXMLApplicationContext(String path){
        if (StringUtil.isEmpty(path)){
            throw new RuntimeException("IOC容器的配置文件没有指定");
        }
        try {
            //获取类加载器去读取配置文件
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            //1.创建DocumentBuilderFactory 这个对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder 这个对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //3.创建document对象(前两步的目的就是为了创建doucment对象)
            Document document = documentBuilder.parse(inputStream);
            //4.通过标签名获取所有bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);  //获取单个beanNode
                //判断这个beeanNode是不是一个元素节点
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id"); //获取bean中的ID属性值
                    String className = beanElement.getAttribute("class"); //获取bean中的class属性值
                    //通过反射获取className这个类，并创建className的对象
                    Object beanObj = Class.forName(className).newInstance();
                    //将bean实例对象保存到map容器中
                    beanMap.put(beanId, beanObj);
                    //到目前为止，此处需要注意的是，bean和bean之间的依赖关系还没有设置
                }
            }
            //5.获取所有bean节点的property节点中的属性，组装bean之间的依赖关系
            //5.1还是得循环遍历被一个bean
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);  //获取单个beanNode

                //判断这个beeanNode是不是一个元素节点
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;  //这里强制为element是为获取节点的属性
                    String beanId = beanElement.getAttribute("id"); //获取bean中的ID属性值
                    //获取beanElement中的所有子节点属性
                    NodeList BeanChildNodeList = beanElement.getChildNodes();
                    //从子节点中筛选出property节点
                    for (int j = 0; j < BeanChildNodeList.getLength(); j++) {
                        //获取子节点
                        Node beanChildNode = BeanChildNodeList.item(j);
                        //进行判断，首先判断该子节点是否是元素节点，其次判断该子节点的名字是否是property
                        if (beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            //取出property的两个属性：name和ret
                            //代表的是当前bean所对应的类的属性名字
                            String propertyName = propertyElement.getAttribute("name");
                            //代表的是依赖的组件名
                            String propertyRef = propertyElement.getAttribute("ref");

                            //1.获取当前bean组件所对应的对象
                            Object beanObj = beanMap.get(beanId);
                            //2.通过beanObj对象 进行反射获取其属性名字
                            Field propertyField = beanObj.getClass().getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            //3.将ref对应的对象设置到当前bean对应的实例的property属性上去
                            propertyField.set(beanObj,beanMap.get(propertyRef));
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
