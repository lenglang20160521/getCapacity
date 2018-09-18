import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlGenerator {
//       private static final Logger logger = LoggerFactory.getLogger(SqlGenerator.class);

    /**
     * 根据实体类生成建表语句
     * @author
     * @date    2018年4月11日
     * @param className 全类名
     */
    public static String generateSql(String className){
        try {
            Class<?> clz = Class.forName(className);
            className = clz.getSimpleName();
            Field[] fields = clz.getDeclaredFields();
            StringBuffer column = new StringBuffer();
            String varchar = " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,";
            for (Field f : fields) {
                column.append(" \n `"+f.getName()+"`").append(varchar);
            }
            StringBuffer sql = new StringBuffer();
            sql.append("\n DROP TABLE IF EXISTS `"+className+"`; ")
                    .append(" \n CREATE TABLE `"+className+"`  (")
                    .append(" \n `id` int(11) NOT NULL AUTO_INCREMENT,")
                    .append(" \n "+column)
                    .append(" \n PRIMARY KEY (`id`) USING BTREE,")
                    .append("\n INDEX `id`(`id`) USING BTREE")
                    .append(" \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;");
            return sql.toString();
        } catch (ClassNotFoundException e) {
//            logger.debug("该类未找到！");
            System.out.println("该类未找到！");
            return null;
        }

    }

    /**
     * 获取包下的所有类名称,获取的结果类似于 XXX.java
     * @author
     * @date    2018年4月11日
     * @param packageName
     * @return
     */
    public static List<String> getAllClasses(String packageName){
        List<String> classList = new ArrayList<String>();
        String className="";
        File f = new File(packageName);
        if(f.exists() && f.isDirectory()){
            File[] files = f.listFiles();
            for (File file : files) {
                className = file.getName();
                classList.add(className);
            }
            return classList;
        }else{
//            logger.debug("包路径未找到！");
            System.out.println("包路径未找到！");
            return null;
        }
    }
    /**
     * 将string 写入sql文件
     * @author
     * @date    2018年4月11日
     * @param str
     * @param path
     */
    public static void StringToSql(String str,String path){
        byte[] sourceByte = str.getBytes();
        if(null != sourceByte){
            try {
                File file = new File(path);     //文件路径（路径+文件名）
                if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file);    //文件输出流用于将数据写入文件
                outStream.write(sourceByte);
                outStream.flush();
                outStream.close();  //关闭文件输出流
                System.out.println("生成成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        String className = "dto.AAAAMapper";
        String sql = generateSql(className);
        System.out.println(sql);
    }
}
