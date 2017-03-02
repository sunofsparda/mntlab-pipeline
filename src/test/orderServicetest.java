import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import mtp.web.OrderController;

@SpringApplicationConfiguration(classes = ApplicationAndConfiguration.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServicetest {

    private MockMvc mvc;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new OrderController()).build();
    }

    @Test
    public void MyTestController() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/orders/getStoreOrder/Hounslow").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("name was test//MyUtility correctly initialized!")));
    }
}