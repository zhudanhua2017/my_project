$(function() {
	common.showMessage($("#message").val());
	$("#mainForm").validate({
		rules : {
			"title" : "required",
			"subtitle":"required",
			"city":"required",
				"category":"required",
				"price":"required",
				"distance":"required",
				
		},
		messages : {
			"title" : "请输入标题！"
		}
	});
});


function modify() {
	if(check()){
		$("#mainForm").submit();
	}
}

function check() {
	// TODO 需要添加表单验证
	return true;
}

function goback() {
	location.href ="/comment/businesses";
}