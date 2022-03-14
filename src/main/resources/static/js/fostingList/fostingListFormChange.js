function getPost(mode) {
var theForm = document.getElementById("category_form");
var sel_1 = document.querySelector(".category_food");
var sel_2 = document.querySelector(".category_locate");
var sel_3 = document.querySelector(".category_recommend");
var sel_4 = document.querySelector(".category_count");
var val;

theForm.method = "post";
if(mode == "01") {
          val = sel_1.options[sel_1.selectedIndex].value;
    } else if(mode == "02") {
          val = sel_2.options[sel_2.selectedIndex].value;
    } else if(mode == "03") {
          val = sel_3.options[sel_3.selectedIndex].value;
    } else if(mode == "04") {
          val = sel_4.options[sel_4.selectedIndex].value;
    }
          theForm.action = `/Restaurant/AllList/${val}`;
    theForm.submit();
}

