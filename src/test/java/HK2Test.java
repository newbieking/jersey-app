import com.newbieking.jersey_app.service.MessageService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

class HK2Test {
    public static void main(String[] args) {
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        MessageService service = locator.getService(MessageService.class);
        System.out.println(service.getMessage()); // 应输出"Singleton Message Service"
    }
}
