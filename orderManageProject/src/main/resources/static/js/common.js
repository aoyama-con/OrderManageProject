// ブラウザバックの制御
$(() => {
	history.pushState(null, null, location.href);
	window.addEventListener('popstate', (e) => {
	  history.go(1);
	});
}); 

/* カレンダーに本日日付をせ設定する */
var today = new Date();
today.setDate(today.getDate());
var yyyy = today.getFullYear();
var mm = ("0"+(today.getMonth()+1)).slice(-2);
var dd = ("0"+today.getDate()).slice(-2);
document.getElementById("today").value=yyyy+'-'+mm+'-'+dd;

