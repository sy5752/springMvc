package kr.or.ddit.hello;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
// ctrl+shift+o : import �젙由�
import kr.or.ddit.user.model.UserVo;

/*
 * 
 */

@ContextConfiguration(locations = { "classpath:/kr/or/ddit/config/spring/application-context.xml",
									"classpath:/kr/or/ddit/config/spring/root-context.xml"})
@WebAppConfiguration		// �뒪�봽留곹솚寃쎌쓣 web湲곕컲�쓽 application Context濡� �깮�꽦
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest extends WebTestConfig {
	
	//@Resource(name="helloController")
	
	//TYPE. 스프링빈중에 대입 가능한 타입의 스프링 빈을 주입한다
	// 만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해
	// 특정 스프링 빈의 이름을 지칭할 수 있다
	//  ==> @Resource 어노테이션 하나를 사용 했을 때와 동일 하다
	
//	@Autowired			
//	private WebApplicationContext context;
//	
//	private MockMvc mockMvc;
//	
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
	
	//localhost/hello/view
	@Test
	public void viewTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/hello/view"))  //get諛⑹떇�쑝濡� �슂泥��쓣 遺�瑜닿쿋�떎. 硫붿꽌�뱶 �옄泥댁뿉 �삁�쇅瑜� 諛쒖깮�빐�꽌 �뿉�윭媛� �쑙
				.andExpect(status().isOk())
				.andExpect(view().name("hello"))
				.andExpect(model().attributeExists("userVo")) // 湲곕�媛�, 寃�利앷낵�젙�씠 �븳 �씪�씤�뿉 �뱾�뼱媛�
				.andDo(print())
				.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		assertEquals("hello", mav.getViewName()); // 湲곕�媛�, �떎�젣媛�
		UserVo userVo = (UserVo)mav.getModel().get("userVo");
		assertEquals("브라운", userVo.getUsernm());
		
		
		/***Given***/

		/***When***/

		/***Then***/

	
	}
	@Test
	public void pathVariableTest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/hello/path/brown"))  //get諛⑹떇�쑝濡� �슂泥��쓣 遺�瑜닿쿋�떎. 硫붿꽌�뱶 �옄泥댁뿉 �삁�쇅瑜� 諛쒖깮�빐�꽌 �뿉�윭媛� �쑙
				.andExpect(status().isOk())
				.andExpect(view().name("hello"))
				.andExpect(model().attributeExists("subpath")) // 湲곕�媛�, 寃�利앷낵�젙�씠 �븳 �씪�씤�뿉 �뱾�뼱媛�
				.andDo(print())
				.andReturn();
		
	}
	
//	@Test
//	public void helloControllerTest() {
//		assertNotNull(helloController);
//	}

}
