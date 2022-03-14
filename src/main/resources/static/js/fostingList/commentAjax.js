$("#commentButton").click((event)=>{
    event.preventDefault();
     $.ajax({
            url :"/Restaurant/Comment",
            type : "post",
            dataType : "json",
            data : {
                    postComment : $("#postComment").val(),
                    resIndex : $("#resIndex").val()
                },
            success: function(res){
                if(res.false == false){
                    alert("로그인 후 댓글을 등록해 주세요");
                } else {
                   $("#commentForm").before(
                     "<div class='member_Comment'>"+
                     "  <p class='writer' id='commentMember'>" + `${res.commentMember}` + "</p>" +
                     "  <p class='time' id='commentTime'>" + `${res.commentTime}` + "</p>" +
                     "  <p class='comment' id='getComment'>" + `${res.comment}` + "</p>" +
                     "</div>"
                        )
                    }
                }
          });
})