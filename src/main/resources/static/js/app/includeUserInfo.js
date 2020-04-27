$(function() {
	includeUserInfo();
});

var userName = $(".userInfo name").val();
var userId = $(".userInfo id").val();

function includeUserInfo() {
	var includeArea = $("header");
	includeArea.load("/templates/userInfo.html")
}
