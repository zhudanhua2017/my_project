package com.zdh.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zdh.constant.DicTypeConst;
import com.zdh.constant.PageCodeEnum;
import com.zdh.dto.BusinessDto;
import com.zdh.service.BusinessService;
import com.zdh.service.DicService;

@Controller
@RequestMapping("/businesses")
public class BusinessesController {
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private DicService dicService;
	
	/**
	 * 商户列表
	 * @param dto
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model,BusinessDto dto){
		model.addAttribute("list", businessService.selectByPage(dto));
		model.addAttribute("searchParam", dto);
		return "/content/businessList";
	}
	
	/**
	 * 删除商户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String remove(@PathVariable("id")Long id,Model model){
		if(businessService.remove(id))
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
		else
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
		
		return "redirect:/businesses";
	}
	
	
	/**
	 * 商户修改页面初始化
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String modifyInit(Model model,@PathVariable("id")Long id){
		model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
		model.addAttribute("modifyObj", businessService.getById(id));
		return "/content/businessModify";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String modify(@PathVariable("id")Long id,BusinessDto dto,Model model){
		if(businessService.modify(dto)){
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
		}else{
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL);
		}
		return "/content/businessModify";
	}

	/**
	 * 商户新增页初始化
	 * @param model
	 * @return
	 */
	@RequestMapping(value="addInit",method=RequestMethod.GET)
	public String addInit(Model model){
		model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
		return "/content/businessAdd";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String add(BusinessDto businessDto,Model model){
		if(businessService.add(businessDto)){
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
		}else{
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
			
		}
		return "redirect:/businesses/addInit";
	}
}
