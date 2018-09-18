import com.envision.eeop.api.EnvisionClient;
import com.envision.eeop.api.EnvisionDefaultClient;
import com.envision.eeop.api.domain.MdmObjectAttributes;
import com.envision.eeop.api.exception.EnvisionApiException;
import com.envision.eeop.api.request.MdmObjectAttributesGetRequest;
import com.envision.eeop.api.response.MdmObjectAttributesGetResponse;
import conf.Conf;
import dao.AAAAMapper;
import dao.FieldStationMapper;
import dao.StationGroupMapper;
import dao.StationMapper;
import dto.AAAA;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.*;

public class GetData2 {
    public static  void main(String[] args){
        //mybatis的配置文件
        String resource = "MyBatisConf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = GetData.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession,它的内部含有一块数据区域，存在线程不安全，因此声明在方法内部
        boolean autoCommit =true; //是否自动提交事务
        SqlSession session = sessionFactory.openSession(autoCommit);

        //创建FieldStationMappper对象
        FieldStationMapper fieldStationMapper = session.getMapper(FieldStationMapper.class);
        StationMapper stationMapper = session.getMapper(StationMapper.class);
        StationGroupMapper stationGroupMapper = session.getMapper(StationGroupMapper.class);
        AAAAMapper aaaaMapper = session.getMapper(AAAAMapper.class);
        List<String> fieldStationMdmIds = fieldStationMapper.getFieldStationsMdmIdFromMysql();
        List<String> stationMdmIds = stationMapper.getStationsMdmIdFromMysql();
        List<String> stationGroups = stationGroupMapper.getStationGroupsMdmIdFromMysql();
//        for (int i = 0; i < fieldStationMdmIds.size(); i++) {
//            String s =  fieldStationMdmIds.get(i);
//            System.out.println(s);
//        }
        EnvisionClient eeopClient = new EnvisionDefaultClient(Conf.EEOP_URL, Conf.APP_KEY, Conf.APP_SECRET);
        String newToken = GetData.getUserToken(eeopClient);
        List<String> mdmIDList = fieldStationMdmIds;
//        List<String> mdmIDList = stationMdmIds;
//        List<String> mdmIDList = stationGroups;
//        List<String> attributeList = Arrays.asList("name","capacity");
        Set<String> aa =new HashSet<String>();
        try {
            MdmObjectAttributesGetRequest request = new MdmObjectAttributesGetRequest(mdmIDList);
//            MdmObjectAttributesGetRequest request = new MdmObjectAttributesGetRequest(mdmIDList);
            MdmObjectAttributesGetResponse response = eeopClient.execute(request, newToken);
            List<AAAA> qqq = new ArrayList<AAAA>();
            for (Map.Entry<String,MdmObjectAttributes> entry : response.getMdmObjects().entrySet()) {
                AAAA aaaa = new AAAA();
                System.out.println(entry.getKey());
                aaaa.setMdmID(entry.getKey());
                for (Map.Entry<String,String> entry1 :entry.getValue().getAttributes().entrySet()){
                    if (entry1.getKey().equals("capacity")){
                        aaaa.setCapacity(entry1.getValue());
                    }
                    if (entry1.getKey().equals("name") ){
                        aaaa.setName(entry1.getValue());
                    }
//                    System.out.println(entry1.getKey()+"    :    "+entry1.getValue());
                    aa.add(entry1.getKey());
                }
                qqq.add(aaaa);
                System.out.println("*******************");
            }
//            aaaaMapper.putAAAAsToMysql(qqq);
        }catch (EnvisionApiException e){
            e.printStackTrace();
        }


        for (String s: aa){
            System.out.println(s);
        }


    }
}
