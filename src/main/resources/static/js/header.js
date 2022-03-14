const login = document.getElementById("header_login");
const signUp = document.getElementById("header_signUp");
const logout = document.getElementById("header_logout");

var cookie = document.cookie;
const HIDDEN_KEY = "hidden";

if(cookie.includes("fosting_idx")){
    login.classList.add(HIDDEN_KEY);
    logout.classList.remove(HIDDEN_KEY);
    signUp.classList.add(HIDDEN_KEY);
} else{
    login.classList.remove(HIDDEN_KEY);
    logout.classList.add(HIDDEN_KEY);
    signUp.classList.remove(HIDDEN_KEY);
}
