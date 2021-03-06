package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.validator.UserVoValidator;

@RequestMapping("user")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static final Object Date = null;

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("allUser")
	public String allUser(Model model) {

		model.addAttribute("userList", userService.selectAllUser());
		return "user/allUser";

	}

	@RequestMapping("allUserTiles")
	public String allUserTiles(Model model) {

		model.addAttribute("userList", userService.selectAllUser());
		return "tiles.user.allUser";

	}

//	 @RequestMapping("pagingUser")
//	public String pagingUser(@RequestParam(defaultValue = "1", name="p") int page,
//							 @RequestParam(defaultValue = "5") int pageSize,
//							 @RequestParam(name="p") int price,
//							 Model model) {
//		logger.debug("page : {}, pageSize : {}, price : {}", page, pageSize,price);
//		
//		PageVo pageVo = new PageVo(page, pageSize);
//		
//		// Map<String, Object> map = userService.selectPagingUser(pageVo); 
//		// model.addAllAttributes(map) ??? ????????? ????????? ?????? ?????????
//		model.addAllAttributes(userService.selectPagingUser(pageVo));
//		
//		//int userCnt = map.get(map)
//		//model.addAllAttributes(map);
//		
//		return "user/pagingUser";
//	
//	}

	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
			Model model) {
		logger.debug("page : {}, pageSize : {}", page, pageSize);

		PageVo pageVo = new PageVo(page, pageSize);

		// Map<String, Object> map = userService.selectPagingUser(pageVo);
		// model.addAllAttributes(map) ??? ????????? ????????? ?????? ?????????

		model.addAllAttributes(userService.selectPagingUser(pageVo));
		// model.addAttribute("List", userService.selectPagingUser(pageVo));

		// int userCnt = map.get(map)
		// model.addAllAttributes(map);

		return "user/pagingUser";

	}

	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int pageSize, Model model) {
		logger.debug("page : {}, pageSize : {}", page, pageSize);

		PageVo pageVo = new PageVo(page, pageSize);

		model.addAllAttributes(userService.selectPagingUser(pageVo));

		// tiles-definition??? ????????? name
		return "tiles.user.pagingUser";

	}
	//????????? ???????????? ?????? ????????? ????????? ???????????? ??????
	@RequestMapping("pagingUserAjaxView")
	public String pagingUserAjaxView() {
		return "tiles.user.pagingUserAjax";
	}
	

	@RequestMapping("pagingUserAjax")
	public String pagingUserAjax(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int pageSize, Model model) {
		logger.debug("page : {}, pageSize : {}", page, pageSize);

		PageVo pageVo = new PageVo(page, pageSize);

		model.addAllAttributes(userService.selectPagingUser(pageVo));

		// tiles-definition??? ????????? name
		return "jsonView";

	}
	@RequestMapping("pagingUserAjaxHtml")
	public String pagingUserAjaxHtml(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int pageSize, Model model) {
		logger.debug("page : {}, pageSize : {}", page, pageSize);
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		// tiles-definition??? ????????? name
		return "user/pagingUserAjaxHtml";
		
//		pagingUserAjaxHtml ==> /WEB-INF/views/user/pagingUserAjaxHtml
		
	}

	// @RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo) {

		logger.debug("pageVo : {}", pageVo);

		return "";
	}

	// ????????? ?????? ?????????
	@RequestMapping(path = "userTiles", method = RequestMethod.GET)
	public String selectUserTiles(String userid, Model model) {
		model.addAttribute("user", userService.selectUser(userid));

		return "tiles.user.user";
		// return "user/user";
	}

	// ????????? ??????

	@RequestMapping(path = "modifyUser", method = RequestMethod.GET)
	public String modifyUser(String userid, Model model) {
		model.addAttribute("user", userService.selectUser(userid));
		return "user/userModify";
	}

	@RequestMapping(path = "modifyUser", method = RequestMethod.POST)
	public String modifyUser(UserVo userVo, Model model, MultipartFile profile) {

		UserVo vo = userService.selectUser(userVo.getUserid());

		if ("".equals(profile.getOriginalFilename())) {
			userVo.setFilename(vo.getFilename());
			userVo.setRealfilename(vo.getRealfilename());

			if (userVo.getFilename() == null) {
				userVo.setFilename("");
			}
			if (userVo.getRealfilename() == null) {
				userVo.setRealfilename("");
			}

		} else {
			try {
				userVo.setFilename(profile.getOriginalFilename());
				String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
				String realFileName = "D:\\upload\\" + UUID.randomUUID().toString() + fileExtension;
				// safsafsjiafjapasfjsapf + .jpg
				userVo.setRealfilename(realFileName);
				profile.transferTo(new File(realFileName));
			} catch (IllegalStateException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		int updateCnt = userService.modifyUser(userVo);
		// ????????? ????????? ??????????????? ??? ?????? ==> ?????? ???????????? ?????? ?????? ???????????? ??????
		if (updateCnt == 1) {
			// ?????? 1 // doGet(req, resp); // ?????? 2
			return "redirect:/user/userTiles?userid=" + userVo.getUserid();
		} else {
			model.addAttribute("user", userVo);
			return "user/userModify";
		}
	}

	// ????????? ????????? ?????????????????? ??? ?????? ==> ?????? ???????????? ?????? ?????? ???????????? ?????? else { return
	// "redirect:user/modifyUser"; } }

	// ????????? ??????

	@RequestMapping(path = "registUser", method = RequestMethod.GET)
	public String registUser() {
		return "tiles.user.registUser";
	}

	// BindingResult????????? command??????(UserVo) ?????? ?????? ????????? ??????????????????
	@RequestMapping(path = "registUser", method = RequestMethod.POST)
	public String registUser(@Valid UserVo userVo, BindingResult result, Model model, MultipartFile profile) {

//		new UserVoValidator().validate(userVo, result);

		if (result.hasErrors()) {
			logger.debug("result has error");
			return "user/registUser";
		}

		//

		// userid ?????? ??????
//		if(userVo.getUserid().length() < 5) {
//			
//		}

		// model.addAttribute("regist", userService.registUser(userVo));

		if ("".equals(profile.getOriginalFilename())) {
			userVo.setFilename("");
			userVo.setRealfilename("");
		} else {
			try {
				userVo.setFilename(profile.getOriginalFilename());
				String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
				String realFileName = "D:\\upload\\" + UUID.randomUUID().toString() + fileExtension;
				// safsafsjiafjapasfjsapf + .jpg
				userVo.setRealfilename(realFileName);
				profile.transferTo(new File(realFileName));
			} catch (IllegalStateException | IOException e1) {
				e1.printStackTrace();
			}
		}

		int insertCnt = 0;
		try {
			insertCnt = userService.registUser(userVo);
		} catch (Exception e) {
			insertCnt = 0;
			e.printStackTrace();
		}

		if (insertCnt == 1) {
			return "redirect:/user/pagingUser";
		} else {
			return "user/registUser";
		}
	}

	// ????????? ??????

	@RequestMapping(path = "deleteUser", method = RequestMethod.POST)
	public String deleteUser(String userid, Model model) {
		// model.addAttribute("delete",userService.deleteUser(userid));

		int deleteCnt = 0;

		try {

			deleteCnt = userService.deleteUser(userid);

		} catch (Exception e) {
			deleteCnt = -1;
		}

		if (deleteCnt == 1) {
			return "redirect:/user/pagingUser";

		} else {
			return "redirect:/user/user?userid=" + userid;

		}

	}

	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		List<String> header = new ArrayList<String>();
		header.add("????????? ?????????");
		header.add("????????? ??????");
		header.add("????????? ??????");

		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUser());

		return "userExcelDownloadView";
	}

	// localhost/user/profile
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("image");

		// userid ??????????????? ????????????
		// userService ????????? ?????? ???????????? ?????? ?????? ????????? ??????
		// ?????? ???????????? ?????? ????????? ???????????? resp????????? outputStream?????? ?????? ??????

		UserVo userVo = userService.selectUser(userid);

		String path = "";
		if (userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
		} else {
			path = userVo.getRealfilename();
		}

		logger.debug("path : {} ", path);

		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();

			byte[] buff = new byte[512];

			while (fis.read(buff) != -1) {
				sos.write(buff);
			}

			fis.close();
			sos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("profileDownload")
	public void profileDownload(String userid, HttpServletRequest req, HttpServletResponse resp) {

		UserVo userVo = userService.selectUser(userid);

		String path = "";
		String filename = "";
		
		if (userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
			filename = "unknown.png";
		} else {
			path = userVo.getRealfilename();
			filename = userVo.getFilename();
		}

		resp.setHeader("Content-Disposition", "attachment; filename=" + filename);

		// userid ??????????????? ????????????
		// userService ????????? ?????? ???????????? ?????? ?????? ????????? ??????
		// ?????? ???????????? ?????? ????????? ???????????? resp????????? outputStream?????? ?????? ??????

		logger.debug("path : {} ", path);

		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();

			byte[] buff = new byte[512];

			while (fis.read(buff) != -1) {
				sos.write(buff);
			}

			fis.close();
			sos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}