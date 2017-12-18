package org.koma.javamyadmin;

import org.junit.Test;
import org.koma.javamyadmin.controller.IndexController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author koma <komazhang@foxmail.com>
 */
public class IndexControllerTest {
    @Test
    public void testIndexPage() throws Exception {
        IndexController controller = new IndexController();
        /**
         * 以 Mock 形式测试控制器
         */
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }
}
