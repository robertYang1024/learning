package learning.gzip;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author bo.yang
 */
@Service
public class OssGeoDataSyncService {

    @Autowired
    private GeoBizService geoBizService;
    @Autowired
    private OSSClient ossClient;
    // todo
    @Value("${geo.oss.pathPrefix}")
    private String ossprefix;

    private static String bucketCusaccount;
    private static String geoOssPath;
    private static String env;

    private String gizpString = "";

    @Value("${aliyun.bucketname:cusaccount}")
    public void setBucketCusaccount(String bucketCusaccount) {
        OssGeoDataSyncService.bucketCusaccount = bucketCusaccount;

    }

    @Value("${geo.oss.path}")
    public void setGeoOssPath(String geoOssPath) {
        OssGeoDataSyncService.geoOssPath = geoOssPath;

    }

    @Value("${spring.profiles:dev}")
    public void setEnv(String env) {
        OssGeoDataSyncService.env = env;
    }

    public void upload() {
        List<GeoSimple> geoSimples = GeoFactory.toGeoSimpleList(geoBizService.listAll());
//        String data = "12345";
        System.out.println("size:" + geoSimples.size());
        String data = JsonUtil.serializer(geoSimples);
        try {
//            file = new File("E:\\test\\out.js");
//            if (!file.exists()) {
//                file.createNewFile();
//            }

            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            GZIPOutputStream gzipout = new GZIPOutputStream(byteout);
            gzipout.write(data.getBytes("utf-8"));
            gzipout.close();

            // 上传到阿里云oss

            /**
             * 不要把压缩的IO流转为string，直接返回IO流，或者直接写入文件
             */
//            FileWriter fileWriter = new FileWriter("E:\\test\\out3.gzip");
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
////            System.out.println(data);
//            bufferedWriter.write(gzipdata);
//            bufferedWriter.flush();
//            bufferedWriter.close();
            System.out.println("done");

//-----------------------------------------------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String depress() {
        long startTiem = System.currentTimeMillis();
        String url = "";
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity<byte[]> entity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), byte[].class);
            byte[] body = entity.getBody();

            System.out.println("request cost:" + (System.currentTimeMillis() - startTiem));

            //------gzip解压-----
            long depresstart = System.currentTimeMillis();
            // 创建一个新的 byte 数组输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
            ByteArrayInputStream in = new ByteArrayInputStream(body);
            // 使用默认缓冲区大小创建新的输入流
            GZIPInputStream gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n = 0;
            while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
                // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
                out.write(buffer, 0, n);
            }
            // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
            String geotext = out.toString("UTF-8");

            System.out.println("depress cost:" + (System.currentTimeMillis() - depresstart));

            //-----反序列化----
            long deserialize = System.currentTimeMillis();
            ObjectMapper MAPPER = new ObjectMapper();
            JavaType jt = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, Geo.class);
            List<Geo> geoList = MAPPER.readValue(geotext, jt);

            long endTime = System.currentTimeMillis();
            System.out.println("deserialize cost:" + (endTime - deserialize) + "ms");

            System.out.println("total cost:" + (endTime - startTiem) + "ms");
            System.out.println("geoList size:" + geoList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
