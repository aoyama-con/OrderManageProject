$(document).ready(function(){
	modalControl();
});

function modalControl(){
	// モーダルを開く
	$("#openSerchModal").click(function () {
          $("#serchModal").modal('show'); 
     });
	
	// モーダルを閉じる
	$("#modalClose").click(function () {
          $("#serchModal").modal('hide');  
     });

	// 検索実行
	$("#modalSerch").click(function () {
          
     });

}