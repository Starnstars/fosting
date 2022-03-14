$("#SingUp").click((event)=>{
    event.preventDefault();
    $.ajax({
        url :'SignUp',
        type : 'post',
        dataType : 'json',
        data : {
                id : $("#id").val(),
                pass : $("#pass").val(),
                name : $("#name").val(),
                phone : $("#phone").val(),
                email : $("#email").val()
        },
        success: function(res){
                    if(res){
                        var form = document.getElementById("signUpForm");
                        form.submit();
                    } else{
                        alert("중복된 회원 아이디 입니다.");
                    }
        }
    });
})