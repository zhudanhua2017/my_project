package com.zdh.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zdh.constant.PageCodeEnum;
import com.zdh.dto.AdDto;
import com.zdh.service.AdService;

@Controller
@RequestMapping("/ad")
public class AdController {

	@Autowired
	private AdService adService;
	
	/**
	 * 初始化页面
	 * @param model
	 * @param request
	 * @return 广告列表页
	 */
	@RequestMapping
	public String init(Model model){
		AdDto adDto = new AdDto();
		model.addAttribute("list", adService.searchByPage(adDto));
		model.addAttribute("searchParam",adDto);
		return "/content/adList";
	}
	
	/**
	 * 新增初始化页面
	 * @return
	 */
	@RequestMapping("/addInit")
	public String addInit(){
		return "/content/adAdd";
	}
	
	/**
	 * 新增页
	 * @param adDto
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(AdDto adDto,Model model){
		if(adService.add(adDto))
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
		else{
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
		}
		return "/content/adAdd";
	}
	
	/**
	 * 分页显示广告列表
	 * @param model
	 * @param adDto
	 * @return
	 */
	@RequestMapping("/search")
	public String search(Model model,AdDto adDto){
		model.addAttribute("list",adService.searchByPage(adDto));
		model.addAttribute("searchParam",adDto);
		return "/content/adList";
	}
	
	/**
	 * 删除广告
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/remove")
	public String remove(@RequestParam("id")Long id,Model model){
		if(adService.remove(id)){
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
		}else{
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
		}
			
		return "redirect:/ad";  //return "forward:/ad";也可以
	}
	
	@RequestMapping("/modifyInit")
	public String modifyInit(Model model,@RequestParam("id")Long id){
		model.addAttribute("modifyObj", adService.getById(id));
		return "/content/adModify";
	}
	
	/**
	 * 修改
	 * @param model
	 * @param adDto
	 * @return
	 */
	@RequestMapping("/modify")
	public String modify(Model model,AdDto adDto){
		model.addAttribute("modifyObj", adDto);
		if(adService.modify(adDto))
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
		else
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
		return "/content/adModify";
	}
	
}
