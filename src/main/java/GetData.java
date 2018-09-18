import dao.FieldStationMapper;
import dao.StationGroupMapper;
import dao.StationMapper;
import dto.FieldStation;
import dto.Station;
import dto.StationGroup;
import dto.User;
import com.envision.eeop.api.EnvisionClient;
import com.envision.eeop.api.EnvisionDefaultClient;
import com.envision.eeop.api.domain.MdmObject;
import com.envision.eeop.api.exception.EnvisionApiException;
import com.envision.eeop.api.request.MdmFilteredObjectsGetRequest;
import com.envision.eeop.api.request.UserLoginRequest;
import com.envision.eeop.api.response.MdmObjectStructureGetResponse;
import com.envision.eeop.api.response.UserLoginResponse;
import conf.Conf;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetData{
    public  static  String getUserToken(EnvisionClient eeopClient){
        UserLoginRequest req = new UserLoginRequest();
        User user = new User();
        try {
            req.setPassword(Conf.PASS);
            req.setName(Conf.USER);
            UserLoginResponse resp = null;
            resp = eeopClient.execute(req);
            user.setId(resp.getUserId());
            user.setName(resp.getUserName());
            user.setToken(resp.getAccessToken());
            user.setExpiredAt(resp.getAccessTokenExpire());
        } catch (Exception e) {
            System.out.println("login failed:" + e.getMessage());
        }
        return user.getToken();
    }

    public static List<FieldStation> getFieldStations(EnvisionClient eeopClient,String newToken){
        //对象类型:54:EOS系统;55:伙伴;56:客户;57:场站组(区域);58:场站;102:领域场站;其他具体设备对象类型
        String type = "56";
        List<FieldStation> fieldStations = new ArrayList<FieldStation>();
        try {
            MdmFilteredObjectsGetRequest request = new MdmFilteredObjectsGetRequest(newToken, type);
            MdmObjectStructureGetResponse response = eeopClient.execute(request, newToken);
            for (MdmObject obj : response.getMdmObjects()) {
//                String jsonStr = JSON.toJSONString(a,SerializerFeature.PrettyFormat);
//                System.out.println(jsonStr);
                FieldStation fieldStation = new FieldStation();
                fieldStation.setMdmID(obj.getMdmID());
                Map<String, String> attributes = obj.getAttributes();
            }
        }catch (EnvisionApiException e){
            e.printStackTrace();
        }
        return fieldStations;
    }
    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }


    public static void main(String[] args){

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


        EnvisionClient eeopClient = new EnvisionDefaultClient(Conf.EEOP_URL, Conf.APP_KEY, Conf.APP_SECRET);
        String newToken = getUserToken(eeopClient);
        List<FieldStation> fieldStations = new ArrayList<FieldStation>();
        String type = "102";
        String className = "dto.FieldStation";
        try {
            MdmFilteredObjectsGetRequest request = new MdmFilteredObjectsGetRequest(newToken, type);
            MdmObjectStructureGetResponse response = eeopClient.execute(request, newToken);
            for (MdmObject obj : response.getMdmObjects()) {
//                String jsonStr = JSON.toJSONString(obj,SerializerFeature.PrettyFormat);
//                System.out.println(jsonStr);
                Map<String, String> attributes = obj.getAttributes();
                try {
                    Class<?> clz = Class.forName(className);
                    //获取类 实例
                    Object objectInstance = clz.newInstance();
                    Field[] fields = clz.getDeclaredFields(); //获取说有属性
                    for (Field field: fields) {
                        String fieldName = field.getName(); //属性名
//                        Method setMethod = new PropertyDescriptor(field.getName(), clazz);
                        try {
                            Method setMethod = clz.getMethod("set" + getMethodName(fieldName), String.class);
                            if(fieldName.equals("mdmID")){
                                setMethod.invoke(objectInstance,obj.getMdmID());
                            }else {
                                setMethod.invoke(objectInstance, attributes.get(fieldName));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                     fieldStations.add((dto.FieldStation) objectInstance);

                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                    }catch (InstantiationException e){
                        e.printStackTrace();
                    }catch (ClassNotFoundException e){
                        System.out.println("该类未找到！");
                    }
                }
        }catch (EnvisionApiException e){
            e.printStackTrace();
        }

//        //存入mysql
        fieldStationMapper.putFieldStationsToMysql(fieldStations);
//        for(Station station: stations){
//            System.out.println(station.toJson());
////            fieldStationMapper.putFieldStationToMysql(fieldStation);
////            session.commit();
//        }
    }
}
