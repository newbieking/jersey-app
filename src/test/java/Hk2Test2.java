import com.newbieking.jersey_app.service.MessageService;
import com.newbieking.jersey_app.service.impl.SingletonMessageServiceImpl;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

class Hk2Test2 {
    public static void main(String[] args) {
        // 创建 ServiceLocator
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();

        // 手动绑定服务（替代 @Service 注解）
        ServiceLocatorUtilities.bind(locator, new AbstractBinder() {
            @Override
            protected void configure() {
                bind(SingletonMessageServiceImpl.class).to(MessageService.class).in(Singleton.class);
            }
        });

        // 获取服务并验证
        MessageService service = locator.getService(MessageService.class);
        if (service == null) {
            System.out.println("Error: MessageService not found!");
        } else {
            System.out.println(service.getMessage()); // 应输出 "Singleton Message Service"
        }
    }
}
