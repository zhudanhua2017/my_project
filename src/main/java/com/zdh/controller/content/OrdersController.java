package com.zdh.controller.content;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.zdh.dto.OrdersDto;
import com.zdh.service.MemberService;
import com.zdh.service.OrdersService;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 订单列表
	 * @param model
	 * @param ordersDto
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String init(Model model,OrdersDto ordersDto) {
		List<OrdersDto> list = ordersService.getOrdersList(ordersDto);
		model.addAttribute("ordersList", list);
		model.addAttribute("searchParam", ordersDto);
		return "/content/orderList";
	}
	
}