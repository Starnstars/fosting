$("#Button").click((event)=>{
event.preventDefault();
    $.ajax({
        url :'Login',
        type : 'post',
        dataType : 'json',
        data : {
                id : $("#id").val(),
                pass : $("#pass").val(),
        },
        success: function(res){
                    if(res){
                        var Form = document.getElementById("Login");
                        Form.method = "post";
                        Form.submit();
                    } else{
                        alert("아이디 혹은 비밀번호가 잘못되었습니다.");
                    }
        }
    });
})