import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月15日 10:45:00
 */
public class ZonedDataTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        // 2022-01-15T10:48:50.431+08:00[Asia/Shanghai]
        System.out.println(now);
    }
}
