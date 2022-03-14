$("#recButton").click((event)=>{
    event.preventDefault();
     $.ajax({
            url :"/Restaurant/Recommend",
            type : "post",
            dataType : "json",
            data : {
                    recommend : $("#recommend").val(),
                    recIndex : $("#recIndex").val(),
                },
            success: function(res){
                    if(res.false==false){
                        alert("로그인 후 추천이 가능합니다.");
                    } else if(res.noCookie=="noCookie"){
                        alert("이미 추천한 포스팅 입니다.");
                    } else{
                        alert("추천해 주셔서 감사합니다.");
                        $("#resRecommend").text(res.resRecommend);
                    }
                }
    })
})