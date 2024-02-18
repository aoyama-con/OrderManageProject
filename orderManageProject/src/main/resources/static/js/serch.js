$(document).ready(function(){
	inputDataSend();
});

function inputDataSend(){
	$("#serch_button").click(function () {
          $.ajax({
            url: "/orderSerch",
            type: "POST",
            data: $("form").serialize(),
            dataType: "json",
            timespan: 1000,
          })
            .done(function (data1, textStatus, jqXHR) {
              console.log(jqXHR.status); // 200
              console.log(textStatus); // 成功した時のコンソールログ
              
              $(".modal-body").find("p").remove();//モダールウィンドウの表示前に<p>タグを削除する
              $(".modal-body").append("<p>"+data1["category"]+"</p>");//<p>タグの追加。
              $(".modal-body").append("<p>"+data1["attribute"]+"</p>");//<p>タグの追加
              $("#serch-modal").modal('show');//もダルウィンドウを表示する
            })
            .fail(function (jqXHR, textStatus, errorThrown) {
              console.log(jqXHR.status); //例：404
              console.log(textStatus); //例：error
              console.log(errorThrown); //例：NOT FOUND
            })
            .always(function () {
              console.log("complete"); // complete
            });
     });
}