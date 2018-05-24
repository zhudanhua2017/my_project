$(function() {
	common.showMessage($("#message").val());
});

function remove(id) {
	if(confirm("确定要删除这条信息吗？")){
		$("#mainForm").attr("action",$("#basePath").val() + "/businesses/" + id);
		$("#mainForm").submit();
	}
}

function search(currentPage) {
	$("#mainForm").attr("method","GET");
	$("#currentPage").val(currentPage);
	$("#mainForm").attr("action",$("#basePath").val() + "/businesses");
	$("#mainForm").submit();
}

function modifyInit(id) {
	location.href = $("#basePath").val() + "/businesses/" + id;
}