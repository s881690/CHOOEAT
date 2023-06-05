// 拿到會員icon
let accountIcon = $("a.accountIcon");
// console.log(accountIcon);
let account = sessionStorage.getItem("loginReq");

if (account != null) {
  accountIcon.attr("href", "../../../account/usercenter.html");
} else {
  accountIcon.attr("href", "../../../account/login.html");
}
