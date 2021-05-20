package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class BoardController {
	
	//글 등록
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		boardDAO.insertBoard(vo);
		return "getBoardList.do";
	}
	
	//글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo,BoardDAO boardDAO,ModelAndView mav) {
		boardDAO.updateBoard(vo);
		return "getBoardList.do";
	}
	
	//글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo,BoardDAO boardDAO) {
		boardDAO.deleteBoard(vo);
		return "getBoardList.do";
	}

	//글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		//모델 정보 저장
		model.addAttribute("board",boardDAO.getBoard(vo));
		return "getBoard.jsp";
	}
	
	//글 목록
	@RequestMapping("/getBoardList.do")
	public String getBoardList(@RequestParam(value="searchCondition",defaultValue="TITLE",required=false) String condition,@RequestParam(value="searchKeyword",defaultValue="",required=false) String keyword, BoardDAO boardDAO, Model model) {
		System.out.println("검색 조건: "+condition);
		System.out.println("검색 단어: "+keyword);
		//모델 정보 저장
		//model.addAttribute("boardList",boardDAO.getBoardList(vo));
		return "getBoardList.jsp";
	}
}
