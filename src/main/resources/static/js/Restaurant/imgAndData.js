$("#Button").click((event)=>{
    event.preventDefault();
    var data = {
            resPostName : $("#resPostName").val(),
            resCategory : $("#resCategory").val(),
            resLocate : $("#resLocate").val(),
            resName : $("#resName").val(),
            resComment : $(".resComment").val(),
            resLatLng : $("#resLatLng").val(),
            };

    var formData = new FormData();
    var inputFile = $("#imgFile");
    var files = inputFile[0].files;


    for(var i = 0; i < files.length ; i++){
        formData.append("uploadFile",files[i]);
    }

    formData.append("data",new Blob([ JSON.stringify(data)], {type : "application/json"}));
    $.ajax({
//        url : $("#resform").attr("action"),
        url : "/Restaurant/Post",
        data : formData,
        type : "post",
        processData : false,
        contentType : false,
        success : function(res){
                  var Form = document.getElementById("resform");
                  Form.method = "post";
                  Form.submit();
                }
        });
});