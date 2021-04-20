package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;


public interface UserService {
	
	
	
	// 전체 사용자 정보 조회
	List<UserVo> selectAllUser();

	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
	//사용자 페이징 조회
	Map<String, Object> selectPagingUser(PageVo pageVo);
	
//	List<UserVo> selectPagingUser(PageVo pageVo); 페이징 처리
//	int selectAllUserCnt(); 모든 유저의 수 
	
//	위 두개의 메서드(리스트, 인트타입)를 모두 받을 수 있는 타입 -> Map
//	페이징 처리를 하려면 몇번까지 뜨는지 알기 위해 유저의 수가 모두 몇명인지 알아야됨 
//	ex) 유저가 16명, 5개의 페이지 사이즈 = 4번 
	
	
	// 사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	// 사용자 신규 등록
	int registUser(UserVo userVo);
	
	//사용자 삭제
	int deleteUser(String userid);
}
