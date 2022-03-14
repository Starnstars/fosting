$("#updateButton").click((event)=>{
        event.preventDefault();
        $.ajax({
            url : "/Restaurant/Update",
            type : "get",
            dataType : "json",
            data : {
                resIndex : $("#resIndex").val()
            },
            success : function(res){
                  if(res.false==false){
                        alert("로그인 후 이용해주세요.");
                    } else if(res.noCookie=="noCookie"){
                        alert("포스팅 수정 권한이 없습니다.");
                    } else{
                        alert("수정 페이지로 이동합니다.");
                        var form = document.getElementById("updateForm");
                        form.submit();
                    }

            }
        });
    })