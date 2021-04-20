package kr.or.ddit.user.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.WebTestConfig;

public class UserControllerTest extends WebTestConfig{

	@Test
	public void allUserTest() throws Exception {
		mockMvc.perform(get("/user/allUser"))
				.andExpect(view().name("user/allUser"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userList"))
				.andDo(print());
	}
	
	@Test
	public void PagingUserTest() throws Exception {
		mockMvc.perform(get("/user/pagingUser"))
				.andExpect(view().name("user/pagingUser"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userList"))
				.andExpect(model().attributeExists("pageVo"))
				.andExpect(model().attributeExists("pagination"))
				.andDo(print());
	}
	
	@Test
	public void pagingUser2PageTest() throws Exception {
		mockMvc.perform(get("/user/pagingUser").param("page", "2"))
			.andExpect(view().name("user/pagingUser"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("userList"))
			.andExpect(model().attributeExists("pageVo"))
			.andExpect(model().attributeExists("pagination"))
			.andDo(print());
		
	}
	
	
	
//	@Test
//	public void test(){
//		PageVo pageVo = new PageVo(0, 0);
//		System.out.println("pageVo.getPage() : " + pageVo.getPage());
//			
//		int page;
//		System.out.println();
//	}
}
